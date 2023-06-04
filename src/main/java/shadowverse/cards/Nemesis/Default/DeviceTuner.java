package shadowverse.cards.Nemesis.Default;

import basemod.abstracts.CustomCard;
import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsInHandAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Nemesis;

public class DeviceTuner
        extends CustomCard {
    public static final String ID = "shadowverse:DeviceTuner";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:DeviceTuner");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/DeviceTuner.png";
    public static final String[] TEXT = (CardCrawlGame.languagePack.getUIString("shadowverse:applyEffect")).TEXT;

    public DeviceTuner() {
        super(ID, NAME, IMG_PATH, 0, DESCRIPTION, CardType.ATTACK, Nemesis.Enums.COLOR_SKY, CardRarity.COMMON, CardTarget.NONE);
        this.isEthereal = true;
    }



    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            this.isEthereal = false;
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }

    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        boolean canUse = super.canUse(p, m);
        if (!canUse)
            return false;
        boolean hasArtifact = false;
        for (AbstractCard c:p.hand.group){
            if (c.hasTag(AbstractShadowversePlayer.Enums.ARTIFACT)){
                hasArtifact = true;
                break;
            }
        }
        if (!hasArtifact) {
            canUse = false;
        }
        return canUse;
    }

    public void triggerOnGlowCheck() {
        boolean glow = true;
        boolean hasArtifact = false;
        for (AbstractCard c:AbstractDungeon.player.hand.group){
            if (c.hasTag(AbstractShadowversePlayer.Enums.ARTIFACT)){
                hasArtifact = true;
                break;
            }
        }
        if (!hasArtifact) {
            glow = false;
        }
        if (glow) {
            this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
        } else {
            this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        }
    }

    public void use(AbstractPlayer p, AbstractMonster abstractMonster) {
        addToBot((AbstractGameAction)new SFXAction("DeviceTuner"));
        addToBot((AbstractGameAction)new SelectCardsInHandAction(1,TEXT[0],false,false,card -> {
            return card.hasTag(AbstractShadowversePlayer.Enums.ARTIFACT);
        }, abstractCards ->{
            for (AbstractCard c:abstractCards){
                AbstractCard card = c.makeStatEquivalentCopy();
                card.resetAttributes();
                addToBot((AbstractGameAction)new MakeTempCardInHandAction(card));
            }
        } ));
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new DeviceTuner();
    }
}


