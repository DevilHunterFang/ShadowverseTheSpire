package shadowverse.cards.Neutral.Neutral;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.cards.AbstractNeutralCard;
import shadowverse.cards.Neutral.Status.EvolutionPoint;

import java.util.ArrayList;


public class RearriveOne
        extends AbstractNeutralCard {
    public static final String ID = "shadowverse:RearriveOne";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:RearriveOne");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/RearriveOne.png";

    public RearriveOne() {
        super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.SKILL, CardColor.COLORLESS, CardRarity.UNCOMMON, CardTarget.ALL_ENEMY);
        this.baseDamage = 10;
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeDamage(5);
        }
    }


    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot((AbstractGameAction) new SFXAction("RearriveOne"));
        addToBot((AbstractGameAction)new DamageRandomEnemyAction(new DamageInfo(AbstractDungeon.player, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HEAVY));
        boolean deckCheck = true;
        ArrayList<String> tmp = new ArrayList<>();
        for (AbstractCard c : abstractPlayer.drawPile.group) {
            if (tmp.contains(c.cardID)) {
                deckCheck = false;
                break;
            }
            tmp.add(c.cardID);
        }
        if (deckCheck) {
            addToBot((AbstractGameAction)new GainEnergyAction(1));
            addToBot((AbstractGameAction)new DrawCardAction(1));
            addToBot((AbstractGameAction)new MakeTempCardInHandAction(new EvolutionPoint(),2));
        }
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new RearriveOne();
    }
}

