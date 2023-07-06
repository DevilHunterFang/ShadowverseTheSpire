package shadowverse.cards.Witch.Earth2;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.cards.Dragon.Ramp.GenesisDragon;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Witchcraft;

public class ColossalSummoning extends CustomCard {
    public static final String ID = "shadowverse:ColossalSummoning";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:ColossalSummoning");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/ColossalSummoning.png";
    private static final String TEXT = CardCrawlGame.languagePack.getUIString("shadowverse:Exhaust").TEXT[0];

    public ColossalSummoning() {
        super(ID, NAME, IMG_PATH, 2, DESCRIPTION, CardType.SKILL, Witchcraft.Enums.COLOR_BLUE, CardRarity.UNCOMMON, CardTarget.SELF);
        this.baseBlock = 15;
        this.cardsToPreview = new GenesisDragon();
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBlock(5);
            this.cardsToPreview.upgrade();
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }

    public void applyPowers() {
        super.applyPowers();
        int count = 0;
        if (AbstractDungeon.player instanceof AbstractShadowversePlayer) {
            count = ((AbstractShadowversePlayer) AbstractDungeon.player).earthCount;
        }
        this.rawDescription = cardStrings.DESCRIPTION;
        this.rawDescription += cardStrings.EXTENDED_DESCRIPTION[0] + count;
        this.rawDescription += cardStrings.EXTENDED_DESCRIPTION[1];
        initializeDescription();
    }


    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        if (abstractPlayer instanceof AbstractShadowversePlayer){
            int count = ((AbstractShadowversePlayer) abstractPlayer).earthCount;
            if (count >= 7){
                AbstractCard tmp = this.cardsToPreview.makeStatEquivalentCopy();
                tmp.setCostForTurn(0);
                tmp.costForTurn = 0;
                tmp.isCostModified = true;
                tmp.exhaustOnUseOnce = true;
                tmp.exhaust = true;
                tmp.rawDescription += " NL " + TEXT + " 。";
                tmp.initializeDescription();
                tmp.applyPowers();
                abstractPlayer.hand.addToTop(tmp);
            }else {
                addToBot(new GainBlockAction(abstractPlayer,this.block));
            }
        }else {
            addToBot(new GainBlockAction(abstractPlayer,this.block));
        }
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new ColossalSummoning();
    }
}

