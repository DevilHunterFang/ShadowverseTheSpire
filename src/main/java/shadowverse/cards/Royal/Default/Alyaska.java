package shadowverse.cards.Royal.Default;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.cards.Neutral.Status.EvolutionPoint;
import shadowverse.cards.Neutral.Temp.ExterminusWeapon;
import shadowverse.characters.Royal;
import shadowverse.powers.DisableEffectDamagePower;
import shadowverse.relics.KagemitsuSword;

public class Alyaska extends CustomCard {
    public static final String ID = "shadowverse:Alyaska";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/Alyaska.png";
    public static final String IMG_PATH_EV = "img/cards/Alyaska_Ev.png";

    public Alyaska() {
        super(ID, NAME, IMG_PATH, 1, DESCRIPTION, AbstractCard.CardType.ATTACK, Royal.Enums.COLOR_YELLOW, AbstractCard.CardRarity.RARE, AbstractCard.CardTarget.ENEMY);
        this.baseDamage = 10;
        this.cardsToPreview = new ExterminusWeapon();
    }


    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            this.textureImg = IMG_PATH_EV;
            this.loadCardImage(IMG_PATH_EV);
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }

    public void degrade() {
        if (this.upgraded) {
            degradeName();
            this.textureImg = IMG_PATH;
            this.loadCardImage(IMG_PATH);
            this.rawDescription = cardStrings.DESCRIPTION;
            initializeDescription();
            this.superFlash();
            this.applyPowers();
        }
    }

    public void degradeName() {
        --this.timesUpgraded;
        this.upgraded = false;
        this.name = NAME;
        this.initializeTitle();
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        if (this.upgraded) {
            addToBot(new SFXAction(ID.replace("shadowverse:", "") + "_Ev"));
        } else {
            addToBot(new SFXAction(ID.replace("shadowverse:", "")));
        }
        addToBot(new ApplyPowerAction(abstractPlayer,abstractPlayer,(AbstractPower)new DisableEffectDamagePower(abstractPlayer,1),1));
        addToBot(new DamageAction(abstractMonster, new DamageInfo(abstractPlayer, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        if (this.upgraded) {
            AbstractCard c = this.cardsToPreview.makeStatEquivalentCopy();
            addToBot(new MakeTempCardInHandAction(c, 1));
            this.degrade();
            if(abstractPlayer.hasRelic(KagemitsuSword.ID)){
                this.upgrade();
            }
        }
    }

    @Override
    public void triggerWhenDrawn() {
        if (!this.upgraded) {
            addToBot(new SFXAction(ID.replace("shadowverse:", "") + "_Eff"));
            this.addToTop(new MakeTempCardInHandAction(new EvolutionPoint(), 1));
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new Alyaska();
    }
}

