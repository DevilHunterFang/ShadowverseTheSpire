package shadowverse.cards.Witch.Spellboost1;

import basemod.abstracts.CustomCard;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ReduceCostAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.actions.watcher.SkipEnemiesTurnAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.WhirlwindEffect;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Witchcraft;
import shadowverse.powers.DimensionShiftPower;

public class DimensionShift extends CustomCard {
    public static final String ID = "shadowverse:DimensionShift";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:DimensionShift");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/DimensionShift.png";
    public static final int BASE_COST = 12;

    public DimensionShift() {
        super(ID, NAME, IMG_PATH, 12, DESCRIPTION, CardType.SKILL, Witchcraft.Enums.COLOR_BLUE, CardRarity.UNCOMMON, CardTarget.ALL);
        this.exhaust = true;
        this.selfRetain = true;
        this.tags.add(AbstractShadowversePlayer.Enums.SPELL_BOOST);
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            this.upgradeBaseCost(11);
        }
    }


    public void triggerOnOtherCardPlayed(AbstractCard c) {
        if (c.type == CardType.SKILL) {
            flash();
            addToBot((AbstractGameAction) new SFXAction("spell_boost"));
            addToBot((AbstractGameAction) new ReduceCostAction((AbstractCard) this));
        }
    }


    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot((AbstractGameAction) new SFXAction("DimensionShift"));
        int used = 0;
        for (AbstractCard c : AbstractDungeon.actionManager.cardsPlayedThisTurn) {
            if (c.cardID.equals(this.cardID)) {
                used++;
            }
        }
        if (used>1){
            addToBot((AbstractGameAction) new ApplyPowerAction(abstractPlayer, abstractPlayer, (AbstractPower) new DimensionShiftPower(abstractPlayer, 1), 1));
        }
        else{
            addToBot((AbstractGameAction) new VFXAction((AbstractGameEffect) new WhirlwindEffect(new Color(1.0F, 0.9F, 0.4F, 1.0F), true)));
            addToBot((AbstractGameAction) new SkipEnemiesTurnAction());
        }
        this.cost = 12;
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new DimensionShift();
    }
}
