package shadowverse.cards.Necromancer.Default;


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
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.vfx.stance.StanceAuraEffect;
import rs.lazymankits.interfaces.cards.BranchableUpgradeCard;
import rs.lazymankits.interfaces.cards.UpgradeBranch;
import shadowverse.action.NecromanceAction;
import shadowverse.action.SilenceAction;
import shadowverse.cards.Neutral.Temp.ExpandingScreaming;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Necromancer;
import shadowverse.powers.Cemetery;

import java.util.ArrayList;
import java.util.List;


public class OmenOfSilence extends CustomCard implements BranchableUpgradeCard {
    public static final String ID = "shadowverse:OmenOfSilence";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:OmenOfSilence");
    public static CardStrings cardStrings2 = CardCrawlGame.languagePack.getCardStrings("shadowverse:OmenOfSilence2");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/OmenOfSilence.png";
    public static final String IMG_PATH2 = "img/cards/OmenOfSilence2.png";

    public OmenOfSilence() {
        super(ID, NAME, IMG_PATH, 2, DESCRIPTION, CardType.POWER, Necromancer.Enums.COLOR_PURPLE, CardRarity.RARE, CardTarget.ALL_ENEMY);
    }


    public void upgrade() {
        ((UpgradeBranch) ((BranchableUpgradeCard) this).possibleBranches().get(chosenBranch())).upgrade();
    }

    public void applyPowers() {
        int amt = 0;
        for (AbstractCard c : AbstractDungeon.actionManager.cardsPlayedThisCombat) {
            if (c.cardID.contains("OmenOfSilence"))
                amt++;
        }
        if (amt>10){
            amt=10;
        }
        int realBaseDamage = this.baseDamage;
        this.baseDamage = amt * this.baseDamage;
        super.applyPowers();
        this.baseDamage = realBaseDamage;
        this.isDamageModified = (this.damage != this.baseDamage);
    }

    public void calculateCardDamage(AbstractMonster mo) {
        int amt = 0;
        for (AbstractCard c : AbstractDungeon.actionManager.cardsPlayedThisCombat) {
            if (c.cardID.contains("OmenOfSilence"))
                amt++;
        }
        if (amt>10){
            amt=10;
        }
        int realBaseDamage = this.baseDamage;
        this.baseDamage =  amt * this.baseDamage;
        super.calculateCardDamage(mo);
        this.baseDamage = realBaseDamage;
        this.isDamageModified = (this.damage != this.baseDamage);
    }

    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        switch (chosenBranch()) {
            case 0:
                AbstractDungeon.effectsQueue.add(new StanceAuraEffect("Divinity"));
                addToBot((AbstractGameAction) new SFXAction("OmenOfSilence"));
                int playerNecromance = 0;
                if (abstractPlayer.hasPower(Cemetery.POWER_ID)) {
                    for (AbstractPower p : abstractPlayer.powers) {
                        if (p.ID.equals(Cemetery.POWER_ID))
                            playerNecromance = p.amount;
                    }
                }
                if (playerNecromance >= 3) {
                    addToBot((AbstractGameAction) new NecromanceAction(3, null, (AbstractGameAction) new SilenceAction()));
                }
                break;
            case 1:
                addToBot((AbstractGameAction) new SFXAction("OmenOfSilence2"));
                int amt = 0;
                for (AbstractCard c : AbstractDungeon.actionManager.cardsPlayedThisCombat) {
                    if (c.cardID.contains("OmenOfSilence"))
                        amt++;
                }
                calculateCardDamage(abstractMonster);
                if (amt>10) {
                    AbstractMonster mo = AbstractDungeon.getRandomMonster();
                    if (!mo.isDeadOrEscaped()) {
                        addToBot((AbstractGameAction) new ApplyPowerAction(mo, abstractPlayer, new VulnerablePower(abstractMonster, 3, false), 3));
                        addToBot((AbstractGameAction) new ApplyPowerAction(mo, abstractPlayer, new WeakPower(abstractMonster, 3, false), 3));
                    }
                }
                addToBot((AbstractGameAction)new DamageAction((AbstractCreature)abstractMonster, new DamageInfo((AbstractCreature)abstractPlayer, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
                break;
        }
    }

    @Override
    public void triggerOnExhaust() {
        if (chosenBranch()==1){
            addToBot((AbstractGameAction)new MakeTempCardInHandAction(new ExpandingScreaming()));
        }
    }

    public AbstractCard makeCopy() {
        return (AbstractCard) new OmenOfSilence();
    }

    @Override
    public List<UpgradeBranch> possibleBranches() {
        ArrayList<UpgradeBranch> list = new ArrayList<UpgradeBranch>();
        list.add(new UpgradeBranch() {
            @Override
            public void upgrade() {
                ++OmenOfSilence.this.timesUpgraded;
                OmenOfSilence.this.upgraded = true;
                OmenOfSilence.this.name = NAME + "+";
                OmenOfSilence.this.initializeTitle();
                OmenOfSilence.this.upgradeBaseCost(1);
            }
        });
        list.add(new UpgradeBranch() {
            @Override
            public void upgrade() {
                ++OmenOfSilence.this.timesUpgraded;
                OmenOfSilence.this.upgraded = true;
                OmenOfSilence.this.textureImg = IMG_PATH2;
                OmenOfSilence.this.loadCardImage(IMG_PATH2);
                OmenOfSilence.this.name = cardStrings2.NAME;
                OmenOfSilence.this.initializeTitle();
                OmenOfSilence.this.baseDamage = 3;
                OmenOfSilence.this.upgradedDamage = true;
                OmenOfSilence.this.type = CardType.ATTACK;
                OmenOfSilence.this.target = CardTarget.ENEMY;
                OmenOfSilence.this.exhaust = true;
                OmenOfSilence.this.tags.add(AbstractShadowversePlayer.Enums.LASTWORD);
                OmenOfSilence.this.rawDescription = cardStrings2.DESCRIPTION;
                OmenOfSilence.this.initializeDescription();
                OmenOfSilence.this.cardsToPreview = new ExpandingScreaming();
                OmenOfSilence.this.upgradeBaseCost(1);
            }
        });
        return list;
    }
}

