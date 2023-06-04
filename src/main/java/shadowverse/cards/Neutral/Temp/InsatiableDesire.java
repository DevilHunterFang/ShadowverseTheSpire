package shadowverse.cards.Neutral.Temp;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.watcher.EnergyDownPower;
import shadowverse.characters.Vampire;
import shadowverse.powers.InsatiableDesirePower;

public class InsatiableDesire extends CustomCard {
    public static final String ID = "shadowverse:InsatiableDesire";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:InsatiableDesire");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/InsatiableDesire.png";

    public InsatiableDesire() {
        super(ID, NAME, IMG_PATH, 0, DESCRIPTION, CardType.SKILL, Vampire.Enums.COLOR_SCARLET, CardRarity.SPECIAL, CardTarget.SELF);
        this.exhaust = true;
    }


    public void upgrade() {
        if (!this.upgraded){
            upgradeName();
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }



    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot((AbstractGameAction)new SFXAction("InsatiableDesire"));
        addToBot((AbstractGameAction)new ApplyPowerAction(p,p,new InsatiableDesirePower(p)));
        if (!this.upgraded){
            addToBot((AbstractGameAction)new ApplyPowerAction(p,p,new EnergyDownPower(p,1),1));
        }
    }

    public AbstractCard makeCopy() {
        return new InsatiableDesire();
    }
}
