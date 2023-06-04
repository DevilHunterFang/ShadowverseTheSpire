package shadowverse.cards.Necromancer.Mech;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.status.VoidCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Necromancer;

public class GigantSkull extends CustomCard {
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:GigantSkull");
    public static final String ID = "shadowverse:GigantSkull";
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION; public static final String IMG_PATH = "img/cards/GigantSkull.png";
    public GigantSkull() {
        super(ID, NAME, IMG_PATH, 3, DESCRIPTION, CardType.ATTACK, Necromancer.Enums.COLOR_PURPLE, CardRarity.RARE, CardTarget.ALL_ENEMY);
        this.baseDamage = 28;
        this.isMultiDamage = true;
        this.tags.add(AbstractShadowversePlayer.Enums.MACHINE);
        this.cardsToPreview = (AbstractCard)new VoidCard();
    }

    @Override
    public void upgrade() {
        if(!this.upgraded){
            upgradeDamage(7);
            upgradeName();
        }
    }
    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        boolean mCheck = false;
        for (AbstractCard c:abstractPlayer.hand.group){
            if (c.hasTag(AbstractShadowversePlayer.Enums.MACHINE)&&c!=this){
                mCheck = true;
                break;
            }
        }
        addToBot((AbstractGameAction)new DamageAllEnemiesAction((AbstractCreature)abstractPlayer, this.multiDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.FIRE));
        if (!mCheck)
            addToBot((AbstractGameAction)new MakeTempCardInDiscardAction((AbstractCard)new VoidCard(), 1));
    }

    public AbstractCard makeCopy() {
        return new GigantSkull();
    }
}
