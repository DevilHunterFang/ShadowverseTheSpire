package shadowverse.cards.Rare;

import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsInHandAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.unique.ExpertiseAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.CleaveEffect;
import com.megacrit.cardcrawl.vfx.combat.VerticalImpactEffect;
import shadowverse.action.FusionAction2;
import shadowverse.cards.AbstractRightClickCard2;
import shadowverse.characters.Elf;
import shadowverse.characters.Vampire;

import java.util.ArrayList;
import java.util.List;

public class TerrorFormer
        extends AbstractRightClickCard2 {
    public static final String ID = "shadowverse:TerrorFormer";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:TerrorFormer");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/TerrorFormer.png";
    private boolean hasFusion = false;
    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("ExhaustAction");
    public static final String[] TEXT = uiStrings.TEXT;
    private int fusionAmt;



    public TerrorFormer() {
        super(ID, NAME, IMG_PATH, 2, DESCRIPTION, CardType.ATTACK, Elf.Enums.COLOR_GREEN, CardRarity.RARE, CardTarget.ENEMY);
        this.baseMagicNumber = 12;
        this.magicNumber = this.baseMagicNumber;
        this.baseDamage = 20;
        this.selfRetain = true;
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeDamage(4);
            upgradeMagicNumber(4);
        }
    }

    @Override
    protected void onRightClick() {
        if (!this.hasFusion && AbstractDungeon.player!=null){
            addToBot((AbstractGameAction)new SelectCardsInHandAction(8,TEXT[0],true,true,card -> {
                return card.type==CardType.ATTACK&&card.color==Elf.Enums.COLOR_GREEN&&card.cost>0&&card!=this;
            }, abstractCards -> {
                if (abstractCards.size()>1){
                    addToBot((AbstractGameAction)new DrawCardAction(1));
                    fusionAmt++;
                    this.applyPowers();
                }
                for (AbstractCard c:abstractCards){
                    addToBot((AbstractGameAction) new ExhaustSpecificCardAction(c,AbstractDungeon.player.hand));
                }
            }));
            this.hasFusion = true;
        }
    }

    public void applyPowers() {
        int realBaseDamage = this.baseDamage;
        this.baseDamage = fusionAmt * this.magicNumber + this.baseDamage;
        super.applyPowers();
        this.baseDamage = realBaseDamage;
        this.isDamageModified = (this.damage != this.baseDamage);
    }

    public void calculateCardDamage(AbstractMonster mo) {
        int realBaseDamage = this.baseDamage;
        this.baseDamage = fusionAmt * this.magicNumber + this.baseDamage;
        super.calculateCardDamage(mo);
        this.baseDamage = realBaseDamage;
        this.isDamageModified = (this.damage != this.baseDamage);
    }

    @Override
    public void atTurnStart(){
        hasFusion = false;
    }



    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot((AbstractGameAction) new ApplyPowerAction(abstractMonster, abstractPlayer, (AbstractPower) new VulnerablePower(abstractMonster, 2, false), 2));
        addToBot((AbstractGameAction) new ApplyPowerAction(abstractMonster, abstractPlayer, (AbstractPower) new WeakPower(abstractMonster, 2, false), 2));
        if (abstractMonster != null)
            addToBot((AbstractGameAction) new VFXAction((AbstractGameEffect) new VerticalImpactEffect(abstractMonster.hb.cX + abstractMonster.hb.width / 4.0F, abstractMonster.hb.cY - abstractMonster.hb.height / 4.0F)));
        calculateCardDamage(abstractMonster);
        addToBot((AbstractGameAction) new DamageAction((AbstractCreature) abstractMonster, new DamageInfo((AbstractCreature) abstractPlayer, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new TerrorFormer();
    }
}

