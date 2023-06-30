package shadowverse.cards.Dragon.Discard1;


import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.DamageRandomEnemyAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.action.NaturalDiscardAction;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Dragon;


public class RockbackAnkylosaurus
        extends CustomCard {
    public static final String ID = "shadowverse:RockbackAnkylosaurus";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:RockbackAnkylosaurus");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/RockbackAnkylosaurus.png";

    public RockbackAnkylosaurus() {
        super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.ATTACK, Dragon.Enums.COLOR_BROWN, CardRarity.COMMON, CardTarget.ALL_ENEMY);
        this.tags.add(AbstractShadowversePlayer.Enums.NATURAL);
        this.tags.add(AbstractShadowversePlayer.Enums.LASTWORD);
        this.baseDamage = 8;
        this.baseMagicNumber = 3;
        this.magicNumber = this.baseMagicNumber;
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeDamage(3);
            upgradeMagicNumber(1);
        }
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new NaturalDiscardAction(abstractPlayer,abstractPlayer,1,false,false,new DamageAllEnemiesAction(abstractPlayer, DamageInfo.createDamageMatrix(this.magicNumber, true), DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.BLUNT_LIGHT)));
        addToBot(new DamageRandomEnemyAction(new DamageInfo(abstractPlayer,this.damage,this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
        addToBot(new DrawCardAction(1));
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new RockbackAnkylosaurus();
    }
}

