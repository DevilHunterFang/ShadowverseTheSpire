package shadowverse.cards.Neutral.Temp;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.IntangiblePlayerPower;
import com.megacrit.cardcrawl.powers.NoDrawPower;
import com.megacrit.cardcrawl.vfx.SpotlightPlayerEffect;
import shadowverse.characters.Elf;

public class TreacherousReversal extends CustomCard {
    public static final String ID = "shadowverse:TreacherousReversal";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:TreacherousReversal");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/TreacherousReversal.png";

    public TreacherousReversal() {
        super(ID, NAME, IMG_PATH, 0, DESCRIPTION, CardType.SKILL, Elf.Enums.COLOR_GREEN, CardRarity.SPECIAL, CardTarget.ALL);
        this.exhaust = true;
    }

    @Override
    public boolean canUpgrade() {
        return false;
    }

    @Override
    public void upgrade() {
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        AbstractDungeon.effectsQueue.add(new SpotlightPlayerEffect());
        addToBot((AbstractGameAction)new SFXAction("TreacherousReversal"));
        addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)abstractPlayer, (AbstractCreature)abstractPlayer, (AbstractPower)new NoDrawPower((AbstractCreature)abstractPlayer), 1));
        addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)abstractPlayer, (AbstractCreature)abstractPlayer, (AbstractPower)new IntangiblePlayerPower((AbstractCreature)abstractPlayer, 1), 1));
        for (AbstractMonster mo:(AbstractDungeon.getCurrRoom()).monsters.monsters){
            if(mo.hasPower("Artifact")){
                addToBot((AbstractGameAction) new RemoveSpecificPowerAction(mo,abstractPlayer,"Artifact"));
            }else {
                for (AbstractPower pow : mo.powers){
                    if (pow.type == AbstractPower.PowerType.BUFF && pow.ID!="Invincible" &&pow.ID!="Mode Shift"&&pow.ID!="Split"&&pow.ID!="Unawakened"&&pow.ID!="Life Link"&&pow.ID!="Fading"&&pow.ID!="Stasis"&&pow.ID!="Minion"&&pow.ID!="Shifting"&&pow.ID!="shadowverse:chushouHealPower"){
                        addToBot((AbstractGameAction) new RemoveSpecificPowerAction(pow.owner,abstractPlayer,pow.ID));
                    }
                }
            }
        }
        for (AbstractCard c : abstractPlayer.drawPile.group) {
            addToBot((AbstractGameAction)new ExhaustSpecificCardAction(c, abstractPlayer.drawPile));
        }
        for (AbstractCard ca : abstractPlayer.discardPile.group) {
            addToBot((AbstractGameAction)new ExhaustSpecificCardAction(ca, abstractPlayer.discardPile));
        }
        for (AbstractCard car : abstractPlayer.hand.group) {
            addToBot((AbstractGameAction)new ExhaustSpecificCardAction(car, abstractPlayer.hand));
        }
        AbstractDungeon.actionManager.cardsPlayedThisCombat.clear();
        for (int i=0;i<20;i++){
            int roll = AbstractDungeon.cardRandomRng.random(99);
            CardRarity cardRarity;
            if (roll < 60) {
                cardRarity = AbstractCard.CardRarity.COMMON;
            } else if (roll < 80) {
                cardRarity = AbstractCard.CardRarity.UNCOMMON;
            } else {
                cardRarity = AbstractCard.CardRarity.RARE;
            }
            AbstractCard tmp = CardLibrary.getAnyColorCard(cardRarity);
            addToBot((AbstractGameAction)new MakeTempCardInDrawPileAction(tmp, 1, true, true));
        }
        AbstractCard v = new VictoryCard();
        addToBot((AbstractGameAction)new MakeTempCardInDrawPileAction(v, 1, false, false, true));
    }
}
