package shadowverse.cards.Vampire.Vengeance;


import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.action.BetterAutoPlayCardAction;
import shadowverse.characters.Vampire;
import shadowverse.powers.EpitaphPower;
import shadowverse.stance.Vengeance;

import java.util.ArrayList;
import java.util.Collections;


public class ChaosShip
        extends CustomCard {
    public static final String ID = "shadowverse:ChaosShip";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:ChaosShip");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/ChaosShip.png";

    public ChaosShip() {
        super(ID, NAME, IMG_PATH, 2, DESCRIPTION, CardType.ATTACK, Vampire.Enums.COLOR_SCARLET, CardRarity.COMMON, CardTarget.SELF);
        this.baseBlock = 10;
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBlock(3);
        }
    }


    public void use(AbstractPlayer p, AbstractMonster m) {
        ArrayList<AbstractCard> list = new ArrayList<>();
        for (AbstractCard card:p.drawPile.group){
            if (card.type == CardType.ATTACK && card.cost<=1){
                list.add(card);
            }
        }
        if (list.size()>0){
            Collections.shuffle(list);
            list.get(0).setCostForTurn(0);
            addToBot((AbstractGameAction)new BetterAutoPlayCardAction(list.get(0),p.drawPile));
        }
        if (list.size()>1){
            if (p.stance.ID== Vengeance.STANCE_ID||p.hasPower(EpitaphPower.POWER_ID)){
                list.get(1).setCostForTurn(0);
                addToBot((AbstractGameAction)new BetterAutoPlayCardAction(list.get(1),p.drawPile));
            }
        }
        addToBot((AbstractGameAction)new GainBlockAction(p,this.block));
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new ChaosShip();
    }
}

