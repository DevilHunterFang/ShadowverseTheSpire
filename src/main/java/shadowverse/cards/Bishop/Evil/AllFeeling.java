package shadowverse.cards.Bishop.Evil;

import basemod.abstracts.CustomCard;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.ExhaustAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.BorderFlashEffect;
import shadowverse.action.FeelingAction;
import shadowverse.cards.Neutral.Curse.EvilWorship;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Bishop;

public class AllFeeling
        extends CustomCard {
    public static final String ID = "shadowverse:AllFeeling";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:AllFeeling");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/AllFeeling.png";

    public AllFeeling() {
        super(ID, NAME, IMG_PATH, 2, DESCRIPTION, CardType.ATTACK, Bishop.Enums.COLOR_WHITE, CardRarity.RARE, CardTarget.ALL_ENEMY);
        this.cardsToPreview = new EvilWorship();
        this.baseMagicNumber = 3;
        this.magicNumber = this.baseMagicNumber;
        this.baseDamage = 3;
        this.tags.add(AbstractShadowversePlayer.Enums.FES);
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeMagicNumber(1);
        }
    }


    public void use(AbstractPlayer p, AbstractMonster abstractMonster) {
        addToBot((AbstractGameAction)new SFXAction("AllFeeling"));
        addToBot((AbstractGameAction)new ExhaustAction(this.magicNumber,false,true,true));
        addToBot((AbstractGameAction)new DrawCardAction(this.magicNumber));
        addToBot((AbstractGameAction)new MakeTempCardInDiscardAction(this.cardsToPreview.makeStatEquivalentCopy(),this.magicNumber));
        addToBot((AbstractGameAction)new VFXAction((AbstractGameEffect)new BorderFlashEffect(Color.PURPLE, true),0.7F));
        addToBot((AbstractGameAction)new FeelingAction(this.damage));
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new AllFeeling();
    }
}


