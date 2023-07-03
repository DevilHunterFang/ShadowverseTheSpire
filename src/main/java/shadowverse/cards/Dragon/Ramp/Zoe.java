package shadowverse.cards.Dragon.Ramp;


import basemod.abstracts.CustomCard;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.vfx.BorderFlashEffect;
import com.megacrit.cardcrawl.vfx.combat.ShockWaveEffect;
import shadowverse.Shadowverse;
import shadowverse.cards.Neutral.Temp.Zoe_Copy;
import shadowverse.characters.Dragon;
import shadowverse.powers.ZoePower;


public class Zoe
        extends CustomCard {
    public static final String ID = "shadowverse:Zoe";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Zoe");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/Zoe.png";

    public Zoe() {
        super(ID, NAME, IMG_PATH, 6, DESCRIPTION, CardType.ATTACK, Dragon.Enums.COLOR_BROWN, CardRarity.RARE, CardTarget.ENEMY);
        this.baseDamage = 54;
        this.cardsToPreview = new Zoe_Copy();
        this.exhaust = true;
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeDamage(10);
            this.cardsToPreview.upgrade();
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }

    @Override
    public void update() {
        if (AbstractDungeon.currMapNode != null && (AbstractDungeon.getCurrRoom()).phase == AbstractRoom.RoomPhase.COMBAT&&
                Shadowverse.Accelerate(this)){
            setCostForTurn(0);
            this.type = CardType.SKILL;
        }else {
            if (this.type==CardType.SKILL){
                setCostForTurn(6);
                this.type = CardType.ATTACK;
            }
        }
        super.update();
    }

    public void triggerOnGlowCheck() {
        if (Shadowverse.Accelerate(this)) {
            this.glowColor = AbstractCard.GREEN_BORDER_GLOW_COLOR.cpy();
        } else {
            this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        }
    }

    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        if (Shadowverse.Accelerate(this) && this.type == CardType.SKILL) {
            addToBot(new MakeTempCardInDiscardAction(this.cardsToPreview.makeStatEquivalentCopy(), 1));
            addToBot(new DrawCardAction(1));
        }else {
            addToBot(new SFXAction("Zoe"));
            addToBot(new VFXAction(new BorderFlashEffect(Color.BLUE, true),0.7f));
            addToBot(new VFXAction(new ShockWaveEffect(abstractPlayer.hb.cX, abstractPlayer.hb.cY, Color.WHITE, ShockWaveEffect.ShockWaveType.CHAOTIC)));
            addToBot(new LoseHPAction(abstractPlayer, abstractPlayer, abstractPlayer.currentHealth - 1));
            addToBot(new ApplyPowerAction(abstractPlayer, abstractPlayer, new ZoePower(abstractPlayer, abstractPlayer.currentHealth - 1), abstractPlayer.currentHealth - 1));
            addToBot(new DamageAction(abstractMonster, new DamageInfo(abstractPlayer, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
        }
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new Zoe();
    }
}

