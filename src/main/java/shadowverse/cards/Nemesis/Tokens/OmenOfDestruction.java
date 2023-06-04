package shadowverse.cards.Nemesis.Tokens;

import basemod.abstracts.CustomCard;
import com.badlogic.gdx.Gdx;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.ArtifactPower;
import com.megacrit.cardcrawl.vfx.SpotlightPlayerEffect;
import rs.lazymankits.interfaces.cards.BranchableUpgradeCard;
import rs.lazymankits.interfaces.cards.UpgradeBranch;
import shadowverse.action.ChoiceAction2;
import shadowverse.action.ReanimateAction;
import shadowverse.cards.Neutral.Temp.*;
import shadowverse.characters.Nemesis;

import java.util.ArrayList;
import java.util.List;


public class OmenOfDestruction
        extends CustomCard implements BranchableUpgradeCard {
    public static final String ID = "shadowverse:OmenOfDestruction";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:OmenOfDestruction");
    public static CardStrings cardStrings2 = CardCrawlGame.languagePack.getCardStrings("shadowverse:OmenOfDestruction2");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/OmenOfDestruction.png";
    public static final String IMG_PATH2 = "img/cards/OmenOfDestruction2.png";
    private float rotationTimer;
    private int previewIndex;
    private int previewBranch;

    private static ArrayList<AbstractCard> returnChoice() {
        ArrayList<AbstractCard> list = new ArrayList<>();
        list.add(new WhiteArtifact2());
        list.add(new BlackArtifact2());
        list.add(new SoloOfMelody());
        return list;
    }

    private static AbstractCard white = (AbstractCard) new WhiteArtifact();

    public OmenOfDestruction() {
        super(ID, NAME, IMG_PATH, 2, DESCRIPTION, CardType.POWER, Nemesis.Enums.COLOR_SKY, CardRarity.RARE, CardTarget.SELF);
        this.baseMagicNumber = 2;
        this.magicNumber = this.baseMagicNumber;
    }

    public void update() {
        super.update();
        switch (previewBranch) {
            case 0:
                if (this.hb.hovered)
                    this.cardsToPreview = white;
                break;
            case 1:
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
                break;
        }
    }

    public void upgrade() {
        ((UpgradeBranch)((BranchableUpgradeCard)this).possibleBranches().get(chosenBranch())).upgrade();
    }


    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        AbstractDungeon.effectsQueue.add(new SpotlightPlayerEffect());
        addToBot((AbstractGameAction) new ApplyPowerAction((AbstractCreature) abstractPlayer, (AbstractCreature) abstractPlayer, (AbstractPower) new ArtifactPower((AbstractCreature) abstractPlayer, this.magicNumber), this.magicNumber));
        switch (chosenBranch()){
            case 0:
                addToBot((AbstractGameAction) new SFXAction("OmenOfDestruction"));
                addToBot((AbstractGameAction) new MakeTempCardInHandAction(white.makeStatEquivalentCopy()));
                break;
            case 1:
                addToBot((AbstractGameAction) new SFXAction("OmenOfDestruction2"));
                int amt = 0;
                for (AbstractCard card:abstractPlayer.exhaustPile.group){
                    if (card.type==CardType.ATTACK)
                        amt++;
                }
                if (amt<10){
                    addToBot((AbstractGameAction)new ChoiceAction2(new AbstractCard[] {new WhiteArtifact2(),new BlackArtifact2(),new SoloOfMelody()}));
                }else {
                    addToBot((AbstractGameAction)new MakeTempCardInHandAction(new WhiteArtifact2()));
                    addToBot((AbstractGameAction)new MakeTempCardInHandAction(new BlackArtifact2()));
                    addToBot((AbstractGameAction)new MakeTempCardInHandAction(new SoloOfMelody()));
                }
                addToBot((AbstractGameAction)new ReanimateAction(0));
                addToBot((AbstractGameAction)new ReanimateAction(0));
                addToBot((AbstractGameAction)new ReanimateAction(1));
                break;
        }
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new OmenOfDestruction();
    }

    @Override
    public List<UpgradeBranch> possibleBranches() {
        ArrayList<UpgradeBranch> list = new ArrayList<UpgradeBranch>();
        list.add(new UpgradeBranch() {
            @Override
            public void upgrade() {
                ++OmenOfDestruction.this.timesUpgraded;
                OmenOfDestruction.this.upgraded = true;
                OmenOfDestruction.this.name = NAME + "+";
                OmenOfDestruction.this.initializeTitle();
                OmenOfDestruction.this.isInnate = true;;
                rawDescription = cardStrings.UPGRADE_DESCRIPTION;
                initializeDescription();
                OmenOfDestruction.this.previewBranch = 0;
            }
        });
        list.add(new UpgradeBranch() {
            @Override
            public void upgrade() {
                ++OmenOfDestruction.this.timesUpgraded;
                OmenOfDestruction.this.upgraded = true;
                OmenOfDestruction.this.textureImg = IMG_PATH2;
                OmenOfDestruction.this.loadCardImage(IMG_PATH2);
                OmenOfDestruction.this.name = cardStrings2.NAME;
                OmenOfDestruction.this.initializeTitle();
                OmenOfDestruction.this.rawDescription = cardStrings2.DESCRIPTION;
                OmenOfDestruction.this.initializeDescription();
                OmenOfDestruction.this.previewBranch = 1;
                OmenOfDestruction.this.type = CardType.ATTACK;
                OmenOfDestruction.this.exhaust = true;
            }
        });
        return list;
    }
}
