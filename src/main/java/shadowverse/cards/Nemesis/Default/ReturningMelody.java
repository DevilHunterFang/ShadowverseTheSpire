package shadowverse.cards.Nemesis.Default;

import basemod.abstracts.CustomCard;
import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsInHandAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.characters.Nemesis;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ReturningMelody
        extends CustomCard {
    public static final String ID = "shadowverse:ReturningMelody";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:ReturningMelody");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/ReturningMelody.png";
    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("ExhaustAction");
    public static final String[] TEXT = uiStrings.TEXT;

    public ReturningMelody() {
        super(ID, NAME, IMG_PATH, 0, DESCRIPTION, CardType.SKILL, Nemesis.Enums.COLOR_SKY, CardRarity.UNCOMMON, CardTarget.NONE);
        this.baseMagicNumber = 1;
        this.magicNumber = this.baseMagicNumber;
    }



    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeMagicNumber(1);
        }
    }


    public void use(AbstractPlayer p, AbstractMonster abstractMonster) {
        addToBot((AbstractGameAction)new SFXAction("ReturningMelody"));
        addToBot((AbstractGameAction)new SelectCardsInHandAction(this.magicNumber,TEXT[0],true,true, card -> {
            return card.type ==CardType.ATTACK;
        }, abstractCards ->{
            for (AbstractCard c:abstractCards){
                addToBot((AbstractGameAction)new ExhaustSpecificCardAction(c,p.hand));
                addToBot((AbstractGameAction)new MakeTempCardInHandAction(c.makeStatEquivalentCopy()));
            }
        } ));
        int amt = 0;
        for (AbstractCard c:p.exhaustPile.group){
            if (c.type==CardType.ATTACK){
                amt++;
            }
        }
        if (amt>=10){
            List<AbstractCard> group = new ArrayList<>();
            List<AbstractCard> group2 = new ArrayList<>();
            for (AbstractCard card:p.exhaustPile.group){
                if (card.cost == 1 && card.type!=CardType.SKILL){
                    group.add(card);
                }else if (card.cost == 2 && card.type!=CardType.SKILL){
                    group2.add(card);
                }
            }
            if (group.size()>0){
                Collections.shuffle(group);
                addToBot((AbstractGameAction) new MakeTempCardInHandAction(group.get(0).makeStatEquivalentCopy()));
            }
            if (group2.size()>0){
                Collections.shuffle(group2);
                addToBot((AbstractGameAction) new MakeTempCardInHandAction(group2.get(0).makeStatEquivalentCopy()));
            }
        }
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new ReturningMelody();
    }
}


