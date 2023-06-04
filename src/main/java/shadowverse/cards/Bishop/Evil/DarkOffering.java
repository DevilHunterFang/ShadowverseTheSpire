package shadowverse.cards.Bishop.Evil;

import basemod.abstracts.CustomCard;
import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsInHandAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.action.PlaceAmulet;
import shadowverse.cards.Neutral.Neutral.OldSatan;
import shadowverse.cards.Neutral.Neutral.Satan;
import shadowverse.characters.Bishop;

public class DarkOffering
        extends CustomCard {
    public static final String ID = "shadowverse:DarkOffering";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:DarkOffering");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/DarkOffering.png";
    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("shadowverse:PlaceCardToAmuletZone");
    public static final String[] TEXT = uiStrings.TEXT;

    public DarkOffering() {
        super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.SKILL, Bishop.Enums.COLOR_WHITE, CardRarity.UNCOMMON, CardTarget.NONE);
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBaseCost(0);
        }
    }


    public void use(AbstractPlayer p, AbstractMonster abstractMonster) {
        addToBot((AbstractGameAction)new SelectCardsInHandAction(1,TEXT[0],true,true,card -> {
            return card.type==CardType.CURSE || card instanceof Satan || card instanceof OldSatan
                    ||Satan.returnCocytusDeck().stream().anyMatch(card1 -> card.cardID.equals(card1.cardID))
                    ||OldSatan.returnApocalypseDeck().stream().anyMatch(card1 -> card.cardID.equals(card1.cardID));
        },abstractCards -> {
            for (AbstractCard c:abstractCards){
                if (p.hasEmptyOrb()){
                    addToBot((AbstractGameAction)new PlaceAmulet(c,p.hand));
                }else {
                    addToBot((AbstractGameAction)new ExhaustSpecificCardAction(c,p.hand));
                }
                addToBot((AbstractGameAction)new GainEnergyAction(2));
            }
        }));
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new DarkOffering();
    }
}


