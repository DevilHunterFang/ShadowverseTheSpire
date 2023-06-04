package shadowverse.cards.Vampire.Vengeance;


import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.characters.Vampire;
import shadowverse.powers.EpitaphPower;
import shadowverse.stance.Vengeance;


public class DevilOfVengeance
        extends CustomCard {
    public static final String ID = "shadowverse:DevilOfVengeance";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:DevilOfVengeance");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/DevilOfVengeance.png";

    public DevilOfVengeance() {
        super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.ATTACK, Vampire.Enums.COLOR_SCARLET, CardRarity.COMMON, CardTarget.SELF);
        this.baseBlock = 14;
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBlock(3);
        }
    }

    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        boolean canUse = super.canUse(p, m);
        if (!canUse)
            return false;
        if (!p.hasPower(EpitaphPower.POWER_ID)&&!p.stance.ID.equals(Vengeance.STANCE_ID)) {
            canUse = false;
        }
        return canUse;
    }

    public void triggerOnGlowCheck() {
        boolean glow = true;
        if (!AbstractDungeon.player.hasPower(EpitaphPower.POWER_ID)&&!AbstractDungeon.player.stance.ID.equals(Vengeance.STANCE_ID)) {
            glow = false;
        }
        if (glow) {
            this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
        } else {
            this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        }
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot((AbstractGameAction) new SFXAction("DevilOfVengeance"));
        addToBot((AbstractGameAction)new GainBlockAction(p,this.block));
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new DevilOfVengeance();
    }
}

