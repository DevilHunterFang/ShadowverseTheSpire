package shadowverse.cards.Necromancer.Natura;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Necromancer;

public class GhostRider extends CustomCard {
    public static final String ID = "shadowverse:GhostRider";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:GhostRider");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/GhostRider.png";

    public GhostRider() {
        super(ID, NAME, IMG_PATH, 2, DESCRIPTION, CardType.ATTACK, Necromancer.Enums.COLOR_PURPLE, CardRarity.COMMON, CardTarget.SELF);
        this.baseBlock = 12;
        this.tags.add(AbstractShadowversePlayer.Enums.LASTWORD);
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBlock(4);
        }
    }

    @Override
    public void triggerOnExhaust() {
        addToBot((AbstractGameAction)new GainBlockAction(AbstractDungeon.player,this.baseBlock));
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot((AbstractGameAction)new SFXAction("GhostRider"));
        addToBot((AbstractGameAction)new GainBlockAction(AbstractDungeon.player,this.block));
    }

    public AbstractCard makeCopy() {
        return new GhostRider();
    }
}
