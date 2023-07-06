package shadowverse.cards.Elf.Long;


import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.characters.Elf;
import shadowverse.powers.PameraPower;


public class Pamera extends CustomCard {
    public static final String ID = "shadowverse:Pamera";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Pamera");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/Pamera.png";

    public Pamera() {
        super(ID, NAME, IMG_PATH, 2, DESCRIPTION, CardType.ATTACK, Elf.Enums.COLOR_GREEN, CardRarity.RARE, CardTarget.SELF);
        this.exhaust = true;
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            this.exhaust = false;
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }


    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new SFXAction("Pamera"));
        if (!abstractPlayer.hasPower("shadowverse:PameraPower"))
            addToBot(new ApplyPowerAction(abstractPlayer, abstractPlayer, new PameraPower(abstractPlayer)));
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new Pamera();
    }
}

