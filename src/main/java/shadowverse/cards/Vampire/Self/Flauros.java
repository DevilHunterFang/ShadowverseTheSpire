package shadowverse.cards.Vampire.Self;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.utility.DiscardToHandAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.action.InvocationAction;
import shadowverse.cards.AbstractEndTurnInvocationCard;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Vampire;

public class Flauros
        extends AbstractEndTurnInvocationCard {
    public static final String ID = "shadowverse:Flauros";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Flauros");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/Flauros.png";
    public static boolean dupCheck = true;

    public Flauros() {
        super(ID, NAME, IMG_PATH, 2, DESCRIPTION, CardType.ATTACK, Vampire.Enums.COLOR_SCARLET, CardRarity.UNCOMMON, CardTarget.SELF);
        this.baseBlock = 9;
        this.baseMagicNumber = 3;
        this.magicNumber = this.baseMagicNumber;
        this.tags.add(AbstractShadowversePlayer.Enums.LASTWORD);
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeMagicNumber(2);
            upgradeBlock(2);
        }
    }

    @Override
    public void triggerOnExhaust() {
        addToBot((AbstractGameAction)new HealAction(AbstractDungeon.player,AbstractDungeon.player,this.magicNumber));
    }


    public void use(AbstractPlayer p, AbstractMonster abstractMonster) {
        addToBot((AbstractGameAction)new SFXAction("Flauros"));
        addToBot((AbstractGameAction)new GainBlockAction(p,this.block));
        addToBot((AbstractGameAction)new HealAction(p,p,this.magicNumber));
    }

    @Override
    public void atTurnStart(){
        dupCheck = true;
    }

    public AbstractCard makeCopy() {
        return (AbstractCard) new Flauros();
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        if (isPlayer){
            if (AbstractDungeon.player instanceof AbstractShadowversePlayer){
                if (((AbstractShadowversePlayer) AbstractDungeon.player).wrathThisTurn>=4 && dupCheck){
                    dupCheck = false;
                    if (AbstractDungeon.player.discardPile.contains((AbstractCard)this)) {
                        addToBot((AbstractGameAction)new WaitAction(0.4F));
                        addToBot((AbstractGameAction)new DiscardToHandAction((AbstractCard)this));
                        addToBot((AbstractGameAction)new WaitAction(0.4F));
                        addToBot((AbstractGameAction)new SFXAction("Flauros"));
                        addToBot((AbstractGameAction)new GainBlockAction(AbstractDungeon.player,this.baseBlock));
                        addToBot((AbstractGameAction)new HealAction(AbstractDungeon.player,AbstractDungeon.player,this.magicNumber));
                    } else if (AbstractDungeon.player.drawPile.contains((AbstractCard)this)) {
                        addToBot((AbstractGameAction)new WaitAction(0.4F));
                        addToBot((AbstractGameAction)new InvocationAction(this));
                        addToBot((AbstractGameAction)new WaitAction(0.4F));
                        addToBot((AbstractGameAction)new SFXAction("Flauros"));
                        addToBot((AbstractGameAction)new GainBlockAction(AbstractDungeon.player,this.baseBlock));
                        addToBot((AbstractGameAction)new HealAction(AbstractDungeon.player,AbstractDungeon.player,this.magicNumber));
                    }
                }
            }
        }
    }
}


