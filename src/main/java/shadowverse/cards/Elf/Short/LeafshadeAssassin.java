package shadowverse.cards.Elf.Short;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Elf;
import shadowverse.powers.LeafshadeAssassinPower;

public class LeafshadeAssassin extends CustomCard {
    public static final String ID = "shadowverse:LeafshadeAssassin";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:LeafshadeAssassin");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/LeafshadeAssassin.png";

    public LeafshadeAssassin() {
        super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.ATTACK, Elf.Enums.COLOR_GREEN, CardRarity.COMMON, CardTarget.SELF);
        this.baseBlock = 7;
        this.baseMagicNumber = 6;
        this.tags.add(AbstractShadowversePlayer.Enums.CONDEMNED);
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBlock(2);
            upgradeMagicNumber(2);
        }
    }


    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.cardsPlayedThisTurn.add(this);
        addToBot(new SFXAction("LeafshadeAssassin"));
        addToBot(new GainBlockAction(p,this.block));
        if (!p.hasPower(LeafshadeAssassinPower.POWER_ID)){
            addToBot(new ApplyPowerAction(p,p,new LeafshadeAssassinPower(p,this.magicNumber,1)));
        }else {
            addToBot(new ApplyPowerAction(p,p,new LeafshadeAssassinPower(p,0,1)));
        }
    }


    public AbstractCard makeCopy() {
        return new LeafshadeAssassin();
    }
}
