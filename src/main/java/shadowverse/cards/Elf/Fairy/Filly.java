package shadowverse.cards.Elf.Fairy;


import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.cards.Elf.Long.SkyDevouring;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Elf;
import shadowverse.powers.FillyPower;


public class Filly extends CustomCard {
    public static final String ID = "shadowverse:Filly";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Filly");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/Filly.png";

    public Filly() {
        super(ID, NAME, IMG_PATH, 2, DESCRIPTION, CardType.POWER, Elf.Enums.COLOR_GREEN, CardRarity.RARE, CardTarget.SELF);
        this.tags.add(AbstractShadowversePlayer.Enums.ACADEMIC);
        this.cardsToPreview = new SkyDevouring();
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
        addToBot(new SFXAction("Filly"));
        addToBot(new ApplyPowerAction(abstractPlayer, abstractPlayer, new FillyPower(abstractPlayer)));
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new Filly();
    }
}

