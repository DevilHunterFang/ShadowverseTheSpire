package shadowverse.cards.Neutral.Temp;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.characters.AbstractShadowversePlayer;

public class ManaForce extends CustomCard {
    public static final String ID = "shadowverse:ManaForce";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:ManaForce");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/ManaForce.png";

    public ManaForce() {
        super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.SKILL, CardColor.COLORLESS, CardRarity.SPECIAL, CardTarget.ENEMY);
        this.selfRetain = true;
        this.exhaust = true;
        this.tags.add(AbstractShadowversePlayer.Enums.NATURAL);
    }

    @Override
    public void upgrade() {
        if (!this.upgraded){
            upgradeName();
            upgradeBaseCost(0);
        }
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot((AbstractGameAction) new RemoveSpecificPowerAction(abstractMonster,abstractPlayer,"shadowverse:ManaBarrier"));
    }

    @Override
    public AbstractCard makeCopy(){
        return new ManaForce();
    }
}
