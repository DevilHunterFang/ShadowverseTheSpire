package shadowverse.cards.Bishop.Amulet1;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import shadowverse.action.PlaceAmulet;
import shadowverse.cards.AbstractEndTurnInvocationCard;
import shadowverse.cards.AbstractNoCountDownAmulet;
import shadowverse.characters.Bishop;
import shadowverse.orbs.AmuletOrb;

public class GoldenCity extends AbstractEndTurnInvocationCard implements AbstractNoCountDownAmulet {
    public static final String ID = "shadowverse:GoldenCity";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:GoldenCity");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/GoldenCity.png";
    public static boolean dupCheck = true;



    public GoldenCity() {
        super(ID, NAME, IMG_PATH, 2, DESCRIPTION,CardType.POWER, Bishop.Enums.COLOR_WHITE, CardRarity.UNCOMMON, CardTarget.SELF);
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBaseCost(1);
        }
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {

    }

    @Override
    public void atTurnStart(){
        dupCheck = true;
    }

    @Override
    public void onStartOfTurn(AmuletOrb paramOrb) {

    }

    @Override
    public void onEvoke(AmuletOrb paramOrb) {

    }

    @Override
    public void endOfTurn(AmuletOrb paramOrb) {

    }

    @Override
    public void onGainedBlock(int blockAmt, AmuletOrb paramOrb) {

    }

    @Override
    public void onOtherCardPlayed(AbstractCard c, AmuletOrb paramOrb) {

    }

    @Override
    public int onHeal(int healAmount, AmuletOrb paramOrb) {
        return healAmount;
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        if (isPlayer){
            if (AbstractDungeon.player.hasEmptyOrb()){
                if (EnergyPanel.getCurrentEnergy()>this.cost && dupCheck){
                    dupCheck = false;
                    if (AbstractDungeon.player.discardPile.contains((AbstractCard)this)) {
                        addToBot((AbstractGameAction)new ExhaustSpecificCardAction(this,AbstractDungeon.player.discardPile));
                        AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new PlaceAmulet(this.makeStatEquivalentCopy(),null));
                    } else if (AbstractDungeon.player.drawPile.contains((AbstractCard)this)) {
                        addToBot((AbstractGameAction)new ExhaustSpecificCardAction(this,AbstractDungeon.player.drawPile));
                        AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new PlaceAmulet(this.makeStatEquivalentCopy(),null));
                    }
                }
            }
        }
    }

}
