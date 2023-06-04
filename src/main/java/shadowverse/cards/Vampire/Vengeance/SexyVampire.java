package shadowverse.cards.Vampire.Vengeance;


import basemod.abstracts.CustomCard;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.stances.AbstractStance;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.BiteEffect;
import shadowverse.characters.Vampire;
import shadowverse.powers.EpitaphPower;
import shadowverse.powers.VengeanceHealthPower;
import shadowverse.stance.Vengeance;


public class SexyVampire extends CustomCard {
    public static final String ID = "shadowverse:SexyVampire";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:SexyVampire");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/SexyVampire.png";

    public SexyVampire() {
        super(ID, NAME, IMG_PATH, 3, DESCRIPTION, CardType.ATTACK, Vampire.Enums.COLOR_SCARLET, CardRarity.RARE, CardTarget.ENEMY);
        this.baseDamage = 28;
        this.baseMagicNumber = 4;
        this.magicNumber = this.baseMagicNumber;
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeDamage(10);
        }
    }

    @Override
    public void triggerWhenDrawn(){
        if (!AbstractDungeon.player.hasPower(EpitaphPower.POWER_ID)||!AbstractDungeon.player.stance.ID.equals(Vengeance.STANCE_ID)){
            int half = AbstractDungeon.player.maxHealth/2;
            int current = AbstractDungeon.player.currentHealth;
            if (!AbstractDungeon.player.hasPower(VengeanceHealthPower.POWER_ID)&&current>half){
                this.superFlash();
                addToBot((AbstractGameAction)new WaitAction(1.2F));
                AbstractDungeon.player.currentHealth = half;
                AbstractDungeon.player.update();
                AbstractDungeon.actionManager.addToBottom((AbstractGameAction) new ChangeStanceAction((AbstractStance) new Vengeance()));
                addToBot((AbstractGameAction) new ApplyPowerAction((AbstractCreature)AbstractDungeon.player, (AbstractCreature)AbstractDungeon.player, (AbstractPower) new VengeanceHealthPower((AbstractCreature) AbstractDungeon.player,half,current)));
                addToBot((AbstractGameAction)new ExhaustSpecificCardAction(this,AbstractDungeon.player.hand));
            }
        }
    }

    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot((AbstractGameAction)new SFXAction("SexyVampire"));
        addToBot((AbstractGameAction)new VFXAction((AbstractGameEffect)new BiteEffect(abstractMonster.hb.cX, abstractMonster.hb.cY - 40.0F * Settings.scale, Color.SCARLET.cpy()), 0.4F));
        addToBot((AbstractGameAction)new DamageAction((AbstractCreature)abstractMonster, new DamageInfo((AbstractCreature)abstractPlayer, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.NONE));
        addToBot((AbstractGameAction)new HealAction(abstractPlayer,abstractPlayer,this.magicNumber));
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new SexyVampire();
    }
}

