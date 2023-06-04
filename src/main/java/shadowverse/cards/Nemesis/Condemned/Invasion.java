package shadowverse.cards.Nemesis.Condemned;

import com.badlogic.gdx.Gdx;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.action.DrawPileToHandAction_Tag;
import shadowverse.cards.AbstractEnhanceCard;
import shadowverse.cards.Neutral.Temp.ArmoredTentacle;
import shadowverse.cards.Neutral.Temp.AssaultTentacle;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Nemesis;

import java.util.ArrayList;

public class Invasion extends AbstractEnhanceCard {
    public static final String ID = "shadowverse:Invasion";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Invasion");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/Invasion.png";

    private float rotationTimer;
    private int previewIndex;

    public static ArrayList<AbstractCard> returnTentacle() {
        ArrayList<AbstractCard> list = new ArrayList<>();
        list.add(new ArmoredTentacle());
        list.add(new AssaultTentacle());
        return list;
    }

    public Invasion() {
        super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.SKILL, Nemesis.Enums.COLOR_SKY, CardRarity.UNCOMMON, CardTarget.ALL_ENEMY, 3);
        this.baseDamage = 9;
        this.exhaust = true;
        this.tags.add(AbstractShadowversePlayer.Enums.MACHINE);
    }

    public void update() {
        super.update();
        if (this.hb.hovered)
            if (this.rotationTimer <= 0.0F) {
                this.rotationTimer = 2.0F;
                this.cardsToPreview = returnTentacle().get(previewIndex).makeCopy();
                if (this.previewIndex == returnTentacle().size() - 1) {
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

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }

    @Override
    public void exEnhanceUse(AbstractPlayer p, AbstractMonster m) {

    }

    @Override
    public void enhanceUse(AbstractPlayer p, AbstractMonster m) {
        addToBot(new SFXAction("Belphomet2"));
        addToBot(new DamageAllEnemiesAction(p, DamageInfo.createDamageMatrix(this.damage, true), DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.FIRE, true));
        AbstractCard assault = new AssaultTentacle();
        AbstractCard armored = new ArmoredTentacle();
        if (this.upgraded) {
            armored.upgrade();
            assault.upgrade();
        }
        addToBot(new MakeTempCardInHandAction(assault.makeStatEquivalentCopy()));
        addToBot(new MakeTempCardInHandAction(armored.makeStatEquivalentCopy()));
        addToBot(new DrawPileToHandAction_Tag(1, AbstractShadowversePlayer.Enums.MACHINE, null));
    }

    @Override
    public void baseUse(AbstractPlayer p, AbstractMonster m) {
        addToBot(new SFXAction("Belphomet2"));
        addToBot(new DamageAllEnemiesAction(p, DamageInfo.createDamageMatrix(this.damage, true), DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.FIRE, true));
    }

    public AbstractCard makeCopy() {
        return new Invasion();
    }
}
