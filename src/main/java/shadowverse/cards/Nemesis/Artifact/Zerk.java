package shadowverse.cards.Nemesis.Artifact;


import basemod.abstracts.CustomCard;
import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import shadowverse.Shadowverse;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Nemesis;
import shadowverse.powers.Cemetery;

import java.util.ArrayList;
import java.util.Collections;


public class Zerk extends CustomCard {
    public static final String ID = "shadowverse:Zerk";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Zerk");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/Zerk.png";
    public static final String[] TEXT = (CardCrawlGame.languagePack.getUIString("ExhaustAction")).TEXT;

    public Zerk() {
        super(ID, NAME, IMG_PATH, 2, DESCRIPTION, CardType.ATTACK, Nemesis.Enums.COLOR_SKY, CardRarity.UNCOMMON, CardTarget.SELF);
        this.baseBlock = 9;
        this.tags.add(AbstractShadowversePlayer.Enums.ACCELERATE);
        this.exhaust = true;
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            this.exhaust = false;
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }

    @Override
    public void update() {
        if (AbstractDungeon.currMapNode != null && (AbstractDungeon.getCurrRoom()).phase == AbstractRoom.RoomPhase.COMBAT &&
                Shadowverse.Accelerate(this)) {
            setCostForTurn(0);
            this.type = CardType.SKILL;
        } else {
            if (this.type == CardType.SKILL) {
                setCostForTurn(2);
                this.type = CardType.ATTACK;
            }
        }
        super.update();
    }

    public void triggerOnGlowCheck() {
        if (Shadowverse.Accelerate(this)) {
            this.glowColor = AbstractCard.GREEN_BORDER_GLOW_COLOR.cpy();
        } else {
            this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        }
    }


    public void use(AbstractPlayer p, AbstractMonster abstractMonster) {
        if (Shadowverse.Accelerate((AbstractCard) this) && this.type == CardType.SKILL) {
            addToBot((AbstractGameAction) new SFXAction("Zerk_Acc"));
            addToBot(new SelectCardsAction(p.discardPile.group, TEXT[0], false, card -> {
                return card.hasTag(AbstractShadowversePlayer.Enums.ARTIFACT);
            }, abstractCards -> {
                for (AbstractCard c : abstractCards) {
                    addToBot((AbstractGameAction) new ExhaustSpecificCardAction(c, AbstractDungeon.player.discardPile));
                    addToBot(new DrawCardAction(1));
                    addToBot(new HealAction(p, p, 2));
                    addToBot(new ApplyPowerAction(p, p, new Cemetery(p, 1), 1));
                }
            }));
        } else {
            addToBot((AbstractGameAction) new SFXAction("Zerk"));
            addToBot((AbstractGameAction) new GainBlockAction(p, p, this.block));
            ArrayList<AbstractCard> list = new ArrayList<>();
            ArrayList<String> dup = new ArrayList<>();
            for (AbstractCard c : p.exhaustPile.group) {
                if (c.hasTag(AbstractShadowversePlayer.Enums.ARTIFACT) && !dup.contains(c.cardID)) {
                    dup.add(c.cardID);
                    AbstractCard card = c.makeCopy();
                    list.add(card);
                }
            }
            if (list.size() > 0) {
                Collections.shuffle(list);
                for (AbstractCard ca : list) {
                    ca.setCostForTurn(0);
                }
                addToBot((AbstractGameAction) new MakeTempCardInHandAction(list.get(0)));
                if (list.size() > 1)
                    addToBot((AbstractGameAction) new MakeTempCardInHandAction(list.get(1)));
                if (list.size() > 2)
                    addToBot((AbstractGameAction) new MakeTempCardInHandAction(list.get(2)));
            }
        }
    }

    public AbstractCard makeCopy() {
        return (AbstractCard) new Zerk();
    }
}
