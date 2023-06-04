package shadowverse.cards.Vampire.Vengeance;

import basemod.abstracts.CustomCard;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
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
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.DoubleDamagePower;
import com.megacrit.cardcrawl.powers.PenNibPower;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.ClawEffect;
import rs.lazymankits.interfaces.cards.BranchableUpgradeCard;
import rs.lazymankits.interfaces.cards.UpgradeBranch;
import shadowverse.characters.Vampire;
import shadowverse.powers.AvaricePower;
import shadowverse.powers.EpitaphPower;
import shadowverse.stance.Vengeance;

import java.util.ArrayList;
import java.util.List;


public class RageCommander
        extends CustomCard implements BranchableUpgradeCard {
    public static final String ID = "shadowverse:RageCommander";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:RageCommander");
    public static CardStrings cardStrings2 = CardCrawlGame.languagePack.getCardStrings("shadowverse:CrimsonWar");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/RageCommander.png";
    public static final String IMG_PATH2 = "img/cards/CrimsonWar.png";
    private boolean doubleCheck = false;
    private boolean drainCheck = false;
    private boolean aoeCheck = false;
    private int turnCount = 0;

    public RageCommander() {
        super(ID, NAME, IMG_PATH, 2, DESCRIPTION, CardType.ATTACK, Vampire.Enums.COLOR_SCARLET, CardRarity.RARE, CardTarget.ENEMY);
        this.baseDamage = 8;
        this.baseMagicNumber = 8;
        this.magicNumber = this.baseMagicNumber;
    }


    public void upgrade() {
        ((UpgradeBranch)((BranchableUpgradeCard)this).possibleBranches().get(chosenBranch())).upgrade();
    }



    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        switch (chosenBranch()){
            case 1:
                addToBot((AbstractGameAction) new SFXAction("RageCommander"));
                addToBot((AbstractGameAction)new DamageAction((AbstractCreature)abstractMonster, new DamageInfo((AbstractCreature)abstractPlayer, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
                if (abstractPlayer.hasPower(EpitaphPower.POWER_ID)||abstractPlayer.stance.ID.equals(Vengeance.STANCE_ID)){
                    addToBot((AbstractGameAction)new ApplyPowerAction(abstractPlayer, abstractPlayer, new DoubleDamagePower(abstractPlayer, 1, false), 1));
                }else {
                    addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)abstractPlayer, (AbstractCreature)abstractPlayer, (AbstractPower)new PenNibPower((AbstractCreature)abstractPlayer, 1), 1, true));
                }
                break;
            case 0:
                addToBot((AbstractGameAction)new SFXAction("CrimsonWar"));
                if (doubleCheck&&drainCheck){
                    addToBot((AbstractGameAction)new VampireDamageAction((AbstractCreature)abstractMonster, new DamageInfo((AbstractCreature)abstractPlayer, this.damage*2, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
                }else if (doubleCheck){
                    addToBot((AbstractGameAction)new DamageAction((AbstractCreature)abstractMonster, new DamageInfo((AbstractCreature)abstractPlayer, this.damage*2, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
                }else {
                    addToBot((AbstractGameAction)new DamageAction((AbstractCreature)abstractMonster, new DamageInfo((AbstractCreature)abstractPlayer, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
                }
                if (aoeCheck){
                    for (AbstractMonster m : (AbstractDungeon.getCurrRoom()).monsters.monsters) {
                        if (!m.isDeadOrEscaped()){
                            addToBot((AbstractGameAction)new VFXAction((AbstractGameEffect)new ClawEffect(m.hb.cX, m.hb.cY, Color.SCARLET, Color.ORANGE), 0.1F));
                        }
                        if (abstractPlayer.hasPower(EpitaphPower.POWER_ID)||abstractPlayer.stance.ID.equals(Vengeance.STANCE_ID)){
                            addToBot((AbstractGameAction)new DamageAction((AbstractCreature)m, new DamageInfo((AbstractCreature)abstractPlayer, this.magicNumber*2, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.NONE));
                        }else {
                            addToBot((AbstractGameAction)new DamageAction((AbstractCreature)m, new DamageInfo((AbstractCreature)abstractPlayer, this.magicNumber, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.NONE));
                        }
                    }
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void onRetained(){
        if (chosenBranch()==0 && AbstractDungeon.player.hasPower(EpitaphPower.POWER_ID)||AbstractDungeon.player.hasPower(AvaricePower.POWER_ID)){
            this.turnCount++;
            switch (turnCount){
                case 1:
                    this.doubleCheck = true;
                    flash();
                    break;
                case 2:
                    this.drainCheck = true;
                    flash();
                    break;
                case 3:
                    this.aoeCheck = true;
                    flash();
                    break;
                default:
                    break;
            }
        }
    }

    public AbstractCard makeCopy() {
        return (AbstractCard) new RageCommander();
    }

    @Override
    public int defaultBranch() {
        if (canBranch())
            return 1;
        return -1;
    }

    @Override
    public List<UpgradeBranch> possibleBranches() {
        ArrayList<UpgradeBranch> list = new ArrayList<UpgradeBranch>();
        list.add(new UpgradeBranch() {
            @Override
            public void upgrade() {
                ++RageCommander.this.timesUpgraded;
                RageCommander.this.upgraded = true;
                RageCommander.this.textureImg = IMG_PATH2;
                RageCommander.this.loadCardImage(IMG_PATH2);
                RageCommander.this.name = cardStrings2.NAME;
                RageCommander.this.initializeTitle();
                RageCommander.this.rawDescription = cardStrings2.DESCRIPTION;
                RageCommander.this.initializeDescription();
                RageCommander.this.baseDamage = 12;
                RageCommander.this.upgradedDamage = true;
                RageCommander.this.selfRetain = true;
                RageCommander.this.upgradeBaseCost(2);
                RageCommander.this.exhaust = true;
            }
        });
        list.add(new UpgradeBranch() {
            @Override
            public void upgrade() {
                ++RageCommander.this.timesUpgraded;
                RageCommander.this.upgraded = true;
                RageCommander.this.name = NAME + "+";
                RageCommander.this.initializeTitle();
                RageCommander.this.upgradeBaseCost(1);
            }
        });
        return list;
    }
}


