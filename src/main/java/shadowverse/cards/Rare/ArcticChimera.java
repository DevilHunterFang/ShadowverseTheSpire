package shadowverse.cards.Rare;

import basemod.abstracts.CustomCard;
import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.mod.stslib.actions.defect.EvokeSpecificOrbAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.BiteEffect;
import com.megacrit.cardcrawl.vfx.combat.ClawEffect;
import com.megacrit.cardcrawl.vfx.combat.CleaveEffect;
import shadowverse.Shadowverse;
import shadowverse.cards.Temp.NaterranGreatTree;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Witchcraft;
import shadowverse.orbs.AmuletOrb;
import shadowverse.powers.EarthEssence;

public class ArcticChimera
        extends CustomCard {
    public static final String ID = "shadowverse:ArcticChimera";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:ArcticChimera");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/ArcticChimera.png";

    public ArcticChimera() {
        super(ID, NAME, IMG_PATH, 2, DESCRIPTION, CardType.ATTACK, Witchcraft.Enums.COLOR_BLUE, CardRarity.RARE, CardTarget.ENEMY);
        this.baseBlock = 8;
        this.baseDamage = 6;
        this.tags.add(AbstractShadowversePlayer.Enums.EARTH_RITE);
        this.tags.add(AbstractShadowversePlayer.Enums.ACCELERATE);
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeDamage(3);
            upgradeBlock(2);
        }
    }

    @Override
    public void update() {
        if (AbstractDungeon.currMapNode != null && (AbstractDungeon.getCurrRoom()).phase == AbstractRoom.RoomPhase.COMBAT &&
                Shadowverse.Accelerate(this)) {
            setCostForTurn(0);
            this.type = CardType.SKILL;
        } else {
            if (this.type == CardType.SKILL) {
                setCostForTurn(2);
                this.type = CardType.ATTACK;
            }
        }
        super.update();
    }

    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        AbstractPower p = abstractPlayer.getPower(EarthEssence.POWER_ID);
        if (Shadowverse.Accelerate((AbstractCard) this) && this.type == CardType.SKILL) {
            if (p!=null&&p.amount>0) {
                if (abstractMonster!=null)
                    addToBot((AbstractGameAction)new VFXAction((AbstractGameEffect)new ClawEffect(abstractMonster.hb.cX, abstractMonster.hb.cY, Color.BLUE, Color.WHITE), 0.1F));
                addToBot((AbstractGameAction) new DamageAction((AbstractCreature) abstractMonster, new DamageInfo((AbstractCreature) abstractPlayer, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.NONE));
                addToBot((AbstractGameAction) new ReducePowerAction(abstractPlayer, abstractPlayer, abstractPlayer.getPower(EarthEssence.POWER_ID), 1));
                addToBot((AbstractGameAction) new DrawCardAction(1));
            }
        }else {
            addToBot((AbstractGameAction)new GainBlockAction(abstractPlayer,this.block));
            addToBot((AbstractGameAction)new GainBlockAction(abstractPlayer,this.block));
            if (p!=null&&p.amount>2) {
                addToBot((AbstractGameAction)new GainBlockAction(abstractPlayer,this.block));
                addToBot((AbstractGameAction)new VFXAction((AbstractGameEffect)new BiteEffect(abstractMonster.hb.cX, abstractMonster.hb.cY - 40.0F * Settings.scale, Color.WHITE.cpy()), 0.2F));
                addToBot((AbstractGameAction)new DamageAction((AbstractCreature) abstractMonster, new DamageInfo((AbstractCreature) abstractPlayer, abstractPlayer.currentBlock+this.block*3, this.damageTypeForTurn), AbstractGameAction.AttackEffect.NONE));
                addToBot((AbstractGameAction)new ReducePowerAction(abstractPlayer, abstractPlayer, abstractPlayer.getPower(EarthEssence.POWER_ID), 3));
            }else {
                addToBot((AbstractGameAction)new ApplyPowerAction(abstractMonster,abstractPlayer,new VulnerablePower(abstractMonster,2,false),2));
                addToBot((AbstractGameAction)new ApplyPowerAction(abstractPlayer,abstractPlayer,new EarthEssence(abstractPlayer,2),2));
            }
        }
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new ArcticChimera();
    }
}

