package shadowverse.cards.Nemesis.Pile;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.action.DrawPileToHandAction_Tag;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Nemesis;
import shadowverse.stance.Resonance;

public class ArtifactCall
        extends CustomCard {
    public static final String ID = "shadowverse:ArtifactCall";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:ArtifactCall");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/ArtifactCall.png";

    public ArtifactCall() {
        super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.SKILL, Nemesis.Enums.COLOR_SKY, CardRarity.UNCOMMON, CardTarget.SELF);
        this.baseMagicNumber = 2;
        this.magicNumber = this.baseMagicNumber;
    }



    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeMagicNumber(1);
        }
    }


    public void use(AbstractPlayer p, AbstractMonster abstractMonster) {
        int amount = this.magicNumber;
        if (p.stance.ID.equals(Resonance.STANCE_ID)){
            ++amount;
        }
        addToBot((AbstractGameAction)new DrawPileToHandAction_Tag(amount, AbstractShadowversePlayer.Enums.ARTIFACT,null));
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new ArtifactCall();
    }
}


