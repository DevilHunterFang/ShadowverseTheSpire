package shadowverse.cards.Royal.Ambush;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.AttackDamageRandomEnemyAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import shadowverse.action.MinionBuffAction;
import shadowverse.cards.AbstractEnhanceCard;
import shadowverse.characters.Royal;
import shadowverse.orbs.Minion;

public class BladeDance
        extends AbstractEnhanceCard {
    public static final String ID = "shadowverse:BladeDance";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:BladeDance");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/BladeDance.png";

    public BladeDance() {
        super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.SKILL, Royal.Enums.COLOR_YELLOW, CardRarity.COMMON, CardTarget.ALL_ENEMY, 2);
        this.baseDamage = 3;
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeDamage(2);
        }
    }

    @Override
    public void exEnhanceUse(AbstractPlayer p, AbstractMonster m) {

    }

    @Override
    public void enhanceUse(AbstractPlayer p, AbstractMonster m) {
        int atk = 0;
        AbstractOrb target = null;
        for (AbstractOrb o : p.orbs) {
            if (o instanceof Minion) {
                if (((Minion) o).attack > atk) {
                    atk = ((Minion) o).attack;
                    target = o;
                }
            }
        }
        if (target != null) {
            atk += 2;
            AbstractDungeon.actionManager.addToBottom(new MinionBuffAction(2, 0, (Minion) target));
            addToBot(new SFXAction("BladeDance_EH"));
        }
        if (atk > 10)
            atk = 10;
        for (int i = 0; i < atk; i++) {
            addToBot(new AttackDamageRandomEnemyAction(this, AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        }
    }

    @Override
    public void baseUse(AbstractPlayer p, AbstractMonster m) {
        int atk = 0;
        AbstractOrb target = null;
        for (AbstractOrb o : p.orbs) {
            if (o instanceof Minion) {
                if (((Minion) o).attack > atk) {
                    atk = ((Minion) o).attack;
                    target = o;
                }
            }
        }
        addToBot(new SFXAction("BladeDance"));
        if (atk > 10)
            atk = 10;
        for (int i = 0; i < atk; i++) {
            addToBot(new AttackDamageRandomEnemyAction(this, AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        }
    }


    public AbstractCard makeCopy() {
        return new BladeDance();
    }
}


