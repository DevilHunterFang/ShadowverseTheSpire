package shadowverse.cards.Witch.Earth1;

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
import shadowverse.powers.EarthEssence;
import shadowverse.powers.PiousInstructorPower;

public class PiousInstructor extends CustomCard {
    public static final String ID = "shadowverse:PiousInstructor";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:PiousInstructor");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/PiousInstructor.png";

    public PiousInstructor() {
        super("shadowverse:PiousInstructor", NAME, "img/cards/PiousInstructor.png", 1, DESCRIPTION, CardType.POWER, Witchcraft.Enums.COLOR_BLUE, CardRarity.UNCOMMON, CardTarget.SELF);
        this.baseMagicNumber = 4;
        this.magicNumber = this.baseMagicNumber;
        this.tags.add(AbstractShadowversePlayer.Enums.EARTH_RITE);
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeMagicNumber(2);
        }
    }


    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new SFXAction("PiousInstructor"));
        addToBot(new ApplyPowerAction(abstractPlayer, abstractPlayer, new EarthEssence(abstractPlayer, 1), 1));
        addToBot(new ApplyPowerAction(abstractPlayer, abstractPlayer, new PiousInstructorPower(abstractPlayer, this.magicNumber), this.magicNumber));
    }


    public AbstractCard makeCopy() {
        /* 49 */
        return (AbstractCard) new PiousInstructor();
        /*    */
    }
}
