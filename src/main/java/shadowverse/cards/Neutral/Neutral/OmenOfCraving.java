package shadowverse.cards.Neutral.Neutral;


import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.unique.VampireDamageAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.BlurPower;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import rs.lazymankits.interfaces.cards.BranchableUpgradeCard;
import rs.lazymankits.interfaces.cards.UpgradeBranch;
import shadowverse.cards.AbstractNeutralCard;
import shadowverse.cards.Neutral.Temp.RavenousSweetness;

import java.util.ArrayList;
import java.util.List;

public class OmenOfCraving extends AbstractNeutralCard implements BranchableUpgradeCard {
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:OmenOfCraving");
    public static CardStrings cardStrings2 = CardCrawlGame.languagePack.getCardStrings("shadowverse:OmenOfCraving2");
    public static final String ID = "shadowverse:OmenOfCraving";
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/OmenOfCraving.png";
    public static final String IMG_PATH2 = "img/cards/OmenOfCraving2.png";
    public static boolean dupCheck = true;

    public OmenOfCraving() {
        super(ID, NAME, IMG_PATH, 3, DESCRIPTION, CardType.ATTACK, CardColor.COLORLESS, CardRarity.RARE, CardTarget.ENEMY);
        this.baseDamage = 3;
        this.baseBlock = 10;
    }

    @Override
    public void upgrade() {
        ((UpgradeBranch) ((BranchableUpgradeCard) this).possibleBranches().get(chosenBranch())).upgrade();
    }

    @Override
    public void atTurnStart() {
        if (chosenBranch()==1){
            if (AbstractDungeon.actionManager.turn >= 4 && dupCheck) {
                dupCheck = false;
                AbstractCard c = new RavenousSweetness();
                if (AbstractDungeon.player.discardPile.contains((AbstractCard)this)) {
                    addToBot((AbstractGameAction)new ExhaustSpecificCardAction(this, AbstractDungeon.player.discardPile));
                    addToBot((AbstractGameAction)new MakeTempCardInHandAction(c, 1));
                } else if (AbstractDungeon.player.drawPile.contains((AbstractCard)this)) {
                    addToBot((AbstractGameAction)new ExhaustSpecificCardAction(this, AbstractDungeon.player.drawPile));
                    addToBot((AbstractGameAction)new MakeTempCardInHandAction(c, 1));
                }
            }else if (AbstractDungeon.actionManager.turn < 4){
                dupCheck = true;
            }
        }
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        switch (chosenBranch()){
            case 0:
                addToBot((AbstractGameAction)new SFXAction("OmenOfCraving"));
                addToBot((AbstractGameAction)new VampireDamageAction((AbstractCreature)abstractMonster, new DamageInfo((AbstractCreature)abstractPlayer, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SMASH));
                addToBot((AbstractGameAction)new GainBlockAction(abstractPlayer,this.block));
                addToBot((AbstractGameAction)new ApplyPowerAction(abstractPlayer,abstractPlayer,new StrengthPower(abstractPlayer,2),2));
                addToBot((AbstractGameAction)new ApplyPowerAction(abstractPlayer,abstractPlayer,new BlurPower(abstractPlayer,1),1));
                if (AbstractDungeon.actionManager.turn >= 4){
                    addToBot((AbstractGameAction)new DrawCardAction(5));
                }
                break;
            case 1:
                addToBot((AbstractGameAction)new DamageAction((AbstractCreature)abstractMonster, new DamageInfo((AbstractCreature)abstractPlayer, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SMASH));
                addToBot((AbstractGameAction)new ApplyPowerAction(abstractPlayer,abstractPlayer,new StrengthPower(abstractPlayer,2),2));
                addToBot((AbstractGameAction)new ApplyPowerAction(abstractPlayer,abstractPlayer,new DexterityPower(abstractPlayer,-2),-2));
                addToBot((AbstractGameAction)new HealAction(abstractPlayer,abstractPlayer,6));
                addToBot((AbstractGameAction)new SFXAction("OmenOfCraving2"));
                break;
        }
    }

    @Override
    public List<UpgradeBranch> possibleBranches() {
        ArrayList<UpgradeBranch> list = new ArrayList<UpgradeBranch>();
        list.add(new UpgradeBranch() {
            @Override
            public void upgrade() {
                ++OmenOfCraving.this.timesUpgraded;
                OmenOfCraving.this.upgraded = true;
                OmenOfCraving.this.name = NAME + "+";
                OmenOfCraving.this.initializeTitle();
                OmenOfCraving.this.baseDamage = 5;
                OmenOfCraving.this.upgradedDamage = true;
                OmenOfCraving.this.baseBlock = 15;
                OmenOfCraving.this.upgradedBlock = true;
            }
        });
        list.add(new UpgradeBranch() {
            @Override
            public void upgrade() {
                ++OmenOfCraving.this.timesUpgraded;
                OmenOfCraving.this.upgraded = true;
                OmenOfCraving.this.textureImg = IMG_PATH2;
                OmenOfCraving.this.loadCardImage(IMG_PATH2);
                OmenOfCraving.this.name = cardStrings2.NAME;
                OmenOfCraving.this.initializeTitle();
                OmenOfCraving.this.upgradeBaseCost(1);
                OmenOfCraving.this.baseDamage = 6;
                OmenOfCraving.this.upgradedDamage = true;
                OmenOfCraving.this.rawDescription = cardStrings2.DESCRIPTION;
                OmenOfCraving.this.initializeDescription();
                OmenOfCraving.this.cardsToPreview = new RavenousSweetness();
            }
        });
        return list;
    }
}
