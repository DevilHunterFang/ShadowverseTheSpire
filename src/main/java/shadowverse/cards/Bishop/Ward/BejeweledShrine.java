package shadowverse.cards.Bishop.Ward;

import basemod.abstracts.CustomCard;
import com.evacipated.cardcrawl.mod.stslib.actions.defect.EvokeSpecificOrbAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.powers.*;
import shadowverse.cards.AbstractAmuletCard;
import shadowverse.cards.AbstractCrystalizeCard;
import shadowverse.cards.AbstractNoCountDownAmulet;
import shadowverse.characters.Bishop;
import shadowverse.orbs.AmuletOrb;

public class BejeweledShrine extends CustomCard implements AbstractNoCountDownAmulet {
    public static final String ID = "shadowverse:BejeweledShrine";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:BejeweledShrine");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/BejeweledShrine.png";


    public BejeweledShrine() {
        super(ID, NAME, IMG_PATH, 1, DESCRIPTION,CardType.POWER, Bishop.Enums.COLOR_WHITE, CardRarity.UNCOMMON, CardTarget.SELF);
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBaseCost(0);
        }
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new DrawCardAction(1));
        for (AbstractOrb o : abstractPlayer.orbs){
            if (o instanceof AmuletOrb){
                if (((AmuletOrb) o).amulet instanceof BejeweledShrine){
                    addToBot(new EvokeSpecificOrbAction(o));
                }
            }
        }
    }

    @Override
    public void onStartOfTurn(AmuletOrb paramOrb) {
        int rand = AbstractDungeon.cardRandomRng.random(1);
        if (rand < 1){
            addToBot(new ApplyPowerAction(AbstractDungeon.player,AbstractDungeon.player,new BlurPower(AbstractDungeon.player,1),1));
        }else {
            addToBot(new ApplyPowerAction(AbstractDungeon.player,AbstractDungeon.player,new PenNibPower(AbstractDungeon.player,1),1));
        }
    }

    @Override
    public void onEvoke(AmuletOrb paramOrb) {

    }

    @Override
    public void endOfTurn(AmuletOrb paramOrb) {
        int amount = 1;
        int count = 0;
        for (AbstractCard c: AbstractDungeon.actionManager.cardsPlayedThisCombat){
            if (c instanceof AbstractAmuletCard || (c instanceof AbstractCrystalizeCard && c.type==CardType.POWER)){
                count++;
            }
        }
        if (count>=5){
            amount = 3;
        }
        if ((AbstractDungeon.player.hasPower(PenNibPower.POWER_ID)&&AbstractDungeon.player.hasPower(BlurPower.POWER_ID))||
                (AbstractDungeon.player.hasPower(BarricadePower.POWER_ID) && AbstractDungeon.player.hasPower(DoubleDamagePower.POWER_ID))||
                (AbstractDungeon.player.hasPower(PenNibPower.POWER_ID)&&AbstractDungeon.player.hasPower(BarricadePower.POWER_ID))||
                (AbstractDungeon.player.hasPower(DoubleDamagePower.POWER_ID)&&AbstractDungeon.player.hasPower(BlurPower.POWER_ID))
        ){
            addToBot(new ApplyPowerAction(AbstractDungeon.player,AbstractDungeon.player,new StrengthPower(AbstractDungeon.player,amount),amount));
            addToBot(new ApplyPowerAction(AbstractDungeon.player,AbstractDungeon.player,new DexterityPower(AbstractDungeon.player,amount),amount));
        }
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
}
