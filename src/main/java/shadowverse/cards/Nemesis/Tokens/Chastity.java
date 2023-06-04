package shadowverse.cards.Nemesis.Tokens;


import basemod.abstracts.CustomCard;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.ReduceCostAction;
import com.megacrit.cardcrawl.actions.unique.ExpertiseAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.MiracleEffect;
import shadowverse.characters.Nemesis;


public class Chastity extends CustomCard {
    public static final String ID = "shadowverse:Chastity";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Chastity");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/Chastity.png";

    public Chastity() {
        super(ID, NAME, IMG_PATH, 7, DESCRIPTION, CardType.ATTACK, Nemesis.Enums.COLOR_SKY, CardRarity.RARE, CardTarget.ENEMY);
        this.baseBlock = 15;
        this.baseMagicNumber = 2;
        this.magicNumber = this.baseMagicNumber;
        this.selfRetain = true;
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBlock(5);
            upgradeMagicNumber(1);
        }
    }

    public void triggerOnOtherCardPlayed(AbstractCard c) {
        if (c.exhaust && c.type==CardType.ATTACK) {
            flash();
            addToBot((AbstractGameAction)new ReduceCostAction((AbstractCard)this));
        }
    }

    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot((AbstractGameAction) new SFXAction("Chastity"));
        addToBot((AbstractGameAction) new ApplyPowerAction(abstractMonster, abstractPlayer, (AbstractPower) new VulnerablePower(abstractMonster, this.magicNumber, false), this.magicNumber));
        addToBot((AbstractGameAction) new ApplyPowerAction(abstractMonster, abstractPlayer, (AbstractPower) new WeakPower(abstractMonster, this.magicNumber, false), this.magicNumber));
        if (abstractMonster != null)
            addToBot((AbstractGameAction) new VFXAction((AbstractGameEffect) new MiracleEffect(Color.SKY.cpy(), Color.WHITE.cpy(), "HEAL_3")));
        addToBot(new GainBlockAction(abstractPlayer,this.block));
        addToBot(new ExpertiseAction(abstractPlayer,7));
        this.cost = 7;
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new Chastity();
    }
}

