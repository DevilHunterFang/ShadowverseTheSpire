package shadowverse.cards.Dragon.Buff;


import basemod.abstracts.CustomCard;
import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsInHandAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.DrawCardNextTurnPower;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import shadowverse.Shadowverse;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Dragon;


public class Joe
        extends CustomCard {
    public static final String ID = "shadowverse:Joe";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Joe");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/Joe.png";
    public static final String[] TEXT = (CardCrawlGame.languagePack.getUIString("shadowverse:applyEffect")).TEXT;

    public Joe() {
        super(ID, NAME, IMG_PATH, 2, DESCRIPTION, CardType.ATTACK, Dragon.Enums.COLOR_BROWN, CardRarity.RARE, CardTarget.ALL);
        this.baseBlock = 15;
        this.baseDamage = 20;
        this.baseMagicNumber = 28;
        this.magicNumber = this.baseMagicNumber;
        this.tags.add(AbstractShadowversePlayer.Enums.ACADEMIC);
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBlock(5);
            upgradeDamage(5);
            upgradeMagicNumber(4);
        }
    }

    @Override
    public void update() {
        if (AbstractDungeon.currMapNode != null && (AbstractDungeon.getCurrRoom()).phase == AbstractRoom.RoomPhase.COMBAT &&
                Shadowverse.Accelerate(this)) {
            setCostForTurn(0);
            this.type = CardType.POWER;
        } else {
            if (this.type == CardType.POWER) {
                setCostForTurn(2);
                this.type = CardType.ATTACK;
            }
        }
        super.update();
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        if (Shadowverse.Accelerate(this) && this.type == CardType.POWER) {
            addToBot(new SFXAction("Joe_Acc"));
            addToBot(new SelectCardsInHandAction(1, TEXT[0], false, true, card -> {
                return true;
            }, abstractCards -> {
                for (AbstractCard c : abstractCards) {
                    addToBot(new ExhaustSpecificCardAction(c,abstractPlayer.hand));
                    addToBot(new MakeTempCardInDrawPileAction(c.makeStatEquivalentCopy(),1,true,true));
                    addToBot(new ApplyPowerAction(abstractPlayer,abstractPlayer,new DrawCardNextTurnPower(abstractPlayer,1),1));
                    if (c.hasTag(AbstractShadowversePlayer.Enums.ACADEMIC)){
                        addToBot(new ApplyPowerAction(abstractPlayer,abstractPlayer,new DrawCardNextTurnPower(abstractPlayer,1),1));
                    }
                }
            }));
            addToBot(new ApplyPowerAction(abstractPlayer,abstractPlayer,new DexterityPower(abstractPlayer,1),1));
            addToBot(new MakeTempCardInDiscardAction(this.makeStatEquivalentCopy(),1));
        } else {
            addToBot(new SFXAction("Joe"));
            addToBot(new GainBlockAction(abstractPlayer,this.block));
            if (abstractPlayer.hasPower(DexterityPower.POWER_ID) && abstractPlayer.getPower(DexterityPower.POWER_ID).amount >= 4) {
                addToBot(new DamageAllEnemiesAction(abstractPlayer, DamageInfo.createDamageMatrix(this.damage, true), this.damageTypeForTurn, AbstractGameAction.AttackEffect.FIRE));
            }
            if (abstractPlayer.hasPower(DexterityPower.POWER_ID) && abstractPlayer.getPower(DexterityPower.POWER_ID).amount >= 6) {
                addToBot(new DamageAllEnemiesAction(abstractPlayer, DamageInfo.createDamageMatrix(this.magicNumber, true), DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.FIRE));
            }
        }
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new Joe();
    }
}

