package shadowverse.cards.Royal.Levin;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Royal;
import shadowverse.powers.JenoPower;

public class Jeno extends CustomCard {
    public static final String ID = "shadowverse:Jeno";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Jeno");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/Jeno.png";

    public Jeno() {
        super(ID, NAME, IMG_PATH, 2, DESCRIPTION, CardType.POWER, Royal.Enums.COLOR_YELLOW, CardRarity.COMMON, CardTarget.SELF);
        this.magicNumber = this.baseMagicNumber = 4;
        this.tags.add(AbstractShadowversePlayer.Enums.LEVIN);
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            this.upgradeBaseCost(1);
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
        addToBot(new ApplyPowerAction(p, p, new JenoPower(p, this.magicNumber)));
    }


    @Override
    public AbstractCard makeCopy() {
        return new Jeno();
    }
}

