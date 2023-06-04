package shadowverse.cards.Neutral.Temp;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.RainingGoldEffect;
import com.megacrit.cardcrawl.vfx.SpotlightPlayerEffect;
import shadowverse.action.MinionOrderAction;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Royal;

import java.util.Objects;

public class GlitteringGold extends CustomCard {
    public static final String ID = "shadowverse:GlitteringGold";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:GlitteringGold");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/GlitteringGold.png";

    public GlitteringGold() {
        super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.SKILL, Royal.Enums.COLOR_YELLOW, CardRarity.SPECIAL, CardTarget.NONE);
        this.baseBlock = 6;
        this.exhaust = true;
        this.selfRetain = true;
        this.tags.add(AbstractShadowversePlayer.Enums.FES);
    }


    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBlock(3);
        }
    }

    public int used(){
        int s = 0;
        for (AbstractCard c : AbstractDungeon.actionManager.cardsPlayedThisCombat) {
            if (Objects.equals(c.cardID, ID) && c != this) {
                s += 1;
            }
        }
        return s;
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        AbstractDungeon.effectList.add(new RainingGoldEffect(10, true));
        AbstractDungeon.effectsQueue.add(new SpotlightPlayerEffect());
        AbstractDungeon.actionManager.addToBottom(new GainBlockAction(abstractPlayer, abstractPlayer, this.block));
        int s = used();
        if (s == 0 || s == 3 || s == 6) {
            this.addToTop(new GainEnergyAction(1));
        } else if (s == 1 || s == 4 || s == 7) {
            AbstractDungeon.player.gainGold(5);
        } else if (s == 2 || s == 5 || s == 8) {
            AbstractDungeon.actionManager.addToBottom(new HealAction(abstractPlayer, AbstractDungeon.player, 2));
        } else {
            AbstractDungeon.actionManager.addToBottom(new GainBlockAction(abstractPlayer, abstractPlayer, this.block));
        }
        addToBot(new MinionOrderAction("shadowverse:MasterfulMusicianOrb"));
    }

    @Override
    public void applyPowers() {
        super.applyPowers();
        this.rawDescription = cardStrings.EXTENDED_DESCRIPTION[0];
        int s = used();
        if (s == 0 || s == 3 || s == 6) {
            this.rawDescription += cardStrings.EXTENDED_DESCRIPTION[1];
        } else if (s == 1 || s == 4 || s == 7) {
            this.rawDescription += cardStrings.EXTENDED_DESCRIPTION[2];
        } else if (s == 2 || s == 5 || s == 8) {
            this.rawDescription += cardStrings.EXTENDED_DESCRIPTION[3];
        } else {
            this.rawDescription += cardStrings.EXTENDED_DESCRIPTION[4];
        }
        this.rawDescription += cardStrings.EXTENDED_DESCRIPTION[5] + s + cardStrings.EXTENDED_DESCRIPTION[6];
        this.initializeDescription();
    }

    @Override
    public void onMoveToDiscard() {
        this.rawDescription = cardStrings.EXTENDED_DESCRIPTION[0];
        int s = used();
        if (s == 0 || s == 3 || s == 6) {
            this.rawDescription += cardStrings.EXTENDED_DESCRIPTION[1];
        } else if (s == 1 || s == 4 || s == 7) {
            this.rawDescription += cardStrings.EXTENDED_DESCRIPTION[2];
        } else if (s == 2 || s == 5 || s == 8) {
            this.rawDescription += cardStrings.EXTENDED_DESCRIPTION[3];
        } else {
            this.rawDescription += cardStrings.EXTENDED_DESCRIPTION[4];
        }
        this.rawDescription += cardStrings.EXTENDED_DESCRIPTION[5] + s + cardStrings.EXTENDED_DESCRIPTION[6];
        this.initializeDescription();
    }

    @Override
    public AbstractCard makeCopy() {
        return new GlitteringGold();
    }
}

