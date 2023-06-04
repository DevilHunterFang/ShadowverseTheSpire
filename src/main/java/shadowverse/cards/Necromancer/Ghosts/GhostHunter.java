package shadowverse.cards.Necromancer.Ghosts;


import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.action.BurialAction;
import shadowverse.cards.Neutral.Status.Ghost;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Necromancer;
import shadowverse.powers.MementoPower;


public class GhostHunter extends CustomCard {
    public static final String ID = "shadowverse:GhostHunter";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:GhostHunter");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/GhostHunter.png";

    public GhostHunter() {
        super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.ATTACK, Necromancer.Enums.COLOR_PURPLE, CardRarity.COMMON, CardTarget.SELF);
        this.baseBlock = 5;
        this.cardsToPreview = new Ghost();
        this.tags.add(AbstractShadowversePlayer.Enums.ACADEMIC);
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBlock(3);
        }
    }


    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new SFXAction("GhostHunter"));
        addToBot(new GainBlockAction(abstractPlayer, this.block));
        addToBot(new BurialAction(1, new MakeTempCardInHandAction(this.cardsToPreview.makeStatEquivalentCopy())));
        int attackAmt = 0;
        for (AbstractCard c : abstractPlayer.hand.group) {
            if (c != this && c.type == CardType.ATTACK)
                attackAmt++;
        }
        if (attackAmt >= 2) {
            if (abstractPlayer.hasPower(MementoPower.POWER_ID))
                addToBot(new MakeTempCardInHandAction(this.cardsToPreview.makeStatEquivalentCopy()));
        }
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new GhostHunter();
    }
}
