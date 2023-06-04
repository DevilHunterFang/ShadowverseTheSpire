package shadowverse.cards.Vampire.NatMech;

import basemod.abstracts.CustomCard;
import com.badlogic.gdx.Gdx;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.cards.Neutral.Temp.ProductMachine;
import shadowverse.cards.Neutral.Temp.RepairMode;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Vampire;

import java.util.ArrayList;


public class MechaforgeDevil
        extends CustomCard {
    public static final String ID = "shadowverse:MechaforgeDevil";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:MechaforgeDevil");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/MechaforgeDevil.png";
    private float rotationTimer;
    private int previewIndex;

    public static ArrayList<AbstractCard> returnChoice() {
        ArrayList<AbstractCard> list = new ArrayList<>();
        list.add(new ProductMachine());
        list.add(new RepairMode());
        return list;
    }

    public MechaforgeDevil() {
        super(ID, NAME, IMG_PATH, 2, DESCRIPTION, CardType.ATTACK, Vampire.Enums.COLOR_SCARLET, CardRarity.COMMON, CardTarget.SELF);
        this.baseBlock = 8;
        this.tags.add(AbstractShadowversePlayer.Enums.MACHINE);
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
            } else {
                this.rotationTimer -= Gdx.graphics.getDeltaTime();
            }
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBlock(3);
        }
    }


    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot((AbstractGameAction) new SFXAction("MechaforgeDevil"));
        addToBot((AbstractGameAction) new GainBlockAction((AbstractCreature) abstractPlayer, (AbstractCreature) abstractPlayer, this.block));
        addToBot((AbstractGameAction) new MakeTempCardInHandAction((AbstractCard) new RepairMode(), 1));
        addToBot((AbstractGameAction) new MakeTempCardInHandAction((AbstractCard)new ProductMachine(), 1));
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new MechaforgeDevil();
    }
}


