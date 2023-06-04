package shadowverse.cards.Bishop.Evil;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import shadowverse.cards.AbstractAmuletCard;
import shadowverse.cards.Neutral.Curse.EvilWorship;
import shadowverse.characters.Bishop;
import shadowverse.orbs.AmuletOrb;

public class MoriaeEncomium extends AbstractAmuletCard {
    public static final String ID = "shadowverse:MoriaeEncomium";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:MoriaeEncomium");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/MoriaeEncomium.png";

    public MoriaeEncomium() {
        super(ID, NAME, IMG_PATH, 1, DESCRIPTION, Bishop.Enums.COLOR_WHITE, CardRarity.COMMON, CardTarget.NONE);
        this.baseMagicNumber = 2;
        this.magicNumber = this.baseMagicNumber;
        this.countDown = 1;
        this.cardsToPreview = new EvilWorship();
    }

    @Override
    public void onStartOfTurn(AmuletOrb paramOrb) {

    }

    @Override
    public void onEvoke(AmuletOrb paramOrb) {
        AbstractCreature m = (AbstractCreature) AbstractDungeon.getMonsters().getRandomMonster(null, true, AbstractDungeon.cardRandomRng);
        if (m != null){
            addToBot((AbstractGameAction)new ApplyPowerAction(m,AbstractDungeon.player,(AbstractPower)new VulnerablePower(m,this.magicNumber,false),this.magicNumber));
            addToBot((AbstractGameAction)new ApplyPowerAction(m,AbstractDungeon.player,(AbstractPower)new WeakPower(m,this.magicNumber,false),this.magicNumber));
        }
    }

    @Override
    public void endOfTurn(AmuletOrb paramOrb) {

    }

    @Override
    public int onHeal(int healAmount, AmuletOrb paramOrb) {
        return 0;
    }

    @Override
    public void onOtherCardPlayed(AbstractCard c, AmuletOrb paramOrb) {

    }

    @Override
    public void onGainedBlock(int blockAmt, AmuletOrb paramOrb) {

    }


    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeMagicNumber(1);
        }
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot((AbstractGameAction)new DrawCardAction(this.magicNumber));
        AbstractCard c = this.cardsToPreview.makeStatEquivalentCopy();
        addToBot((AbstractGameAction)new MakeTempCardInDrawPileAction(c,1,true,true,false));
    }
}
