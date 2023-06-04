package shadowverse.cards.Vampire.Default;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.utility.DiscardToHandAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DrawCardNextTurnPower;
import com.megacrit.cardcrawl.vfx.BorderFlashEffect;
import com.megacrit.cardcrawl.vfx.stance.StanceChangeParticleGenerator;
import shadowverse.action.InvocationAction;
import shadowverse.cards.AbstractEndTurnInvocationCard;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Vampire;

public class Paracelise
        extends AbstractEndTurnInvocationCard {
    public static final String ID = "shadowverse:Paracelise";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Paracelise");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/Paracelise.png";
    public static boolean dupCheck = true;

    public Paracelise() {
        super(ID, NAME, IMG_PATH, 2, DESCRIPTION, CardType.ATTACK, Vampire.Enums.COLOR_SCARLET, CardRarity.RARE, CardTarget.ENEMY);
        this.baseBlock = 9;
        this.baseDamage = 15;
        this.baseMagicNumber = 2;
        this.magicNumber = this.baseMagicNumber;
        this.tags.add(AbstractShadowversePlayer.Enums.FES);
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBlock(3);
            upgradeDamage(5);
        }
    }


    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot((AbstractGameAction) new SFXAction("Paracelise"));
        addToBot((AbstractGameAction) new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_VERTICAL));
        addToBot((AbstractGameAction) new DiscardAction((AbstractCreature) p, (AbstractCreature) p, 3, true));
    }

    @Override
    public void atTurnStart() {
        dupCheck = true;
    }

    public AbstractCard makeCopy() {
        return (AbstractCard) new Paracelise();
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        if (isPlayer) {
            if (AbstractDungeon.player.hand.group.size()==0 && dupCheck) {
                AbstractDungeon.effectsQueue.add(new BorderFlashEffect(Color.SCARLET, true));
                AbstractDungeon.effectsQueue.add(new StanceChangeParticleGenerator(AbstractDungeon.player.hb.cX, AbstractDungeon.player.hb.cY, "Divinity"));
                dupCheck = false;
                if (AbstractDungeon.player.discardPile.contains((AbstractCard) this)) {
                    addToBot((AbstractGameAction) new WaitAction(0.4F));
                    addToBot((AbstractGameAction) new DiscardToHandAction((AbstractCard) this));
                    addToBot((AbstractGameAction) new WaitAction(0.4F));
                    addToBot((AbstractGameAction) new SFXAction("Paracelise"));
                    addToBot((AbstractGameAction) new GainBlockAction(AbstractDungeon.player, this.baseBlock));
                    addToBot((AbstractGameAction) new HealAction(AbstractDungeon.player, AbstractDungeon.player, this.magicNumber));
                    addToBot((AbstractGameAction)new DamageAllEnemiesAction(AbstractDungeon.player, DamageInfo.createDamageMatrix(this.magicNumber, true), DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.FIRE, true));
                    addToBot((AbstractGameAction)new ApplyPowerAction(AbstractDungeon.player,AbstractDungeon.player,new DrawCardNextTurnPower(AbstractDungeon.player,1)));
                } else if (AbstractDungeon.player.drawPile.contains((AbstractCard) this)) {
                    addToBot((AbstractGameAction) new WaitAction(0.4F));
                    addToBot((AbstractGameAction) new InvocationAction(this));
                    addToBot((AbstractGameAction) new WaitAction(0.4F));
                    addToBot((AbstractGameAction) new SFXAction("Paracelise"));
                    addToBot((AbstractGameAction) new GainBlockAction(AbstractDungeon.player, this.baseBlock));
                    addToBot((AbstractGameAction) new HealAction(AbstractDungeon.player, AbstractDungeon.player, this.magicNumber));
                    addToBot((AbstractGameAction)new DamageAllEnemiesAction(AbstractDungeon.player, DamageInfo.createDamageMatrix(this.magicNumber, true), DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.FIRE, true));
                    addToBot((AbstractGameAction)new ApplyPowerAction(AbstractDungeon.player,AbstractDungeon.player,new DrawCardNextTurnPower(AbstractDungeon.player,1)));
                }
            }
        }
    }
}


