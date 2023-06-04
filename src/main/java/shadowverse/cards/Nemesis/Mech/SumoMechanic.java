package shadowverse.cards.Nemesis.Mech;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.cards.Neutral.Temp.ProductMachine;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Nemesis;

public class SumoMechanic
        extends CustomCard {
    public static final String ID = "shadowverse:SumoMechanic";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:SumoMechanic");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/SumoMechanic.png";

    public SumoMechanic() {
        super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.ATTACK, Nemesis.Enums.COLOR_SKY, CardRarity.COMMON, CardTarget.SELF);
        this.baseBlock = 6;
        this.cardsToPreview = new ProductMachine();
        this.tags.add(AbstractShadowversePlayer.Enums.MACHINE);
        this.tags.add(AbstractShadowversePlayer.Enums.LASTWORD);
        this.tags.add(AbstractShadowversePlayer.Enums.ACADEMIC);
    }
    
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBlock(3);
        }
    }

    @Override
    public void triggerOnExhaust(){
        addToBot(new MakeTempCardInHandAction(this.cardsToPreview.makeStatEquivalentCopy()));
    }


    public void use(AbstractPlayer p, AbstractMonster abstractMonster) {
        addToBot(new SFXAction("SumoMechanic"));
        addToBot(new GainBlockAction(p,this.block));
        addToBot(new MakeTempCardInHandAction(this.cardsToPreview.makeStatEquivalentCopy()));
        int count = 0;
        for (AbstractCard c : p.exhaustPile.group){
            if (c.hasTag(AbstractShadowversePlayer.Enums.MACHINE)){
                count++;
            }
        }
        if (count >= 10){
            addToBot(new GainBlockAction(p,this.block));
        }
    }


    public AbstractCard makeCopy() {
        return  new SumoMechanic();
    }
}


