package shadowverse.cards.Neutral.Neutral;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.cards.AbstractNeutralCard;

public class StarBright extends AbstractNeutralCard {
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:StarBright");
    public static final String ID = "shadowverse:StarBright";
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION; public static final String IMG_PATH = "img/cards/StarBright.png";
    public StarBright() {
        super(ID, NAME, IMG_PATH, 2, DESCRIPTION, CardType.ATTACK, CardColor.COLORLESS, CardRarity.UNCOMMON, CardTarget.NONE);
    }

    @Override
    public void upgrade() {
        if(!this.upgraded){
            upgradeBaseCost(1);
            upgradeName();
        }
    }
    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot((AbstractGameAction)new SFXAction("StarBright"));
        if (abstractPlayer.hand.group.size() >= 4){
            int count = 0;
            for (int i = 0; i < abstractPlayer.hand.group.size(); i++){
                if (abstractPlayer.hand.group.get(i) != this){
                    addToBot((AbstractGameAction)new MakeTempCardInHandAction(abstractPlayer.hand.group.get(i).makeStatEquivalentCopy(), 1));
                    count++;
                }
                if (count >= 3)
                    break;
            }
        }else{
            for (AbstractCard c : abstractPlayer.hand.group){
                if (c != this){
                    addToBot((AbstractGameAction)new MakeTempCardInHandAction(c.makeStatEquivalentCopy(), 1));
                }
            }
        }
    }

    public AbstractCard makeCopy() {
        return new StarBright();
    }
}
