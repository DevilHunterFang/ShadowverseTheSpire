package shadowverse.cards.Uncommon;

import basemod.abstracts.CustomCard;
import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsInHandAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.common.UpgradeSpecificCardAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.action.AnimationAction;
import shadowverse.characters.*;

public class MirrorImage extends CustomCard {
    public static final String ID = "shadowverse:MirrorImage";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:MirrorImage");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/MirrorImage.png";
    public static final String[] TEXT = CardCrawlGame.languagePack.getUIString("ArmamentsAction").TEXT;

    public MirrorImage() {
        super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.SKILL, Royal.Enums.COLOR_YELLOW, CardRarity.COMMON, CardTarget.SELF);
        this.baseMagicNumber = 1;
        this.magicNumber = this.baseMagicNumber;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBaseCost(0);
        }
    }


    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new SelectCardsInHandAction(1, TEXT[0], false, false, card -> (card.type == CardType.ATTACK || card.type == CardType.POWER) && !card.hasTag(CardTags.HEALING), abstractCards -> {
            for (AbstractCard c : abstractCards) {
                AbstractCard tmp = c.makeStatEquivalentCopy();
                tmp.exhaustOnUseOnce = true;
                tmp.exhaust = true;
                tmp.isEthereal = true;
                tmp.rawDescription += " NL 虚无 。 NL 消耗 。";
                tmp.initializeDescription();
                tmp.applyPowers();
                abstractPlayer.hand.addToTop(tmp);
            }
        }));
    }

    @Override
    public AbstractCard makeCopy() {
        return new MirrorImage();
    }
}
