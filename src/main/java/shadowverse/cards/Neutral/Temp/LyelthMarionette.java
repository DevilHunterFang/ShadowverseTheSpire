package shadowverse.cards.Neutral.Temp;


import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.cardmods.UnseenStrengthMod;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.powers.OrchidPower;


public class LyelthMarionette
        extends Puppet {
    public static final String ID = "shadowverse:LyelthMarionette";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:LyelthMarionette");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/LyelthMarionette.png";

    public LyelthMarionette() {
        super();
        this.cardID = ID;
        this.textureImg = IMG_PATH;
        this.loadCardImage(IMG_PATH);
        this.rawDescription = DESCRIPTION;
        this.name = cardStrings.NAME;
        initializeTitle();
        initializeDescription();
        this.baseDamage = 12;
        this.baseBlock = 6;
        this.exhaust = true;
        this.selfRetain = true;
        this.tags.add(AbstractShadowversePlayer.Enums.ACADEMIC);
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeDamage(3);
            upgradeBlock(3);
        }
    }

    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        if (abstractPlayer.hasPower(OrchidPower.POWER_ID)) {
            addToBot(new SFXAction("Orchid_EW_Eff"));
        }
        addToBot(new DamageAction(abstractMonster, new DamageInfo(abstractPlayer, this.damage, CardModifierManager.hasModifier(this, UnseenStrengthMod.ID)? DamageInfo.DamageType.HP_LOSS:this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_VERTICAL));
        addToBot(new GainBlockAction(abstractPlayer,this.block));
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new LyelthMarionette();
    }
}

