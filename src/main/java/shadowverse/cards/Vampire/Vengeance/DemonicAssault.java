package shadowverse.cards.Vampire.Vengeance;


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
import shadowverse.cards.Vampire.Basic.DarkGeneral;
import shadowverse.characters.Vampire;
import shadowverse.powers.EpitaphPower;
import shadowverse.stance.Vengeance;


public class DemonicAssault
        extends CustomCard {
    public static final String ID = "shadowverse:DemonicAssault";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:DemonicAssault");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/DemonicAssault.png";
    private static final String TEXT = CardCrawlGame.languagePack.getUIString("shadowverse:Exhaust").TEXT[0];

    public DemonicAssault() {
        super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.SKILL, Vampire.Enums.COLOR_SCARLET, CardRarity.COMMON, CardTarget.ALL_ENEMY);
        this.baseDamage = 6;
        this.cardsToPreview = (AbstractCard) new DarkGeneral();
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeDamage(3);
        }
    }


    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new SFXAction("DemonicAssault"));
        addToBot(new DamageRandomEnemyAction(new DamageInfo(AbstractDungeon.player, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        if (p.stance.ID.equals(Vengeance.STANCE_ID) || p.hasPower(EpitaphPower.POWER_ID)) {
            AbstractCard tmp = this.cardsToPreview.makeStatEquivalentCopy();
            tmp.setCostForTurn(0);
            tmp.costForTurn = 0;
            tmp.isCostModified = true;
            tmp.exhaustOnUseOnce = true;
            tmp.exhaust = true;
            tmp.rawDescription += " NL " + TEXT + " 。";
            tmp.initializeDescription();
            tmp.applyPowers();
            p.hand.addToTop(tmp);
        }
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new DemonicAssault();
    }
}

