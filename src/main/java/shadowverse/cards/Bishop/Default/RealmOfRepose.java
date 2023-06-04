package shadowverse.cards.Bishop.Default;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.cards.AbstractAmuletCard;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Bishop;
import shadowverse.orbs.AmuletOrb;
import shadowverse.powers.RealmOfReposePower;

public class RealmOfRepose extends AbstractAmuletCard {
    public static final String ID = "shadowverse:RealmOfRepose";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:RealmOfRepose");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/RealmOfRepose.png";


    public RealmOfRepose() {
        super(ID, NAME, IMG_PATH, 1, DESCRIPTION, Bishop.Enums.COLOR_WHITE, CardRarity.UNCOMMON, CardTarget.SELF);
        this.countDown = 2;
        this.isEthereal = true;
        this.tags.add(AbstractShadowversePlayer.Enums.AMULET_FOR_ONECE);
    }


    @Override
    public void onStartOfTurn(AmuletOrb paramOrb) {

    }

    @Override
    public void onEvoke(AmuletOrb paramOrb) {
    }

    @Override
    public void endOfTurn(AmuletOrb paramOrb) {
        addToBot((AbstractGameAction) new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new RealmOfReposePower(AbstractDungeon.player, 1)));
    }

    @Override
    public int onHeal(int healAmount, AmuletOrb paramOrb) {
        return 0;
    }

    @Override
    public void onOtherCardPlayed(AbstractCard c, AmuletOrb paramOrb) {

    }

    @Override
    public void onGainedBlock(int blockAmt, AmuletOrb paramOrb) {

    }


    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            this.isEthereal = false;
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot((AbstractGameAction) new SFXAction("RealmOfRepose"));
        boolean attacked = false;
        for (AbstractCard c : AbstractDungeon.actionManager.cardsPlayedThisTurn) {
            if (c.type == CardType.ATTACK) {
                attacked = true;
                break;
            }
        }
        if (!attacked)
            addToBot((AbstractGameAction) new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new RealmOfReposePower(AbstractDungeon.player, 1)));
    }
}
