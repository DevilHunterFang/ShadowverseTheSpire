package shadowverse.cards.Neutral.Temp;


import basemod.abstracts.CustomCard;
import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.cardmods.UnseenStrengthMod;
import shadowverse.characters.Nemesis;
import shadowverse.powers.OrchidPower;


public class Puppet
        extends CustomCard {
    public static final String ID = "shadowverse:Puppet";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Puppet");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/Puppet.png";

    public Puppet() {
        super(ID, NAME, IMG_PATH, 0, DESCRIPTION, CardType.ATTACK, Nemesis.Enums.COLOR_SKY, CardRarity.SPECIAL, CardTarget.ENEMY);
        this.baseDamage = 5;
        this.exhaust = true;
        this.isEthereal = true;
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeDamage(2);
        }
    }

    public void applyPowers() {
        if (AbstractDungeon.player.hasPower(OrchidPower.POWER_ID)) {
            int realBaseDamage = this.baseDamage;
            this.baseDamage = this.baseDamage * 2;
            super.applyPowers();
            this.baseDamage = realBaseDamage;
            this.isDamageModified = (this.damage != this.baseDamage);
        } else {
            super.applyPowers();
        }
    }

    public void calculateCardDamage(AbstractMonster mo) {
        if(AbstractDungeon.player.hasPower(OrchidPower.POWER_ID)){
            int realBaseDamage = this.baseDamage;
            this.baseDamage = this.baseDamage * 2;
            super.calculateCardDamage(mo);
            this.baseDamage = realBaseDamage;
            this.isDamageModified = (this.damage != this.baseDamage);
        }else{
            super.calculateCardDamage(mo);
        }
    }

    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        if (abstractPlayer.hasPower(OrchidPower.POWER_ID)) {
            addToBot(new SFXAction("Orchid_EW_Eff"));
        }
        addToBot(new DamageAction(abstractMonster, new DamageInfo(abstractPlayer, this.damage, CardModifierManager.hasModifier(this, UnseenStrengthMod.ID)? DamageInfo.DamageType.HP_LOSS:this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_VERTICAL));
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new Puppet();
    }
}

