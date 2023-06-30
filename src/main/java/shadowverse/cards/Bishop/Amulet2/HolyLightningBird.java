package shadowverse.cards.Bishop.Amulet2;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import com.megacrit.cardcrawl.vfx.combat.LightningEffect;
import com.megacrit.cardcrawl.vfx.combat.SweepingBeamEffect;
import shadowverse.Shadowverse;
import shadowverse.cards.AbstractAmuletCard;
import shadowverse.cards.AbstractCrystalizeCard;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Bishop;
import shadowverse.orbs.AmuletOrb;

public class HolyLightningBird
        extends CustomCard implements AbstractCrystalizeCard {
    public static final String ID = "shadowverse:HolyLightningBird";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:HolyLightningBird");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/HolyLightningBird.png";

    public HolyLightningBird() {
        super(ID, NAME, IMG_PATH, 2, DESCRIPTION, CardType.ATTACK, Bishop.Enums.COLOR_WHITE, CardRarity.RARE, CardTarget.ENEMY);
        this.baseDamage = 20;
        this.baseMagicNumber = 1;
        this.magicNumber = this.baseMagicNumber;
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeDamage(8);
            upgradeMagicNumber(1);
        }
    }

    @Override
    public void update() {
        if (AbstractDungeon.currMapNode != null && (AbstractDungeon.getCurrRoom()).phase == AbstractRoom.RoomPhase.COMBAT&&
                Shadowverse.Accelerate(this)){
            setCostForTurn(0);
            this.type = CardType.POWER;
        }else {
            if (this.type==CardType.POWER){
                setCostForTurn(2);
                this.type = CardType.ATTACK;
            }
        }
        super.update();
    }

    public void use(AbstractPlayer p, AbstractMonster abstractMonster) {
        if (this.type==CardType.POWER && this.costForTurn == 0){

        }else {
            addToBot(new DamageAction(abstractMonster,new DamageInfo(p,this.damage,this.damageTypeForTurn), AbstractGameAction.AttackEffect.NONE));
            if (!abstractMonster.isDeadOrEscaped())
                addToBot(new VFXAction(new LightningEffect(abstractMonster.drawX, abstractMonster.drawY), 0.05F));
            int count = 0;
            for (AbstractCard c: AbstractDungeon.actionManager.cardsPlayedThisCombat){
                if (c instanceof AbstractAmuletCard || (c instanceof AbstractCrystalizeCard && c.type==CardType.POWER)){
                    count++;
                }
            }
            if (count >= 5){
                addToBot(new GainEnergyAction(1));
            }
            if (this.type == CardType.POWER){
                addToBot(new MakeTempCardInDiscardAction(this.makeStatEquivalentCopy(),1));
            }
        }
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new HolyLightningBird();
    }

    @Override
    public void onStartOfTurn(AmuletOrb paramOrb) {

    }

    @Override
    public void onEvoke(AmuletOrb paramOrb) {
        addToBot(new GainEnergyAction(this.magicNumber));
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
    public int returnCountDown() {
        return 1;
    }
}


