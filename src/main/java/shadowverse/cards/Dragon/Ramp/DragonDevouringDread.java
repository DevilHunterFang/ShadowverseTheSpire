package shadowverse.cards.Dragon.Ramp;


import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsInHandAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.vfx.combat.BiteEffect;
import shadowverse.cards.AbstractRightClickCard2;
import shadowverse.characters.Dragon;
import shadowverse.characters.Vampire;
import shadowverse.powers.OverflowPower;


public class DragonDevouringDread extends AbstractRightClickCard2 {
    public static final String ID = "shadowverse:DragonDevouringDread";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:DragonDevouringDread");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/DragonDevouringDread.png";
    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("ExhaustAction");
    public static final String[] TEXT = uiStrings.TEXT;
    private boolean hasFusion = false;

    public DragonDevouringDread() {
        super(ID, NAME, IMG_PATH, 5, DESCRIPTION, CardType.ATTACK, Dragon.Enums.COLOR_BROWN, CardRarity.RARE, CardTarget.ALL_ENEMY);
        this.baseMagicNumber = 1;
        this.magicNumber = this.baseMagicNumber;
        this.exhaust = true;
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            this.selfRetain = true;
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }

    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        for (AbstractCard c : abstractPlayer.hand.group){
            if (c.type == CardType.CURSE || c.type == CardType.STATUS){
                addToBot(new ExhaustSpecificCardAction(c,abstractPlayer.hand));
            }
        }
        for (AbstractMonster mo : (AbstractDungeon.getCurrRoom()).monsters.monsters) {
            if (!mo.isDeadOrEscaped()){
                addToBot(new VFXAction(new BiteEffect(mo.hb.cX, mo.hb.cY - 40.0F * Settings.scale, Color.DARK_GRAY.cpy()), 0.2F));
                addToBot(new ApplyPowerAction(mo, abstractPlayer, new WeakPower(mo, this.magicNumber, false), this.magicNumber, true, AbstractGameAction.AttackEffect.NONE));
                addToBot(new ApplyPowerAction(mo, abstractPlayer, new VulnerablePower(mo, this.magicNumber, false), this.magicNumber, true, AbstractGameAction.AttackEffect.NONE));
            }
        }
        addToBot(new GainEnergyAction(this.magicNumber));
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new DragonDevouringDread();
    }

    @Override
    public void atTurnStart(){
        hasFusion = false;
    }

    public void applyPowers() {
        super.applyPowers();
        this.baseMagicNumber = this.magicNumber;
        this.magicNumber = this.baseMagicNumber;
    }

    @Override
    protected void onRightClick() {
        if (!this.hasFusion && AbstractDungeon.player!=null){
            addToBot(new SelectCardsInHandAction(9,TEXT[0],true,true, card -> {
                return CardLibrary.getCard(card.cardID).type==CardType.ATTACK&&CardLibrary.getCard(card.cardID).cost > 2&&card!=this;
            }, abstractCards -> {
                for (AbstractCard c:abstractCards){
                    addToBot(new ExhaustSpecificCardAction(c,AbstractDungeon.player.hand));
                }
                if (abstractCards.size()>0){
                    addToBot(new GainEnergyAction(1));
                    this.magicNumber++;
                    this.applyPowers();
                }
                if (this.magicNumber == 3){
                    addToBot(new ApplyPowerAction(AbstractDungeon.player,AbstractDungeon.player,new OverflowPower(AbstractDungeon.player,1)));
                }
                if (this.magicNumber == 4){
                    addToBot(new DrawCardAction(2));
                }
                this.applyPowers();
            }));
            this.hasFusion = true;
        }
    }
}

