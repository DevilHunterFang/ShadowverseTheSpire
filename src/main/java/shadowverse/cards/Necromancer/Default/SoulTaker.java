package shadowverse.cards.Necromancer.Default;


import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.characters.Necromancer;

import java.util.ArrayList;
import java.util.Collections;


public class SoulTaker extends CustomCard {
    public static final String ID = "shadowverse:SoulTaker";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:SoulTaker");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/SoulTaker.png";
    private static final String TEXT = CardCrawlGame.languagePack.getUIString("shadowverse:Exhaust").TEXT[0];
    private static final String TEXT2 = CardCrawlGame.languagePack.getUIString("shadowverse:Ethereal").TEXT[0];

    public SoulTaker() {
        super(ID, NAME, IMG_PATH, 2, DESCRIPTION, CardType.ATTACK, Necromancer.Enums.COLOR_PURPLE, CardRarity.RARE, CardTarget.SELF);
        this.baseBlock = 8;
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBlock(5);
        }
    }


    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot((AbstractGameAction) new SFXAction("SoulTaker"));
        addToBot((AbstractGameAction)new GainBlockAction(abstractPlayer,this.block));
        ArrayList<AbstractCard> list = new ArrayList<>();
        for (AbstractCard c:abstractPlayer.discardPile.group){
            if (c.type==CardType.ATTACK&&!(c instanceof SoulTaker))
                list.add(c);
        }
        if(list.size()!=0){
            Collections.shuffle(list);
            AbstractCard tmp = list.get(0).makeSameInstanceOf();
            tmp.cost=0;
            tmp.costForTurn=0;
            tmp.isCostModified = true;
            tmp.exhaustOnUseOnce = true;
            tmp.exhaust = true;
            tmp.isEthereal = true;
            tmp.rawDescription += " NL "+TEXT2+" 。 NL "+TEXT+" 。";
            tmp.initializeDescription();
            tmp.applyPowers();
            abstractPlayer.hand.addToTop(tmp);
        }
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new SoulTaker();
    }
}

