package shadowverse.cards.Elf.Wood;

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
import com.megacrit.cardcrawl.powers.AbstractPower;
import shadowverse.action.ChoiceAction2;
import shadowverse.cards.Neutral.Temp.ArisaArrow;
import shadowverse.cards.Neutral.Temp.GaleArrow;
import shadowverse.cards.Neutral.Temp.StormArrow;
import shadowverse.characters.Elf;
import shadowverse.powers.ArisaPower;

import java.util.ArrayList;

public class Arisa
        extends CustomCard {
    public static final String ID = "shadowverse:Arisa";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Arisa");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/Arisa.png";
    private float rotationTimer;
    private int previewIndex;

    public static ArrayList<AbstractCard> returnChoice() {
        ArrayList<AbstractCard> list = new ArrayList<>();
        list.add(new ArisaArrow());
        list.add(new StormArrow());
        list.add(new GaleArrow());
        return list;
    }

    public Arisa() {
        super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.POWER, Elf.Enums.COLOR_GREEN, CardRarity.RARE, CardTarget.SELF);
        this.baseMagicNumber = 3;
        this.magicNumber = this.baseMagicNumber;
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


    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        AbstractCard arrow = (AbstractCard) new ArisaArrow();
        AbstractCard storm = (AbstractCard) new StormArrow();
        AbstractCard gale = (AbstractCard) new GaleArrow();
        if (this.upgraded) {
            arrow.upgrade();
            storm.upgrade();
            gale.upgrade();
        }
        addToBot((AbstractGameAction) new SFXAction("Arisa"));
        int count = 0;
        for (AbstractCard c : AbstractDungeon.actionManager.cardsPlayedThisTurn) {
            count++;
        }
        count--;
        if (count >= 2) {
            addToBot((AbstractGameAction) new MakeTempCardInHandAction(arrow));
        }
        addToBot((AbstractGameAction)new ApplyPowerAction(abstractPlayer,abstractPlayer,(AbstractPower)new ArisaPower(abstractPlayer,this.magicNumber),this.magicNumber));
        addToBot((AbstractGameAction) new ChoiceAction2(new AbstractCard[]{storm, gale}));
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new Arisa();
    }
}

