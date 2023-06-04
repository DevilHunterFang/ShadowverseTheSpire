package shadowverse.cards.Bishop.Amulet2;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageRandomEnemyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.action.ReduceAllCountDownAction;
import shadowverse.characters.Bishop;

public class GodLovingSmite extends CustomCard {
    public static final String ID = "shadowverse:GodLovingSmite";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:GodLovingSmite");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/GodLovingSmite.png";

    public GodLovingSmite() {
        super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.SKILL, Bishop.Enums.COLOR_WHITE, CardRarity.COMMON, CardTarget.ALL_ENEMY);
        this.baseDamage = 9;
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeDamage(3);
        }
    }



    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot((AbstractGameAction)new DamageRandomEnemyAction(new DamageInfo(AbstractDungeon.player, this.baseDamage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.POISON));
        addToBot((AbstractGameAction)new ReduceAllCountDownAction(1));
    }


    public AbstractCard makeCopy() {
        return new GodLovingSmite();
    }
}
