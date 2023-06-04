package shadowverse.cards.Vampire.Wrath;


import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Vampire;
import shadowverse.powers.SteamrollingTankPower;


public class SteamrollingTank
        extends CustomCard {
    public static final String ID = "shadowverse:SteamrollingTank";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:SteamrollingTank");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/SteamrollingTank.png";

    public SteamrollingTank() {
        super(ID, NAME, IMG_PATH, 0, DESCRIPTION, CardType.ATTACK, Vampire.Enums.COLOR_SCARLET, CardRarity.COMMON, CardTarget.SELF);
        this.baseBlock = 5;
        this.baseMagicNumber = 1;
        this.magicNumber = this.baseMagicNumber;
        this.tags.add(AbstractShadowversePlayer.Enums.CONDEMNED);
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBlock(2);
            upgradeMagicNumber(1);
        }
    }


    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new SFXAction("SteamrollingTank"));
        if (p.hasPower(SteamrollingTankPower.POWER_ID)){
            addToBot(new ApplyPowerAction(p,p,new SteamrollingTankPower(p,this.magicNumber,0),this.magicNumber));
        }else {
            addToBot(new ApplyPowerAction(p,p,new SteamrollingTankPower(p,this.magicNumber,8),this.magicNumber));
        }
        addToBot(new LoseHPAction(p, p, 1));
        addToBot(new GainBlockAction(p,this.block));
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new SteamrollingTank();
    }
}

