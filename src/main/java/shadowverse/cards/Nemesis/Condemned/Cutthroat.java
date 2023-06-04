package shadowverse.cards.Nemesis.Condemned;


import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import com.megacrit.cardcrawl.vfx.StarBounceEffect;
import com.megacrit.cardcrawl.vfx.combat.ViolentAttackEffect;;
import shadowverse.cards.AbstractEnhanceCard;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Nemesis;
import shadowverse.powers.CutthroatPower;

import java.util.ArrayList;


public class Cutthroat
        extends AbstractEnhanceCard {
    public static final String ID = "shadowverse:Cutthroat";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Cutthroat");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/Cutthroat.png";
    private static final Texture LEADER_SKIN_VERSION = ImageMaster.loadImage("img/cards/Cutthroat_L.png");

    public Cutthroat() {
        super(ID, NAME, IMG_PATH, 0, DESCRIPTION, CardType.ATTACK, Nemesis.Enums.COLOR_SKY, CardRarity.RARE, CardTarget.ENEMY, 3, 4);
        this.baseDamage = 6;
        this.tags.add(AbstractShadowversePlayer.Enums.CONDEMNED);
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeDamage(3);
        }
    }


    @Override
    public void exEnhanceUse(AbstractPlayer p, AbstractMonster m) {
        if (!p.hasPower(CutthroatPower.POWER_ID)) {
            boolean deckCheck = true;
            ArrayList<String> tmp = new ArrayList<>();
            for (AbstractCard c : p.drawPile.group) {
                if (tmp.contains(c.cardID)) {
                    deckCheck = false;
                    break;
                }
                if (!c.hasTag(AbstractShadowversePlayer.Enums.CONDEMNED))
                    tmp.add(c.cardID);
            }
            if (deckCheck)
                addToBot(new ApplyPowerAction(p, p, new CutthroatPower(p)));
        }
        addToBot(new VFXAction(new ViolentAttackEffect(m.hb.cX, m.hb.cY, Color.SKY)));
        if ((UnlockTracker.betaCardPref.getBoolean(this.cardID, false))) {
            addToBot(new SFXAction("Cutthroat_L_EXEH"));
        }else {
            addToBot(new SFXAction("Cutthroat_EXEH"));
        }
        for (int i = 0; i < 7; i++)
            addToBot(new VFXAction(new StarBounceEffect(m.hb.cX, m.hb.cY)));
        addToBot(new DamageAction(m, new DamageInfo(p, this.damage * 7, this.damageTypeForTurn), AbstractGameAction.AttackEffect.NONE));
    }

    @Override
    public void enhanceUse(AbstractPlayer p, AbstractMonster m) {
        if (!p.hasPower(CutthroatPower.POWER_ID)) {
            boolean deckCheck = true;
            ArrayList<String> tmp = new ArrayList<>();
            for (AbstractCard c : p.drawPile.group) {
                if (tmp.contains(c.cardID)) {
                    deckCheck = false;
                    break;
                }
                if (!c.hasTag(AbstractShadowversePlayer.Enums.CONDEMNED))
                    tmp.add(c.cardID);
            }
            if (deckCheck)
                addToBot(new ApplyPowerAction(p, p, new CutthroatPower(p)));
        }
        addToBot(new VFXAction(new ViolentAttackEffect(m.hb.cX, m.hb.cY, Color.SKY)));
        if ((UnlockTracker.betaCardPref.getBoolean(this.cardID, false))) {
            addToBot(new SFXAction("Cutthroat_L_EH"));
        }else {
            addToBot(new SFXAction("Cutthroat_EH"));
        }
        for (int i = 0; i < 5; i++)
            addToBot(new VFXAction(new StarBounceEffect(m.hb.cX, m.hb.cY)));
        addToBot(new DamageAction(m, new DamageInfo(p, this.damage * 5, this.damageTypeForTurn), AbstractGameAction.AttackEffect.NONE));
    }

    @Override
    public void baseUse(AbstractPlayer p, AbstractMonster m) {
        if (!p.hasPower(CutthroatPower.POWER_ID)) {
            boolean deckCheck = true;
            ArrayList<String> tmp = new ArrayList<>();
            for (AbstractCard c : p.drawPile.group) {
                if (tmp.contains(c.cardID)) {
                    deckCheck = false;
                    break;
                }
                if (!c.hasTag(AbstractShadowversePlayer.Enums.CONDEMNED))
                    tmp.add(c.cardID);
            }
            if (deckCheck)
                addToBot(new ApplyPowerAction(p, p, new CutthroatPower(p)));
        }
        addToBot(new VFXAction(new ViolentAttackEffect(m.hb.cX, m.hb.cY, Color.SKY)));
        if ((UnlockTracker.betaCardPref.getBoolean(this.cardID, false))) {
            addToBot(new SFXAction("Cutthroat_L"));
        }else {
            addToBot(new SFXAction("Cutthroat"));
        }
        addToBot(new VFXAction(new StarBounceEffect(m.hb.cX, m.hb.cY)));
        addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.NONE));
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new Cutthroat();
    }
}

