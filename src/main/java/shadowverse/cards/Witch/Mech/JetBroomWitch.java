package shadowverse.cards.Witch.Mech;

import basemod.abstracts.CustomCard;
import com.badlogic.gdx.Gdx;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.action.ChoiceAction;
import shadowverse.cards.Neutral.Temp.ProductMachine;
import shadowverse.cards.Neutral.Temp.RepairMode;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Witchcraft;

import java.util.ArrayList;

public class JetBroomWitch
        extends CustomCard {
    public static final String ID = "shadowverse:JetBroomWitch";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:JetBroomWitch");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/JetBroomWitch.png";
    private float rotationTimer;
    private int previewIndex;

    public static ArrayList<AbstractCard> returnChoice() {
        ArrayList<AbstractCard> list = new ArrayList<>();
        list.add(new ProductMachine());
        list.add(new RepairMode());
        return list;
    }

    public JetBroomWitch() {
        super("shadowverse:JetBroomWitch", NAME, "img/cards/JetBroomWitch.png", 1, DESCRIPTION, CardType.ATTACK, Witchcraft.Enums.COLOR_BLUE, CardRarity.COMMON, CardTarget.SELF);
        this.baseBlock = 5;
        this.tags.add(AbstractShadowversePlayer.Enums.MACHINE);
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBlock(3);
        }
    }

    public void update() {
        super.update();
        if (this.hb.hovered)
            if (this.rotationTimer <= 0.0F) {
                this.rotationTimer = 2.0F;
                this.cardsToPreview =  returnChoice().get(previewIndex).makeCopy();
                if (this.previewIndex == returnChoice().size() - 1) {
                    this.previewIndex = 0;
                } else {
                    this.previewIndex++;
                }
            } else {
                this.rotationTimer -= Gdx.graphics.getDeltaTime();
            }
    }


    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new SFXAction("JetBroomWitch"));
        addToBot(new GainBlockAction(abstractPlayer, abstractPlayer, this.block));
        addToBot(new ChoiceAction(new AbstractCard[]{ new RepairMode(),  new ProductMachine()}));
    }


    public AbstractCard makeCopy() {
        return  new JetBroomWitch();
    }
}

