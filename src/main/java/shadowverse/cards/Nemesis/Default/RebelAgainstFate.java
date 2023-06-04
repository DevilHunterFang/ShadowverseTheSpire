package shadowverse.cards.Nemesis.Default;

import basemod.abstracts.CustomCard;
import com.badlogic.gdx.Gdx;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.actions.watcher.ChooseOneAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.random.Random;
import shadowverse.cards.Neutral.Temp.*;
import shadowverse.characters.Nemesis;
import shadowverse.stance.Resonance;

import java.util.ArrayList;


public class RebelAgainstFate
        extends CustomCard {
    public static final String ID = "shadowverse:RebelAgainstFate";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:RebelAgainstFate");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/RebelAgainstFate.png";
    private float rotationTimer;
    private int previewIndex;
    public static ArrayList<AbstractCard> returnChoice() {
        ArrayList<AbstractCard> list = new ArrayList<>();
        list.add(new AncientArtifact());
        list.add(new MysticArtifact());
        list.add(new TisiphoneCard());
        list.add(new AlectorCard());
        list.add(new MegaeraCard());
        return list;
    }

    public static ArrayList<AbstractCard> returnElinese(){
        ArrayList<AbstractCard> list = new ArrayList<>();
        list.add(new TisiphoneCard());
        list.add(new AlectorCard());
        list.add(new MegaeraCard());
        return list;
    }
    public static AbstractCard returnRandomElinese(Random rng){
        return returnElinese().get(rng.random(returnElinese().size() - 1));
    }

    public RebelAgainstFate() {
        super(ID, NAME, IMG_PATH, 2, DESCRIPTION, CardType.SKILL, Nemesis.Enums.COLOR_SKY, CardRarity.UNCOMMON, CardTarget.SELF);
        this.exhaust = true;
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
            } else {
                this.rotationTimer -= Gdx.graphics.getDeltaTime();
            }
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBaseCost(1);
        }
    }


    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        ArrayList<AbstractCard> stanceChoices = new ArrayList<>();
        stanceChoices.add(new YuwanFury());
        stanceChoices.add(new BelphometCrackdown());
        if (abstractPlayer.stance.ID.equals(Resonance.STANCE_ID)){
            addToBot((AbstractGameAction)new SFXAction("RebelAgainstFate"));
            AbstractCard a = (AbstractCard)new AncientArtifact();
            AbstractCard m = (AbstractCard)new MysticArtifact();
            m.setCostForTurn(0);
            addToBot((AbstractGameAction)new MakeTempCardInHandAction(a));
            addToBot((AbstractGameAction)new MakeTempCardInHandAction(m));
            AbstractCard c = returnRandomElinese(AbstractDungeon.cardRandomRng).makeStatEquivalentCopy();
            c.retain= true;
            c.selfRetain = true;
            c.initializeDescription();
            c.applyPowers();
            abstractPlayer.hand.addToTop(c);
        }else {
            addToBot((AbstractGameAction)new ChooseOneAction(stanceChoices));
        }
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new RebelAgainstFate();
    }
}

