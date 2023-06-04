package shadowverse.cards.Nemesis.Pile;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.HeartBuffEffect;
import shadowverse.characters.Nemesis;
import shadowverse.powers.DeusExMachinaPower;

public class DeusExMachina
        extends CustomCard {
    public static final String ID = "shadowverse:DeusExMachina";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:DeusExMachina");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/DeusExMachina.png";

    public DeusExMachina() {
        super(ID, NAME, IMG_PATH, 3, DESCRIPTION, CardType.POWER, Nemesis.Enums.COLOR_SKY, CardRarity.RARE, CardTarget.SELF);
        this.isEthereal = true;
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            this.isEthereal = false;
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }


    public void use(AbstractPlayer p, AbstractMonster abstractMonster) {
        addToBot((AbstractGameAction)new SFXAction("DeusExMachina"));
        addToBot((AbstractGameAction)new VFXAction((AbstractGameEffect)new HeartBuffEffect(p.hb.cX, p.hb.cY)));
        addToBot((AbstractGameAction) new ApplyPowerAction((AbstractCreature) p, (AbstractCreature) p, (AbstractPower) new DeusExMachinaPower((AbstractCreature) p)));
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new DeusExMachina();
    }
}


