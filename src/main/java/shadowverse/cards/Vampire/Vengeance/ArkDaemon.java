package shadowverse.cards.Vampire.Vengeance;


import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
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


public class ArkDaemon
        extends CustomCard {
    public static final String ID = "shadowverse:ArkDaemon";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:ArkDaemon");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/ArkDaemon.png";

    public ArkDaemon() {
        super(ID, NAME, IMG_PATH, 3, DESCRIPTION, CardType.ATTACK, Vampire.Enums.COLOR_SCARLET, CardRarity.UNCOMMON, CardTarget.SELF);
        this.baseBlock = 20;
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBlock(5);
        }
    }


    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot((AbstractGameAction)new SFXAction("ArkDaemon"));
        //复仇拉怪
        if (p.stance.ID.equals(Vengeance.STANCE_ID)||p.hasPower(EpitaphPower.POWER_ID)){
            ArrayList<AbstractCard> list = new ArrayList<>();
            for (AbstractCard card:p.drawPile.group){
                if (card.type == CardType.ATTACK){
                    list.add(card);
                }
            }
            ArrayList<Integer> costTmp = new ArrayList<>();
            if (list != null && list.size() != 0) {
                ArrayList<AbstractCard> finalList = new ArrayList<>();
                for (AbstractCard c : list) {
                    costTmp.add(c.cost);
                }
                int max = Collections.max(costTmp);
                for (AbstractCard ca:list){
                    if (ca.cost == max){
                        finalList.add(ca);
                    }
                }
                if (finalList.size()>0){
                    Collections.shuffle(finalList);
                    AbstractCard card = finalList.get(0);
                    card.setCostForTurn(0);
                    addToBot((AbstractGameAction)new BetterAutoPlayCardAction(card,p.drawPile));
                }
            }
        }
        //进复仇
        else {
            int half = p.maxHealth/2;
            if (p.currentHealth>half){
                addToBot((AbstractGameAction)new LoseHPAction(p,p,p.currentHealth-half));
                addToBot((AbstractGameAction)new GainEnergyAction(2));
            }
        }
        addToBot((AbstractGameAction)new GainBlockAction(p,this.block));
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new ArkDaemon();
    }
}

