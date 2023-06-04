package shadowverse.cards.Nemesis.Mech;

import basemod.abstracts.CustomCard;
import com.badlogic.gdx.Gdx;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.cards.Neutral.Temp.ProductMachine;
import shadowverse.cards.Neutral.Temp.RepairMode;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Nemesis;

import java.util.ArrayList;

public class Enforcer
        extends CustomCard {
    public static final String ID = "shadowverse:Enforcer";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Enforcer");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/Enforcer.png";
    private float rotationTimer;
    private int previewIndex;
    public static ArrayList<AbstractCard> returnChoice() {
        ArrayList<AbstractCard> list = new ArrayList<>();
        list.add(new ProductMachine());
        list.add(new RepairMode());
        return list;
    }

    public Enforcer() {
        super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.ATTACK, Nemesis.Enums.COLOR_SKY, CardRarity.COMMON, CardTarget.ENEMY);
        this.baseDamage = 9;
        this.tags.add(AbstractShadowversePlayer.Enums.MACHINE);
        this.tags.add(AbstractShadowversePlayer.Enums.LASTWORD);
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
            upgradeDamage(5);
        }
    }

    @Override
    public void triggerOnExhaust(){
        AbstractCard product = new ProductMachine();
        AbstractCard repair = new RepairMode();
        addToBot(new MakeTempCardInHandAction(product));
        addToBot(new MakeTempCardInHandAction(repair));
    }


    public void use(AbstractPlayer p, AbstractMonster abstractMonster) {
        addToBot(new SFXAction("Enforcer"));
        AbstractCard product = new ProductMachine();
        AbstractCard repair = new RepairMode();
        addToBot(new MakeTempCardInHandAction(product));
        addToBot(new MakeTempCardInHandAction(repair));
        boolean mCheck = false;
        for (AbstractCard c:p.hand.group){
            if (c.hasTag(AbstractShadowversePlayer.Enums.MACHINE)&&c!=this){
                mCheck = true;
                break;
            }
        }
        if (mCheck){
            addToBot( new DamageAction(abstractMonster, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
        }
    }


    public AbstractCard makeCopy() {
        return  new Enforcer();
    }
}


