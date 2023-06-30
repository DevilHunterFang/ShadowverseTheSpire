package shadowverse.cards.Bishop.Default;

import basemod.abstracts.CustomCard;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.BorderFlashEffect;
import shadowverse.cards.Neutral.Temp.*;
import shadowverse.characters.Bishop;
import shadowverse.powers.ErisPower;

import java.util.ArrayList;

public class Eris
        extends CustomCard {
    public static final String ID = "shadowverse:Eris";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Eris");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/Eris.png";
    private float rotationTimer;
    private int previewIndex;
    public static ArrayList<AbstractCard> returnChoice() {
        ArrayList<AbstractCard> list = new ArrayList<>();
        list.add(new RelicSphere());
        list.add(new RelicPrism());
        list.add(new RelicTorus());
        list.add(new RelicPlaton());
        return list;
    }

    public Eris() {
        super(ID, NAME, IMG_PATH, 2, DESCRIPTION, CardType.ATTACK, Bishop.Enums.COLOR_WHITE, CardRarity.RARE, CardTarget.SELF);
        this.baseBlock = 8;
        this.exhaust = true;
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBlock(3);
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


    public void use(AbstractPlayer p, AbstractMonster abstractMonster) {
        addToBot(new SFXAction("Eris"));
        addToBot(new VFXAction(new BorderFlashEffect(Color.GOLDENROD, true)));
        addToBot(new GainBlockAction(p,this.block));
        addToBot( new ApplyPowerAction(p, p, new ErisPower(p,this.upgraded)));
        AbstractCard c = new RelicSphere();
        if (this.upgraded)
            c.upgrade();
        addToBot(new MakeTempCardInHandAction(c));
    }


    public AbstractCard makeCopy() {
        return  new Eris();
    }
}


