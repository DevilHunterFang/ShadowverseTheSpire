package shadowverse.cards.Dragon.Tempo;


import com.badlogic.gdx.Gdx;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.cards.AbstractEnhanceCard;
import shadowverse.cards.Dragon.Discard1.Heliodragon;
import shadowverse.cards.Neutral.Temp.HellFlameDragon;
import shadowverse.cards.Neutral.Temp.InfernoDragon;
import shadowverse.cards.Neutral.Temp.IniquitousLindworm;
import shadowverse.cards.Neutral.Temp.VirtuousLindworm;
import shadowverse.characters.Dragon;
import shadowverse.powers.DragonHoardPower;

import java.util.ArrayList;

public class DragonHoard extends AbstractEnhanceCard {
    public static final String ID = "shadowverse:DragonHoard";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:DragonHoard");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/DragonHoard.png";
    private float rotationTimer;
    private int previewIndex;

    public static ArrayList<AbstractCard> returnChoice() {
        ArrayList<AbstractCard> list = new ArrayList<>();
        list.add(new InfernoDragon());
        list.add(new HellFlameDragon());
        return list;
    }

    public DragonHoard() {
        super(ID, NAME, IMG_PATH, 0, DESCRIPTION, CardType.POWER, Dragon.Enums.COLOR_BROWN, CardRarity.UNCOMMON, CardTarget.SELF, 2);
        this.cardsToPreview = new Heliodragon();
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
            } else {
                this.rotationTimer -= Gdx.graphics.getDeltaTime();
            }
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            this.isInnate = true;
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }


    @Override
    public void exEnhanceUse(AbstractPlayer p, AbstractMonster m) {

    }

    @Override
    public void enhanceUse(AbstractPlayer p, AbstractMonster m) {
        addToBot(new MakeTempCardInHandAction(new HellFlameDragon(),2));
        addToBot(new ApplyPowerAction(p,p,new DragonHoardPower(p,8)));
    }

    @Override
    public void baseUse(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p,p,new DragonHoardPower(p,16)));
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new DragonHoard();
    }
}

