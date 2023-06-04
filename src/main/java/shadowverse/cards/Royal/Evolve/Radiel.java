package shadowverse.cards.Royal.Evolve;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Royal;
import shadowverse.orbs.AmbushMinion;
import shadowverse.orbs.ErikaOrb;
import shadowverse.orbs.Minion;

public class Radiel extends CustomCard {
    public static final String ID = "shadowverse:Radiel";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/Radiel.png";

    public Radiel() {
        super(ID, NAME, IMG_PATH, 3, DESCRIPTION, CardType.ATTACK, Royal.Enums.COLOR_YELLOW, CardRarity.RARE, CardTarget.ENEMY);
        this.baseDamage = 28;
        this.baseMagicNumber = this.magicNumber = 2;
        this.tags.add(AbstractShadowversePlayer.Enums.CONDEMNED);
    }


    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeDamage(7);
            upgradeMagicNumber(1);
        }
    }

    public int rally() {
        int rally = 0;
        for (AbstractOrb o : AbstractDungeon.actionManager.orbsChanneledThisCombat) {
            if (o instanceof Minion && !(o instanceof AmbushMinion) && !(o instanceof ErikaOrb)) {
                rally++;
            }
        }
        for (AbstractCard c : AbstractDungeon.actionManager.cardsPlayedThisCombat) {
            if (c.type == CardType.ATTACK && !(c.hasTag(CardTags.STRIKE))) {
                rally++;
            }
        }
        return rally;
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new SFXAction(ID.replace("shadowverse:", "")));
        int upgradeAmount = 0;
        for (AbstractCard c : abstractPlayer.hand.group){
            if (c.upgraded && (c.type == CardType.ATTACK || c.type == CardType.POWER))
                upgradeAmount++;
        }
        for (AbstractCard c : abstractPlayer.drawPile.group){
            if (c.upgraded && (c.type == CardType.ATTACK || c.type == CardType.POWER))
                upgradeAmount++;
        }
        for (AbstractCard c : abstractPlayer.discardPile.group){
            if (c.upgraded && (c.type == CardType.ATTACK || c.type == CardType.POWER))
                upgradeAmount++;
        }
        for (AbstractCard c : abstractPlayer.exhaustPile.group){
            if (c.upgraded && (c.type == CardType.ATTACK || c.type == CardType.POWER))
                upgradeAmount++;
        }
        if (upgradeAmount > 6){
            for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters){
                if (mo != null && !mo.isDeadOrEscaped() && !mo.isDying){
                    addToBot(new ApplyPowerAction(mo,abstractPlayer,new VulnerablePower(mo,this.magicNumber,false),this.magicNumber));
                    addToBot(new ApplyPowerAction(mo,abstractPlayer,new WeakPower(mo,this.magicNumber,false),this.magicNumber));
                }
            }
        }else {
            addToBot(new ApplyPowerAction(abstractMonster,abstractPlayer,new VulnerablePower(abstractMonster,this.magicNumber,false),this.magicNumber));
            addToBot(new ApplyPowerAction(abstractMonster,abstractPlayer,new WeakPower(abstractMonster,this.magicNumber,false),this.magicNumber));
        }
        addToBot(new DamageAction(abstractMonster, new DamageInfo(abstractPlayer, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HEAVY));
        if (rally() > 20){
            addToBot(new DamageAction(abstractMonster, new DamageInfo(abstractPlayer, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HEAVY));
            addToBot(new DamageAction(abstractMonster, new DamageInfo(abstractPlayer, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HEAVY));
        }
    }


    @Override
    public void applyPowers() {
        super.applyPowers();
        this.rawDescription = cardStrings.DESCRIPTION;
        this.rawDescription += cardStrings.EXTENDED_DESCRIPTION[0] + rally() + cardStrings.EXTENDED_DESCRIPTION[1];
        this.initializeDescription();
        AbstractPower strength = AbstractDungeon.player.getPower("Strength");
        if (strength != null)
            strength.amount *= this.magicNumber;
        super.applyPowers();
        if (strength != null)
            strength.amount /= this.magicNumber;
    }

    public void calculateCardDamage(AbstractMonster mo) {
        AbstractPower strength = AbstractDungeon.player.getPower("Strength");
        if (strength != null)
            strength.amount *= this.magicNumber;
        super.calculateCardDamage(mo);
        if (strength != null)
            strength.amount /= this.magicNumber;
    }

    @Override
    public AbstractCard makeCopy() {
        return new Radiel();
    }
}

