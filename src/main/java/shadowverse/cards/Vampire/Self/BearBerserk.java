package shadowverse.cards.Vampire.Self;


import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.characters.Vampire;
import shadowverse.powers.AvaricePower;
import shadowverse.powers.EpitaphPower;


public class BearBerserk
        extends CustomCard {
    public static final String ID = "shadowverse:BearBerserk";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:BearBerserk");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/BearBerserk.png";

    public BearBerserk() {
        super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.ATTACK, Vampire.Enums.COLOR_SCARLET, CardRarity.UNCOMMON, CardTarget.SELF);
        this.baseBlock = 8;
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBlock(3);
        }
    }


    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot((AbstractGameAction)new SFXAction("BearBerserk"));
        addToBot((AbstractGameAction)new GainBlockAction(p,this.block));
        int lostAmt = 1;
        if (p.hasPower(EpitaphPower.POWER_ID)||p.hasPower(AvaricePower.POWER_ID)){
            lostAmt = 2;
            addToBot((AbstractGameAction)new HealAction(p,p,4));
        }else {
            addToBot((AbstractGameAction)new HealAction(p,p,2));
        }
        for (int i=0;i<lostAmt;i++){
            addToBot((AbstractGameAction) new LoseHPAction((AbstractCreature) p, (AbstractCreature) p, 1));
            for (AbstractMonster mo : (AbstractDungeon.getCurrRoom()).monsters.monsters) {
                if (!mo.isDeadOrEscaped())
                addToBot((AbstractGameAction)new LoseHPAction(mo,p,1));
            }
        }
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new BearBerserk();
    }
}

