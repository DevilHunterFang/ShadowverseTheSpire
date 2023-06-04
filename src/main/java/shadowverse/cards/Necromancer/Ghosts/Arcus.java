package shadowverse.cards.Necromancer.Ghosts;


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
import shadowverse.cards.Neutral.Status.Ghost;
import shadowverse.characters.Necromancer;
import shadowverse.powers.ArcusPower;


public class Arcus extends CustomCard {
    public static final String ID = "shadowverse:Arcus";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Arcus");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/Arcus.png";

    public Arcus() {
        super(ID, NAME, IMG_PATH, 3, DESCRIPTION, CardType.POWER, Necromancer.Enums.COLOR_PURPLE, CardRarity.RARE, CardTarget.SELF);
        this.cardsToPreview = new Ghost();
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBaseCost(2);
        }
    }


    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        AbstractDungeon.effectsQueue.add(new StanceAuraEffect("Divinity"));
        addToBot(new SFXAction("Arcus"));
        for (AbstractCard c:abstractPlayer.hand.group){
            if (c.type == AbstractCard.CardType.ATTACK && c.cost==1)
                c.setCostForTurn(-9);
        }
        if (!abstractPlayer.hasPower("shadowverse:ArcusPower"))
            addToBot(new ApplyPowerAction(abstractPlayer, abstractPlayer, new ArcusPower(abstractPlayer)));
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new Arcus();
    }
}

