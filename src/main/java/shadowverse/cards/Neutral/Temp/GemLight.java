package shadowverse.cards.Neutral.Temp;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.action.GemLightAction;

public class GemLight extends CustomCard {
        public static final String ID = "shadowverse:GemLight";
        public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:GemLight");
        public static final String NAME = cardStrings.NAME;
        public static final String DESCRIPTION = cardStrings.DESCRIPTION;
        public static final String IMG_PATH = "img/cards/GemLight.png";

   public GemLight() {
            super(ID, NAME, IMG_PATH, 0, DESCRIPTION, CardType.SKILL, CardColor.COLORLESS, CardRarity.SPECIAL, CardTarget.NONE);
            this.baseMagicNumber = 2;
            this.magicNumber = this.baseMagicNumber;
            this.exhaust = true;
        }

    @Override
    public void upgrade() {
        if (!this.upgraded){
            upgradeName();
            this.selfRetain = true;
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
       addToBot((AbstractGameAction)new GemLightAction(abstractPlayer,this.magicNumber,false));
    }
}
