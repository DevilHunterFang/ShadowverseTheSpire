package shadowverse.cards.Vampire.Default;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ThornsPower;
import shadowverse.cards.Royal.Levin.Albert;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Vampire;

public class YuriusLevinDuke extends CustomCard {
    public static final String ID = "shadowverse:YuriusLevinDuke";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:YuriusLevinDuke");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/YuriusLevinDuke.png";

    public YuriusLevinDuke() {
        super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.POWER, Vampire.Enums.COLOR_SCARLET, CardRarity.COMMON, CardTarget.SELF);
        this.magicNumber = this.baseMagicNumber = 3;
        this.tags.add(AbstractShadowversePlayer.Enums.LEVIN);
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            this.upgradeMagicNumber(2);
        }
    }


    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        boolean co = false;
        for (AbstractCard c : p.hand.group) {
            if (c instanceof Albert) {
                co = true;
                break;
            }
        }
        if (co) {
            addToBot(new SFXAction(ID.replace("shadowverse:", "") + "_Co"));
        } else {
            addToBot(new SFXAction(ID.replace("shadowverse:", "")));
        }
        addToBot(new ApplyPowerAction(p, p, new ThornsPower(p, this.magicNumber), this.magicNumber));
    }


    @Override
    public AbstractCard makeCopy() {
        return new YuriusLevinDuke();
    }
}
