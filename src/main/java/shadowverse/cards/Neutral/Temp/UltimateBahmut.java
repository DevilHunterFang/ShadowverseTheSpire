package shadowverse.cards.Neutral.Temp;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.utility.DiscardToHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import shadowverse.Shadowverse;
import shadowverse.action.InvocationAction;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.powers.UltimateBahmutPower;


public class UltimateBahmut extends CustomCard {
    public static final String ID = "shadowverse:UltimateBahmut";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:UltimateBahmut");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/UltimateBahmut.png";
    public static boolean dupCheck = true;


    public UltimateBahmut() {
        super(ID, NAME, IMG_PATH, 3, DESCRIPTION, CardType.ATTACK, CardColor.COLORLESS, CardRarity.SPECIAL, CardTarget.ENEMY);
        this.baseDamage = 130;
        this.baseMagicNumber = 15;
        this.magicNumber = this.baseMagicNumber;
        this.tags.add(AbstractShadowversePlayer.Enums.LEGEND);
    }

    @Override
    public void update() {
        if (AbstractDungeon.currMapNode != null && (AbstractDungeon.getCurrRoom()).phase == AbstractRoom.RoomPhase.COMBAT&&
                Shadowverse.Accelerate(this)){
            setCostForTurn(2);
            this.type = CardType.SKILL;
        }else {
            if (this.type==CardType.SKILL){
                setCostForTurn(3);
                this.type = CardType.ATTACK;
            }
        }
        super.update();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeDamage(20);
        }
    }


    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (Shadowverse.Accelerate((AbstractCard)this) && this.type == CardType.SKILL) {
            addToBot(new DamageRandomEnemyAction(new DamageInfo(p,this.magicNumber, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.FIRE));
            addToBot(new GainEnergyAction(2));
        }else {
            addToBot((AbstractGameAction) new DamageAction((AbstractCreature) m, new DamageInfo((AbstractCreature) p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
        }
    }

    public void applyPowers() {
        super.applyPowers();
        int count = 0;
        if (AbstractDungeon.player instanceof AbstractShadowversePlayer)
            count = ((AbstractShadowversePlayer) AbstractDungeon.player).costUsedAmt;
        this.rawDescription = cardStrings.DESCRIPTION;
        this.rawDescription += cardStrings.EXTENDED_DESCRIPTION[0] + count;
        this.rawDescription += cardStrings.EXTENDED_DESCRIPTION[1];
        initializeDescription();
    }


    public void atTurnStart() {
        int count = 0;
        if (AbstractDungeon.player instanceof AbstractShadowversePlayer)
            count = ((AbstractShadowversePlayer) AbstractDungeon.player).costUsedAmt;
        if (count>= 50 && dupCheck) {
            dupCheck = false;
            setCostForTurn(0);
            if (AbstractDungeon.player.discardPile.contains((AbstractCard) this)) {
                addToBot((AbstractGameAction) new DiscardToHandAction((AbstractCard) this));
            } else if (AbstractDungeon.player.drawPile.contains((AbstractCard) this)) {
                addToBot((AbstractGameAction) new InvocationAction((AbstractCard) this));
            }
            for (AbstractMonster mo : (AbstractDungeon.getCurrRoom()).monsters.monsters){
                if (mo!=null && !mo.isDeadOrEscaped() && !mo.isDying && !mo.halfDead && !mo.hasPower(UltimateBahmutPower.POWER_ID)){
                    addToBot(new ApplyPowerAction(mo,AbstractDungeon.player,new UltimateBahmutPower(mo)));
                }
            }
        } else if (count < 50 ) {
            dupCheck = true;
        }
    }

    @Override
    public void triggerOnCardPlayed(AbstractCard cardPlayed) {
        dupCheck = true;
    }

    @Override
    public void triggerOnOtherCardPlayed(AbstractCard c) {
        dupCheck = true;
    }

    @Override
    public AbstractCard makeCopy() {
        return new UltimateBahmut();
    }
}

