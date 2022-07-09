package shadowverse.cards.Rare;

import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsInHandAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.unique.ExpertiseAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.CleaveEffect;
import shadowverse.action.FusionAction2;
import shadowverse.cards.AbstractRightClickCard2;
import shadowverse.characters.Elf;
import shadowverse.characters.Vampire;

public class Baal
        extends AbstractRightClickCard2 {
    public static final String ID = "shadowverse:Baal";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Baal");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/Baal.png";
    private boolean hasFusion = false;



    public Baal() {
        super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.ATTACK, Vampire.Enums.COLOR_SCARLET, CardRarity.RARE, CardTarget.ALL_ENEMY);
        this.baseMagicNumber = 0;
        this.magicNumber = this.baseMagicNumber;
        this.baseDamage = 18;
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeDamage(6);
        }
    }

    @Override
    protected void onRightClick() {
            if (!this.hasFusion && AbstractDungeon.player!=null){
                addToBot((AbstractGameAction)new SelectCardsInHandAction(9,TEXT[0],true,true, card -> {
                    return card.type==CardType.ATTACK&&card.color== Vampire.Enums.COLOR_SCARLET&&card.cost<2&&card!=this;
                }, abstractCards -> {
                    for (AbstractCard c:abstractCards){
                        this.magicNumber++;
                        this.applyPowers();
                        addToBot((AbstractGameAction)new ExhaustSpecificCardAction(c,AbstractDungeon.player.hand));
                    }
                }));
            this.hasFusion = true;
        }
    }


    @Override
    public void atTurnStart(){
        hasFusion = false;
    }

    public void applyPowers() {
        super.applyPowers();
        this.baseMagicNumber = this.magicNumber;
        this.magicNumber = this.baseMagicNumber;
        int count = this.magicNumber;
        this.rawDescription = cardStrings.DESCRIPTION;
        this.rawDescription += cardStrings.EXTENDED_DESCRIPTION[0] + count;
        this.rawDescription += cardStrings.EXTENDED_DESCRIPTION[1];
        initializeDescription();
    }


    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot((AbstractGameAction)new SFXAction("Baal"));
        if (this.magicNumber>=2){
            addToBot((AbstractGameAction)new ExpertiseAction((AbstractCreature)abstractPlayer, 6));
        }
        if (this.magicNumber>=5){
            addToBot((AbstractGameAction)new VFXAction((AbstractCreature)abstractPlayer, (AbstractGameEffect)new CleaveEffect(), 0.4F));
            addToBot((AbstractGameAction)new DamageAllEnemiesAction((AbstractCreature)abstractPlayer, DamageInfo.createDamageMatrix(this.damage, true), DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.FIRE, true));
        }
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new Baal();
    }
}

