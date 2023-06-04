package shadowverse.cards.Neutral.Temp;


import basemod.abstracts.AbstractCardModifier;
import basemod.abstracts.CustomCard;
import basemod.helpers.CardModifierManager;
import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ReduceCostForTurnAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.cardmods.UndyingResolveMod;
import shadowverse.cards.Necromancer.Necromancy.Anisage;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Necromancer;


public class UndyingResolve
        extends CustomCard {
    public static final String ID = "shadowverse:UndyingResolve";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:UndyingResolve");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/UndyingResolve.png";
    public static final String[] TEXT = (CardCrawlGame.languagePack.getUIString("shadowverse:applyEffect")).TEXT;

    public UndyingResolve() {
        super(ID, NAME, IMG_PATH, 0, DESCRIPTION, CardType.SKILL, Necromancer.Enums.COLOR_PURPLE, CardRarity.SPECIAL, CardTarget.NONE);
        this.tags.add(AbstractShadowversePlayer.Enums.FES);
        this.exhaust = true;
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }


    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new SFXAction("UndyingResolve"));
        addToBot(new SelectCardsAction(p.discardPile.group,1,TEXT[0],true,card -> {
            return card.type == CardType.ATTACK;
        }, abstractCards -> {
            for (AbstractCard c : abstractCards) {
                if (c instanceof Anisage){
                    CardModifierManager.addModifier(c, (AbstractCardModifier) new UndyingResolveMod());
                }
                if (!this.upgraded) {
                    addToBot((AbstractGameAction) new ReduceCostForTurnAction(c, 1));
                } else {
                    c.freeToPlayOnce = true;
                }
                c.superFlash();
            }
        }));
   }
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new UndyingResolve();
   }
 }

