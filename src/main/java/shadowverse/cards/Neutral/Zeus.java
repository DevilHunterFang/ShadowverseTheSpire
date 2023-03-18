package shadowverse.cards.Neutral;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.action.ZeusAction;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Witchcraft;

public class Zeus extends AbstractNeutralCard{
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Zeus");
    public static final String ID = "shadowverse:Zeus";
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION; public static final String IMG_PATH = "img/cards/Zeus.png";
    public Zeus(int upgrades) {
        super(ID, NAME, IMG_PATH, -1, DESCRIPTION, CardType.ATTACK, CardColor.COLORLESS, CardRarity.RARE, CardTarget.ALL_ENEMY);
        this.baseDamage = 7;
        this.isMultiDamage = true;
        this.baseBlock = 8;
        this.baseMagicNumber = 1;
        this.magicNumber = this.baseMagicNumber;
        this.timesUpgraded = upgrades;
        this.exhaust = true;
    }

    @Override
    public void upgrade() {
        upgradeMagicNumber(1);
        this.timesUpgraded++;
        this.upgraded = true;
        this.name = cardStrings.NAME + "+" + this.timesUpgraded;
        initializeTitle();
    }

    public void applyPowers() {
        super.applyPowers();
        int count = 0;
        if (AbstractDungeon.player instanceof AbstractShadowversePlayer){
            count = ((AbstractShadowversePlayer) AbstractDungeon.player).upgradedThisCombat;
        }
        this.rawDescription = cardStrings.DESCRIPTION;
        this.rawDescription += cardStrings.EXTENDED_DESCRIPTION[0] + count;
        this.rawDescription += cardStrings.EXTENDED_DESCRIPTION[1];
        initializeDescription();
    }

    @Override
    public boolean canUpgrade() {
        return true;
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new SFXAction("Zeus"));
        int count = 0;
        if (abstractPlayer instanceof AbstractShadowversePlayer){
            count = ((AbstractShadowversePlayer) abstractPlayer).upgradedThisCombat;
        }
        addToBot(new ZeusAction(abstractPlayer,this.multiDamage,this.block,this.damageTypeForTurn,this.freeToPlayOnce,this.energyOnUse+this.magicNumber+count));
    }

    public AbstractCard makeCopy() {
        return new Zeus(this.timesUpgraded);
    }
}
