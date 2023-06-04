package shadowverse.cards.Nemesis.Default;


import basemod.abstracts.CustomCard;
import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsInHandAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.BlurPower;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.MindblastEffect;
import shadowverse.Shadowverse;
import shadowverse.action.ExhaustSpecificGroupAndDrawAction;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Nemesis;

import java.util.ArrayList;


public class OmniscientKaiser extends CustomCard {
    public static final String ID = "shadowverse:OmniscientKaiser";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:OmniscientKaiser");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/OmniscientKaiser.png";
    public static final String[] TEXT = (CardCrawlGame.languagePack.getUIString("shadowverse:applyEffect")).TEXT;

    public OmniscientKaiser() {
        super(ID, NAME, IMG_PATH, 5, DESCRIPTION, CardType.ATTACK, Nemesis.Enums.COLOR_SKY, CardRarity.RARE, CardTarget.ALL);
        this.baseBlock = 21;
        this.baseDamage = 49;
        this.isMultiDamage = true;
        this.exhaust = true;
        this.tags.add(AbstractShadowversePlayer.Enums.ACCELERATE);
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBlock(7);
            upgradeDamage(7);
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }

    @Override
    public void update() {
        if (AbstractDungeon.currMapNode != null && (AbstractDungeon.getCurrRoom()).phase == AbstractRoom.RoomPhase.COMBAT&&
                Shadowverse.Accelerate(this)){
            setCostForTurn(0);
            this.type = CardType.SKILL;
        }else {
            if (this.type==CardType.SKILL){
                setCostForTurn(5);
                this.type = CardType.ATTACK;
            }
        }
        super.update();
    }

    public void use(AbstractPlayer p, AbstractMonster abstractMonster) {
        if (Shadowverse.Accelerate((AbstractCard) this) && this.type == CardType.SKILL) {
            ArrayList<AbstractCard> cardsNotToExhaust = new ArrayList<>();
            addToBot((AbstractGameAction) new SelectCardsInHandAction(1, TEXT[0], false, false, card -> {
                return true;
            }, abstractCards -> {
                for (AbstractCard c:abstractCards){
                    cardsNotToExhaust.add(c);
                }
            }));
            addToBot((AbstractGameAction) new SFXAction("OmniscientKaiser_Acc"));
            addToBot((AbstractGameAction)new ExhaustSpecificGroupAndDrawAction(cardsNotToExhaust,p.hand,this.upgraded));
        } else {
            addToBot((AbstractGameAction) new SFXAction("OmniscientKaiser"));
            addToBot((AbstractGameAction) new GainBlockAction(p, p, this.block));
            addToBot((AbstractGameAction) new ApplyPowerAction(p, p, (AbstractPower) new BlurPower(p, 1), 1));
            addToBot((AbstractGameAction) new VFXAction((AbstractCreature) p, (AbstractGameEffect) new MindblastEffect(p.dialogX, p.dialogY, p.flipHorizontal), 0.1F));
            addToBot((AbstractGameAction) new DamageAllEnemiesAction((AbstractCreature) p, this.multiDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.NONE));
        }
    }

    private void incrementAndGet(int i){
        i++;
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new OmniscientKaiser();
    }
}
