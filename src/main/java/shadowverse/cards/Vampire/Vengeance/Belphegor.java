package shadowverse.cards.Vampire.Vengeance;


import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.characters.Vampire;
import shadowverse.powers.EpitaphPower;
import shadowverse.stance.Vengeance;


public class Belphegor extends CustomCard {
    public static final String ID = "shadowverse:Belphegor";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Belphegor");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/Belphegor.png";

    public Belphegor() {
        super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.ATTACK, Vampire.Enums.COLOR_SCARLET, CardRarity.RARE, CardTarget.SELF);
        this.baseMagicNumber = 3;
        this.magicNumber = this.baseMagicNumber;
        this.baseBlock = 4;
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeMagicNumber(1);
            upgradeBlock(4);
        }
    }


    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot((AbstractGameAction)new SFXAction("Belphegor"));
        addToBot((AbstractGameAction)new GainBlockAction(abstractPlayer,this.block));
        addToBot((AbstractGameAction)new DrawCardAction(this.magicNumber));
        int half = abstractPlayer.maxHealth/2;
        if (!abstractPlayer.stance.ID.equals(Vengeance.STANCE_ID)&&!abstractPlayer.hasPower(EpitaphPower.POWER_ID)&&abstractPlayer.currentHealth>half){
            addToBot((AbstractGameAction)new LoseHPAction(abstractPlayer,abstractPlayer,abstractPlayer.currentHealth-half));
        }
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new Belphegor();
    }
}

