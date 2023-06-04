package shadowverse.cards.Bishop.Amulet2;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.unique.ArmamentsAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import shadowverse.action.ReduceCountDownAction;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Bishop;
import shadowverse.orbs.AmuletOrb;

public class Saren extends CustomCard {
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Saren");
    public static final String ID = "shadowverse:Saren";
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/Saren.png";
    public Saren(int upgrades) {
        super(ID, NAME, IMG_PATH, 2, DESCRIPTION, CardType.ATTACK, Bishop.Enums.COLOR_WHITE, CardRarity.RARE, CardTarget.ENEMY);
        this.baseDamage = 12;
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
        addToBot((AbstractGameAction)new DamageAction((AbstractCreature)m, new DamageInfo((AbstractCreature)p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HEAVY));
        addToBot((AbstractGameAction)new ReduceCountDownAction(3));
        int count = 0;
        for (AbstractOrb o:p.orbs){
            if (o instanceof AmuletOrb){
                if (!((AmuletOrb) o).amulet.hasTag(AbstractShadowversePlayer.Enums.MINION))
                    count++;
            }
        }
        if (count>=2){
            addToBot((AbstractGameAction)new ArmamentsAction(true));
        }
        if (this.magicNumber>0){
            addToBot((AbstractGameAction)new SFXAction("Saren"));
        }else {
            addToBot((AbstractGameAction)new SFXAction("Saren_UB"));
            if (p.isBloodied){
                addToBot((AbstractGameAction)new DamageAllEnemiesAction((AbstractCreature)AbstractDungeon.player, DamageInfo.createDamageMatrix(this.damage*2, true), DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.FIRE, true));
            }else {
                addToBot((AbstractGameAction)new DamageAllEnemiesAction((AbstractCreature)AbstractDungeon.player, DamageInfo.createDamageMatrix(this.damage, true), DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.FIRE, true));
            }
        }
    }

    public AbstractCard makeCopy() {
        return new Saren(this.timesUpgraded);
    }
}
