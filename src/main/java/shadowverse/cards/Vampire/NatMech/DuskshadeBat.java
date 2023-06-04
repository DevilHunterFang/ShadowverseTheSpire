package shadowverse.cards.Vampire.NatMech;


import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.characters.Vampire;
import shadowverse.powers.AvaricePower;
import shadowverse.powers.EpitaphPower;
import shadowverse.powers.WrathPower;
import shadowverse.stance.Vengeance;


public class DuskshadeBat
        extends CustomCard {
    public static final String ID = "shadowverse:DuskshadeBat";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:DuskshadeBat");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/DuskshadeBat.png";

    public DuskshadeBat() {
        super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.ATTACK, Vampire.Enums.COLOR_SCARLET, CardRarity.UNCOMMON, CardTarget.SELF);
        this.baseBlock = 5;
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBlock(3);
        }
    }


    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot((AbstractGameAction)new GainBlockAction(p,this.block));
        if (p.hasPower(EpitaphPower.POWER_ID)){
            addToBot((AbstractGameAction)new GainEnergyAction(2));
            addToBot((AbstractGameAction)new DrawCardAction(2));
        }else {
            if (p.stance.ID.equals(Vengeance.STANCE_ID) || p.hasPower(WrathPower.POWER_ID)){
                addToBot((AbstractGameAction)new GainEnergyAction(1));
                addToBot((AbstractGameAction)new DrawCardAction(1));
            }
            if (p.hasPower(AvaricePower.POWER_ID)){
                addToBot((AbstractGameAction)new GainEnergyAction(1));
                addToBot((AbstractGameAction)new DrawCardAction(1));
            }
        }
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new DuskshadeBat();
    }
}

