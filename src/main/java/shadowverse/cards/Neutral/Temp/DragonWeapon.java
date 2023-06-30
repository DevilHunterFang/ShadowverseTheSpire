package shadowverse.cards.Neutral.Temp;

import basemod.abstracts.CustomCard;
import basemod.helpers.CardModifierManager;
import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.cardmods.ArmedMod;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Dragon;


public class DragonWeapon
        extends CustomCard {
    public static final String ID = "shadowverse:DragonWeapon";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:DragonWeapon");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/DragonWeapon.png";
    public static final String[] TEXT = (CardCrawlGame.languagePack.getUIString("shadowverse:applyEffect")).TEXT;

    public DragonWeapon() {
        super(ID, NAME, IMG_PATH, 0, DESCRIPTION, CardType.POWER, Dragon.Enums.COLOR_BROWN, CardRarity.SPECIAL, CardTarget.NONE);
        this.tags.add(AbstractShadowversePlayer.Enums.ARMED);
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            this.selfRetain = true;
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }


    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new SelectCardsInHandAction(1, TEXT[0], false, true, card -> {
            return card.type == CardType.ATTACK;
        }, abstractCards -> {
            for (AbstractCard c : abstractCards) {
                CardModifierManager.addModifier(c, new ArmedMod());
                c.superFlash();
            }
        }));
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new DragonWeapon();
    }
}

