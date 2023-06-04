package shadowverse.cards.Neutral.Temp;

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
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.DexterityPower;
import shadowverse.characters.Elf;

public class Beauty extends CustomCard {
    public static final String ID = "shadowverse:Beauty";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Beauty");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/Beauty.png";

    public Beauty() {
        super(ID, NAME, IMG_PATH, -2, DESCRIPTION, CardType.ATTACK, Elf.Enums.COLOR_GREEN, CardRarity.SPECIAL, CardTarget.SELF);
        this.baseBlock = 8;
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBlock(3);
        }
    }



    public void use(AbstractPlayer p, AbstractMonster m) {
        onChoseThisOption();
    }

    public void onChoseThisOption() {
        addToBot((AbstractGameAction)new SFXAction("Beauty"));
        int count = 0;
        for (AbstractCard c : AbstractDungeon.actionManager.cardsPlayedThisTurn){
            count++;
        }
        int dAmt = 0;
        for (AbstractPower p :AbstractDungeon.player.powers){
            if (p instanceof DexterityPower){
                dAmt = p.amount;
                break;
            }
        }
        if (count > 2){
            addToBot((AbstractGameAction)new GainBlockAction(AbstractDungeon.player,(this.baseBlock+dAmt)*2));
        }else {
            addToBot((AbstractGameAction)new GainBlockAction(AbstractDungeon.player,this.baseBlock+dAmt));
        }
    }

    public AbstractCard makeCopy() {
        return new Beauty();
    }
}
