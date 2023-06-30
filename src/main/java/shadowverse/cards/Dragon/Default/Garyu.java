package shadowverse.cards.Dragon.Default;


import com.badlogic.gdx.Gdx;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.vfx.combat.ClashEffect;
import shadowverse.action.ChoiceAction2;
import shadowverse.cards.AbstractEnhanceCard;
import shadowverse.cards.Neutral.Temp.Ember;
import shadowverse.cards.Neutral.Temp.GoldenDragon;
import shadowverse.cards.Neutral.Temp.Inferno;
import shadowverse.cards.Neutral.Temp.SilverDragon;
import shadowverse.characters.Dragon;

import java.util.ArrayList;


public class Garyu extends AbstractEnhanceCard {
    public static final String ID = "shadowverse:Garyu";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Garyu");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/Garyu.png";

    private float rotationTimer;
    private int previewIndex;

    public static ArrayList<AbstractCard> returnChoice() {
        ArrayList<AbstractCard> list = new ArrayList<>();
        list.add(new GoldenDragon());
        list.add(new SilverDragon());
        return list;
    }

    public Garyu() {
        super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.ATTACK, Dragon.Enums.COLOR_BROWN, CardRarity.RARE, CardTarget.ENEMY, 2, 4);
        this.baseDamage = 8;
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeDamage(3);
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
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

    public AbstractCard makeCopy() {
        return (AbstractCard) new Garyu();
    }

    @Override
    public void exEnhanceUse(AbstractPlayer p, AbstractMonster m) {
        addToBot(new SFXAction("Garyu_Eh4"));
        addToBot(new VFXAction(new ClashEffect(m.hb.cX, m.hb.cY), 0.1F));
        addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.NONE));
        addToBot(new ApplyPowerAction(p, p, new StrengthPower(p, 2), 2));
        addToBot(new ApplyPowerAction(p, p, new DexterityPower(p, 2), 2));
        AbstractCard a = new GoldenDragon();
        AbstractCard b = new SilverDragon();
        if (this.upgraded){
            a.upgrade();
            b.upgrade();
        }
        a.setCostForTurn(0);
        a.cost = 0;
        b.setCostForTurn(0);
        b.cost = 0;
        addToBot(new MakeTempCardInHandAction(a));
        addToBot(new MakeTempCardInHandAction(b));
    }

    @Override
    public void enhanceUse(AbstractPlayer p, AbstractMonster m) {
        addToBot(new SFXAction("Garyu_Eh2"));
        addToBot(new VFXAction(new ClashEffect(m.hb.cX, m.hb.cY), 0.1F));
        addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.NONE));
        AbstractCard a = new GoldenDragon();
        AbstractCard b = new SilverDragon();
        if (this.upgraded){
            a.upgrade();
            b.upgrade();
        }
        a.setCostForTurn(0);
        a.cost = 0;
        b.setCostForTurn(0);
        b.cost = 0;
        addToBot(new ChoiceAction2(new AbstractCard[] { a, b }));
    }

    @Override
    public void baseUse(AbstractPlayer p, AbstractMonster m) {
        addToBot(new SFXAction("Garyu"));
        addToBot(new VFXAction(new ClashEffect(m.hb.cX, m.hb.cY), 0.1F));
        addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.NONE));
    }
}

