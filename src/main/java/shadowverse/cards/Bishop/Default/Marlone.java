package shadowverse.cards.Bishop.Default;


import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.ClashEffect;
import rs.lazymankits.interfaces.cards.BranchableUpgradeCard;
import rs.lazymankits.interfaces.cards.UpgradeBranch;
import shadowverse.cards.Neutral.Temp.MarkOfBalance;
import shadowverse.characters.Bishop;

import java.util.ArrayList;
import java.util.List;


public class Marlone extends CustomCard implements BranchableUpgradeCard {
    public static final String ID = "shadowverse:Marlone";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Marlone");
    public static CardStrings cardStrings2 = CardCrawlGame.languagePack.getCardStrings("shadowverse:Marlone2");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/Marlone.png";
    public static final String IMG_PATH2 = "img/cards/Marlone2.png";
    private boolean branch1 = true;


    public Marlone() {
        super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.ATTACK, Bishop.Enums.COLOR_WHITE, CardRarity.UNCOMMON, CardTarget.SELF);
        this.baseBlock = 9;
        if (branch1){
            this.cardsToPreview = new MarkOfBalance();
        }
    }


    public void upgrade() {
        ((UpgradeBranch) ((BranchableUpgradeCard) this).possibleBranches().get(chosenBranch())).upgrade();
    }


    public void use(AbstractPlayer p, AbstractMonster abstractMonster) {
        switch (chosenBranch()) {
            case 0:
                addToBot((AbstractGameAction) new SFXAction("Marlone"));
                addToBot((AbstractGameAction) new GainBlockAction(p, this.block));
                addToBot((AbstractGameAction) new MakeTempCardInHandAction(this.cardsToPreview.makeStatEquivalentCopy()));
                break;
            case 1:
                addToBot((AbstractGameAction) new SFXAction("Marlone2"));
                int atkAmt = 0;
                for (AbstractCard c : p.hand.group) {
                    if (c.type == AbstractCard.CardType.ATTACK && c != this) {
                        atkAmt++;
                    }
                }
                int monsterAmt = 0;
                for (AbstractMonster mo : (AbstractDungeon.getCurrRoom()).monsters.monsters) {
                    if (!mo.isDeadOrEscaped()) {
                        monsterAmt++;
                        addToBot((AbstractGameAction)new VFXAction((AbstractGameEffect)new ClashEffect(mo.hb.cX, mo.hb.cY), 0.1F));
                    }
                }
                addToBot((AbstractGameAction)new DamageAllEnemiesAction((AbstractCreature)p, DamageInfo.createDamageMatrix((this.damage-atkAmt*5)*monsterAmt, true), this.damageTypeForTurn, AbstractGameAction.AttackEffect.NONE, true));
                break;
        }
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new Marlone();
    }

    @Override
    public List<UpgradeBranch> possibleBranches() {
        ArrayList<UpgradeBranch> list = new ArrayList<UpgradeBranch>();
        list.add(new UpgradeBranch() {
            @Override
            public void upgrade() {
                ++Marlone.this.timesUpgraded;
                Marlone.this.upgraded = true;
                Marlone.this.name = NAME + "+";
                Marlone.this.initializeTitle();
                Marlone.this.baseBlock = 12;
                Marlone.this.upgradedBlock = true;
                Marlone.this.cardsToPreview.upgrade();
                Marlone.this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
                Marlone.this.initializeDescription();
            }
        });
        list.add(new UpgradeBranch() {
            @Override
            public void upgrade() {
                ++Marlone.this.timesUpgraded;
                Marlone.this.upgraded = true;
                Marlone.this.textureImg = IMG_PATH2;
                Marlone.this.loadCardImage(IMG_PATH2);
                Marlone.this.name = cardStrings2.NAME;
                Marlone.this.baseDamage = 20;
                Marlone.this.upgradedDamage = true;
                Marlone.this.initializeTitle();
                Marlone.this.rawDescription = cardStrings2.DESCRIPTION;
                Marlone.this.initializeDescription();
                Marlone.this.branch1 = false;
                Marlone.this.cardsToPreview = null;
                Marlone.this.rarity = CardRarity.RARE;
                Marlone.this.setDisplayRarity(Marlone.this.rarity);
            }
        });
        return list;
    }
}

