package shadowverse.cards.Bishop.Amulet2;


import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.DrawPileToHandAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import shadowverse.cards.AbstractAmuletCard;
import shadowverse.cards.AbstractCrystalizeCard;
import shadowverse.characters.Bishop;


public class JewelShine
        extends CustomCard {
    public static final String ID = "shadowverse:JewelShine";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:JewelShine");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/JewelShine.png";

    public JewelShine() {
        super(ID, NAME, IMG_PATH, 0, DESCRIPTION, CardType.SKILL, Bishop.Enums.COLOR_WHITE, CardRarity.COMMON, CardTarget.NONE);
        this.baseMagicNumber = 1;
        this.magicNumber = this.baseMagicNumber;
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeMagicNumber(1);
        }
    }


    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new SFXAction("JewelShine"));
        addToBot(new DrawPileToHandAction(this.magicNumber,CardType.POWER));
        int count = 0;
        for (AbstractCard c: AbstractDungeon.actionManager.cardsPlayedThisCombat){
            if (c instanceof AbstractAmuletCard || (c instanceof AbstractCrystalizeCard && c.type==CardType.POWER)){
                count++;
            }
        }
        if (count >= 5){
            addToBot(new ApplyPowerAction(AbstractDungeon.player,AbstractDungeon.player,new StrengthPower(AbstractDungeon.player,1),1));
            addToBot(new ApplyPowerAction(AbstractDungeon.player,AbstractDungeon.player,new DexterityPower(AbstractDungeon.player,3),3));
            this.exhaust = true;
            p.hand.removeCard(this);
        }
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new JewelShine();
    }
}

