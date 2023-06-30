package shadowverse.cards.Neutral.Curse;

import basemod.abstracts.CustomCard;
import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.mod.stslib.powers.abstracts.TwoAmountPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.ClawEffect;
import com.megacrit.cardcrawl.vfx.combat.WeightyImpactEffect;
import shadowverse.powers.OverflowPower;

public class CurseOfTheBlackDragon extends CustomCard {
    public static final String ID = "shadowverse:CurseOfTheBlackDragon";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:CurseOfTheBlackDragon");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/CurseOfTheBlackDragon.png";

    public CurseOfTheBlackDragon() {
        super(ID, NAME, IMG_PATH, -2, DESCRIPTION, CardType.CURSE, CardColor.CURSE, CardRarity.SPECIAL, CardTarget.NONE);
        this.baseDamage = 8;
        this.selfRetain = true;
    }

    @Override
    public void upgrade() {
    }

    @Override
    public void onRetained() {
        boolean overflow = false;
        if (AbstractDungeon.player.hasPower(OverflowPower.POWER_ID)) {
            TwoAmountPower p = (TwoAmountPower) AbstractDungeon.player.getPower(OverflowPower.POWER_ID);
            if (p.amount2 > 0) {
                overflow = true;
            }
        }
        if (overflow) {
            AbstractCreature m = AbstractDungeon.getMonsters().getRandomMonster(null, true, AbstractDungeon.cardRandomRng);
            if (m != null){
                addToBot(new VFXAction(new ClawEffect(m.hb.cX, m.hb.cY, Color.SCARLET, Color.PURPLE), 0.1F));
                addToBot(new DamageAction(m,new DamageInfo(AbstractDungeon.player,this.damage,this.damageTypeForTurn), AbstractGameAction.AttackEffect.NONE));
            }
        } else {
            addToBot(new LoseHPAction(AbstractDungeon.player, AbstractDungeon.player, 1));
        }
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
    }

    @Override
    public AbstractCard makeCopy() {
        return new CurseOfTheBlackDragon();
    }
}
