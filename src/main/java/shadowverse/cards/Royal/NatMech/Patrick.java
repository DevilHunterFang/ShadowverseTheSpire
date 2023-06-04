package shadowverse.cards.Royal.NatMech;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import shadowverse.action.PatrickAction;
import shadowverse.cards.Neutral.Temp.NaterranGreatTree;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Royal;
import shadowverse.orbs.Minion;

public class Patrick extends CustomCard {
    public static final String ID = "shadowverse:Patrick";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Patrick");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/Patrick.png";

    public Patrick() {
        super(ID, NAME, IMG_PATH, 3, DESCRIPTION, CardType.ATTACK, Royal.Enums.COLOR_YELLOW, CardRarity.RARE, CardTarget.SELF);
        this.baseBlock = 20;
        this.tags.add(AbstractShadowversePlayer.Enums.NATURAL);
        this.cardsToPreview = new NaterranGreatTree();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            this.upgradeBlock(5);
        }
    }


    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new SFXAction(ID.replace("shadowverse:", "")));
        AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, this.block));
        int ms = 0;
        for (AbstractOrb o : p.orbs) {
            if (o instanceof Minion) {
                ms++;
            }
        }
        if (ms <= 1) {
            this.addToTop(new GainEnergyAction(2));
        }
        addToBot(new PatrickAction());
        addToBot(new MakeTempCardInHandAction(new NaterranGreatTree()));
    }


    @Override
    public AbstractCard makeCopy() {
        return new Patrick();
    }
}
