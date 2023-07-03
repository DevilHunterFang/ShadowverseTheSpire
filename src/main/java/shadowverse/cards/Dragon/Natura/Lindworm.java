package shadowverse.cards.Dragon.Natura;


import basemod.abstracts.CustomCard;
import com.badlogic.gdx.Gdx;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import shadowverse.action.ChoiceAction;
import shadowverse.cards.Neutral.Temp.IniquitousLindworm;
import shadowverse.cards.Neutral.Temp.VirtuousLindworm;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Dragon;

import java.util.ArrayList;


public class Lindworm extends CustomCard {
    public static final String ID = "shadowverse:Lindworm";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Lindworm");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/Lindworm.png";
    private float rotationTimer;
    private int previewIndex;

    public static ArrayList<AbstractCard> returnChoice() {
        ArrayList<AbstractCard> list = new ArrayList<>();
        list.add(new VirtuousLindworm());
        list.add(new IniquitousLindworm());
        return list;
    }

    public Lindworm() {
        super(ID, NAME, IMG_PATH, 5, DESCRIPTION, CardType.ATTACK, Dragon.Enums.COLOR_BROWN, CardRarity.RARE, CardTarget.ENEMY);
        this.baseDamage = 30;
        this.baseBlock = 30;
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeDamage(5);
            upgradeBlock(5);
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }

    public void update() {
        super.update();
        if (this.hb.hovered)
            if (this.rotationTimer <= 0.0F) {
                this.rotationTimer = 2.0F;
                this.cardsToPreview = returnChoice().get(previewIndex).makeCopy();
                if (this.previewIndex == returnChoice().size() - 1) {
                    this.previewIndex = 0;
                } else {
                    this.previewIndex++;
                }
                if (this.upgraded)
                    this.cardsToPreview.upgrade();
            } else {
                this.rotationTimer -= Gdx.graphics.getDeltaTime();
            }
    }

    public void applyPowers() {
        super.applyPowers();
        int count = 0;
        for (AbstractCard c : AbstractDungeon.actionManager.cardsPlayedThisCombat) {
            if (c.type != CardType.ATTACK) {
                count++;
            }
        }
        this.rawDescription = cardStrings.DESCRIPTION;
        this.rawDescription += cardStrings.EXTENDED_DESCRIPTION[0] + count;
        this.rawDescription += cardStrings.EXTENDED_DESCRIPTION[1];
        initializeDescription();
    }


    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        int count = 0;
        for (AbstractCard c : AbstractDungeon.actionManager.cardsPlayedThisCombat) {
            if (c.type != CardType.ATTACK && c.hasTag(AbstractShadowversePlayer.Enums.ARMED)) {
                count++;
            }
        }
        if (count > 9 && (EnergyPanel.getCurrentEnergy() + this.costForTurn >= 5)) {
            EnergyPanel.useEnergy(5 - this.costForTurn);
            AbstractCard v = new VirtuousLindworm();
            AbstractCard i = new IniquitousLindworm();
            addToBot(new ChoiceAction(v, i));
        } else {
            addToBot(new DamageAction(abstractMonster, new DamageInfo(abstractPlayer, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HEAVY));
            addToBot(new GainBlockAction(abstractPlayer, this.block));
        }
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new Lindworm();
    }
}

