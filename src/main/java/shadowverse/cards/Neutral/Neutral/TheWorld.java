package shadowverse.cards.Neutral.Neutral;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.ClashEffect;
import shadowverse.cards.AbstractNeutralCard;
import shadowverse.cards.Neutral.Temp.TheWorld_I;

public class TheWorld extends AbstractNeutralCard {
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:TheWorld");
    public static final String ID = "shadowverse:TheWorld";
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION; public static final String IMG_PATH = "img/cards/TheWorld.png";
    public int turnCount = 0;
    public static boolean dupCheck = true;
    public static boolean combatCheck = true;

    public TheWorld() {
        super(ID, NAME, IMG_PATH, 2, DESCRIPTION, CardType.ATTACK, CardColor.COLORLESS, CardRarity.RARE, CardTarget.ALL);
        this.baseDamage = 20;
        this.baseBlock = 14;
        this.cardsToPreview = (AbstractCard)new TheWorld_I();
        combatCheck = true;
    }



    @Override
    public boolean canUpgrade() {
        return false;
    }

    @Override
    public void upgrade() {
    }

    @Override
    public void atTurnStart() {
        this.turnCount++;
        if (this.turnCount == 5 && dupCheck && combatCheck) {
            dupCheck = false;
            combatCheck = false;
            AbstractCard c = this.cardsToPreview.makeStatEquivalentCopy();
            if (AbstractDungeon.player.discardPile.contains((AbstractCard)this)) {
                addToBot((AbstractGameAction)new ExhaustSpecificCardAction(this, AbstractDungeon.player.discardPile));
                addToBot((AbstractGameAction)new MakeTempCardInHandAction(c, 1));
            } else if (AbstractDungeon.player.drawPile.contains((AbstractCard)this)) {
                addToBot((AbstractGameAction)new ExhaustSpecificCardAction(this, AbstractDungeon.player.drawPile));
                addToBot((AbstractGameAction)new MakeTempCardInHandAction(c, 1));
            }
        }else if (this.turnCount != 5){
            dupCheck = true;
        }
    }




    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot((AbstractGameAction)new SFXAction("TheWorld"));
        addToBot((AbstractGameAction)new GainBlockAction((AbstractCreature)abstractPlayer, (AbstractCreature)abstractPlayer, this.block));
        if (abstractPlayer.currentHealth < abstractPlayer.maxHealth*3/4){
            AbstractMonster strongestMonster = null;
            for (AbstractMonster m : (AbstractDungeon.getMonsters()).monsters) {
                if (!m.isDead || !m.isDeadOrEscaped()) {
                    if (strongestMonster == null) {
                        strongestMonster = m;
                        continue;
                    }
                    if (m.currentHealth > strongestMonster.currentHealth) {
                        strongestMonster = m;
                    }
                }
            }
            addToBot((AbstractGameAction)new VFXAction((AbstractGameEffect)new ClashEffect(strongestMonster.hb.cX, strongestMonster.hb.cY), 0.1F));
            addToBot((AbstractGameAction)new DamageAction((AbstractCreature)strongestMonster, new DamageInfo((AbstractCreature)abstractPlayer, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.NONE));
            addToBot((AbstractGameAction)new HealAction((AbstractCreature)AbstractDungeon.player, (AbstractCreature)AbstractDungeon.player, 5));
            addToBot((AbstractGameAction)new DrawCardAction((AbstractCreature)abstractPlayer, 2));
        }
    }

    public AbstractCard makeCopy() {
        return new TheWorld();
    }
}
