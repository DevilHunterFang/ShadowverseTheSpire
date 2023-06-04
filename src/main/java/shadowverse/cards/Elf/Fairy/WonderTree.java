package shadowverse.cards.Elf.Fairy;

import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsInHandAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import shadowverse.cards.AbstractRightClickCard2;
import shadowverse.cards.Neutral.Temp.Fairy;
import shadowverse.characters.Elf;
import shadowverse.powers.WonderTreePower;

public class WonderTree
        extends AbstractRightClickCard2 {
    public static final String ID = "shadowverse:WonderTree";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:WonderTree");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/WonderTree.png";
    private boolean hasFusion = false;
    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("ExhaustAction");
    public static final String[] TEXT = uiStrings.TEXT;


    public WonderTree() {
        super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.POWER, Elf.Enums.COLOR_GREEN, CardRarity.COMMON, CardTarget.SELF);
        this.baseMagicNumber = 3;
        this.magicNumber = this.baseMagicNumber;
        this.cardsToPreview = new Fairy();
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeMagicNumber(2);
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }

    @Override
    protected void onRightClick() {
        if (!this.hasFusion && AbstractDungeon.player!=null){
            addToBot((AbstractGameAction)new SelectCardsInHandAction(8,TEXT[0],true,true,card -> {
                return card instanceof Fairy;
            }, abstractCards -> {
                if (abstractCards.size()>0){
                    this.hasFusion = true;
                }
                for(AbstractCard c:abstractCards){
                    addToBot((AbstractGameAction)new ExhaustSpecificCardAction(c,AbstractDungeon.player.hand));
                }
            }));
        }
    }

    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot((AbstractGameAction)new ApplyPowerAction(abstractPlayer,abstractPlayer,(AbstractPower)new WonderTreePower(abstractPlayer,1,this.magicNumber,this.hasFusion,this.upgraded)));
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new WonderTree();
    }
}

