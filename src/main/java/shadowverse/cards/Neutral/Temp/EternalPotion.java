package shadowverse.cards.Neutral.Temp;


import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.action.ReanimateAction;
import shadowverse.characters.Necromancer;


public class EternalPotion extends CustomCard {
    public static final String ID = "shadowverse:EternalPotion";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:EternalPotion");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/EternalPotion.png";

    public EternalPotion() {
        super(ID, NAME, IMG_PATH, 2, DESCRIPTION, CardType.SKILL, Necromancer.Enums.COLOR_PURPLE, CardRarity.SPECIAL, CardTarget.NONE);
        this.baseMagicNumber = 4;
        this.magicNumber = this.baseMagicNumber;
        this.exhaust = true;
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBaseCost(1);
        }
    }


    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot((AbstractGameAction)new SFXAction("EternalPotion"));
        addToBot((AbstractGameAction)new ReanimateAction(this.magicNumber));
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new EternalPotion();
    }
}
