package shadowverse.cards.Neutral.Temp;

import basemod.abstracts.CustomCard;
import com.badlogic.gdx.Gdx;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.CleaveEffect;
import shadowverse.characters.Vampire;

import java.util.ArrayList;

public class TerrorNight extends CustomCard {
    public static final String ID = "shadowverse:TerrorNight";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:TerrorNight");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/TerrorNight.png";
    private float rotationTimer;
    private int previewIndex;
    public static ArrayList<AbstractCard> returnChoice() {
        ArrayList<AbstractCard> list = new ArrayList<>();
        list.add(new NightmareTime());
        list.add(new BloodyNail());
        list.add(new DreadAura());
        return list;
    }

    public TerrorNight() {
        super(ID, NAME, IMG_PATH, 0, DESCRIPTION, CardType.SKILL, Vampire.Enums.COLOR_SCARLET, CardRarity.SPECIAL, CardTarget.SELF);
        this.exhaust = true;
        this.baseDamage = 9;
        this.isMultiDamage = true;
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
            upgradeDamage(3);
        }
    }


    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot((AbstractGameAction)new SFXAction("TerrorNight"));
        int count = 0;
        for (AbstractCard c: AbstractDungeon.actionManager.cardsPlayedThisCombat){
            if (c.cardID==this.cardID)
                count++;
        }
        if (count>1){
            addToBot((AbstractGameAction)new SFXAction("ATTACK_HEAVY"));
            addToBot((AbstractGameAction)new VFXAction((AbstractCreature)p, (AbstractGameEffect)new CleaveEffect(), 0.1F));
            addToBot((AbstractGameAction)new DamageAllEnemiesAction((AbstractCreature)p, this.multiDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.NONE));
        }else {
            addToBot((AbstractGameAction)new DiscardAction((AbstractCreature)p, (AbstractCreature)p, 3, false));
            AbstractCard n = (AbstractCard)new NightmareTime();
            AbstractCard b = (AbstractCard)new BloodyNail();
            AbstractCard d = (AbstractCard)new DreadAura();
            addToBot((AbstractGameAction)new MakeTempCardInHandAction(n));
            addToBot((AbstractGameAction)new MakeTempCardInHandAction(b));
            addToBot((AbstractGameAction)new MakeTempCardInHandAction(d));
        }
    }


    public AbstractCard makeCopy() {
        return new TerrorNight();
    }
}
