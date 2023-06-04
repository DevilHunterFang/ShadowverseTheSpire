package shadowverse.cards.Nemesis.Puppet;

import com.badlogic.gdx.Gdx;
import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsInHandAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.actions.watcher.ChooseOneAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.ShockWaveEffect;
import rs.lazymankits.interfaces.cards.BranchableUpgradeCard;
import rs.lazymankits.interfaces.cards.UpgradeBranch;
import shadowverse.action.LinkHeartChoiceAction;
import shadowverse.cards.AbstractRightClickCard2;
import shadowverse.cards.Neutral.Temp.*;
import shadowverse.characters.Nemesis;
import shadowverse.powers.OrchidPower;

import java.util.ArrayList;
import java.util.List;

public class Orchid extends AbstractRightClickCard2 implements BranchableUpgradeCard{
    public static final String ID = "shadowverse:Orchid";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Orchid");
    public static CardStrings cardStrings2 = CardCrawlGame.languagePack.getCardStrings("shadowverse:OrchidNeo");
    public static CardStrings cardStrings3 = CardCrawlGame.languagePack.getCardStrings("shadowverse:Orchid_EW");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/Orchid.png";
    public static final String IMG_PATH2 = "img/cards/OrchidNeo.png";
    public static final String IMG_PATH3 = "img/cards/Orchid_EW.png";
    private float rotationTimer;
    private int previewIndex;
    private int previewBranch;
    private boolean hasFusion = false;


    public static ArrayList<AbstractCard> returnChoice(){
        ArrayList<AbstractCard> list = new ArrayList<>();
        list.add(new Orchid2());
        list.add(new Orchid1());
        return list;
    }


    public static ArrayList<AbstractCard> returnLinkHeart(){
        ArrayList<AbstractCard> list = new ArrayList<>();
        list.add(new Roid());
        list.add(new Uno());
        list.add(new Due());
        list.add(new Puppet());
        list.add(new Tre());
        return list;
    }

    public static ArrayList<AbstractCard> returnEndlessWorld(){
        ArrayList<AbstractCard> list = new ArrayList<>();
        list.add(new Roid_EW());
        list.add(new Puppet());
        return list;
    }



    public Orchid() {
        super(ID, NAME, IMG_PATH, 3, DESCRIPTION, CardType.ATTACK, Nemesis.Enums.COLOR_SKY, CardRarity.RARE, CardTarget.SELF);
        this.baseBlock = 20;
    }

    @Override
    protected void onRightClick() {
        if (this.chosenBranch() == 2){
            if (!this.hasFusion && AbstractDungeon.player!=null){
                addToBot((AbstractGameAction)new SelectCardsInHandAction(9,TEXT[0],true,true, card -> {
                    return card instanceof Puppet;
                }, abstractCards -> {
                    if (abstractCards.size()>0){
                        AbstractCard pup = new Puppet();
                        pup.baseDamage += 2;
                        pup.applyPowers();
                        addToBot((AbstractGameAction)new MakeTempCardInHandAction(pup));
                    }
                    for (AbstractCard c:abstractCards){
                        this.magicNumber++;
                        this.applyPowers();
                        addToBot((AbstractGameAction)new ExhaustSpecificCardAction(c,AbstractDungeon.player.hand));
                    }
                }));
                this.hasFusion = true;
            }
        }
    }

    @Override
    public void atTurnStart(){
        hasFusion = false;
    }

