package shadowverse.cards.Neutral.Temp;


import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.cardmods.UnseenStrengthMod;
import shadowverse.powers.OrchidPower;


public class EnhancedPuppet
        extends Puppet {
    public static final String ID = "shadowverse:EnhancedPuppet";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:EnhancedPuppet");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/EnhancedPuppet.png";

    public EnhancedPuppet() {
        super();
        this.cardID = ID;
        this.textureImg = IMG_PATH;
        this.loadCardImage(IMG_PATH);
        this.rawDescription = DESCRIPTION;
        this.name = cardStrings.NAME;
        initializeTitle();
        initializeDescription();
        this.baseDamage = 6;
        this.exhaust = true;
        this.isEthereal = true;
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeDamage(3);
        }
    }

    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        if (abstractPlayer.hasPower(OrchidPower.POWER_ID)) {
            addToBot(new SFXAction("Orchid_EW_Eff"));
        }
        addToBot(new DamageAction(abstractMonster, new DamageInfo(abstractPlayer, this.damage, CardModifierManager.hasModifier(this, UnseenStrengthMod.ID)? DamageInfo.DamageType.HP_LOSS:this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_VERTICAL));
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new EnhancedPuppet();
    }
}

