package shadowverse.cards.Neutral.Curse;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class KMRGaze extends CustomCard {
    public static final String ID = "shadowverse:KMRGaze";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:KMRGaze");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/KMRGaze.png";

    public KMRGaze() {
        super(ID, NAME, IMG_PATH, -2, DESCRIPTION, CardType.CURSE, CardColor.CURSE, CardRarity.SPECIAL, CardTarget.NONE);
        this.isEthereal = true;
    }

    @Override
    public void upgrade() {
    }


    @Override
    public void triggerWhenDrawn(){
        for (AbstractCard c:AbstractDungeon.player.hand.group){
            if (c.type==CardType.SKILL)
                c.setCostForTurn(c.costForTurn+10);
        }
    }


    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
    }

    @Override
    public AbstractCard makeCopy(){
        return new KMRGaze();
    }
}
