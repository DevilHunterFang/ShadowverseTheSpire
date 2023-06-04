package shadowverse.cards.Elf.Long;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.characters.Elf;

public class HarvestSeason extends CustomCard {
    public static final String ID = "shadowverse:HarvestSeason";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:HarvestSeason");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/HarvestSeason.png";

    public HarvestSeason() {
        super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.SKILL, Elf.Enums.COLOR_GREEN, CardRarity.COMMON, CardTarget.NONE);
        this.baseMagicNumber = 2;
        this.magicNumber = this.baseMagicNumber;
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeMagicNumber(1);
        }
    }



    public void use(AbstractPlayer p, AbstractMonster m) {
        int count = 0;
        for (AbstractCard c : AbstractDungeon.actionManager.cardsPlayedThisTurn){
            count++;
        }
        if (count > 2){
            addToBot((AbstractGameAction)new DrawCardAction(this.magicNumber+1));
        }else {
            addToBot((AbstractGameAction)new DrawCardAction(this.magicNumber));
        }
    }


    public AbstractCard makeCopy() {
        return new HarvestSeason();
    }
}
