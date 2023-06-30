package shadowverse.cards.Vampire.Academic;


import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Vampire;
import shadowverse.powers.EpitaphPower;
import shadowverse.stance.Vengeance;


public class Galom extends CustomCard {
    public static final String ID = "shadowverse:Galom";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Galom");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/Galom.png";

    public Galom() {
        super(ID, NAME, IMG_PATH, 2, DESCRIPTION, CardType.ATTACK, Vampire.Enums.COLOR_SCARLET, CardRarity.RARE, CardTarget.ALL_ENEMY);
        this.baseDamage = 20;
        this.tags.add(AbstractShadowversePlayer.Enums.ACADEMIC);
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeDamage(4);
        }
    }


    public void use(AbstractPlayer p, AbstractMonster abstractMonster) {
        addToBot(new SFXAction("Galom"));
        if (p.stance.ID.equals(Vengeance.STANCE_ID) || p.hasPower(EpitaphPower.POWER_ID)) {
            addToBot(new DamageAllEnemiesAction(p, DamageInfo.createDamageMatrix(this.damage * 2, true), DamageInfo.DamageType.NORMAL, AbstractGameAction.AttackEffect.FIRE));
        }else {
            addToBot(new DamageAllEnemiesAction(p, DamageInfo.createDamageMatrix(this.damage, true), DamageInfo.DamageType.NORMAL, AbstractGameAction.AttackEffect.FIRE));
            addToBot(new LoseHPAction(p,p,2));
        }
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new Galom();
    }
}

