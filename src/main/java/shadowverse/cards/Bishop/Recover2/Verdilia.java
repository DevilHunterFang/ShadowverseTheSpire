package shadowverse.cards.Bishop.Recover2;

import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsInHandAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import shadowverse.cards.AbstractRightClickCard2;
import shadowverse.cards.Neutral.Temp.CyclicalGuidance;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Bishop;


public class Verdilia extends AbstractRightClickCard2 {
    public static final String ID = "shadowverse:Verdilia";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Verdilia");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/Verdilia.png";
    private boolean hasFusion = false;
    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("ExhaustAction");
    public static final String[] TEXT = uiStrings.TEXT;

    public Verdilia() {
        super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.ATTACK, Bishop.Enums.COLOR_WHITE, CardRarity.RARE, CardTarget.SELF);
        this.baseBlock= 8;
        this.tags.add(AbstractShadowversePlayer.Enums.ACADEMIC);
        this.cardsToPreview = new CyclicalGuidance();
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBlock(3);
            this.cardsToPreview.upgrade();
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }

    @Override
    protected void onRightClick() {
        if (!this.hasFusion && AbstractDungeon.player!=null){
            addToBot(new SelectCardsInHandAction(9,TEXT[0],true,true, card -> {
                return card.hasTag(AbstractShadowversePlayer.Enums.ACADEMIC) && card != this;
            }, abstractCards -> {
                if (abstractCards.size()>0){
                    this.hasFusion = true;
                    addToBot(new HealAction(AbstractDungeon.player,AbstractDungeon.player,1));
                }
                for(AbstractCard c:abstractCards){
                    addToBot(new ExhaustSpecificCardAction(c,AbstractDungeon.player.hand));
                }
            }));
        }
    }

    @Override
    public void atTurnStart(){
        hasFusion = false;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new SFXAction("Verdilia"));
        addToBot(new GainBlockAction(p,this.block));
        if (p.hand.group.stream().anyMatch(card -> card.hasTag(AbstractShadowversePlayer.Enums.ACADEMIC) && card !=this)){
            addToBot(new ApplyPowerAction(p,p,new StrengthPower(p,1),1));
            addToBot(new ApplyPowerAction(p,p,new DexterityPower(p,1),1));
        }
        if (this.hasFusion){
            addToBot(new MakeTempCardInHandAction(this.cardsToPreview.makeStatEquivalentCopy()));
        }
    }

    public AbstractCard makeCopy() {
        return new Verdilia();
    }
}
