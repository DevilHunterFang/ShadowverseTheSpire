package shadowverse.cards.Common;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
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
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import shadowverse.Shadowverse;
import shadowverse.action.BounceAction;
import shadowverse.cards.Temp.Whisp;
import shadowverse.cards.Temp.WindFairy_Accelerate;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Elf;


public class WindFairy
        extends CustomCard {
    public static final String ID = "shadowverse:WindFairy";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:WindFairy");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/WindFairy.png";

    public WindFairy() {
        super(ID, NAME, IMG_PATH, 3, DESCRIPTION, CardType.ATTACK, Elf.Enums.COLOR_GREEN, CardRarity.COMMON, CardTarget.ENEMY);
        this.baseDamage = 12;
        this.baseMagicNumber = 1;
        this.magicNumber = this.baseMagicNumber;
        this.cardsToPreview = (AbstractCard) new Whisp();
        this.tags.add(AbstractShadowversePlayer.Enums.ACCELERATE);
        this.exhaust = true;
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeDamage(4);
        }
    }

    @Override
    public void update() {
        if (AbstractDungeon.currMapNode != null && (AbstractDungeon.getCurrRoom()).phase == AbstractRoom.RoomPhase.COMBAT &&
                Shadowverse.Accelerate(this)) {
            setCostForTurn(0);
            this.type = CardType.SKILL;
        } else {
            if (this.type == CardType.SKILL) {
                setCostForTurn(3);
                this.type = CardType.ATTACK;
            }
        }
        super.update();
    }

    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        boolean canUse = super.canUse(p, m);
        if (!canUse)
            return false;
        boolean hasAttack = false;
        if (Shadowverse.Accelerate((AbstractCard) this) && this.type == CardType.SKILL) {
            for (AbstractCard c : p.discardPile.group) {
                if (c.type == AbstractCard.CardType.ATTACK || c.type == CardType.POWER)
                    hasAttack = true;
            }
            if (!hasAttack) {
                this.cantUseMessage = cardStrings.EXTENDED_DESCRIPTION[0];
                canUse = false;
            }
        }
        return canUse;
    }

    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        AbstractCard c = this.cardsToPreview.makeStatEquivalentCopy();
        if (Shadowverse.Accelerate((AbstractCard) this) && this.type == CardType.SKILL) {
            addToBot((AbstractGameAction) new BounceAction(1));
            addToBot((AbstractGameAction) new SFXAction("WindFairy_Accelerate"));
            addToBot((AbstractGameAction) new MakeTempCardInHandAction(c, 1));
        } else {
            addToBot((AbstractGameAction) new SFXAction("WindFairy"));
            addToBot((AbstractGameAction) new DamageAction((AbstractCreature) abstractMonster, new DamageInfo((AbstractCreature) abstractPlayer, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
            addToBot((AbstractGameAction) new DamageAction((AbstractCreature) abstractMonster, new DamageInfo((AbstractCreature) abstractPlayer, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        }
    }


    public AbstractCard makeSameInstanceOf() {
        AbstractCard card = null;
        if (Shadowverse.Accelerate((AbstractCard) this) && this.type == CardType.SKILL) {
            card = (new WindFairy_Accelerate()).makeStatEquivalentCopy();
            card.uuid = (new WindFairy_Accelerate()).uuid;
        } else {
            card = makeStatEquivalentCopy();
            card.uuid = this.uuid;
        }
        return card;
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new WindFairy();
    }
}

