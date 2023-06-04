package shadowverse.cards.Witch.Earth1;


import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.cards.Witch.Earth2.Telescope;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Witchcraft;
import shadowverse.powers.AstrologicalSorcererPower;
import shadowverse.powers.EarthEssence;


public class AstrologicalSorcerer
        extends CustomCard {
    public static final String ID = "shadowverse:AstrologicalSorcerer";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:AstrologicalSorcerer");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/AstrologicalSorcerer.png";

    public AstrologicalSorcerer() {
        super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.ATTACK, Witchcraft.Enums.COLOR_BLUE, CardRarity.RARE, CardTarget.SELF);
        this.baseBlock = 6;
        this.cardsToPreview = new Telescope();
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBlock(2);
        }
    }

    public void applyPowers() {
        super.applyPowers();
        int count = 0;
        if (AbstractDungeon.player instanceof AbstractShadowversePlayer){
            count = ((AbstractShadowversePlayer) AbstractDungeon.player).earthCount;
        }
        this.rawDescription = cardStrings.DESCRIPTION;
        this.rawDescription += cardStrings.EXTENDED_DESCRIPTION[0] + count;
        this.rawDescription += cardStrings.EXTENDED_DESCRIPTION[1];
        initializeDescription();
    }


    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new SFXAction("AstrologicalSorcerer"));
        addToBot(new ApplyPowerAction(abstractPlayer,abstractPlayer,new EarthEssence(abstractPlayer,1),1));
        addToBot(new GainBlockAction(abstractPlayer,this.block));
        addToBot(new MakeTempCardInHandAction(this.cardsToPreview.makeStatEquivalentCopy(), 1));
        if (abstractPlayer instanceof AbstractShadowversePlayer){
            if (((AbstractShadowversePlayer) abstractPlayer).earthCount >= 7){
                addToBot(new ApplyPowerAction(abstractPlayer,abstractPlayer,new AstrologicalSorcererPower(abstractPlayer)));
            }
        }
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new AstrologicalSorcerer();
    }
}

