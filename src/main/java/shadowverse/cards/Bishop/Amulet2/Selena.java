package shadowverse.cards.Bishop.Amulet2;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.vfx.combat.InflameEffect;
import shadowverse.action.ReduceAllCountDownAction;
import shadowverse.cards.Neutral.Temp.HeavenFire;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Bishop;
import shadowverse.orbs.AmuletOrb;

public class Selena
        extends CustomCard {
    public static final String ID = "shadowverse:Selena";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Selena");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/Selena.png";

    public Selena() {
        super(ID, NAME, IMG_PATH, 2, DESCRIPTION, CardType.ATTACK, Bishop.Enums.COLOR_WHITE, CardRarity.RARE, CardTarget.ALL);
        this.cardsToPreview = new HeavenFire();
        this.baseBlock = 12;
        this.baseDamage = 15;
        this.isMultiDamage = true;
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBlock(4);
            upgradeDamage(3);
            this.cardsToPreview.upgrade();
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }


    public void use(AbstractPlayer p, AbstractMonster abstractMonster) {
        addToBot((AbstractGameAction)new SFXAction("Selena"));
        addToBot((AbstractGameAction)new VFXAction(new InflameEffect(p),0.3F));
        addToBot((AbstractGameAction)new GainBlockAction(p,this.block));
        int amt = 0;
        for (AbstractOrb o:p.orbs){
            if (o instanceof AmuletOrb && !((AmuletOrb) o).amulet.hasTag(AbstractShadowversePlayer.Enums.MINION)){
                amt++;
            }
        }
        if (amt>0)
            addToBot((AbstractGameAction)new MakeTempCardInHandAction(this.cardsToPreview.makeStatEquivalentCopy()));
        if (amt>1)
            addToBot((AbstractGameAction)new GainEnergyAction(1));
        if (amt>2)
            addToBot((AbstractGameAction)new DamageAllEnemiesAction(p,this.damage,this.damageTypeForTurn, AbstractGameAction.AttackEffect.FIRE));
        if (amt>3)
            addToBot((AbstractGameAction)new ReduceAllCountDownAction(3));
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new Selena();
    }
}


