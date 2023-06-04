package shadowverse.cards.Nemesis.Default;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.SmallLaserEffect;
import rs.lazymankits.interfaces.cards.BranchableUpgradeCard;
import rs.lazymankits.interfaces.cards.UpgradeBranch;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Nemesis;

import java.util.ArrayList;
import java.util.List;


public class Ralmia
        extends CustomCard implements BranchableUpgradeCard {
    public static final String ID = "shadowverse:Ralmia";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Ralmia");
    public static CardStrings cardStrings2 = CardCrawlGame.languagePack.getCardStrings("shadowverse:Ralmia2");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/Ralmia.png";
    public static final String IMG_PATH2 = "img/cards/Ralmia2.png";
    private boolean enhance = false;

    public Ralmia() {
        super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.ATTACK, Nemesis.Enums.COLOR_SKY, CardRarity.RARE, CardTarget.ENEMY);
        this.baseDamage = 7;
        this.tags.add(AbstractShadowversePlayer.Enums.MACHINE);
        this.tags.add(AbstractShadowversePlayer.Enums.ARTIFACT);
    }


    public void upgrade() {
        ((UpgradeBranch)((BranchableUpgradeCard)this).possibleBranches().get(chosenBranch())).upgrade();
    }


    public void applyPowers() {
        if (chosenBranch()==0){
            if (this.enhance&&EnergyPanel.getCurrentEnergy()>=2+this.costForTurn) {
                int count = 0;
                ArrayList<String> dup = new ArrayList<>();
                for (AbstractCard c : AbstractDungeon.player.exhaustPile.group) {
                    if (c.hasTag(AbstractShadowversePlayer.Enums.ARTIFACT) && !dup.contains(c.cardID)) {
                        dup.add(c.cardID);
                        count++;
                    }
                }
                int realBaseDamage = this.baseDamage;
                this.baseDamage += count * this.baseDamage;
                super.applyPowers();
                this.baseDamage = realBaseDamage;
                this.isDamageModified = (this.damage != this.baseDamage);
            }else {
                this.baseDamage = this.upgraded?9:7;
                this.damage = this.baseDamage;
                this.isDamageModified = false;
            }
        }else {
            super.applyPowers();
        }
    }


    public void triggerOnOtherCardPlayed(AbstractCard c) {
        if (chosenBranch()==0){
            if (c.hasTag(AbstractShadowversePlayer.Enums.ARTIFACT)){
                flash();
                addToBot((AbstractGameAction)new SFXAction("Ralmia_EH"));
                this.enhance = true;
            }
        }
    }

    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        switch (chosenBranch()){
            case 0:
                addToBot((AbstractGameAction)new SFXAction("Ralmia"));
                addToBot((AbstractGameAction)new VFXAction((AbstractGameEffect)new SmallLaserEffect(abstractMonster.hb.cX, abstractMonster.hb.cY, abstractPlayer.hb.cX, abstractPlayer.hb.cY), 0.1F));
                if (this.enhance&&EnergyPanel.getCurrentEnergy()>=this.costForTurn+2) {
                    int count = 0;
                    ArrayList<String> dup = new ArrayList<>();
                    for (AbstractCard c : AbstractDungeon.player.exhaustPile.group) {
                        if (c.hasTag(AbstractShadowversePlayer.Enums.ARTIFACT) && !dup.contains(c.cardID)) {
                            dup.add(c.cardID);
                            count++;
                        }
                    }
                    addToBot((AbstractGameAction) new DamageAction((AbstractCreature) abstractMonster, new DamageInfo((AbstractCreature) abstractPlayer, this.damage*(count+1), this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
                    EnergyPanel.useEnergy(2);
                    this.enhance = false;
                }else {
                    addToBot((AbstractGameAction) new DamageAction((AbstractCreature) abstractMonster, new DamageInfo((AbstractCreature) abstractPlayer, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
                }
                break;
            case 1:
                addToBot((AbstractGameAction)new SFXAction("Ralmia2"));
                addToBot((AbstractGameAction)new VFXAction((AbstractGameEffect)new SmallLaserEffect(abstractMonster.hb.cX, abstractMonster.hb.cY, abstractPlayer.hb.cX, abstractPlayer.hb.cY), 0.1F));
                addToBot((AbstractGameAction) new DamageAction((AbstractCreature) abstractMonster, new DamageInfo((AbstractCreature) abstractPlayer, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
                ArrayList<AbstractCard> list = new ArrayList<>();
                ArrayList<String> dup = new ArrayList<>();
                for (AbstractCard c: abstractPlayer.exhaustPile.group){
                    if (c.hasTag(AbstractShadowversePlayer.Enums.ARTIFACT)&&!dup.contains(c.cardID)){
                        dup.add(c.cardID);
                        AbstractCard card = c.makeCopy();
                        list.add(card);
                    }
                }
                if (list.size()>=6){
                    addToBot ((AbstractGameAction)new DamageAllEnemiesAction((AbstractCreature)abstractPlayer, DamageInfo.createDamageMatrix(this.magicNumber, true), this.damageTypeForTurn, AbstractGameAction.AttackEffect.FIRE, true));
                }
                break;
        }
    }

    @Override
    public List<UpgradeBranch> possibleBranches() {
        ArrayList<UpgradeBranch> list = new ArrayList<UpgradeBranch>();
        list.add(new UpgradeBranch() {
            @Override
            public void upgrade() {
                ++Ralmia.this.timesUpgraded;
                Ralmia.this.upgraded = true;
                Ralmia.this.name = NAME + "+";
                Ralmia.this.baseDamage = 9;
                Ralmia.this.upgradedDamage = true;
            }
        });
        list.add(new UpgradeBranch() {
            @Override
            public void upgrade() {
                ++Ralmia.this.timesUpgraded;
                Ralmia.this.upgraded = true;
                Ralmia.this.textureImg = IMG_PATH2;
                Ralmia.this.loadCardImage(IMG_PATH2);
                Ralmia.this.name = cardStrings2.NAME;
                Ralmia.this.initializeTitle();
                Ralmia.this.rawDescription = cardStrings2.DESCRIPTION;
                Ralmia.this.initializeDescription();
                Ralmia.this.baseDamage = 9;
                Ralmia.this.upgradedDamage = true;
                Ralmia.this.baseMagicNumber = 24;
                Ralmia.this.magicNumber = Ralmia.this.baseMagicNumber;
                Ralmia.this.upgradedMagicNumber = true;
            }
        });
        return list;
    }
}


