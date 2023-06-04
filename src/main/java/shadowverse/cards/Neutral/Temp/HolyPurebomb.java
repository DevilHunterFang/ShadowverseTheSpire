package shadowverse.cards.Neutral.Temp;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import shadowverse.cards.Neutral.Status.EvolutionPoint;
import shadowverse.characters.Royal;

import java.util.ArrayList;
import java.util.List;

public class HolyPurebomb extends CustomCard {
    public static final String ID = "shadowverse:HolyPurebomb";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/HolyPurebomb.png";

    public HolyPurebomb() {
        super(ID, NAME, IMG_PATH, 2, DESCRIPTION, CardType.SKILL, Royal.Enums.COLOR_YELLOW, CardRarity.SPECIAL, CardTarget.ENEMY);
        this.exhaust = true;
    }


    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBaseCost(1);
        }
    }


    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new SFXAction(ID.replace("shadowverse:", "")));
        List<AbstractCard> temp = new ArrayList<>();
        for (AbstractCard c : abstractPlayer.hand.group) {
            if ((c.type == CardType.CURSE || c.type == CardType.STATUS) && !(c instanceof EvolutionPoint)) {
                temp.add(c);
            }
        }
        for (AbstractCard c : temp) {
            addToBot(new ExhaustSpecificCardAction(c, abstractPlayer.hand));
        }
        if(abstractMonster.hasPower("Artifact")){
            addToBot((AbstractGameAction) new RemoveSpecificPowerAction(abstractMonster,abstractPlayer,"Artifact"));
        }else {
            for (AbstractPower pow : abstractMonster.powers){
                if (pow.type == AbstractPower.PowerType.BUFF && pow.ID!="Invincible" &&pow.ID!="Mode Shift"&&pow.ID!="Split"&&pow.ID!="Unawakened"&&pow.ID!="Life Link"&&pow.ID!="Fading"&&pow.ID!="Stasis"&&pow.ID!="Minion"&&pow.ID!="Shifting"&&pow.ID!="shadowverse:chushouHealPower"){
                    addToBot((AbstractGameAction) new RemoveSpecificPowerAction(pow.owner,abstractPlayer,pow.ID));
                    break;
                }
            }
        }
    }



    @Override
    public AbstractCard makeCopy() {
        return new HolyPurebomb();
    }
}

