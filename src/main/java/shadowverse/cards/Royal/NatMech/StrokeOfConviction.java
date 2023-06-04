package shadowverse.cards.Royal.NatMech;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.AttackDamageRandomEnemyAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import shadowverse.action.MinionSummonAction;
import com.megacrit.cardcrawl.actions.watcher.ChooseOneAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.action.MinionBuffAction;
import shadowverse.cards.AbstractEnhanceCard;
import shadowverse.cards.Neutral.Temp.*;
import shadowverse.characters.Royal;
import shadowverse.orbs.Quickblader;

import java.util.ArrayList;

public class StrokeOfConviction extends AbstractEnhanceCard {
    public static final String ID = "shadowverse:StrokeOfConviction";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:StrokeOfConviction");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/StrokeOfConviction.png";

    public StrokeOfConviction() {
        super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.SKILL, Royal.Enums.COLOR_YELLOW, CardRarity.UNCOMMON, CardTarget.NONE,2);
        this.baseDamage = 10;
        this.exhaust = true;
    }


    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            this.exhaust = false;
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }

    @Override
    public void exEnhanceUse(AbstractPlayer p, AbstractMonster m) {

    }

    @Override
    public void enhanceUse(AbstractPlayer p, AbstractMonster m) {
        addToBot(new SFXAction(ID.replace("shadowverse:", "")));
        AbstractDungeon.actionManager.addToBottom(new MinionSummonAction(new Quickblader()));
        AbstractDungeon.actionManager.addToBottom(new MinionSummonAction(new Quickblader()));
        AbstractDungeon.actionManager.addToBottom(new AttackDamageRandomEnemyAction(this, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
        AbstractDungeon.actionManager.addToBottom(new MinionBuffAction(1, 1, true));
    }

    @Override
    public void baseUse(AbstractPlayer p, AbstractMonster m) {
        ArrayList<AbstractCard> stanceChoices = new ArrayList<>();
        stanceChoices.add(new ErikasSleight());
        stanceChoices.add(new MistolinasSwordplay());
        stanceChoices.add(new BayleonsCommand());
        addToBot(new ChooseOneAction(stanceChoices));
    }


    @Override
    public AbstractCard makeCopy() {
        return new StrokeOfConviction();
    }
}

