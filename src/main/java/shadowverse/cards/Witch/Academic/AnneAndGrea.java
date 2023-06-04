package shadowverse.cards.Witch.Academic;


import com.badlogic.gdx.Gdx;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.cards.AbstractEnhanceCard;
import shadowverse.cards.Neutral.Temp.*;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Witchcraft;

import java.util.ArrayList;


public class AnneAndGrea extends AbstractEnhanceCard {
    public static final String ID = "shadowverse:AnneAndGrea";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:AnneAndGrea");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/AnneAndGrea.png";
    private float rotationTimer;
    private int previewIndex;

    public static ArrayList<AbstractCard> returnChoice() {
        ArrayList<AbstractCard> list = new ArrayList<>();
        list.add(new MajesticSorcery());
        list.add(new AnnesSummoning());
        return list;
    }

    public AnneAndGrea() {
        super(ID, NAME, IMG_PATH, 2, DESCRIPTION, CardType.ATTACK, Witchcraft.Enums.COLOR_BLUE, CardRarity.RARE, CardTarget.ALL_ENEMY,3);
        this.baseDamage = 15;
        this.tags.add(AbstractShadowversePlayer.Enums.MYSTERIA);
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
            upgradeDamage(5);
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
                if (this.upgraded){
                    if (this.cardsToPreview instanceof MajesticSorcery)
                        this.cardsToPreview.upgrade();
                }
            } else {
                this.rotationTimer -= Gdx.graphics.getDeltaTime();
            }
    }

    @Override
    public void exEnhanceUse(AbstractPlayer p, AbstractMonster m) {

    }

    @Override
    public void enhanceUse(AbstractPlayer p, AbstractMonster m) {
        if (AbstractDungeon.player instanceof AbstractShadowversePlayer) {
            ((AbstractShadowversePlayer) AbstractDungeon.player).mysteriaCount++;
        }
        addToBot(new SFXAction("AnneAndGrea_Eh"));
        addToBot(new DamageAllEnemiesAction(p, DamageInfo.createDamageMatrix(this.damage, true), DamageInfo.DamageType.NORMAL, AbstractGameAction.AttackEffect.FIRE));
        AbstractCard ms = new MajesticSorcery();
        if (upgraded)
            ms.upgrade();
        ms.modifyCostForCombat(-3);
        addToBot(new MakeTempCardInHandAction(ms));
        AbstractCard as = new AnnesSummoning();
        as.modifyCostForCombat(-1);
        addToBot(new MakeTempCardInHandAction(as));
    }

    @Override
    public void baseUse(AbstractPlayer p, AbstractMonster m) {
        if (AbstractDungeon.player instanceof AbstractShadowversePlayer) {
            ((AbstractShadowversePlayer) AbstractDungeon.player).mysteriaCount++;
        }
        addToBot(new SFXAction("AnneAndGrea"));
        addToBot(new DamageAllEnemiesAction(p, DamageInfo.createDamageMatrix(this.damage, true), DamageInfo.DamageType.NORMAL, AbstractGameAction.AttackEffect.FIRE));
        AbstractCard ms = new MajesticSorcery();
        if (upgraded)
            ms.upgrade();
        addToBot(new MakeTempCardInHandAction(ms));
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new AnneAndGrea();
    }
}

