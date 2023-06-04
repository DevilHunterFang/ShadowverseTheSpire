package shadowverse.cards.Elf.Short;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.DiscardToHandAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.DoubleDamagePower;
import com.megacrit.cardcrawl.powers.LoseStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import shadowverse.action.InvocationAction;
import shadowverse.cards.AbstractEndTurnInvocationCard;
import shadowverse.characters.Elf;


public class WhirlwindAssault
        extends AbstractEndTurnInvocationCard {
    public static final String ID = "shadowverse:WhirlwindAssault";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:WhirlwindAssault");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/WhirlwindAssault.png";
    public static boolean dupCheck = true;
    private int playCount;

    public WhirlwindAssault() {
        super(ID, NAME, IMG_PATH, 0, DESCRIPTION, CardType.SKILL, Elf.Enums.COLOR_GREEN, CardRarity.UNCOMMON, CardTarget.SELF);
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            this.isInnate = true;
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }


    public void applyPowers() {
        super.applyPowers();
        int count = 0;
        for (AbstractCard c : AbstractDungeon.actionManager.cardsPlayedThisCombat){
            if (c instanceof WhirlwindAssault)
                count++;
        }
        this.rawDescription = cardStrings.DESCRIPTION;
        this.rawDescription += cardStrings.EXTENDED_DESCRIPTION[0] + count;
        this.rawDescription += cardStrings.EXTENDED_DESCRIPTION[1];
        initializeDescription();
    }


    public void atTurnStart() {
        if (this.playCount>= 4 && dupCheck) {
            dupCheck = false;
            if (AbstractDungeon.player.discardPile.contains((AbstractCard) this)) {
                addToBot((AbstractGameAction) new DiscardToHandAction((AbstractCard) this));
            } else if (AbstractDungeon.player.drawPile.contains((AbstractCard) this)) {
                addToBot((AbstractGameAction) new InvocationAction((AbstractCard) this));
            }
        } else if (this.playCount < 4 ) {
            dupCheck = true;
        }
        this.playCount = 0;
    }

    @Override
    public void triggerOnCardPlayed(AbstractCard cardPlayed) {
        dupCheck = true;
    }

    @Override
    public void triggerOnOtherCardPlayed(AbstractCard c) {
        dupCheck = true;
    }

    public void use(AbstractPlayer p, AbstractMonster abstractMonster) {
        addToBot(new SFXAction("WhirlwindAssault"));
        int count = 0;
        for (AbstractCard c : AbstractDungeon.actionManager.cardsPlayedThisCombat){
            if (c instanceof WhirlwindAssault)
                count++;
        }
        count--;
        addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)p, (AbstractCreature)p, (AbstractPower)new StrengthPower((AbstractCreature)p, count), count));
        addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)p, (AbstractCreature)p, (AbstractPower)new LoseStrengthPower((AbstractCreature)p, count), count));
        if (count >= 5){
            addToBot(new ApplyPowerAction(p,p,new DoubleDamagePower(p,1,false)));
        }
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new WhirlwindAssault();
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        this.playCount = AbstractDungeon.actionManager.cardsPlayedThisTurn.size();
    }
}


