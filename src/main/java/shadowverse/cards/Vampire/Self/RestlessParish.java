package shadowverse.cards.Vampire.Self;

import com.badlogic.gdx.Gdx;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.actions.watcher.ChooseOneAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DrawCardNextTurnPower;
import shadowverse.cards.AbstractEnhanceCard;
import shadowverse.cards.Neutral.Temp.MadnessRevealed;
import shadowverse.cards.Neutral.Temp.NightFall;
import shadowverse.characters.Vampire;

import java.util.ArrayList;

public class RestlessParish
        extends AbstractEnhanceCard {
    public static final String ID = "shadowverse:RestlessParish";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:RestlessParish");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/RestlessParish.png";
    private float rotationTimer;
    private int previewIndex;

    public static ArrayList<AbstractCard> returnChoice() {
        ArrayList<AbstractCard> list = new ArrayList<>();
        list.add(new NightFall());
        list.add(new MadnessRevealed());
        return list;
    }

    public RestlessParish() {
        super(ID, NAME, IMG_PATH, 0, DESCRIPTION, CardType.POWER, Vampire.Enums.COLOR_SCARLET, CardRarity.COMMON, CardTarget.SELF,1);
        this.baseMagicNumber = 1;
        this.magicNumber = this.baseMagicNumber;
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

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeMagicNumber(1);
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }

    @Override
    public void exEnhanceUse(AbstractPlayer p, AbstractMonster m) {

    }

    @Override
    public void enhanceUse(AbstractPlayer p, AbstractMonster m) {
        ArrayList<AbstractCard> stanceChoices = new ArrayList<>();
        AbstractCard n = new NightFall();
        AbstractCard mad = new MadnessRevealed();
        if (this.upgraded) {
            n.upgrade();
            mad.upgrade();
        }
        stanceChoices.add(n);
        stanceChoices.add(mad);
        addToBot(new ChooseOneAction(stanceChoices));
    }

    @Override
    public void baseUse(AbstractPlayer p, AbstractMonster m) {
        addToBot(new LoseHPAction(p, p, 1));
        addToBot(new ApplyPowerAction(p, p, new DrawCardNextTurnPower(p, magicNumber), magicNumber));
    }


    public AbstractCard makeCopy() {
        return new RestlessParish();
    }
}


