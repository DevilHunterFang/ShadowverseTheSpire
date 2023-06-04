package shadowverse.cards.Nemesis.Puppet;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.DrawPower;
import shadowverse.cards.Neutral.Temp.Puppet;
import shadowverse.characters.Nemesis;

public class InfinityPuppeteer
        extends CustomCard {
    public static final String ID = "shadowverse:InfinityPuppeteer";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:InfinityPuppeteer");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/InfinityPuppeteer.png";

    public InfinityPuppeteer() {
        super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.POWER, Nemesis.Enums.COLOR_SKY, CardRarity.UNCOMMON, CardTarget.SELF);
        this.baseMagicNumber = 30;
        this.magicNumber = this.baseMagicNumber;
        this.cardsToPreview = (AbstractCard)new Puppet();
    }



    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            this.isInnate = true;
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }


    public void use(AbstractPlayer p, AbstractMonster abstractMonster) {
        addToBot((AbstractGameAction)new SFXAction("InfinityPuppeteer"));
        addToBot((AbstractGameAction)new MakeTempCardInDrawPileAction(this.cardsToPreview.makeStatEquivalentCopy(),this.magicNumber,true,true));
        addToBot((AbstractGameAction)new ApplyPowerAction(p,p,(AbstractPower)new DrawPower(p,1),1));
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new InfinityPuppeteer();
    }
}


