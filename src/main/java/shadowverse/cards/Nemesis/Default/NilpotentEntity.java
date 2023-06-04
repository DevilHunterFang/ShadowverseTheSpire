package shadowverse.cards.Nemesis.Default;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Nemesis;
import shadowverse.powers.NilpotentPower;

import java.util.ArrayList;

public class NilpotentEntity
        extends CustomCard {
    public static final String ID = "shadowverse:NilpotentEntity";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:NilpotentEntity");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/NilpotentEntity.png";

    public NilpotentEntity() {
        super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.POWER, Nemesis.Enums.COLOR_SKY, CardRarity.UNCOMMON, CardTarget.SELF);
        this.baseMagicNumber = 1;
        this.magicNumber = this.baseMagicNumber;
        this.isEthereal = true;
    }



    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBaseCost(0);
        }
    }


    public void use(AbstractPlayer p, AbstractMonster abstractMonster) {
        ArrayList<AbstractCard> cards = new ArrayList<>();
        for (AbstractCard c:p.hand.group){
            if (c.hasTag(AbstractShadowversePlayer.Enums.ARTIFACT))
                cards.add(c);
        }
        if (cards.size()>0){
            AbstractCard toExhaust = cards.get(AbstractDungeon.cardRandomRng.random(cards.size()-1));
            addToBot((AbstractGameAction)new ExhaustSpecificCardAction(toExhaust,p.hand));
            addToBot((AbstractGameAction)new ApplyPowerAction(p,p,(AbstractPower)new NilpotentPower(p,this.magicNumber+1)));
        }else {
            addToBot((AbstractGameAction)new ApplyPowerAction(p,p,(AbstractPower)new NilpotentPower(p,this.magicNumber)));
        }
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new NilpotentEntity();
    }
}


