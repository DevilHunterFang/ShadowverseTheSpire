package shadowverse.cards.Bishop.Ward;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.BlurPower;
import com.megacrit.cardcrawl.powers.PenNibPower;
import shadowverse.cards.AbstractAmuletCard;
import shadowverse.cards.AbstractCrystalizeCard;
import shadowverse.characters.Bishop;

public class GemstoneWingy extends CustomCard {
    public static final String ID = "shadowverse:GemstoneWingy";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:GemstoneWingy");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/GemstoneWingy.png";


    public GemstoneWingy() {
        super(ID, NAME, IMG_PATH, 0, DESCRIPTION, CardType.ATTACK, Bishop.Enums.COLOR_WHITE, CardRarity.UNCOMMON, CardTarget.SELF);
        this.baseBlock = 5;
    }



    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBlock(2);
        }
    }


    @Override
    public void use(AbstractPlayer p, AbstractMonster abstractMonster) {
        addToBot(new SFXAction("GemstoneWingy"));
        addToBot(new GainBlockAction(p,this.block));
        int count = 0;
        for (AbstractCard c: AbstractDungeon.actionManager.cardsPlayedThisCombat){
            if (c instanceof AbstractAmuletCard || (c instanceof AbstractCrystalizeCard && c.type==CardType.POWER)){
                count++;
            }
        }
        if (count>=5){
            addToBot(new ApplyPowerAction(p,p,new BlurPower(p,1),1));
            addToBot(new ApplyPowerAction(p,p,new PenNibPower(p,1),1));
        }
    }


    @Override
    public AbstractCard makeCopy() {
        return new GemstoneWingy();
    }
}
