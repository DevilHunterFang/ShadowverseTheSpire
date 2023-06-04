package shadowverse.cards.Necromancer.Burial;


import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.stance.StanceAuraEffect;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Necromancer;
import shadowverse.powers.MementoPower;


public class Memento extends CustomCard {
    public static final String ID = "shadowverse:Memento";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Memento");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/Memento.png";

    public Memento() {
        super(ID, NAME, IMG_PATH, 3, DESCRIPTION, CardType.POWER, Necromancer.Enums.COLOR_PURPLE, CardRarity.RARE, CardTarget.SELF);
        this.tags.add(AbstractShadowversePlayer.Enums.ACADEMIC);
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBaseCost(2);
        }
    }


    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        AbstractDungeon.effectsQueue.add(new StanceAuraEffect("Divinity"));
        addToBot(new SFXAction("Memento"));
        addToBot(new ApplyPowerAction(abstractPlayer, abstractPlayer, new MementoPower(abstractPlayer)));
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new Memento();
    }
}

