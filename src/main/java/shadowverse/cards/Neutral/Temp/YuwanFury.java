package shadowverse.cards.Neutral.Temp;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.characters.Nemesis;

public class YuwanFury extends CustomCard {
    public static final String ID = "shadowverse:YuwanFury";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:YuwanFury");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/YuwanFury.png";

    public YuwanFury() {
        super(ID, NAME, IMG_PATH, -2, DESCRIPTION, CardType.SKILL, Nemesis.Enums.COLOR_SKY, CardRarity.SPECIAL, CardTarget.NONE);
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
        }
    }


    public void use(AbstractPlayer p, AbstractMonster m) {
        onChoseThisOption();
    }

    public void onChoseThisOption() {
        addToBot((AbstractGameAction) new SFXAction("YuwanFury"));
        AbstractCard a = (AbstractCard)new AncientArtifact();
        AbstractCard m = (AbstractCard)new MysticArtifact();
        m.setCostForTurn(0);
        addToBot((AbstractGameAction)new MakeTempCardInHandAction(a));
        addToBot((AbstractGameAction)new MakeTempCardInHandAction(m));
    }

    public AbstractCard makeCopy() {
        return new YuwanFury();
    }
}
