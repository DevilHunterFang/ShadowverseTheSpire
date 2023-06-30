package shadowverse.cards.Vampire.Self;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.unique.ArmamentsAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.ShockWaveEffect;
import shadowverse.characters.Vampire;
import shadowverse.powers.EpitaphPower;
import shadowverse.powers.WrathPower;


public class TheBerserker
        extends CustomCard {
    public static final String ID = "shadowverse:TheBerserker";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:TheBerserker");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/TheBerserker.png";

    public TheBerserker() {
        super(ID, NAME, IMG_PATH, 2, DESCRIPTION, CardType.ATTACK, Vampire.Enums.COLOR_SCARLET, CardRarity.RARE, CardTarget.ENEMY);
        this.baseDamage = 12;
        this.baseBlock = 9;
        this.baseMagicNumber = 3;
        this.magicNumber = this.baseMagicNumber;
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeDamage(4);
            upgradeBlock(3);
        }
    }


    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot((AbstractGameAction)new GainBlockAction(abstractPlayer,this.block));
        addToBot((AbstractGameAction)new LoseHPAction(abstractPlayer,abstractPlayer,1));
        addToBot((AbstractGameAction)new HealAction(abstractPlayer,abstractPlayer,this.magicNumber));
        if (abstractPlayer.hasPower(EpitaphPower.POWER_ID)||abstractPlayer.hasPower(WrathPower.POWER_ID)){
            addToBot((AbstractGameAction)new ArmamentsAction(true));
        }
        if (EnergyPanel.getCurrentEnergy()-this.costForTurn>=2){
            addToBot((AbstractGameAction) new SFXAction("TheBerserker2"));
            addToBot((AbstractGameAction)new VFXAction((AbstractCreature)abstractPlayer, (AbstractGameEffect)new ShockWaveEffect(abstractPlayer.hb.cX, abstractPlayer.hb.cY, Settings.GREEN_TEXT_COLOR, ShockWaveEffect.ShockWaveType.CHAOTIC), 0.2F));
            addToBot((AbstractGameAction)new DamageAllEnemiesAction((AbstractCreature)abstractPlayer, DamageInfo.createDamageMatrix(this.damage*2, true), this.damageTypeForTurn, AbstractGameAction.AttackEffect.SMASH, false));
        }else {
            addToBot((AbstractGameAction) new SFXAction("TheBerserker"));
            addToBot((AbstractGameAction) new DamageAction((AbstractCreature) abstractMonster, new DamageInfo((AbstractCreature) abstractPlayer, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
        }

    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new TheBerserker();
    }
}


