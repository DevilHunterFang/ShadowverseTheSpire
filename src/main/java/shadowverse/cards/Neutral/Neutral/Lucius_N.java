package shadowverse.cards.Neutral.Neutral;


import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import shadowverse.cards.AbstractNeutralCard;
import shadowverse.cards.Neutral.Status.Ghost;
import shadowverse.cards.Neutral.Temp.AncientArtifact;
import shadowverse.characters.*;


public class Lucius_N
        extends AbstractNeutralCard {
    public static final String ID = "shadowverse:Lucius_N";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Lucius_N");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/Lucius_N.png";

    public Lucius_N() {
        super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.ATTACK, CardColor.COLORLESS, CardRarity.RARE, CardTarget.ENEMY);
        this.baseDamage = 9;
        this.tags.add(AbstractShadowversePlayer.Enums.ACADEMIC);
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeDamage(6);
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }


    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new SFXAction("Lucius_N"));
        addToBot(new DamageAction(m,new DamageInfo(p, damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HEAVY));
        if (p instanceof Elf || p instanceof Witchcraft){
            addToBot(new GainEnergyAction(1));
        }
        if (p instanceof Necromancer || p instanceof Nemesis){
            addToBot(new MakeTempCardInHandAction(new Ghost(),2));
        }
        if (p instanceof Royal){
            addToBot(new ApplyPowerAction(m,p,new VulnerablePower(m,2,false),2));
        }
        if (p instanceof Vampire || p instanceof Bishop){
            addToBot(new HealAction(p,p,3));
        }
        if (upgraded){
            switch (m.type){
                case ELITE:
                    addToBot(new GainEnergyAction(1));
                    break;
                case BOSS:
                    addToBot(new MakeTempCardInHandAction(new AncientArtifact(),2));
                    break;
                case NORMAL:
                    addToBot(new DamageAction(m,new DamageInfo(p, 4, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.FIRE));
                    break;
            }
        }
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new Lucius_N();
    }
}

