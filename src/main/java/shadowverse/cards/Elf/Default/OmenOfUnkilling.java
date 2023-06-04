package shadowverse.cards.Elf.Default;


import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.CollectorCurseEffect;
import rs.lazymankits.interfaces.cards.BranchableUpgradeCard;
import rs.lazymankits.interfaces.cards.UpgradeBranch;
import shadowverse.cards.Neutral.Temp.ShootOfUnkilling;
import shadowverse.characters.Elf;
import shadowverse.powers.OmenOfUnkillingPower2;

import java.util.ArrayList;
import java.util.List;


public class OmenOfUnkilling
        extends CustomCard implements BranchableUpgradeCard {
    public static final String ID = "shadowverse:OmenOfUnkilling";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:OmenOfUnkilling");
    public static CardStrings cardStrings2 = CardCrawlGame.languagePack.getCardStrings("shadowverse:OmenOfUnkilling2");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/OmenOfUnkilling.png";
    public static final String IMG_PATH2 = "img/cards/OmenOfUnkilling2.png";

    public OmenOfUnkilling() {
        super(ID, NAME, IMG_PATH, 3, DESCRIPTION, CardType.POWER, Elf.Enums.COLOR_GREEN, CardRarity.RARE, CardTarget.ALL_ENEMY);
    }


    public void upgrade() {
        ((UpgradeBranch) ((BranchableUpgradeCard) this).possibleBranches().get(chosenBranch())).upgrade();
    }

    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        boolean canUse = super.canUse(p, m);
        if (chosenBranch() == 0) {
            if (!canUse)
                return false;
            if (p.drawPile.size() != 6) {
                this.cantUseMessage = cardStrings.EXTENDED_DESCRIPTION[0];
                return false;
            }
        }
        return canUse;
    }

    public void triggerOnGlowCheck() {
        boolean glow = true;
        if (chosenBranch() == 0) {
            if (AbstractDungeon.player.drawPile.size() != 6) {
                glow = false;
            }
        }
        if (glow) {
            this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
        } else {
            this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        }
    }

    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        switch (chosenBranch()) {
            case 0:
                addToBot((AbstractGameAction) new SFXAction("OmenOfUnkilling"));
                for (AbstractMonster mo : (AbstractDungeon.getCurrRoom()).monsters.monsters) {
                    if (!mo.isDeadOrEscaped()) {
                        AbstractDungeon.actionManager.addToBottom((AbstractGameAction) new VFXAction((AbstractGameEffect) new CollectorCurseEffect(mo.hb.cX, mo.hb.cY), 2.0F));
                        mo.decreaseMaxHealth((mo.maxHealth - 6));
                    }
                }
                break;
            case 1:
                addToBot((AbstractGameAction)new SFXAction("OmenOfUnkilling2"));
                addToBot((AbstractGameAction)new ApplyPowerAction(abstractPlayer,abstractPlayer,new OmenOfUnkillingPower2(abstractPlayer)));
                addToBot((AbstractGameAction)new MakeTempCardInHandAction(new ShootOfUnkilling()));
                break;
        }
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new OmenOfUnkilling();
    }

    @Override
    public List<UpgradeBranch> possibleBranches() {
        ArrayList<UpgradeBranch> list = new ArrayList<UpgradeBranch>();
        list.add(new UpgradeBranch() {
            @Override
            public void upgrade() {
                ++OmenOfUnkilling.this.timesUpgraded;
                OmenOfUnkilling.this.upgraded = true;
                OmenOfUnkilling.this.name = NAME + "+";
                OmenOfUnkilling.this.initializeTitle();
                OmenOfUnkilling.this.selfRetain = true;
                OmenOfUnkilling.this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
                OmenOfUnkilling.this.initializeDescription();
            }
        });
        list.add(new UpgradeBranch() {
            @Override
            public void upgrade() {
                ++OmenOfUnkilling.this.timesUpgraded;
                OmenOfUnkilling.this.upgraded = true;
                OmenOfUnkilling.this.textureImg = IMG_PATH2;
                OmenOfUnkilling.this.loadCardImage(IMG_PATH2);
                OmenOfUnkilling.this.name = cardStrings2.NAME;
                OmenOfUnkilling.this.initializeTitle();
                OmenOfUnkilling.this.rawDescription = cardStrings2.DESCRIPTION;
                OmenOfUnkilling.this.initializeDescription();
                OmenOfUnkilling.this.upgradeBaseCost(1);
                OmenOfUnkilling.this.cardsToPreview = new ShootOfUnkilling();
            }
        });
        return list;
    }

}


