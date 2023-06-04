package shadowverse.cards.Elf.Short;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.LoseStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import shadowverse.characters.Elf;

public class Rino extends CustomCard {
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Rino");
    public static final String ID = "shadowverse:Rino";
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/Rino.png";
    public Rino(int upgrades) {
        super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.ATTACK, Elf.Enums.COLOR_GREEN, CardRarity.UNCOMMON, CardTarget.ALL_ENEMY);
        this.baseDamage = 10;
        this.baseMagicNumber = 4;
        this.magicNumber = this.baseMagicNumber;
        this.timesUpgraded = upgrades;
    }

    @Override
    public void upgrade() {
        if (this.magicNumber>0){
            upgradeMagicNumber(-1);
        }
        this.timesUpgraded++;
        this.upgraded = true;
        this.name = cardStrings.NAME + "+" + this.timesUpgraded;
        initializeTitle();
    }

    @Override
    public boolean canUpgrade() {
        if (this.timesUpgraded<3){
            return true;
        }else {
            return  false;
        }
    }

    @Override
    public void atTurnStart(){
        if (this.baseMagicNumber>0){
            this.baseMagicNumber--;
            this.magicNumber = this.baseMagicNumber;
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)p, (AbstractCreature)p, (AbstractPower)new StrengthPower((AbstractCreature)p, 4), 4));
        addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)p, (AbstractCreature)p, (AbstractPower)new LoseStrengthPower((AbstractCreature)p, 4), 4));
        AbstractDungeon.actionManager.cardsPlayedThisTurn.add(this);
        AbstractDungeon.actionManager.cardsPlayedThisTurn.add(this);
        if (this.magicNumber>0){
            addToBot((AbstractGameAction)new SFXAction("Rino"));
        }else {
            addToBot((AbstractGameAction)new SFXAction("Rino_UB"));
            for (AbstractMonster mo : (AbstractDungeon.getCurrRoom()).monsters.monsters){
                if (!mo.isDeadOrEscaped()){
                    int rand = AbstractDungeon.cardRandomRng.random(99);
                    if (rand<50){
                        addToBot((AbstractGameAction)new DamageAction((AbstractCreature)mo, new DamageInfo((AbstractCreature)p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_VERTICAL));
                    }else {
                        addToBot((AbstractGameAction)new DamageAction((AbstractCreature)mo, new DamageInfo((AbstractCreature)p, this.damage*2, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_VERTICAL));
                    }
                }
            }
        }
    }

    public AbstractCard makeCopy() {
        return new Rino(this.timesUpgraded);
    }
}
