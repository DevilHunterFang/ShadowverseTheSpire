package shadowverse.cards.Vampire.Academic;


import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Vampire;
import shadowverse.powers.EpitaphPower;
import shadowverse.stance.Vengeance;


public class WardenOfDecay
        extends CustomCard {
    public static final String ID = "shadowverse:WardenOfDecay";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:WardenOfDecay");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/WardenOfDecay.png";

    public WardenOfDecay() {
        super(ID, NAME, IMG_PATH, 2, DESCRIPTION, CardType.ATTACK, Vampire.Enums.COLOR_SCARLET, CardRarity.UNCOMMON, CardTarget.SELF);
        this.baseBlock = 25;
        this.exhaust = true;
        this.tags.add(AbstractShadowversePlayer.Enums.CONDEMNED);
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBlock(5);
        }
    }


    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p,this.block));
        if (p.stance.ID.equals(Vengeance.STANCE_ID)||p.hasPower(EpitaphPower.POWER_ID)){
            for (AbstractMonster mo : (AbstractDungeon.getCurrRoom()).monsters.monsters){
                if (mo!=null && !mo.isDeadOrEscaped() && !mo.isDying && !mo.halfDead){
                    addToBot(new ApplyPowerAction(mo, p, new StrengthPower(mo, -3), -3, true, AbstractGameAction.AttackEffect.NONE));
                    addToBot(new ApplyPowerAction(mo, p, new DexterityPower(mo, -3), -3, true, AbstractGameAction.AttackEffect.NONE));
                }
            }
            addToBot(new HealAction(p,p,3));
            addToBot(new DrawCardAction(2));
        }
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new WardenOfDecay();
    }
}

