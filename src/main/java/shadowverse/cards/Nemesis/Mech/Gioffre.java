package shadowverse.cards.Nemesis.Mech;

import basemod.abstracts.CustomCard;
import com.badlogic.gdx.Gdx;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.cards.Witch.Mech.MechanizedLifeform;
import shadowverse.cards.Neutral.Temp.ProductMachine;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Nemesis;

import java.util.ArrayList;

public class Gioffre
        extends CustomCard {
    public static final String ID = "shadowverse:Gioffre";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Gioffre");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/Gioffre.png";
    private static final String TEXT = CardCrawlGame.languagePack.getUIString("shadowverse:Exhaust").TEXT[0];
    private float rotationTimer;
    private int previewIndex;
    public static ArrayList<AbstractCard> returnChoice() {
        ArrayList<AbstractCard> list = new ArrayList<>();
        list.add(new ProductMachine());
        list.add(new MechanizedLifeform());
        return list;
    }

    public Gioffre() {
        super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.ATTACK, Nemesis.Enums.COLOR_SKY, CardRarity.COMMON, CardTarget.SELF);
        this.baseBlock = 4;
        this.cardsToPreview = new ProductMachine();
        this.tags.add(AbstractShadowversePlayer.Enums.MACHINE);
        this.tags.add(AbstractShadowversePlayer.Enums.LASTWORD);
        this.tags.add(AbstractShadowversePlayer.Enums.ACADEMIC);
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
            upgradeBlock(3);
        }
    }

    @Override
    public void triggerOnExhaust(){
        addToBot(new MakeTempCardInHandAction(new ProductMachine()));
    }


    public void use(AbstractPlayer p, AbstractMonster abstractMonster) {
        addToBot(new SFXAction("Gioffre"));
        addToBot(new GainBlockAction(p,this.block));
        addToBot(new MakeTempCardInHandAction(new ProductMachine()));
        int count = 0;
        for (AbstractCard c : p.exhaustPile.group){
            if (c.hasTag(AbstractShadowversePlayer.Enums.MACHINE)){
                count++;
            }
        }
        if (count >= 5){
            AbstractCard tmp = new MechanizedLifeform();
            tmp.setCostForTurn(0);
            tmp.costForTurn=0;
            tmp.isCostModified = true;
            tmp.exhaustOnUseOnce = true;
            tmp.exhaust = true;
            tmp.rawDescription += " NL "+TEXT+" 。";
            tmp.initializeDescription();
            tmp.applyPowers();
            p.hand.addToTop(tmp);
        }
    }


    public AbstractCard makeCopy() {
        return  new Gioffre();
    }
}


