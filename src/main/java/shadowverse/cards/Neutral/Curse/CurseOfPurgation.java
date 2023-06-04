package shadowverse.cards.Neutral.Curse;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import shadowverse.characters.Nemesis;
import shadowverse.powers.DrawLoseHealthPower;

public class CurseOfPurgation extends CustomCard {
    public static final String ID = "shadowverse:CurseOfPurgation";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:CurseOfPurgation");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/CurseOfPurgation.png";
    public AbstractPlayer p = AbstractDungeon.player;
    private boolean first = true;

    public CurseOfPurgation() {
        super(ID, NAME, IMG_PATH, -2, DESCRIPTION, CardType.CURSE, Nemesis.Enums.COLOR_SKY, CardRarity.SPECIAL, CardTarget.NONE);
    }

    @Override
    public void upgrade() {
    }

    public void onRemoveFromMasterDeck() {
        AbstractDungeon.player.damage(new DamageInfo(null, 999));
    }

    @Override
    public void triggerOnOtherCardPlayed(AbstractCard c) {
        if (AbstractDungeon.actionManager.cardsPlayedThisTurn.size() >= 8 && first) {
            first = false;
            addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)p, (AbstractCreature)p, (AbstractPower)new DrawLoseHealthPower((AbstractCreature)p), 1));
        }
    }

    public void triggerOnExhaust() {
        addToBot((AbstractGameAction)new MakeTempCardInHandAction(makeCopy()));
    }

    @Override
    public void atTurnStart(){
        first = true;
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
    }

    @Override
    public AbstractCard makeCopy(){
        return new CurseOfPurgation();
    }
}
