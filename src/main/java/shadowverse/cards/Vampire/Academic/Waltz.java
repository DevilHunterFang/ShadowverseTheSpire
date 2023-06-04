package shadowverse.cards.Vampire.Academic;

import basemod.abstracts.CustomCard;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DrawCardNextTurnPower;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.ClawEffect;
import shadowverse.Shadowverse;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Vampire;
import shadowverse.powers.ExitVengeancePower;
import shadowverse.stance.Vengeance;

public class Waltz
        extends CustomCard {
    public static final String ID = "shadowverse:Waltz";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Waltz");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/Waltz.png";
    public static final String IMG_PATH_EV = "img/cards/Waltz_Ev.png";

    public Waltz() {
        super(ID, NAME, IMG_PATH, 2, DESCRIPTION, CardType.ATTACK, Vampire.Enums.COLOR_SCARLET, CardRarity.RARE, CardTarget.ENEMY);
        this.baseDamage = 6;
        this.tags.add(AbstractShadowversePlayer.Enums.ACCELERATE);
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeDamage(5);
            this.textureImg = IMG_PATH_EV;
            this.loadCardImage(IMG_PATH_EV);
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }

    @Override
    public void update() {
        if (AbstractDungeon.currMapNode != null && (AbstractDungeon.getCurrRoom()).phase == AbstractRoom.RoomPhase.COMBAT &&
                Shadowverse.Accelerate(this)) {
            setCostForTurn(1);
            this.type = CardType.SKILL;
        } else {
            if (this.type == CardType.SKILL) {
                setCostForTurn(2);
                this.type = CardType.ATTACK;
            }
        }
        super.update();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (Shadowverse.Accelerate((AbstractCard) this) && this.type == CardType.SKILL) {
            addToBot(new SFXAction("Waltz_Acc"));
            addToBot(new ApplyPowerAction(p, p, new DrawCardNextTurnPower(p, 1), 1));
            addToBot(new ChangeStanceAction(new Vengeance()));
            addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new ExitVengeancePower(AbstractDungeon.player, 2)));
        } else {
            addToBot(new SFXAction("Waltz"));
            addToBot(new ChangeStanceAction(new Vengeance()));
            addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new ExitVengeancePower(AbstractDungeon.player, 999)));
            addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.NONE));
            if (m != null)
                addToBot((AbstractGameAction) new VFXAction((AbstractGameEffect) new ClawEffect(m.hb.cX, m.hb.cY, Color.SCARLET, Color.RED), 0.2F));
            if (this.upgraded) {
                addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.NONE));
                if (m != null)
                    addToBot((AbstractGameAction) new VFXAction((AbstractGameEffect) new ClawEffect(m.hb.cX, m.hb.cY, Color.SCARLET, Color.RED), 0.2F));
                addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.NONE));
                if (m != null)
                    addToBot((AbstractGameAction) new VFXAction((AbstractGameEffect) new ClawEffect(m.hb.cX, m.hb.cY, Color.SCARLET, Color.RED), 0.2F));
            }
        }
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new Waltz();
    }
}

