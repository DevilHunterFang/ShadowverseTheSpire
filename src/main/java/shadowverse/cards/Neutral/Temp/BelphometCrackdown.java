package shadowverse.cards.Neutral.Temp;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.random.Random;
import shadowverse.characters.Nemesis;

import java.util.ArrayList;

public class BelphometCrackdown extends CustomCard {
    public static final String ID = "shadowverse:BelphometCrackdown";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:BelphometCrackdown");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/BelphometCrackdown.png";
    private static final String TEXT = CardCrawlGame.languagePack.getUIString("shadowverse:Retain").TEXT[0];
    public static ArrayList<AbstractCard> returnElinese(){
        ArrayList<AbstractCard> list = new ArrayList<>();
        list.add(new TisiphoneCard());
        list.add(new AlectorCard());
        list.add(new MegaeraCard());
        return list;
    }
    public static AbstractCard returnRandomElinese(Random rng){
        return returnElinese().get(rng.random(returnElinese().size() - 1));
    }

    public BelphometCrackdown() {
        super(ID, NAME, IMG_PATH, -2, DESCRIPTION, CardType.SKILL, Nemesis.Enums.COLOR_SKY, CardRarity.SPECIAL, CardTarget.NONE);
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
        }
    }


    public void use(AbstractPlayer p, AbstractMonster m) {
        onChoseThisOption();
    }

    public void onChoseThisOption() {
        addToBot((AbstractGameAction) new SFXAction("Belphomet4"));
        AbstractCard c = returnRandomElinese(AbstractDungeon.cardRandomRng).makeStatEquivalentCopy();
        c.retain= true;
        c.selfRetain = true;
        c.rawDescription += " NL "+TEXT+" 。";
        c.initializeDescription();
        c.applyPowers();
        AbstractDungeon.player.hand.addToTop(c);
    }

    public AbstractCard makeCopy() {
        return new BelphometCrackdown();
    }
}
