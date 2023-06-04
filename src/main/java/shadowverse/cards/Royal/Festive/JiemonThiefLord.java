package shadowverse.cards.Royal.Festive;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.cards.Neutral.Temp.GlitteringGold;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Royal;
import shadowverse.powers.GiveGoldPower;
import shadowverse.powers.JiemonThiefLordPower;

public class JiemonThiefLord extends CustomCard {
    public static final String ID = "shadowverse:JiemonThiefLord";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:JiemonThiefLord");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/JiemonThiefLord.png";

    public JiemonThiefLord() {
        super(ID, NAME, IMG_PATH, 2, DESCRIPTION, CardType.POWER, Royal.Enums.COLOR_YELLOW, CardRarity.RARE, CardTarget.SELF);
        this.cardsToPreview = new GlitteringGold();
        this.tags.add(AbstractShadowversePlayer.Enums.FES);
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }


    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new SFXAction(ID.replace("shadowverse:", "")));
        addToBot((AbstractGameAction)new MakeTempCardInHandAction(new GlitteringGold()));
        addToBot(new ApplyPowerAction(p, p, new JiemonThiefLordPower(p)));
        if(this.upgraded){
            addToBot(new ApplyPowerAction(p, p, new GiveGoldPower(p)));
        }
    }


    @Override
    public AbstractCard makeCopy() {
        return new JiemonThiefLord();
    }
}
