package shadowverse.cards.Necromancer.LastWords;


import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.stances.AbstractStance;
import com.megacrit.cardcrawl.vfx.combat.HeartBuffEffect;
import com.megacrit.cardcrawl.vfx.combat.ShockWaveEffect;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Necromancer;
import shadowverse.powers.Cemetery;;


public class Istyndet
        extends CustomCard {
    public static final String ID = "shadowverse:Istyndet";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Istyndet");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/Istyndet.png";

    public Istyndet() {
        super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.ATTACK, Necromancer.Enums.COLOR_PURPLE, CardRarity.RARE, CardTarget.SELF);
        this.baseBlock = 8;
        this.tags.add(AbstractShadowversePlayer.Enums.CONDEMNED);
        this.tags.add(AbstractShadowversePlayer.Enums.LASTWORD);
        this.cardsToPreview = new SkullFish();
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBlock(4);
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }

    public void applyPowers() {
        super.applyPowers();
        int count = 0;
        for (AbstractCard c : AbstractDungeon.actionManager.cardsPlayedThisCombat) {
            if (c.hasTag(AbstractShadowversePlayer.Enums.LASTWORD))
                count++;
        }
        this.rawDescription = cardStrings.DESCRIPTION;
        this.rawDescription += cardStrings.EXTENDED_DESCRIPTION[0] + count;
        this.rawDescription += cardStrings.EXTENDED_DESCRIPTION[1];
        initializeDescription();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new SFXAction("Istyndet"));
        addToBot(new VFXAction(new HeartBuffEffect(p.hb.cX, p.hb.cY)));
        addToBot(new GainBlockAction(p, this.block));
        if (this.upgraded) {
            AbstractCard c = this.cardsToPreview.makeStatEquivalentCopy();
            c.setCostForTurn(0);
            addToBot(new MakeTempCardInHandAction(c,1));
        }
        int count = -1;
        for (AbstractCard c : AbstractDungeon.actionManager.cardsPlayedThisCombat) {
            if (c.hasTag(AbstractShadowversePlayer.Enums.LASTWORD))
                count++;
        }
        if (count >= 10) {
            addToBot(new VFXAction(p, new ShockWaveEffect(p.hb.cX, p.hb.cY, Settings.PURPLE_COLOR, ShockWaveEffect.ShockWaveType.CHAOTIC), 0.2F));
            for (AbstractCard ca : p.hand.group){
                if (ca.type != CardType.SKILL && ca != this){
                    addToBot(new ExhaustSpecificCardAction(ca,p.hand));
                }
            }
            for (AbstractMonster mo : (AbstractDungeon.getCurrRoom()).monsters.monsters) {
                if (!mo.isDeadOrEscaped()){
                    addToBot(new ApplyPowerAction(mo, p, (AbstractPower)new WeakPower(mo, 1, false), 1, true, AbstractGameAction.AttackEffect.NONE));
                    addToBot(new ApplyPowerAction(mo, p, (AbstractPower)new VulnerablePower(mo, 1, false), 1, true, AbstractGameAction.AttackEffect.NONE));
                }
            }
        }
    }

    @Override
    public void triggerExhaustedCardsOnStanceChange(AbstractStance newStance) {
        addToBot(new ApplyPowerAction(AbstractDungeon.player,AbstractDungeon.player,new Cemetery(AbstractDungeon.player,3)));
    }

    public AbstractCard makeCopy() {
        return (AbstractCard) new Istyndet();
    }
}
