package shadowverse.cards.Neutral.Neutral;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.PoisonPower;
import shadowverse.cards.AbstractNeutralCard;
import shadowverse.characters.AbstractShadowversePlayer;

public class Mother extends AbstractNeutralCard {
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Mother");
    public static final String ID = "shadowverse:Mother";
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION; public static final String IMG_PATH = "img/cards/Mother.png";
    public Mother() {
        super(ID, NAME, IMG_PATH, 2, DESCRIPTION, CardType.ATTACK, CardColor.COLORLESS, CardRarity.RARE, CardTarget.ENEMY);
        this.baseMagicNumber = 7;
        this.magicNumber = this.baseMagicNumber;
        this.baseBlock = 20;
        this.tags.add(AbstractShadowversePlayer.Enums.NATURAL);
    }

    @Override
    public void upgrade() {
        if(!this.upgraded){
            upgradeMagicNumber(2);
            upgradeBlock(3);
            upgradeName();
        }
    }

    public void applyPowers() {
        super.applyPowers();
        if (AbstractDungeon.player instanceof AbstractShadowversePlayer){
            int count = ((AbstractShadowversePlayer) AbstractDungeon.player).naterranCount;
            this.rawDescription = cardStrings.DESCRIPTION;
            this.rawDescription += cardStrings.EXTENDED_DESCRIPTION[0] + count;
            this.rawDescription += cardStrings.EXTENDED_DESCRIPTION[1];
            initializeDescription();
        }
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot((AbstractGameAction)new SFXAction("Mother"));
        addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)abstractMonster, (AbstractCreature)abstractPlayer, (AbstractPower)new PoisonPower((AbstractCreature)abstractMonster, (AbstractCreature)abstractPlayer, this.magicNumber)));
        if (AbstractDungeon.player instanceof AbstractShadowversePlayer){
            if (((AbstractShadowversePlayer)abstractPlayer).naterranCount >= 1){
                addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)abstractMonster, (AbstractCreature)abstractPlayer, (AbstractPower)new PoisonPower((AbstractCreature)abstractMonster, (AbstractCreature)abstractPlayer, this.magicNumber)));
            }
            if (((AbstractShadowversePlayer)abstractPlayer).naterranCount >= 3){
                addToBot((AbstractGameAction)new GainBlockAction(abstractPlayer,this.block));
            }
        }
    }
}