    public void update() {
        super.update();
            switch (previewBranch){
                case 0:
                default:
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
                    break;
                case 1:
                    if (this.hb.hovered)
                        if (this.rotationTimer <= 0.0F) {
                            this.rotationTimer = 2.0F;
                            this.cardsToPreview = (AbstractCard)returnLinkHeart().get(previewIndex).makeCopy();
                            if (this.previewIndex == returnLinkHeart().size() - 1) {
                                this.previewIndex = 0;
                            } else {
                                this.previewIndex++;
                            }
                        } else {
                            this.rotationTimer -= Gdx.graphics.getDeltaTime();
                        }
                    break;
                case 2:
                    if (this.hb.hovered)
                        if (this.rotationTimer <= 0.0F) {
                            this.rotationTimer = 2.0F;
                            this.cardsToPreview = (AbstractCard)returnEndlessWorld().get(previewIndex).makeCopy();
                            if (this.previewIndex == returnEndlessWorld().size() - 1) {
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

    @Override
    public List<UpgradeBranch> possibleBranches() {
        ArrayList<UpgradeBranch> list = new ArrayList<UpgradeBranch>();
        list.add(new UpgradeBranch() {
            @Override
            public void upgrade() {
                ++Orchid.this.timesUpgraded;
                Orchid.this.upgraded = true;
                Orchid.this.name = NAME + "+";
                Orchid.this.initializeTitle();
                Orchid.this.baseBlock = 25;
                Orchid.this.upgradedBlock = true;
                rawDescription = cardStrings.UPGRADE_DESCRIPTION;
                initializeDescription();
                Orchid.this.previewBranch = 0;
            }
        });
        list.add(new UpgradeBranch() {
            @Override
            public void upgrade() {
                ++Orchid.this.timesUpgraded;
                Orchid.this.upgraded = true;
                Orchid.this.textureImg = IMG_PATH2;
                Orchid.this.loadCardImage(IMG_PATH2);
                Orchid.this.name = cardStrings2.NAME;
                Orchid.this.initializeTitle();
                Orchid.this.upgradeBaseCost(2);
                Orchid.this.baseBlock = 15;
                Orchid.this.upgradedBlock = true;
                Orchid.this.rawDescription = cardStrings2.DESCRIPTION;
                Orchid.this.initializeDescription();
                Orchid.this.previewBranch = 1;
            }
        });
        list.add(new UpgradeBranch() {
            @Override
            public void upgrade() {
                ++Orchid.this.timesUpgraded;
                Orchid.this.upgraded = true;
                Orchid.this.textureImg = IMG_PATH3;
                Orchid.this.loadCardImage(IMG_PATH3);
                Orchid.this.name = cardStrings3.NAME;
                Orchid.this.initializeTitle();
                Orchid.this.upgradeBaseCost(2);
                Orchid.this.baseBlock = 10;
                Orchid.this.upgradedBlock = true;
                Orchid.this.baseMagicNumber = 0;
                Orchid.this.magicNumber = Orchid.this.baseMagicNumber;
                Orchid.this.upgradedMagicNumber = true;
                Orchid.this.rawDescription = cardStrings3.DESCRIPTION;
                Orchid.this.initializeDescription();
                Orchid.this.previewBranch = 2;
            }
        });
        return list;
    }

    @Override
    public void upgrade() {
        ((UpgradeBranch)((BranchableUpgradeCard)this).possibleBranches().get(chosenBranch())).upgrade();
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot((AbstractGameAction)new GainBlockAction(abstractPlayer,this.block));
        addToBot((AbstractGameAction)new VFXAction((AbstractCreature)abstractPlayer, (AbstractGameEffect)new ShockWaveEffect(abstractPlayer.hb.cX, abstractPlayer.hb.cY, Settings.BLUE_TEXT_COLOR, ShockWaveEffect.ShockWaveType.CHAOTIC), 0.2F));
        if (!this.upgraded){
            int count = 0;
            for (AbstractCard c:abstractPlayer.hand.group){
                if (c instanceof Puppet)
                    count++;
            }
            if (count>=2){
                ArrayList<AbstractCard> stanceChoices = new ArrayList<>();
                stanceChoices.add(new Orchid2());
                stanceChoices.add(new Orchid1());
                addToBot((AbstractGameAction)new ChooseOneAction(stanceChoices));
            }else {
                addToBot((AbstractGameAction)new SFXAction("Orchid"));
                addToBot((AbstractGameAction)new MakeTempCardInHandAction((AbstractCard)new Puppet(),3));
            }
        }else {
            switch (chosenBranch()){
                case 0:
                    int count = 0;
                    for (AbstractCard c:abstractPlayer.hand.group){
                        if (c instanceof Puppet)
                            count++;
                    }
                    if (count>=2){
                        ArrayList<AbstractCard> stanceChoices = new ArrayList<>();
                        AbstractCard o1 = new Orchid2();
                        AbstractCard o2 = new Orchid1();
                        o1.upgrade();
                        o2.upgrade();
                        stanceChoices.add(o1);
                        stanceChoices.add(o2);
                        addToBot((AbstractGameAction)new ChooseOneAction(stanceChoices));
                    }else {
                        addToBot((AbstractGameAction)new SFXAction("Orchid"));
                        addToBot((AbstractGameAction)new MakeTempCardInHandAction((AbstractCard)new Puppet(),3));
                    }
                    break;
                case 1:
                    addToBot((AbstractGameAction)new SFXAction("OrchidNeo"));
                    addToBot((AbstractGameAction)new SelectCardsInHandAction(1,TEXT[0],false,false, card -> {
                        return card instanceof Puppet;
                    }, abstractCards ->{
                        for (AbstractCard c:abstractCards){
                            addToBot((AbstractGameAction)new ExhaustSpecificCardAction(c,abstractPlayer.hand));
                            addToBot((AbstractGameAction)new MakeTempCardInHandAction((AbstractCard)new Tre()));
                        }
                    } ));
                    addToBot((AbstractGameAction)new LinkHeartChoiceAction());
                    break;
                case 2:
                    addToBot((AbstractGameAction)new SFXAction("Orchid_EW"));
                    addToBot(new ApplyPowerAction(abstractPlayer,abstractPlayer,new OrchidPower(abstractPlayer)));
                    AbstractCard roid = new Roid_EW();
                    if (this.magicNumber > 2){
                        roid.upgrade();
                        addToBot((AbstractGameAction)new GainBlockAction(abstractPlayer,5));
                        this.applyPowers();
                    }
                    if (this.magicNumber > 0){
                        addToBot(new MakeTempCardInHandAction(roid));
                        this.magicNumber = 0;
                    }
                    break;
                default:
                    break;
            }
        }
    }




    @Override
    public AbstractCard makeCopy() {
        return (AbstractCard)new Orchid();
    }
}
