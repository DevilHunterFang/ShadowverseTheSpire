package shadowverse.cards.Neutral.Temp;


import basemod.abstracts.CustomCard;
import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsInHandAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.common.ReduceCostForTurnAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.cards.Bishop.Default.HeavenlyAegis;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Bishop;


public class SalvationLimonia extends CustomCard {
    public static final String ID = "shadowverse:SalvationLimonia";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:SalvationLimonia");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/SalvationLimonia.png";
    public static final String[] TEXT = (CardCrawlGame.languagePack.getUIString("shadowverse:ChooseToReduceCost")).TEXT;

    public SalvationLimonia() {
        super(ID, NAME, IMG_PATH, 0, DESCRIPTION, CardType.SKILL, Bishop.Enums.COLOR_WHITE, CardRarity.SPECIAL, CardTarget.NONE);
        this.tags.add(AbstractShadowversePlayer.Enums.MACHINE);
        this.exhaust = true;
        this.cardsToPreview = new HeavenlyAegis();
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            this.selfRetain = true;
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }


    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot((AbstractGameAction) new SFXAction("SalvationLimonia"));
        addToBot((AbstractGameAction) new SelectCardsInHandAction(1, TEXT[0], false, false, card -> {
            return (card.type == CardType.ATTACK && (card.color == Bishop.Enums.COLOR_WHITE || card.hasTag(AbstractShadowversePlayer.Enums.MACHINE)));
        }, abstractCards -> {
            for (AbstractCard c : abstractCards) {
                c.flash();
                addToBot((AbstractGameAction) new ReduceCostForTurnAction(c, 1));
            }
        }));
        int count = 0;
        for (AbstractCard c : AbstractDungeon.actionManager.cardsPlayedThisCombat){
            if (c instanceof SalvationLimonia){
                count++;
            }
        }
        if (count == 5){
            addToBot((AbstractGameAction)new MakeTempCardInHandAction(new HeavenlyAegis()));
        }
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new SalvationLimonia();
    }
}
