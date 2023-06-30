package shadowverse.cards.Bishop.Amulet2;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import shadowverse.Shadowverse;
import shadowverse.cards.AbstractCrystalizeCard;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Bishop;
import shadowverse.orbs.AmuletOrb;
import shadowverse.orbs.Minion;

public class JudgmentSpearMaster
        extends CustomCard implements AbstractCrystalizeCard {
    public static final String ID = "shadowverse:JudgmentSpearMaster";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:JudgmentSpearMaster");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/JudgmentSpearMaster.png";
    public boolean crystalize;

    public JudgmentSpearMaster() {
        super(ID, NAME, IMG_PATH, 2, DESCRIPTION, CardType.ATTACK, Bishop.Enums.COLOR_WHITE, CardRarity.UNCOMMON, CardTarget.ENEMY);
        this.baseDamage = 15;
        this.baseBlock = 8;
        this.baseMagicNumber = 1;
        this.magicNumber = this.baseMagicNumber;
        this.tags.add(AbstractShadowversePlayer.Enums.AMULET_FOR_ONECE);
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeDamage(5);
            upgradeBlock(2);
        }
    }

    @Override
    public void update() {
        if (AbstractDungeon.currMapNode != null && (AbstractDungeon.getCurrRoom()).phase == AbstractRoom.RoomPhase.COMBAT&&
                Shadowverse.Accelerate(this)&&!this.crystalize){
            setCostForTurn(1);
            this.type = CardType.POWER;
        }else {
            if (this.type==CardType.POWER){
                setCostForTurn(2);
                this.type = CardType.ATTACK;
            }
        }
        super.update();
    }

    public void onMoveToDiscard() {
        this.costForTurn = this.cost;
        this.isCostModifiedForTurn = false;
        this.type = CardType.ATTACK;
        applyPowers();
    }

    public void use(AbstractPlayer p, AbstractMonster abstractMonster) {
        if (this.type==CardType.POWER && this.costForTurn == 1){
            addToBot(new SFXAction("JudgmentSpearMaster_Acc"));
        }else {
            this.crystalize = false;
            addToBot(new SFXAction("JudgmentSpearMaster"));
            addToBot(new GainBlockAction(p,this.block));
            addToBot(new DamageAction(abstractMonster, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
            if (this.type == CardType.POWER){
                addToBot(new MakeTempCardInDiscardAction(this.makeStatEquivalentCopy(),1));
            }
        }
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new JudgmentSpearMaster();
    }

    @Override
    public void onStartOfTurn(AmuletOrb paramOrb) {
    }

    @Override
    public void onEvoke(AmuletOrb paramOrb) {
        AbstractCard c = this.makeCopy();
        if (this.upgraded)
            c.upgrade();
        c.setCostForTurn(0);
        c.type = CardType.ATTACK;
        ((JudgmentSpearMaster)c).crystalize = true;
        AbstractDungeon.player.hand.addToTop(c);
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


