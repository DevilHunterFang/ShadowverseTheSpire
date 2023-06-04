package shadowverse.cards.Bishop.Amulet2;

import com.badlogic.gdx.Gdx;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.cards.AbstractAmuletCard;
import shadowverse.cards.Neutral.Temp.HolyFalcon;
import shadowverse.cards.Neutral.Temp.HolyFlameTiger;
import shadowverse.characters.Bishop;
import shadowverse.orbs.AmuletOrb;

import java.util.ArrayList;

public class BeastCallAria extends AbstractAmuletCard {
    public static final String ID = "shadowverse:BeastCallAria";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:BeastCallAria");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/BeastCallAria.png";
    private float rotationTimer;
    private int previewIndex;
    public static ArrayList<AbstractCard> returnChoice() {
        ArrayList<AbstractCard> list = new ArrayList<>();
        list.add(new HolyFalcon());
        list.add(new HolyFlameTiger());
        return list;
    }

    public BeastCallAria() {
        super(ID, NAME, IMG_PATH, 1, DESCRIPTION, Bishop.Enums.COLOR_WHITE, CardRarity.COMMON, CardTarget.NONE);
        this.countDown = 2;
    }

    public void update() {
        super.update();
        if (this.hb.hovered)
            if (this.rotationTimer <= 0.0F) {
                this.rotationTimer = 2.0F;
                this.cardsToPreview = (AbstractCard)returnChoice().get(previewIndex).makeCopy();
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

    @Override
    public void onStartOfTurn(AmuletOrb paramOrb) {

    }

    @Override
    public void onEvoke(AmuletOrb paramOrb) {
        AbstractCard f = new HolyFalcon();
        AbstractCard t = new HolyFlameTiger();
        if (this.upgraded){
            f.upgrade();
            t.upgrade();
        }
        addToBot((AbstractGameAction)new MakeTempCardInHandAction(f));
        addToBot((AbstractGameAction)new MakeTempCardInHandAction(t));
    }

    @Override
    public void endOfTurn(AmuletOrb paramOrb) {

    }

    @Override
    public int onHeal(int healAmount, AmuletOrb paramOrb) {
        return 0;
    }

    @Override
    public void onOtherCardPlayed(AbstractCard c, AmuletOrb paramOrb) {

    }

    @Override
    public void onGainedBlock(int blockAmt, AmuletOrb paramOrb) {

    }


    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {

    }
}
