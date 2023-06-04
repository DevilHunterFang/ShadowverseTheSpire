package shadowverse.cards.Vampire.Self;

import basemod.abstracts.CustomCard;
import com.badlogic.gdx.Gdx;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.IntangiblePlayerPower;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import shadowverse.Shadowverse;
import shadowverse.cards.Neutral.Temp.*;
import shadowverse.characters.Vampire;
import shadowverse.powers.*;

import java.util.ArrayList;


public class TheTemperance
        extends CustomCard {
    public static final String ID = "shadowverse:TheTemperance";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:TheTemperance");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/TheTemperance.png";
    private float rotationTimer;
    private int previewIndex;
    public static ArrayList<AbstractCard> returnChoice(){
        ArrayList<AbstractCard> list = new ArrayList<>();
        list.add(new InsatiableDesire());
        return list;
    }

    public TheTemperance() {
        super(ID, NAME, IMG_PATH, 4, DESCRIPTION, CardType.POWER, Vampire.Enums.COLOR_SCARLET, CardRarity.RARE, CardTarget.SELF);
        this.baseMagicNumber = 3;
        this.magicNumber = this.baseMagicNumber;
        this.exhaust = true;
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeMagicNumber(1);
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }

    public void update() {
        if (AbstractDungeon.currMapNode != null && (AbstractDungeon.getCurrRoom()).phase == AbstractRoom.RoomPhase.COMBAT&&
                Shadowverse.Accelerate(this)){
            setCostForTurn(0);
            this.type = CardType.SKILL;
        }else {
            if (this.type==CardType.SKILL){
                setCostForTurn(4);
                this.type = CardType.POWER;
            }
        }
        super.update();
        if (this.hb.hovered)
            if (this.rotationTimer <= 0.0F) {
                this.rotationTimer = 2.0F;
                this.cardsToPreview = (AbstractCard) returnChoice().get(previewIndex).makeCopy();
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

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (Shadowverse.Accelerate((AbstractCard)this) && this.type == CardType.SKILL) {
            addToBot(new SFXAction("TheTemperance_Acc"));
            if (p.hasPower(AvaricePower.POWER_ID)||p.hasPower(EpitaphPower.POWER_ID)){
                AbstractCard i = new InsatiableDesire();
                if (this.upgraded)
                    i.upgrade();
                addToBot(new MakeTempCardInHandAction(i));
            }else {
                AbstractCard u = new UnselfishGrace();
                if (this.upgraded)
                    u.upgrade();
                addToBot(new MakeTempCardInHandAction(u));
            }
        }else {
            addToBot((AbstractGameAction) new SFXAction("TheTemperance"));
            addToBot((AbstractGameAction) new ApplyPowerAction(p, p, new TheTemperancePower(p, this.magicNumber,0), this.magicNumber));
            addToBot((AbstractGameAction) new ApplyPowerAction(p,p,new IntangiblePlayerPower(p,1)));
        }
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new TheTemperance();
    }
}


