package shadowverse.cards.Witch.Default;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.actions.unique.ExpertiseAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.status.VoidCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import shadowverse.action.OzSetCostAction;
import shadowverse.characters.Witchcraft;
import shadowverse.powers.OzPower;


public class Oz
        extends CustomCard {
    public static final String ID = "shadowverse:Oz";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Oz");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/Oz.png";

    public Oz() {
        super(ID, NAME, IMG_PATH, 2, DESCRIPTION, CardType.ATTACK, Witchcraft.Enums.COLOR_BLUE, CardRarity.RARE, CardTarget.NONE);
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBaseCost(1);
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }


    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot((AbstractGameAction) new SFXAction("Oz"));
        addToBot((AbstractGameAction) new ExpertiseAction((AbstractCreature) abstractPlayer, 8));
        addToBot((AbstractGameAction) new OzSetCostAction(abstractPlayer, abstractPlayer.hand.group));
        boolean powerExists = false;
        for (AbstractPower pow : abstractPlayer.powers) {
            if (pow.ID.equals("shadowverse:OzPower")) {
                powerExists = true;
                break;
            }
        }
        if (!powerExists) {
            addToBot((AbstractGameAction) new ApplyPowerAction((AbstractCreature) abstractPlayer, (AbstractCreature) abstractPlayer, (AbstractPower) new OzPower((AbstractCreature) abstractPlayer)));
        }
        if (this.upgraded) {
            addToBot((AbstractGameAction) new MakeTempCardInDrawPileAction((AbstractCard) new VoidCard(), 1, true, true));
        } else {
            addToBot((AbstractGameAction) new MakeTempCardInDrawPileAction((AbstractCard) new VoidCard(), 2, true, true));
        }
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new Oz();
    }
}

