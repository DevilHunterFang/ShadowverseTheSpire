package shadowverse.cards.Necromancer.Mech;


import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.action.BurialAction;
import shadowverse.characters.Necromancer;
import shadowverse.powers.MementoPower;


public class HungrySlash extends CustomCard {
    public static final String ID = "shadowverse:HungrySlash";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:HungrySlash");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/HungrySlash.png";

    public HungrySlash() {
        super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.SKILL, Necromancer.Enums.COLOR_PURPLE, CardRarity.COMMON, CardTarget.ENEMY);
        this.baseDamage = 8;
        this.baseMagicNumber = 1;
        this.magicNumber = this.baseMagicNumber;
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeDamage(4);
        }
    }


    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new DamageAction(abstractMonster, new DamageInfo(abstractPlayer, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_VERTICAL));
        addToBot( new BurialAction(1, new DrawCardAction(this.magicNumber)));
        int attackAmt = 0;
        for (AbstractCard c : abstractPlayer.hand.group) {
            if (c != this && c.type == CardType.ATTACK)
                attackAmt++;
        }
        if (attackAmt >= 1) {
            if (abstractPlayer.hasPower(MementoPower.POWER_ID)) {
                addToBot(new DrawCardAction(this.magicNumber));
            }
        }
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new HungrySlash();
    }
}
