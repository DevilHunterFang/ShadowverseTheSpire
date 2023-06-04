package shadowverse.cards.Necromancer.Burial;


import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.CleaveEffect;
import shadowverse.action.BurialAction;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Necromancer;
import shadowverse.powers.MementoPower;


public class WardenOfCorpses extends CustomCard {
    public static final String ID = "shadowverse:WardenOfCorpses";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:WardenOfCorpses");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/WardenOfCorpses.png";

    public WardenOfCorpses() {
        super(ID, NAME, IMG_PATH, 2, DESCRIPTION, CardType.ATTACK, Necromancer.Enums.COLOR_PURPLE, CardRarity.UNCOMMON, CardTarget.ALL_ENEMY);
        this.baseDamage = 16;
        this.isMultiDamage = true;
        this.tags.add(AbstractShadowversePlayer.Enums.CONDEMNED);
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeDamage(6);
        }
    }


    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new BurialAction(1, new DamageAllEnemiesAction(p, this.multiDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.NONE)));
        addToBot(new VFXAction(new CleaveEffect()));
        int attackAmt = 0;
        for (AbstractCard c : p.hand.group) {
            if (c != this && c.type == CardType.ATTACK)
                attackAmt++;
        }
        if (attackAmt >= 2) {
            if (p.hasPower(MementoPower.POWER_ID)){
                addToBot(new DamageAllEnemiesAction(p, this.multiDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.NONE));
                addToBot(new VFXAction(new CleaveEffect()));
            }
        }
        addToBot(new DrawCardAction(2));
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new WardenOfCorpses();
    }
}
