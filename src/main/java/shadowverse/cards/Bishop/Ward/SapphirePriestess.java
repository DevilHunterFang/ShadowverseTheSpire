package shadowverse.cards.Bishop.Ward;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.powers.BlurPower;
import com.megacrit.cardcrawl.powers.PenNibPower;
import shadowverse.characters.Bishop;
import shadowverse.orbs.AmuletOrb;

public class SapphirePriestess extends CustomCard {
    public static final String ID = "shadowverse:SapphirePriestess";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:SapphirePriestess");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/SapphirePriestess.png";


    public SapphirePriestess() {
        super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.ATTACK, Bishop.Enums.COLOR_WHITE, CardRarity.COMMON, CardTarget.SELF);
        this.baseBlock = 5;
    }



    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBlock(3);
        }
    }


    @Override
    public void use(AbstractPlayer p, AbstractMonster abstractMonster) {
        addToBot(new SFXAction("SapphirePriestess"));
        addToBot(new GainBlockAction(p,this.block));
        for (AbstractOrb o : p.orbs){
            if (o instanceof AmuletOrb){
                addToBot(new DrawCardAction(1));
                break;
            }
        }
        int count = 0;
        for (AbstractCard c: p.hand.group){
            if (c.type == CardType.ATTACK){
                count++;
            }
        }
        if (count < 3){
            addToBot(new ApplyPowerAction(p,p,new BlurPower(p,1),1));
            addToBot(new ApplyPowerAction(p,p,new PenNibPower(p,1),1));
        }
    }


    @Override
    public AbstractCard makeCopy() {
        return new SapphirePriestess();
    }
}
