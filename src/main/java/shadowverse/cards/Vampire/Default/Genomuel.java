package shadowverse.cards.Vampire.Default;


import basemod.abstracts.CustomCard;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.BorderFlashEffect;
import com.megacrit.cardcrawl.vfx.combat.MiracleEffect;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Vampire;
import shadowverse.powers.GenomuelPower;


public class Genomuel extends CustomCard {
    public static final String ID = "shadowverse:Genomuel";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Genomuel");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/Genomuel.png";

    public Genomuel() {
        super(ID, NAME, IMG_PATH, 2, DESCRIPTION, CardType.POWER, Vampire.Enums.COLOR_SCARLET, CardRarity.RARE, CardTarget.SELF);
        this.isEthereal = true;
        this.tags.add(AbstractShadowversePlayer.Enums.CONDEMNED);
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            this.isInnate = true;
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }


    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new SFXAction("Genomuel"));
        addToBot(new VFXAction(new BorderFlashEffect(Color.RED, true)));
        addToBot(new VFXAction(new MiracleEffect(Color.LIGHT_GRAY.cpy(),Color.SCARLET.cpy(),"HEAL_3")));
        addToBot(new ApplyPowerAction(abstractPlayer,abstractPlayer, new GenomuelPower(abstractPlayer)));
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new Genomuel();
    }
}
