package shadowverse.cards.Vampire.Academic;


import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.Shadowverse;
import shadowverse.cards.AbstractEnhanceCard;
import shadowverse.characters.Vampire;


public class Lucius
        extends AbstractEnhanceCard {
    public static final String ID = "shadowverse:Lucius";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Lucius");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/Lucius.png";

    public Lucius() {
        super(ID, NAME, IMG_PATH, 0, DESCRIPTION, CardType.ATTACK, Vampire.Enums.COLOR_SCARLET, CardRarity.UNCOMMON, CardTarget.ENEMY,3);
        this.exhaust = true;
        this.baseDamage = 0;
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            this.exhaust = false;
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }

    public void applyPowers() {
        AbstractPlayer p = AbstractDungeon.player;
        if (Shadowverse.Enhance(3)){
            int dmg = p.maxHealth - p.currentHealth;
            int realBaseDamage = this.baseDamage;
            this.baseDamage = dmg;
            super.applyPowers();
            this.baseDamage = realBaseDamage;
        } else{
            resetAttributes();
            float percent = 1.0F-((float) p.currentHealth /(float) p.maxHealth);
            int dmg = (int) (20.0F * percent);
            int realBaseDamage = this.baseDamage;
            this.baseDamage = dmg;
            super.applyPowers();
            this.baseDamage = realBaseDamage;
        }
        this.isDamageModified = (this.damage != this.baseDamage);
    }

    public void calculateCardDamage(AbstractMonster mo) {
        AbstractPlayer p = AbstractDungeon.player;
        if (Shadowverse.Enhance(3)){
            int dmg = p.maxHealth - p.currentHealth;
            int realBaseDamage = this.baseDamage;
            this.baseDamage = dmg;
            super.applyPowers();
            this.baseDamage = realBaseDamage;
        } else{
            resetAttributes();
            float percent = 1.0F-((float) p.currentHealth /(float) p.maxHealth);
            int dmg = (int) (20.0F * percent);
            int realBaseDamage = this.baseDamage;
            this.baseDamage = dmg;
            super.applyPowers();
            this.baseDamage = realBaseDamage;
        }
        this.isDamageModified = (this.damage != this.baseDamage);
    }

    @Override
    public void exEnhanceUse(AbstractPlayer p, AbstractMonster m) {

    }

    @Override
    public void enhanceUse(AbstractPlayer p, AbstractMonster m) {
        addToBot(new SFXAction("Lucius_EH"));
        addToBot(new WaitAction(0.8F));
        calculateCardDamage(m);
        addToBot( new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HEAVY));
    }

    @Override
    public void baseUse(AbstractPlayer p, AbstractMonster m) {
        addToBot( new SFXAction("Lucius"));
        addToBot(new WaitAction(0.8F));
        calculateCardDamage(m);
        addToBot( new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HEAVY));
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new Lucius();
    }
}

