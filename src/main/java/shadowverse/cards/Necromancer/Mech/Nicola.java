package shadowverse.cards.Necromancer.Mech;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.PoisonPower;
import rs.lazymankits.interfaces.cards.BranchableUpgradeCard;
import rs.lazymankits.interfaces.cards.UpgradeBranch;
import shadowverse.cards.Neutral.Temp.ForbiddenArt;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Necromancer;

import java.util.ArrayList;
import java.util.List;

public class Nicola extends CustomCard implements BranchableUpgradeCard {
    public static final String ID = "shadowverse:Nicola";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Nicola");
    public static CardStrings cardStrings2 = CardCrawlGame.languagePack.getCardStrings("shadowverse:Nicola2");
    public static CardStrings cardStrings3 = CardCrawlGame.languagePack.getCardStrings("shadowverse:Nicola3");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/Nicola.png";
    public static final String IMG_PATH3 = "img/cards/Nicola3.png";
    private boolean branchPreview = true;
    private static AbstractCard art = (AbstractCard)new ForbiddenArt();

    public Nicola() {
        super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.ATTACK, Necromancer.Enums.COLOR_PURPLE, CardRarity.UNCOMMON, CardTarget.ENEMY);
        this.baseDamage = 4;
        this.baseMagicNumber = this.baseDamage;
        this.magicNumber = this.baseMagicNumber;
        this.tags.add(AbstractShadowversePlayer.Enums.MACHINE);
        this.tags.add(AbstractShadowversePlayer.Enums.LASTWORD);
        this.exhaust = true;
    }


    public void upgrade() {
        ((UpgradeBranch)((BranchableUpgradeCard)this).possibleBranches().get(chosenBranch())).upgrade();
    }

    public void update(){
        super.update();
        if (branchPreview){
            this.cardsToPreview = art;
        }
    }

    @Override
    public void triggerOnExhaust() {
        if (chosenBranch()==0){
            this.cost = 1;
            this.costForTurn = this.cost;
            this.isCostModified = true;
            this.baseDamage += this.magicNumber;
            this.applyPowers();
            if (this.damage>=this.magicNumber*4){
                this.baseDamage = this.magicNumber;
                addToBot((AbstractGameAction)new MakeTempCardInHandAction(art.makeStatEquivalentCopy()));
            }
            addToBot((AbstractGameAction)new MakeTempCardInHandAction(this.makeSameInstanceOf()));
        }
    }

    public void applyPowers() {
        super.applyPowers();
        if (chosenBranch()==1){
            int realBaseDamage = this.baseDamage;
            int count = 0;
            for (AbstractCard c: AbstractDungeon.player.exhaustPile.group){
                if (c.type==CardType.ATTACK){
                    count++;
                }
            }
            this.baseDamage += this.magicNumber*count;
            super.applyPowers();
            this.baseDamage = realBaseDamage;
            this.isDamageModified = (this.damage != this.baseDamage);
        }
    }

    public void calculateCardDamage(AbstractMonster mo) {
        super.calculateCardDamage(mo);
        if (chosenBranch()==1){
            int count = 0;
            for (AbstractCard c: AbstractDungeon.player.exhaustPile.group){
                if (c.type==CardType.ATTACK){
                    count++;
                }
            }
            int realBaseDamage = this.baseDamage;
            this.baseDamage += this.magicNumber*count;
            super.calculateCardDamage(mo);
            this.baseDamage = realBaseDamage;
            this.isDamageModified = (this.damage != this.baseDamage);
        }
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        switch (chosenBranch()){
            case 0:
                addToBot((AbstractGameAction)new SFXAction("Nicola"));
                addToBot((AbstractGameAction)new DamageAction((AbstractCreature)m, new DamageInfo((AbstractCreature)p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
                break;
            case 1:
                addToBot((AbstractGameAction)new SFXAction("Nicola2"));
                int count = 0;
                for (AbstractCard c: AbstractDungeon.player.exhaustPile.group){
                    if (c.type==CardType.ATTACK){
                        count++;
                    }
                }
                this.damage += this.magicNumber*count;
                calculateCardDamage(m);
                addToBot((AbstractGameAction)new DamageAction((AbstractCreature)m, new DamageInfo((AbstractCreature)p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
                addToBot((AbstractGameAction)new MakeTempCardInDiscardAction(this.makeStatEquivalentCopy(),1));
                break;
            case 2:
                addToBot((AbstractGameAction)new SFXAction("Nicola3"));
                addToBot((AbstractGameAction)new DamageAction((AbstractCreature)m, new DamageInfo((AbstractCreature)p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
                for (AbstractCard c:p.hand.group){
                    if (c.hasTag(AbstractShadowversePlayer.Enums.MACHINE)&&c!=this){
                        addToBot((AbstractGameAction)new ApplyPowerAction(m,p,(AbstractPower)new PoisonPower(m,p,this.magicNumber),this.magicNumber));
                    }
                }
                int mCount = 0;
                for (AbstractCard ca:AbstractDungeon.actionManager.cardsPlayedThisCombat){
                    if (ca.hasTag(AbstractShadowversePlayer.Enums.MACHINE))
                        mCount++;
                }
                if (mCount>=8){
                    AbstractCard token = art.makeStatEquivalentCopy();
                    addToBot((AbstractGameAction)new ReduceCostAction(token));
                    addToBot((AbstractGameAction)new MakeTempCardInHandAction(token));
                }
                break;
            default:
                break;
        }
    }

    public AbstractCard makeCopy() {
        return new Nicola();
    }

    @Override
    public List<UpgradeBranch> possibleBranches() {
        ArrayList<UpgradeBranch> list = new ArrayList<UpgradeBranch>();
        list.add(new UpgradeBranch() {
            @Override
            public void upgrade() {
                ++Nicola.this.timesUpgraded;
                Nicola.this.upgraded = true;
                Nicola.this.name = NAME + "+";
                Nicola.this.initializeTitle();
                Nicola.this.baseDamage = 6;
                Nicola.this.upgradedDamage = true;
                Nicola.this.baseMagicNumber = Nicola.this.baseDamage;
                Nicola.this.magicNumber = Nicola.this.baseMagicNumber;
                Nicola.this.exhaust = true;
            }
        });
        list.add(new UpgradeBranch() {
            @Override
            public void upgrade() {
                ++Nicola.this.timesUpgraded;
                Nicola.this.upgraded = true;
                Nicola.this.name = cardStrings2.NAME;
                Nicola.this.initializeTitle();
                Nicola.this.rawDescription = cardStrings2.DESCRIPTION;
                Nicola.this.initializeDescription();
                Nicola.this.branchPreview = false;
                Nicola.this.baseDamage = 4;
                Nicola.this.baseMagicNumber = Nicola.this.baseDamage;
                Nicola.this.magicNumber = Nicola.this.baseMagicNumber;
                Nicola.this.upgradedDamage = true;
                Nicola.this.upgradedMagicNumber = true;
                Nicola.this.exhaust = true;
                Nicola.this.tags.remove(AbstractShadowversePlayer.Enums.LASTWORD);
            }
        });
        list.add(new UpgradeBranch() {
            @Override
            public void upgrade() {
                ++Nicola.this.timesUpgraded;
                Nicola.this.upgraded = true;
                Nicola.this.textureImg = IMG_PATH3;
                Nicola.this.loadCardImage(IMG_PATH3);
                Nicola.this.name = cardStrings3.NAME;
                Nicola.this.initializeTitle();
                Nicola.this.rawDescription = cardStrings3.DESCRIPTION;
                Nicola.this.initializeDescription();
                Nicola.this.tags.remove(AbstractShadowversePlayer.Enums.LASTWORD);
                Nicola.this.baseDamage = 4;
                Nicola.this.baseMagicNumber = Nicola.this.baseDamage;
                Nicola.this.magicNumber = Nicola.this.baseMagicNumber;
                Nicola.this.upgradedDamage = true;
                Nicola.this.upgradedMagicNumber = true;
                Nicola.this.exhaust = false;
                Nicola.this.rarity = CardRarity.RARE;
                Nicola.this.setDisplayRarity(Nicola.this.rarity);
            }
        });
        return list;
    }
}
