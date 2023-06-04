package shadowverse.cards.Neutral.Temp;

import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsInHandAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.cards.AbstractRightClickCard2;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Royal;

public class LightOfHollow
        extends AbstractRightClickCard2 {
    public static final String ID = "shadowverse:LightOfHollow";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:LightOfHollow");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/LightOfHollow.png";
    private boolean hasFusion = false;
    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("ExhaustAction");
    public static final String[] TEXT = uiStrings.TEXT;



    public LightOfHollow() {
        super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.SKILL, Royal.Enums.COLOR_YELLOW, CardRarity.SPECIAL, CardTarget.ALL_ENEMY);
        this.baseDamage = 28;
        this.baseMagicNumber = 0;
        this.magicNumber = this.baseMagicNumber;
        this.selfRetain = true;
        this.tags.add(AbstractShadowversePlayer.Enums.GILDED);
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeDamage(4);
        }
    }

    @Override
    protected void onRightClick() {
        if (!this.hasFusion && AbstractDungeon.player!=null){
            addToBot(new SelectCardsInHandAction(9,TEXT[0],true,true,card -> {
                return card.hasTag(AbstractShadowversePlayer.Enums.GILDED) && card!=this;
            }, abstractCards -> {
                if (abstractCards.size()>0){
                    addToBot(new DrawCardAction(1));
                }
                for (AbstractCard c:abstractCards){
                    addToBot( new ExhaustSpecificCardAction(c,AbstractDungeon.player.hand));
                    this.baseMagicNumber++;
                    this.magicNumber = this.baseMagicNumber;
                    applyPowers();
                }
            }));
            this.hasFusion = true;
        }
    }

    public void applyPowers() {
        super.applyPowers();
        int count = this.magicNumber;
        this.rawDescription = cardStrings.DESCRIPTION;
        this.rawDescription += cardStrings.EXTENDED_DESCRIPTION[0] + count;
        this.rawDescription += cardStrings.EXTENDED_DESCRIPTION[1];
        initializeDescription();
    }


    @Override
    public void atTurnStart(){
        hasFusion = false;
    }



    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new SFXAction("LightOfHollow"));
        int dmg = this.damage;
        if (this.magicNumber>=4){
            dmg = dmg*2;
            this.baseMagicNumber = 0;
            this.magicNumber = this.baseMagicNumber;
        }
        int leftDamage = dmg;
        AbstractMonster weakestMonster = null;
        int amountOfweakestMonster = 0;
        while (leftDamage > 0) {
            for (AbstractMonster m : (AbstractDungeon.getMonsters()).monsters) {
                if (!m.isDead || !m.isDeadOrEscaped()) {
                    if (weakestMonster == null) {
                        weakestMonster = m;
                        continue;
                    }
                    if (m.currentHealth < weakestMonster.currentHealth) {
                        weakestMonster = m;
                    }
                }
            }
            amountOfweakestMonster = weakestMonster.currentHealth + weakestMonster.currentBlock;
            if (leftDamage < amountOfweakestMonster) {
                addToBot(new DamageAction(weakestMonster, new DamageInfo(abstractPlayer, leftDamage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.FIRE));
                leftDamage = 0;
                continue;
            } else if (leftDamage >= amountOfweakestMonster) {
                int count = 0;
                for (AbstractMonster m2 : (AbstractDungeon.getMonsters()).monsters) {
                    if (!m2.isDeadOrEscaped()) {
                        count++;
                    }
                }
                if (count <= 1) {
                    addToBot(new DamageAction(weakestMonster, new DamageInfo(abstractPlayer, leftDamage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.FIRE));
                    leftDamage = 0;
                    continue;
                }
                if (!weakestMonster.isDead || !weakestMonster.isDeadOrEscaped()) {
                    addToBot(new DamageAction(weakestMonster, new DamageInfo(abstractPlayer, amountOfweakestMonster, this.damageTypeForTurn), AbstractGameAction.AttackEffect.FIRE));
                    leftDamage -= amountOfweakestMonster;
                    (AbstractDungeon.getMonsters()).monsters.remove(weakestMonster);
                    weakestMonster = null;
                }
            }
        }
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new LightOfHollow();
    }
}

