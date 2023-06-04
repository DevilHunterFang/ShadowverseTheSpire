package shadowverse.cards.Royal.Evolve;

import basemod.abstracts.CustomCard;
import com.badlogic.gdx.Gdx;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.cards.Neutral.Status.EvolutionPoint;
import shadowverse.cards.Neutral.Temp.GildedBlade;
import shadowverse.cards.Neutral.Temp.GildedBoots;
import shadowverse.cards.Neutral.Temp.GildedGoblet;
import shadowverse.cards.Neutral.Temp.GildedNecklace;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Royal;
import shadowverse.relics.KagemitsuSword;

import java.util.ArrayList;

public class AdherentOfHollow extends CustomCard {
    public static final String ID = "shadowverse:AdherentOfHollow";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/AdherentOfHollow.png";
    private int previewIndex;
    private float rotationTimer;

    public static ArrayList<AbstractCard> returnProphecy() {
        ArrayList<AbstractCard> list = new ArrayList<>();
        list.add(new GildedBlade());
        list.add(new GildedNecklace());
        list.add(new GildedGoblet());
        list.add(new GildedBoots());
        return list;
    }

    public AdherentOfHollow() {
        super(ID, NAME, IMG_PATH, 0, DESCRIPTION, CardType.ATTACK, Royal.Enums.COLOR_YELLOW, CardRarity.COMMON, CardTarget.ENEMY);
        this.baseDamage = 2;
    }

    @Override
    public void update() {
        super.update();
        if (this.hb.hovered && this.upgraded) {
            if (this.rotationTimer <= 0.0F) {
                this.rotationTimer = 2.0F;
                this.cardsToPreview = returnProphecy().get(previewIndex).makeCopy();
                if (this.previewIndex == returnProphecy().size() - 1) {
                    this.previewIndex = 0;
                } else {
                    this.previewIndex++;
                }
            } else {
                this.rotationTimer -= Gdx.graphics.getDeltaTime();
            }
        }
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeDamage(2);
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new SFXAction(ID.replace("shadowverse:", "")));
        addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        this.degrade();
        if(p.hasRelic(KagemitsuSword.ID)){
            this.upgrade();
        }
    }

    @Override
    public void triggerOnOtherCardPlayed(AbstractCard c) {
        if (!this.upgraded && c.hasTag(AbstractShadowversePlayer.Enums.GILDED)) {
            this.superFlash();
            this.upgrade();
            addToBot(new SFXAction("AdherentOfHollow_Ev"));
        } else if (c instanceof EvolutionPoint) {
            AbstractCard c1 = returnProphecy().get(AbstractDungeon.cardRandomRng.random(3));
            addToBot(new MakeTempCardInHandAction(c1));
            addToBot(new SFXAction("AdherentOfHollow_Eff"));
        }
    }

    public void degrade() {
        if (this.upgraded) {
            degradeName();
            this.superFlash();
            this.applyPowers();
        }
    }

    public void degradeName() {
        --this.timesUpgraded;
        this.upgraded = false;
        this.name = NAME;
        this.rawDescription = cardStrings.DESCRIPTION;
        initializeDescription();
        this.initializeTitle();
    }

    @Override
    public AbstractCard makeCopy() {
        return new AdherentOfHollow();
    }
}

