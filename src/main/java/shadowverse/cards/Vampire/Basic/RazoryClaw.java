package shadowverse.cards.Vampire.Basic;


import basemod.abstracts.CustomCard;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.ClawEffect;
import shadowverse.characters.Vampire;


public class RazoryClaw
        extends CustomCard {
    public static final String ID = "shadowverse:RazoryClaw";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:RazoryClaw");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/RazoryClaw.png";

    public RazoryClaw() {
        super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.SKILL, Vampire.Enums.COLOR_SCARLET, CardRarity.BASIC, CardTarget.ENEMY);
        this.baseDamage = 9;
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeDamage(3);
        }
    }


    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new VFXAction(new ClawEffect(p.hb.cX, p.hb.cY, Color.SCARLET, Color.ORANGE), 0.1F));
        addToBot(new LoseHPAction(p, p, 2));
        if (m != null)
            addToBot(new VFXAction(new ClawEffect(m.hb.cX, m.hb.cY, Color.SCARLET, Color.ORANGE), 0.1F));
        addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.NONE));
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new RazoryClaw();
    }
}

