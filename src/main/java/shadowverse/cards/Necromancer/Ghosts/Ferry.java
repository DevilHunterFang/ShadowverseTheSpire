package shadowverse.cards.Necromancer.Ghosts;


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
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.VerticalAuraEffect;
import shadowverse.action.BurialAction;
import shadowverse.characters.Necromancer;
import shadowverse.powers.FerryPower;


public class Ferry extends CustomCard {
    public static final String ID = "shadowverse:Ferry";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Ferry");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/Ferry.png";

    public Ferry() {
        super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.ATTACK, Necromancer.Enums.COLOR_PURPLE, CardRarity.RARE, CardTarget.SELF);
        this.exhaust = true;
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            this.exhaust = false;
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }


    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new VFXAction(abstractPlayer, (AbstractGameEffect)new VerticalAuraEffect(Color.BLACK, abstractPlayer.hb.cX, abstractPlayer.hb.cY), 0.33F));
        addToBot(new VFXAction(abstractPlayer, (AbstractGameEffect)new VerticalAuraEffect(Color.PURPLE, abstractPlayer.hb.cX, abstractPlayer.hb.cY), 0.33F));
        addToBot( new SFXAction("Ferry"));
        if (!abstractPlayer.hasPower("shadowverse:FerryPower"))
            addToBot(new BurialAction(2, new ApplyPowerAction( abstractPlayer,  abstractPlayer, new FerryPower( abstractPlayer))));
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new Ferry();
    }
}

