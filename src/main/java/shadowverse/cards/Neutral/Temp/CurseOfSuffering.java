package shadowverse.cards.Neutral.Temp;


import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Witchcraft;
import shadowverse.powers.CurseOfSufferingPower;


public class CurseOfSuffering
        extends CustomCard {
    public static final String ID = "shadowverse:CurseOfSuffering";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:CurseOfSuffering");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/CurseOfSuffering.png";

    public CurseOfSuffering() {
        super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.SKILL, Witchcraft.Enums.COLOR_BLUE, CardRarity.SPECIAL, CardTarget.SELF);
        this.tags.add(AbstractShadowversePlayer.Enums.ACADEMIC);
        this.exhaust = true;
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBaseCost(0);
        }
    }


    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new SFXAction("CurseOfSuffering"));
        if (!abstractPlayer.hasPower(CurseOfSufferingPower.POWER_ID))
            addToBot(new ApplyPowerAction(abstractPlayer, abstractPlayer, new CurseOfSufferingPower(abstractPlayer)));
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new CurseOfSuffering();
    }
}

