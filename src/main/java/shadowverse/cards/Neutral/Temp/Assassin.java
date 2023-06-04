package shadowverse.cards.Neutral.Temp;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import shadowverse.characters.Royal;
import shadowverse.orbs.AmbushMinion;

public class Assassin extends CustomCard {
    public static final String ID = "shadowverse:Assassin";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Assassin");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/Assassin.png";

    public Assassin() {
        super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.ATTACK, Royal.Enums.COLOR_YELLOW, CardRarity.SPECIAL, CardTarget.NONE);
        this.exhaust = true;
    }


    public void upgrade() {
        if (!this.upgraded){
            upgradeName();
            upgradeBaseCost(0);
        }
    }



    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot((AbstractGameAction)new SFXAction("Assassin"));
        for (AbstractOrb o : p.orbs){
            if (o instanceof AmbushMinion){
                ((AmbushMinion) o).ambush = true;
                ((AmbushMinion) o).card.superFlash();
            }
        }
    }

    public AbstractCard makeCopy() {
        return new Assassin();
    }
}
