package shadowverse.cards.Rare;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
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
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.LightningEffect;
import rs.lazymankits.interfaces.cards.BranchableUpgradeCard;
import rs.lazymankits.interfaces.cards.UpgradeBranch;
import shadowverse.Shadowverse;
import shadowverse.action.AlbertDrawAction;
import shadowverse.action.DestroyAction;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Royal;

import java.util.ArrayList;
import java.util.List;

public class Albert extends CustomCard implements BranchableUpgradeCard {
    public static final String ID = "shadowverse:Albert";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Albert");
    public static CardStrings cardStrings2 = CardCrawlGame.languagePack.getCardStrings("shadowverse:Albert2");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/Albert.png";
    public static final String IMG_PATH2 = "img/cards/Albert2.png";

    public Albert() {
        super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.ATTACK, Royal.Enums.COLOR_YELLOW, CardRarity.RARE, CardTarget.ENEMY);
        this.tags.add(AbstractShadowversePlayer.Enums.LEVIN);
        this.tags.add(AbstractShadowversePlayer.Enums.HERO);
        this.baseDamage = 12;
    }

    @Override
    public void upgrade() {
        ((UpgradeBranch)((BranchableUpgradeCard)this).possibleBranches().get(chosenBranch())).upgrade();
    }

    @Override
    public void update() {
        if (AbstractDungeon.currMapNode != null && (AbstractDungeon.getCurrRoom()).phase == AbstractRoom.RoomPhase.COMBAT &&
                Shadowverse.Enhance(3)) {
            setCostForTurn(3);
        } else {
            if (this.costForTurn!=0){
                setCostForTurn(1);
            }
        }
        super.update();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        switch (chosenBranch()){
            case 0:
                addToBot(new SFXAction(ID.replace("shadowverse:", "")));
                addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
                if (Shadowverse.Enhance(3) && this.costForTurn == 3) {
                    addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
                    addToBot(new AlbertDrawAction(11 - p.hand.group.size()));
                }
                break;
            case 1:
                if (Shadowverse.Enhance(3) && this.costForTurn == 3){
                    addToBot(new SFXAction("Albert2_EH"));
                    int dmg = this.damage;
                    for (AbstractCard c:p.hand.group){
                        if (c.type==CardType.ATTACK&&c!=this){
                            dmg = this.damage + 6;
                            addToBot((AbstractGameAction)new ExhaustSpecificCardAction(c,p.hand));
                        }
                    }
                    addToBot(new DamageAction(m, new DamageInfo(p, dmg, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HEAVY));
                    addToBot(new DamageAction(m, new DamageInfo(p, dmg, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HEAVY));
                    for (AbstractMonster mo : (AbstractDungeon.getCurrRoom()).monsters.monsters) {
                        if (!mo.isDeadOrEscaped()){
                            addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)mo, (AbstractCreature)p, (AbstractPower)new WeakPower((AbstractCreature)mo, 2, false), 2, true, AbstractGameAction.AttackEffect.NONE));
                            addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)mo, (AbstractCreature)p, (AbstractPower)new VulnerablePower((AbstractCreature)mo, 2, false), 2, true, AbstractGameAction.AttackEffect.NONE));
                            addToBot((AbstractGameAction)new VFXAction((AbstractGameEffect)new LightningEffect(mo.drawX, mo.drawY), 0.05F));
                        }
                    }
                }else {
                    addToBot(new SFXAction("Albert2"));
                    int dmg = this.damage;
                    for (AbstractCard card:p.discardPile.group){
                        if (card.type == CardType.ATTACK){
                            dmg += 6;
                            break;
                        }
                    }
                    addToBot((AbstractGameAction)new DestroyAction(1,(new DamageAction(m, new DamageInfo(p, dmg, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL))));
                }
                break;
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new Albert();
    }

    @Override
    public List<UpgradeBranch> possibleBranches() {
        ArrayList<UpgradeBranch> list = new ArrayList<UpgradeBranch>();
        list.add(new UpgradeBranch() {
            @Override
            public void upgrade() {
                ++Albert.this.timesUpgraded;
                Albert.this.upgraded = true;
                Albert.this.name = NAME + "+";
                Albert.this.initializeTitle();
                Albert.this.upgradeName();
                Albert.this.baseDamage = 16;
                Albert.this.upgradedDamage = true;
            }
        });
        list.add(new UpgradeBranch() {
            @Override
            public void upgrade() {
                ++Albert.this.timesUpgraded;
                Albert.this.upgraded = true;
                Albert.this.textureImg = IMG_PATH2;
                Albert.this.loadCardImage(IMG_PATH2);
                Albert.this.name = cardStrings2.NAME;
                Albert.this.initializeTitle();
                Albert.this.rawDescription = cardStrings2.DESCRIPTION;
                Albert.this.initializeDescription();
                Albert.this.baseDamage = 18;
                Albert.this.upgradedDamage = true;
            }
        });
        return list;
    }
}


