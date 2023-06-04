package shadowverse.cards.Nemesis.Default;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.powers.WeakPower;
import shadowverse.characters.Nemesis;
import shadowverse.powers.TheWheelOfFortunePower;
import shadowverse.stance.Resonance;

public class TheWheelOfFortune
        extends CustomCard {
    public static final String ID = "shadowverse:TheWheelOfFortune";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:TheWheelOfFortune");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/TheWheelOfFortune.png";

    public TheWheelOfFortune() {
        super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.POWER, Nemesis.Enums.COLOR_SKY, CardRarity.RARE, CardTarget.ALL);
        this.isEthereal = true;
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }


    public void use(AbstractPlayer p, AbstractMonster abstractMonster) {
        if (!this.upgraded){
            if (p.stance.ID.equals(Resonance.STANCE_ID)){
                addToBot((AbstractGameAction)new SFXAction("TheWheelOfFortune2"));
                for (AbstractMonster m :(AbstractDungeon.getCurrRoom()).monsters.monsters){
                    addToBot((AbstractGameAction)new ApplyPowerAction(m,p,(AbstractPower)new TheWheelOfFortunePower(m,1,3)));
                }
            }else {
                addToBot((AbstractGameAction)new SFXAction("TheWheelOfFortune"));
                addToBot((AbstractGameAction)new ApplyPowerAction(p,p,(AbstractPower)new StrengthPower(p,1),1));
                addToBot((AbstractGameAction)new ApplyPowerAction(p,p,(AbstractPower)new DexterityPower(p,1),1));
                for (AbstractMonster m :(AbstractDungeon.getCurrRoom()).monsters.monsters){
                    if (!m.isDeadOrEscaped())
                        addToBot((AbstractGameAction)new ApplyPowerAction(m,p,(AbstractPower)new WeakPower(m,3,false),3));
                }
            }
        }else {
            addToBot((AbstractGameAction)new SFXAction("TheWheelOfFortune"));
            addToBot((AbstractGameAction)new ApplyPowerAction(p,p,(AbstractPower)new StrengthPower(p,1),1));
            addToBot((AbstractGameAction)new ApplyPowerAction(p,p,(AbstractPower)new DexterityPower(p,1),1));
            for (AbstractMonster m :(AbstractDungeon.getCurrRoom()).monsters.monsters){
                if (!m.isDeadOrEscaped()){
                    addToBot((AbstractGameAction)new ApplyPowerAction(m,p,(AbstractPower)new TheWheelOfFortunePower(m,1,3)));
                    addToBot((AbstractGameAction)new ApplyPowerAction(m,p,(AbstractPower)new WeakPower(m,1,false),1));
                }
            }
        }
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new TheWheelOfFortune();
    }
}


