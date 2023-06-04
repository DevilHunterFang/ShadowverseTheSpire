package shadowverse.cards.Elf.Return;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DrawCardNextTurnPower;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import shadowverse.Shadowverse;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Elf;

public class WardenOfBalms extends CustomCard {
    public static final String ID = "shadowverse:WardenOfBalms";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:WardenOfBalms");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/WardenOfBalms.png";

    public WardenOfBalms() {
        super(ID, NAME, IMG_PATH, 3, DESCRIPTION, CardType.ATTACK, Elf.Enums.COLOR_GREEN, CardRarity.UNCOMMON, CardTarget.SELF);
        this.baseBlock = 12;
        this.baseMagicNumber = 6;
        this.magicNumber = this.baseMagicNumber;
        this.tags.add(AbstractShadowversePlayer.Enums.CONDEMNED);
        this.tags.add(AbstractShadowversePlayer.Enums.CRYSTALLIZE);
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBlock(4);
        }
    }

    public void update() {
        if (AbstractDungeon.currMapNode != null && (AbstractDungeon.getCurrRoom()).phase == AbstractRoom.RoomPhase.COMBAT &&
                Shadowverse.Accelerate(this)) {
            setCostForTurn(0);
            this.type = CardType.POWER;
        } else {
            if (this.type == CardType.POWER) {
                setCostForTurn(3);
                this.type = CardType.ATTACK;
            }
        }
        super.update();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (Shadowverse.Accelerate(this) && this.type == CardType.POWER) {
            addToBot(new ApplyPowerAction(p,p,new DrawCardNextTurnPower(p,1),1));
        } else {
            addToBot(new GainBlockAction(p, this.block));
            addToBot(new GainBlockAction(p, this.block));
            addToBot(new DamageAllEnemiesAction(p, DamageInfo.createDamageMatrix(this.magicNumber, true), DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.FIRE, true));
            addToBot(new DamageAllEnemiesAction(p, DamageInfo.createDamageMatrix(this.magicNumber, true), DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.FIRE, true));
            if (EnergyPanel.getCurrentEnergy() < 6){
                addToBot(new MakeTempCardInDiscardAction(this.makeStatEquivalentCopy(),1));
            }
        }
    }

    public AbstractCard makeCopy() {
        return new WardenOfBalms();
    }
}
