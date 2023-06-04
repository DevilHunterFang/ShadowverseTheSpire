package shadowverse.cards.Bishop.Default;


import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.RegenPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.CleaveEffect;
import rs.lazymankits.interfaces.cards.BranchableUpgradeCard;
import rs.lazymankits.interfaces.cards.UpgradeBranch;
import shadowverse.characters.Bishop;

import java.util.ArrayList;
import java.util.List;


public class Jeanne extends CustomCard implements BranchableUpgradeCard {
    public static final String ID = "shadowverse:Jeanne";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Jeanne");
    public static CardStrings cardStrings2 = CardCrawlGame.languagePack.getCardStrings("shadowverse:Jeanne2");
    public static CardStrings cardStrings3 = CardCrawlGame.languagePack.getCardStrings("shadowverse:Jeanne3");
    public static CardStrings cardStrings4 = CardCrawlGame.languagePack.getCardStrings("shadowverse:Jeanne4");
    public static CardStrings cardStrings5 = CardCrawlGame.languagePack.getCardStrings("shadowverse:Jeanne5");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/Jeanne.png";
    public static final String IMG_PATH2 = "img/cards/Jeanne2.png";
    public static final String IMG_PATH3 = "img/cards/Jeanne3.png";
    public static final String IMG_PATH4 = "img/cards/Jeanne4.png";
    public static final String IMG_PATH5 = "img/cards/Jeanne5.png";


    public Jeanne() {
        super(ID, NAME, IMG_PATH, 3, DESCRIPTION, CardType.ATTACK, Bishop.Enums.COLOR_WHITE, CardRarity.RARE, CardTarget.ALL);
        this.baseDamage = 16;
        this.baseMagicNumber = 2;
        this.magicNumber = this.baseMagicNumber;
        this.exhaust = true;
        this.isMultiDamage = true;
    }


    public void upgrade() {
        ((UpgradeBranch) ((BranchableUpgradeCard) this).possibleBranches().get(chosenBranch())).upgrade();
    }


    public void use(AbstractPlayer p, AbstractMonster abstractMonster) {
        addToBot((AbstractGameAction)new SFXAction("ATTACK_HEAVY"));
        addToBot((AbstractGameAction)new VFXAction((AbstractCreature)p, (AbstractGameEffect)new CleaveEffect(), 0.1F));
        addToBot((AbstractGameAction)new DamageAllEnemiesAction((AbstractCreature)p, this.multiDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.NONE));
        switch (chosenBranch()) {
            case 0:
                addToBot((AbstractGameAction)new SFXAction("Jeanne"));
                addToBot((AbstractGameAction)new ApplyPowerAction(p,p,(AbstractPower)new DexterityPower(p,this.magicNumber),this.magicNumber));
                break;
            case 1:
                addToBot((AbstractGameAction)new SFXAction("Jeanne2"));
                addToBot((AbstractGameAction)new ApplyPowerAction(p,p,(AbstractPower)new StrengthPower(p,this.magicNumber),this.magicNumber));
                break;
            case 2:
                addToBot((AbstractGameAction)new SFXAction("Jeanne3"));
                addToBot((AbstractGameAction)new HealAction(p,p,this.magicNumber));
                break;
            case 3:
                addToBot((AbstractGameAction)new SFXAction("Jeanne4"));
                addToBot((AbstractGameAction)new LoseHPAction(p,p,this.magicNumber));
                addToBot((AbstractGameAction)new ApplyPowerAction(p,p,(AbstractPower)new RegenPower(p,2),2));
                break;
            case 4:
                addToBot(new DrawCardAction(1));
                addToBot((AbstractGameAction)new SFXAction("ATTACK_HEAVY"));
                addToBot((AbstractGameAction)new VFXAction((AbstractCreature)p, (AbstractGameEffect)new CleaveEffect(), 0.1F));
                addToBot((AbstractGameAction)new DamageAllEnemiesAction((AbstractCreature)p, this.multiDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.NONE));
                addToBot((AbstractGameAction)new SFXAction("Jeanne5"));
                addToBot((AbstractGameAction)new HealAction(p,p,this.magicNumber));
                addToBot((AbstractGameAction)new HealAction(p,p,this.magicNumber));
                break;
        }
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new Jeanne();
    }

