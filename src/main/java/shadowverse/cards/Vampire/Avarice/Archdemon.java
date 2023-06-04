package shadowverse.cards.Vampire.Avarice;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
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
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.ClashEffect;
import shadowverse.Shadowverse;
import shadowverse.action.ArchdemonAction;
import shadowverse.cards.Necromancer.Burial.DemonicProcession;
import shadowverse.cards.Necromancer.Mech.HungrySlash;
import shadowverse.cards.Necromancer.Burial.SpiritCurator;
import shadowverse.cards.Necromancer.Ghosts.Ferry;
import shadowverse.cards.Necromancer.Burial.TheLovers;
import shadowverse.cards.Neutral.Temp.Archdemon_Acc;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Vampire;
import shadowverse.powers.ArchdemonPower;


public class Archdemon
        extends CustomCard {
    public static final String ID = "shadowverse:Archdemon";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Archdemon");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/Archdemon.png";
    public boolean doubleCheck = false;

    public Archdemon() {
        super(ID, NAME, IMG_PATH, 5, DESCRIPTION, CardType.ATTACK, Vampire.Enums.COLOR_SCARLET, CardRarity.RARE, CardTarget.ENEMY);
        this.baseDamage = 44;
        this.baseMagicNumber = 5;
        this.magicNumber = this.baseMagicNumber;
        this.tags.add(AbstractShadowversePlayer.Enums.ACCELERATE);
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeDamage(11);
        }
    }

    public void triggerOnOtherCardPlayed(AbstractCard c) {
        if (c instanceof DemonicProcession ||c instanceof TheLovers ||c instanceof HungrySlash ||c instanceof SpiritCurator ||c instanceof Ferry){
            this.type = CardType.ATTACK;
            this.resetAttributes();
            return;
        }
        if (AbstractDungeon.player.hasPower("Burst")||AbstractDungeon.player.hasPower("Double Tap")||AbstractDungeon.player.hasPower("Amplified")) {
            doubleCheck = true;
            if (EnergyPanel.getCurrentEnergy() - c.costForTurn < this.cost) {
                setCostForTurn(0);
                this.type = CardType.SKILL;
                applyPowers();
            }
        }else {
            if (doubleCheck) {
                doubleCheck = false;
            }else {
                if (EnergyPanel.getCurrentEnergy() - c.costForTurn < this.cost) {
                    setCostForTurn(0);
                    this.type = CardType.SKILL;
                    applyPowers();
                }
            }
        }
    }


    public void triggerOnGainEnergy(int e, boolean dueToCard) {
        if (EnergyPanel.getCurrentEnergy() >= this.cost && this.type != CardType.ATTACK) {
            resetAttributes();
            this.type = CardType.ATTACK;
            applyPowers();
        }
    }

    public void triggerWhenDrawn() {
        if (Shadowverse.Accelerate((AbstractCard)this)) {
            super.triggerWhenDrawn();
            setCostForTurn(0);
            this.type = CardType.SKILL;
        } else {
            this.type = CardType.ATTACK;
        }
        applyPowers();
    }

    @Override
    public void atTurnStart() {
        if (AbstractDungeon.player.hand.group.contains(this)){
            resetAttributes();
            this.type = CardType.ATTACK;
            applyPowers();
        }
    }

    public void onMoveToDiscard() {
        resetAttributes();
        this.type = CardType.ATTACK;
        applyPowers();
    }

    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        if (this.type == CardType.SKILL&& Shadowverse.Accelerate(this)){
            addToBot((AbstractGameAction)new SFXAction("Archdemon_Acc"));
            addToBot((AbstractGameAction)new DrawCardAction(1));
            addToBot((AbstractGameAction)new ArchdemonAction(this));
        }else {
            addToBot((AbstractGameAction)new SFXAction("Archdemon"));
            addToBot((AbstractGameAction)new ApplyPowerAction(abstractPlayer,abstractPlayer,(AbstractPower)new ArchdemonPower(abstractPlayer,this.magicNumber),this.magicNumber));
            addToBot((AbstractGameAction)new VFXAction((AbstractGameEffect)new ClashEffect(abstractMonster.hb.cX, abstractMonster.hb.cY)));
            addToBot((AbstractGameAction)new DamageAction((AbstractCreature)abstractMonster, new DamageInfo((AbstractCreature)abstractPlayer, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.NONE));
        }
    }

    public AbstractCard makeSameInstanceOf() {
        AbstractCard card = null;
        if (Shadowverse.Accelerate((AbstractCard)this) && this.type == CardType.SKILL) {
            card = (new Archdemon_Acc()).makeStatEquivalentCopy();
            card.uuid = (new Archdemon_Acc()).uuid;
        } else {
            card = makeStatEquivalentCopy();
            card.uuid = this.uuid;
        }
        return card;
    }

    public AbstractCard makeCopy() {
        return (AbstractCard) new Archdemon();
    }
}


