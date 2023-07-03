package shadowverse.cards.Elf.Long;

import basemod.abstracts.CustomCard;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Elf;

public class Hozumi extends CustomCard {
    public static final String ID = "shadowverse:Hozumi";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Hozumi");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/Hozumi.png";
    private static final Texture LEADER_SKIN_VERSION = ImageMaster.loadImage("img/cards/Hozumi_L.png");

    public Hozumi() {
        super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.ATTACK, Elf.Enums.COLOR_GREEN, CardRarity.RARE, CardTarget.SELF);
        this.baseBlock = 9;
        this.tags.add(AbstractShadowversePlayer.Enums.FES);
        this.jokePortrait = new TextureAtlas.AtlasRegion(LEADER_SKIN_VERSION, 0, 0, LEADER_SKIN_VERSION.getWidth(), LEADER_SKIN_VERSION.getHeight());
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBlock(3);
        }
    }



    public void use(AbstractPlayer p, AbstractMonster m) {
        if ((UnlockTracker.betaCardPref.getBoolean(this.cardID, false))) {
            addToBot(new SFXAction("Hozumi_L"));
        }else {
            addToBot(new SFXAction("Hozumi"));
        }
        addToBot(new GainBlockAction(p,this.block));
        int count = AbstractDungeon.actionManager.cardsPlayedThisTurn.size();
        if (count > 4){
            for (AbstractCard card : p.hand.group){
                if (card!=this){
                    addToBot(new ExhaustSpecificCardAction(card,p.hand));
                    if (card.type==CardType.ATTACK){
                        AbstractCard c = AbstractDungeon.returnTrulyRandomCardInCombat(AbstractCard.CardType.ATTACK).makeCopy();
                        c.setCostForTurn(0);
                        addToBot(new MakeTempCardInHandAction(c));
                    }
                }
            }
        }
    }


    public AbstractCard makeCopy() {
        return new Hozumi();
    }
}
