package shadowverse.cards.Dragon.Tempo;


import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.InflameEffect;
import shadowverse.characters.Dragon;
import shadowverse.powers.PrimeDragonKeeperPower;

public class PrimeDragonKeeper
        extends CustomCard {
    public static final String ID = "shadowverse:PrimeDragonKeeper";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:PrimeDragonKeeper");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/PrimeDragonKeeper.png";

    public PrimeDragonKeeper() {
        super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.POWER, Dragon.Enums.COLOR_BROWN, CardRarity.RARE, CardTarget.SELF);
        this.baseMagicNumber = 4;
        this.magicNumber = this.baseMagicNumber;
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeMagicNumber(2);
            this.isInnate = true;
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }


    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new SFXAction("PrimeDragonKeeper"));
        addToBot(new VFXAction(new InflameEffect(abstractPlayer)));
        addToBot(new ApplyPowerAction(abstractPlayer, abstractPlayer, new PrimeDragonKeeperPower(abstractPlayer, this.magicNumber), this.magicNumber));
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new PrimeDragonKeeper();
    }
}

