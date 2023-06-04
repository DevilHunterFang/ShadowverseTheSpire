package shadowverse.cards.Vampire.Default;


import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import shadowverse.cards.Neutral.Temp.TerrorNight;
import shadowverse.characters.Vampire;
import shadowverse.effect.BatEffect;
import shadowverse.powers.EpitaphPower;
import shadowverse.powers.WrathPower;
import shadowverse.stance.Vengeance;


public class Urias extends CustomCard {
    public static final String ID = "shadowverse:Urias";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Urias");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/Urias.png";

    public Urias() {
        super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.ATTACK, Vampire.Enums.COLOR_SCARLET, CardRarity.RARE, CardTarget.SELF);
        this.cardsToPreview = (AbstractCard)new TerrorNight();
        this.baseBlock = 6;
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBlock(3);
            this.cardsToPreview.upgrade();
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }


    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot((AbstractGameAction)new SFXAction("Urias"));
        addToBot((AbstractGameAction)new VFXAction((AbstractGameEffect)new BatEffect(),0.1F));
        addToBot((AbstractGameAction)new GainBlockAction(abstractPlayer,this.block));
        addToBot((AbstractGameAction)new MakeTempCardInHandAction(this.cardsToPreview.makeStatEquivalentCopy()));
        if (abstractPlayer.hasPower(EpitaphPower.POWER_ID)){
            addToBot((AbstractGameAction)new DrawCardAction(2));
        }else {
            if (abstractPlayer.hasPower(WrathPower.POWER_ID)){
                addToBot((AbstractGameAction)new DrawCardAction(1));
            }
            if (abstractPlayer.stance.ID.equals(Vengeance.STANCE_ID)){
                addToBot((AbstractGameAction)new DrawCardAction(1));
            }
        }
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new Urias();
    }
}

