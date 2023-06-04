package shadowverse.cards.Neutral.Neutral;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.MindblastEffect;
import shadowverse.action.DrawPileToHandAction_Tag;
import shadowverse.action.DrawPileToHandAction_Tag_Machinus;
import shadowverse.action.FusionAction;
import shadowverse.cards.AbstractRightClickCard;
import shadowverse.characters.AbstractShadowversePlayer;

public class Machinus extends AbstractRightClickCard {
    public static final String ID = "shadowverse:Machinus";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Machinus");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/Machinus.png";
    public boolean machineCheck = false;
    public boolean naturalCheck = false;
    public boolean turnCheck = true;
    public boolean fusionCheck = false;

    public Machinus() {
        super(ID, NAME, IMG_PATH, 3, DESCRIPTION, CardType.ATTACK, CardColor.COLORLESS, CardRarity.RARE, CardTarget.ENEMY);
        this.baseDamage = 7;
        this.tags.add(AbstractShadowversePlayer.Enums.MACHINE);
        this.tags.add(AbstractShadowversePlayer.Enums.NATURAL);
        this.baseBlock = 20;
        this.baseMagicNumber = 0;
        this.magicNumber = this.baseMagicNumber;
    }

    @Override
    protected void onRightClick() {
        if (turnCheck){
            addToBot((AbstractGameAction) new FusionAction(8,false,true,true,this,AbstractShadowversePlayer.Enums.MACHINE,AbstractShadowversePlayer.Enums.NATURAL));
            if (fusionCheck){
                turnCheck = false;
            }
        }
    }

    @Override
    public void atTurnStart(){
        turnCheck = true;
        fusionCheck = false;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            this.upgradeDamage(2);
            this.upgradeBlock(3);
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }

    public void applyPowers() {
        int realBaseDamage = this.baseDamage;
        this.baseDamage += this.baseMagicNumber * this.baseDamage;
        super.applyPowers();
        this.baseDamage = realBaseDamage;
        this.isDamageModified = (this.damage != this.baseDamage);
    }

    public void calculateCardDamage(AbstractMonster mo) {
        int realBaseDamage = this.baseDamage;
        this.baseDamage += this.baseMagicNumber * this.baseDamage;
        super.calculateCardDamage(mo);
        this.baseDamage = realBaseDamage;
        this.isDamageModified = (this.damage != this.baseDamage);
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        calculateCardDamage(abstractMonster);
        addToBot((AbstractGameAction) new GainBlockAction((AbstractCreature) abstractPlayer,(AbstractCreature) abstractPlayer,this.block));
        addToBot((AbstractGameAction)new SFXAction("ATTACK_HEAVY"));
        addToBot((AbstractGameAction)new VFXAction((AbstractCreature)abstractPlayer, (AbstractGameEffect)new MindblastEffect(abstractPlayer.dialogX, abstractPlayer.dialogY, abstractPlayer.flipHorizontal), 0.1F));
        addToBot((AbstractGameAction)new DamageAction((AbstractCreature)abstractMonster, new DamageInfo((AbstractCreature)abstractPlayer, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.NONE));
        if (this.machineCheck && this.naturalCheck){
            addToBot((AbstractGameAction)new DrawPileToHandAction_Tag_Machinus(this.magicNumber, AbstractShadowversePlayer.Enums.NATURAL, AbstractShadowversePlayer.Enums.MACHINE));
        }else if(this.magicNumber !=0 ){
            addToBot((AbstractGameAction)new DrawPileToHandAction_Tag(this.magicNumber, AbstractShadowversePlayer.Enums.NATURAL, AbstractShadowversePlayer.Enums.MACHINE));
        }
        this.baseMagicNumber = 0;
        this.magicNumber = this.baseMagicNumber;
        this.machineCheck = false;
        this.naturalCheck = false;
    }

    @Override
    public AbstractCard makeCopy() {
        return (AbstractCard)new Machinus();
    }
}
