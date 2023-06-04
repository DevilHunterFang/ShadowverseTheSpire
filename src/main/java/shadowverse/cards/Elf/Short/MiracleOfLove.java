package shadowverse.cards.Elf.Short;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageRandomEnemyAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.actions.watcher.ChooseOneAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.cards.AbstractEnhanceCard;
import shadowverse.cards.Neutral.Temp.Beast;
import shadowverse.cards.Neutral.Temp.Beauty;
import shadowverse.characters.Elf;

import java.util.ArrayList;


public class MiracleOfLove
        extends AbstractEnhanceCard {
    public static final String ID = "shadowverse:MiracleOfLove";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:MiracleOfLove");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/MiracleOfLove.png";

    public MiracleOfLove() {
        super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.SKILL, Elf.Enums.COLOR_GREEN, CardRarity.RARE, CardTarget.NONE, 2);
        this.baseBlock = 8;
        this.baseDamage = 10;
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBlock(3);
            upgradeDamage(3);
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }

    @Override
    public void exEnhanceUse(AbstractPlayer p, AbstractMonster m) {

    }

    @Override
    public void enhanceUse(AbstractPlayer p, AbstractMonster m) {
        int count = 0;
        for (AbstractCard c : AbstractDungeon.actionManager.cardsPlayedThisTurn) {
            count++;
        }
        count--;
        if (count >= 2) {
            addToBot(new SFXAction("Beast"));
            addToBot(new DamageRandomEnemyAction(new DamageInfo(AbstractDungeon.player, this.damage * 2, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HEAVY));
            addToBot(new WaitAction(1.0F));
            addToBot(new SFXAction("Beauty"));
            addToBot(new GainBlockAction(AbstractDungeon.player, this.block * 2));
        } else {
            addToBot(new SFXAction("Beast"));
            addToBot(new DamageRandomEnemyAction(new DamageInfo(AbstractDungeon.player, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HEAVY));
            addToBot(new WaitAction(1.0F));
            addToBot(new SFXAction("Beauty"));
            addToBot(new GainBlockAction(AbstractDungeon.player, this.block));
        }
    }

    @Override
    public void baseUse(AbstractPlayer p, AbstractMonster m) {
        ArrayList<AbstractCard> stanceChoices = new ArrayList<>();
        stanceChoices.add(new Beast());
        stanceChoices.add(new Beauty());
        addToBot(new ChooseOneAction(stanceChoices));
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new MiracleOfLove();
    }
}

