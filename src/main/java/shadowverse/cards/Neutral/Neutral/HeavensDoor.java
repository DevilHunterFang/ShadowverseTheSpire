package shadowverse.cards.Neutral.Neutral;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import shadowverse.cards.AbstractNeutralCard;
import shadowverse.powers.HeavensDoorInvocationPower;
import shadowverse.powers.HeavensDoorPower;

public class HeavensDoor extends AbstractNeutralCard {
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:HeavensDoor");
    public static final String ID = "shadowverse:HeavensDoor";
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION; public static final String IMG_PATH = "img/cards/HeavensDoor.png";
    public static boolean dupCheck = true;


    public HeavensDoor() {
        super(ID, NAME, IMG_PATH, 2, DESCRIPTION, CardType.POWER, CardColor.COLORLESS, CardRarity.UNCOMMON, CardTarget.SELF);
        this.baseMagicNumber = 1;
        this.magicNumber = this.baseMagicNumber;
    }

    @Override
    public void upgrade() {
        if(!this.upgraded){
            upgradeBaseCost(1);
            upgradeName();
        }
    }

    @Override
    public void atTurnStart() {
        if (!AbstractDungeon.player.hasPower("shadowverse:HeavensDoorPower")&&(AbstractDungeon.player.drawPile.contains(this)||AbstractDungeon.player.discardPile.contains(this))){
            addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)AbstractDungeon.player, (AbstractCreature)AbstractDungeon.player, (AbstractPower)new HeavensDoorInvocationPower((AbstractCreature)AbstractDungeon.player, this.magicNumber,this), this.magicNumber));
        }
    }



    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)abstractPlayer, (AbstractCreature)abstractPlayer, (AbstractPower)new HeavensDoorPower((AbstractCreature)abstractPlayer, this.magicNumber), this.magicNumber));
    }

    public AbstractCard makeCopy() {
        return new HeavensDoor();
    }
}
