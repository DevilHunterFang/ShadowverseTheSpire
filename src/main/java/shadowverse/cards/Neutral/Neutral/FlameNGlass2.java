package shadowverse.cards.Neutral.Neutral;


import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsInHandAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.*;
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
import com.megacrit.cardcrawl.vfx.combat.BlizzardEffect;
import shadowverse.cards.AbstractRightClickCard;

import java.util.ArrayList;


public class FlameNGlass2 extends AbstractRightClickCard {
    public static final String ID = "shadowverse:FlameNGlass2";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:FlameNGlass2");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/FlameNGlass2.png";
    private boolean hasFusion = false;
    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("ExhaustAction");
    public static final String[] TEXT = uiStrings.TEXT;
    private ArrayList<String> fusionBox = new ArrayList<>();

    public FlameNGlass2() {
        super(ID, NAME, IMG_PATH, 3, DESCRIPTION, CardType.ATTACK, CardColor.COLORLESS, CardRarity.RARE, CardTarget.ENEMY);
        this.baseDamage = 42;
        this.baseMagicNumber = 2;
        this.magicNumber = this.baseMagicNumber;
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeDamage(10);
            upgradeMagicNumber(1);
        }
    }


    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot((AbstractGameAction) new SFXAction("FlameNGlass2"));
        addToBot((AbstractGameAction) new VFXAction((AbstractGameEffect) new BlizzardEffect(2, AbstractDungeon.getMonsters().shouldFlipVfx()), 1.0F));
        addToBot((AbstractGameAction) new DamageAction((AbstractCreature) abstractMonster, new DamageInfo((AbstractCreature) abstractPlayer, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.FIRE));
        if (this.fusionBox.size()>1){
            addToBot(new GainEnergyAction(3));
        }
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new FlameNGlass2();
    }

    @Override
    protected void onRightClick() {
        if (!this.hasFusion && AbstractDungeon.player!=null){
            addToBot((AbstractGameAction)new SelectCardsInHandAction(8,TEXT[0],true,true, card -> {
                return card instanceof Flame || card instanceof Glass;
            }, abstractCards -> {
                for (AbstractCard c:abstractCards){
                    addToBot((AbstractGameAction) new ExhaustSpecificCardAction(c,AbstractDungeon.player.hand));
                    if (!this.fusionBox.contains(c.cardID))
                        fusionBox.add(c.cardID);
                }
                if (abstractCards.size()>0){
                    for (AbstractMonster mo : (AbstractDungeon.getCurrRoom()).monsters.monsters) {
                        addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)mo, (AbstractCreature)AbstractDungeon.player, (AbstractPower)new WeakPower((AbstractCreature)mo, this.magicNumber, false), this.magicNumber, true, AbstractGameAction.AttackEffect.NONE));
                        addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)mo, (AbstractCreature)AbstractDungeon.player, (AbstractPower)new VulnerablePower((AbstractCreature)mo, this.magicNumber, false), this.magicNumber, true, AbstractGameAction.AttackEffect.NONE));
                    }
                }
            }));
            this.hasFusion = true;
        }
    }

    @Override
    public void atTurnStart(){
        hasFusion = false;
    }
}

