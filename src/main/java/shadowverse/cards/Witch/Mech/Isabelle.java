package shadowverse.cards.Witch.Mech;

import com.badlogic.gdx.Gdx;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.common.ReduceCostAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.action.FusionAction2;
import shadowverse.cards.AbstractRightClickCard2;
import shadowverse.cards.Witch.Basic.Insight;
import shadowverse.cards.Witch.Spellboost1.FatesHand;
import shadowverse.cards.Neutral.Temp.FireBall;
import shadowverse.cards.Neutral.Temp.UnionMagic;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Witchcraft;

import java.util.ArrayList;

public class Isabelle
        extends AbstractRightClickCard2 {
    public static final String ID = "shadowverse:Isabelle";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Isabelle");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/Isabelle.png";
    private float rotationTimer;
    private int previewIndex;
    private boolean hasFusion = false;
    private int turnCount = 0;

    public static ArrayList<AbstractCard> returnChoice() {
        ArrayList<AbstractCard> list = new ArrayList<>();
        list.add(new UnionMagic());
        list.add(new Insight());
        list.add(new FireBall());
        list.add(new FatesHand());
        return list;
    }

    public Isabelle() {
        super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.ATTACK, Witchcraft.Enums.COLOR_BLUE, CardRarity.RARE, CardTarget.SELF);
        this.baseMagicNumber = 0;
        this.magicNumber = this.baseMagicNumber;
        this.baseBlock = 6;
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }

    @Override
    protected void onRightClick() {
        if (!this.hasFusion){
            addToBot((AbstractGameAction)new FusionAction2(8,false,true,true,this,this.hasFusion,Witchcraft.Enums.COLOR_BLUE,CardType.ATTACK,null));
        }
    }

    @Override
    public void applyPowers(){
        super.applyPowers();
        if (this.magicNumber>0){
            this.turnCount++;
            this.hasFusion = true;
            switch (turnCount){
                case 1:
                    AbstractCard union = (AbstractCard)new UnionMagic();
                    if (this.upgraded)
                        union.upgrade();
                    addToBot((AbstractGameAction)new MakeTempCardInHandAction(union.makeStatEquivalentCopy()));
                    break;
                case 2:
                    AbstractCard insight = (AbstractCard)new Insight();
                    if (this.upgraded)
                        insight.upgrade();
                    addToBot((AbstractGameAction)new MakeTempCardInHandAction(insight.makeStatEquivalentCopy()));
                    break;
                case 3:
                    AbstractCard fire = (AbstractCard)new FireBall();
                    if (this.upgraded)
                        fire.upgrade();
                    addToBot((AbstractGameAction)new MakeTempCardInHandAction(fire.makeStatEquivalentCopy()));
                    break;
                default:
                    break;
            }
            this.magicNumber = this.baseMagicNumber;
        }
    }

    @Override
    public void atTurnStart(){
        hasFusion = false;
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
        addToBot((AbstractGameAction)new SFXAction("Isabelle"));
        addToBot((AbstractGameAction)new GainBlockAction(abstractPlayer,this.block));
        //增幅
        for (AbstractCard c : AbstractDungeon.player.hand.group) {
            if (c.hasTag(AbstractShadowversePlayer.Enums.SPELL_BOOST)) {
                for (int i = 0; i < 1; i++) {
                    c.flash();
                    addToBot((AbstractGameAction)new SFXAction("spell_boost"));
                    addToBot((AbstractGameAction)new ReduceCostAction(c));
                }  continue;
            }
            if (c.hasTag(AbstractShadowversePlayer.Enums.SPELL_BOOST_ATTACK)) {
                for (int i = 0; i < 1; i++) {
                    c.flash();

                    c.magicNumber = ++c.baseMagicNumber;
                    addToBot((AbstractGameAction)new SFXAction("spell_boost"));
                }
            }
        }
        //指引
        AbstractCard fate = (AbstractCard)new FatesHand();
        if (this.upgraded)
            fate.upgrade();
        addToBot((AbstractGameAction)new MakeTempCardInHandAction(fate.makeStatEquivalentCopy()));
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new Isabelle();
    }
}

