package shadowverse.cards.Neutral;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.AttackDamageRandomEnemyAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.ThrowDaggerEffect;

public class Grimnir extends AbstractNeutralCard{
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Grimnir");
    public static final String ID = "shadowverse:Grimnir";
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION; public static final String IMG_PATH = "img/cards/Grimnir.png";
    public Grimnir() {
        super(ID, NAME, IMG_PATH, 2, DESCRIPTION, CardType.ATTACK, CardColor.COLORLESS, CardRarity.UNCOMMON, CardTarget.ALL_ENEMY);
        this.baseMagicNumber = 5;
        this.magicNumber = this.baseMagicNumber;
        this.baseDamage = 2;
    }

    @Override
    public void upgrade() {
        if(!this.upgraded){
            upgradeBaseCost(1);
            upgradeName();
        }
    }
    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot((AbstractGameAction)new SFXAction("Grimnir"));
        for (int i = 0; i < this.magicNumber; i++) {
            addToBot((AbstractGameAction) new AttackDamageRandomEnemyAction(this, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
        }
    }

    public AbstractCard makeCopy() {
        return new Grimnir();
    }
}