    @Override
    public List<UpgradeBranch> possibleBranches() {
        ArrayList<UpgradeBranch> list = new ArrayList<UpgradeBranch>();
        list.add(new UpgradeBranch() {
            @Override
            public void upgrade() {
                ++Jeanne.this.timesUpgraded;
                Jeanne.this.upgraded = true;
                Jeanne.this.name = NAME + "+";
                Jeanne.this.initializeTitle();
                Jeanne.this.baseDamage = 21;
                Jeanne.this.upgradedDamage = true;
            }
        });
        list.add(new UpgradeBranch() {
            @Override
            public void upgrade() {
                ++Jeanne.this.timesUpgraded;
                Jeanne.this.upgraded = true;
                Jeanne.this.textureImg = IMG_PATH2;
                Jeanne.this.loadCardImage(IMG_PATH2);
                Jeanne.this.name = cardStrings2.NAME;
                Jeanne.this.initializeTitle();
                Jeanne.this.baseDamage = 21;
                Jeanne.this.upgradedDamage = true;
                Jeanne.this.rawDescription = cardStrings2.DESCRIPTION;
                Jeanne.this.initializeDescription();
            }
        });
        list.add(new UpgradeBranch() {
            @Override
            public void upgrade() {
                ++Jeanne.this.timesUpgraded;
                Jeanne.this.upgraded = true;
                Jeanne.this.textureImg = IMG_PATH3;
                Jeanne.this.loadCardImage(IMG_PATH3);
                Jeanne.this.name = cardStrings3.NAME;
                Jeanne.this.baseDamage = 27;
                Jeanne.this.upgradedDamage = true;
                Jeanne.this.baseMagicNumber = 3;
                Jeanne.this.magicNumber = Jeanne.this.baseMagicNumber;
                Jeanne.this.initializeTitle();
                Jeanne.this.rawDescription = cardStrings3.DESCRIPTION;
                Jeanne.this.initializeDescription();
            }
        });
        list.add(new UpgradeBranch() {
            @Override
            public void upgrade() {
                ++Jeanne.this.timesUpgraded;
                Jeanne.this.upgraded = true;
                Jeanne.this.textureImg = IMG_PATH4;
                Jeanne.this.loadCardImage(IMG_PATH4);
                Jeanne.this.name = cardStrings4.NAME;
                Jeanne.this.baseDamage = 35;
                Jeanne.this.upgradedDamage = true;
                Jeanne.this.baseMagicNumber = 5;
                Jeanne.this.magicNumber = Jeanne.this.baseMagicNumber;
                Jeanne.this.initializeTitle();
                Jeanne.this.rawDescription = cardStrings4.DESCRIPTION;
                Jeanne.this.initializeDescription();
            }
        });
        list.add(new UpgradeBranch() {
            @Override
            public void upgrade() {
                ++Jeanne.this.timesUpgraded;
                Jeanne.this.upgraded = true;
                Jeanne.this.textureImg = IMG_PATH5;
                Jeanne.this.loadCardImage(IMG_PATH5);
                Jeanne.this.name = cardStrings5.NAME;
                Jeanne.this.baseDamage = 12;
                Jeanne.this.upgradedDamage = true;
                Jeanne.this.baseMagicNumber = 2;
                Jeanne.this.magicNumber = Jeanne.this.baseMagicNumber;
                Jeanne.this.initializeTitle();
                Jeanne.this.rawDescription = cardStrings5.DESCRIPTION;
                Jeanne.this.initializeDescription();
            }
        });
        return list;
    }
}

