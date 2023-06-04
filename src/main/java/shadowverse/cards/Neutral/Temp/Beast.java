package shadowverse.cards.Neutral.Temp;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageRandomEnemyAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import shadowverse.characters.Elf;

public class Beast extends CustomCard {
    public static final String ID = "shadowverse:Beast";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Beast");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/Beast.png";

    public Beast() {
        super(ID, NAME, IMG_PATH, -2, DESCRIPTION, CardType.ATTACK, Elf.Enums.COLOR_GREEN, CardRarity.SPECIAL, CardTarget.ALL_ENEMY);
        this.baseDamage = 10;
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeDamage(3);
        }
    }



    public void use(AbstractPlayer p, AbstractMonster m) {
        onChoseThisOption();
    }

    public void onChoseThisOption() {
        addToBot((AbstractGameAction)new SFXAction("Beast"));
        int count = 0;
        for (AbstractCard c : AbstractDungeon.actionManager.cardsPlayedThisTurn){
            count++;
        }
        int sAmt = 0;
        for (AbstractPower p :AbstractDungeon.player.powers){
            if (p instanceof StrengthPower){
                sAmt = p.amount;
                break;
            }
        }
        if (count > 2){
            addToBot((AbstractGameAction)new DamageRandomEnemyAction(new DamageInfo(AbstractDungeon.player, (this.baseDamage+sAmt)*2, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HEAVY));
        }else {
            addToBot((AbstractGameAction)new DamageRandomEnemyAction(new DamageInfo(AbstractDungeon.player, this.baseDamage+sAmt, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HEAVY));
        }
    }

    public AbstractCard makeCopy() {
        return new Beast();
    }
}
