package shadowverse.cards.Neutral.Temp;


import basemod.abstracts.CustomCard;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.vfx.BorderFlashEffect;
import com.megacrit.cardcrawl.vfx.combat.ShockWaveEffect;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Dragon;
import shadowverse.characters.Vampire;
import shadowverse.powers.AbyssDoomLordPower;
import shadowverse.powers.ZoePower;


public class Zoe_Copy
        extends CustomCard {
    public static final String ID = "shadowverse:Zoe_Copy";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Zoe_Copy");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/Zoe.png";

    public Zoe_Copy() {
        super(ID, NAME, IMG_PATH, 5, DESCRIPTION, CardType.ATTACK, Dragon.Enums.COLOR_BROWN, CardRarity.SPECIAL, CardTarget.ENEMY);
        this.baseDamage = 48;
        this.exhaust = true;
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeDamage(10);
        }
    }

    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new SFXAction("Zoe"));
        addToBot(new VFXAction(new BorderFlashEffect(Color.BLUE, true),0.7f));
        addToBot(new VFXAction(new ShockWaveEffect(abstractPlayer.hb.cX, abstractPlayer.hb.cY, Color.WHITE, ShockWaveEffect.ShockWaveType.CHAOTIC)));
        addToBot(new LoseHPAction(abstractPlayer, abstractPlayer, abstractPlayer.currentHealth - 1));
        addToBot(new ApplyPowerAction(abstractPlayer, abstractPlayer, new ZoePower(abstractPlayer, abstractPlayer.currentHealth - 1), abstractPlayer.currentHealth - 1));
        addToBot(new DamageAction(abstractMonster, new DamageInfo(abstractPlayer, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new Zoe_Copy();
    }
}

