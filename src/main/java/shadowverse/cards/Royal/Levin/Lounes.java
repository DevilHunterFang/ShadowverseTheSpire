package shadowverse.cards.Royal.Levin;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.Shadowverse;
import shadowverse.cards.AbstractEnhanceCard;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Royal;

public class Lounes extends AbstractEnhanceCard {
    public static final String ID = "shadowverse:Lounes";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/Lounes.png";

    public Lounes() {
        super(ID, NAME, IMG_PATH, 0, DESCRIPTION, CardType.ATTACK, Royal.Enums.COLOR_YELLOW, CardRarity.COMMON, CardTarget.ENEMY, 1);
        this.baseDamage = 3;
        this.baseBlock = 2;
        this.tags.add(AbstractShadowversePlayer.Enums.LEVIN);
    }


    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBlock(1);
            upgradeDamage(2);
        }
    }

    public int levins() {
        AbstractPlayer p = AbstractDungeon.player;
        int l = 0;
        for (AbstractCard c : p.hand.group) {
            if (c.hasTag(AbstractShadowversePlayer.Enums.LEVIN)) {
                l++;
            }
        }
        return l;
    }

    @Override
    public void applyPowers() {
        if (Shadowverse.Enhance(1)) {
            setCostForTurn(1);
        } else {
            resetAttributes();
        }
        super.applyPowers();
        this.rawDescription = cardStrings.DESCRIPTION;
        this.rawDescription += cardStrings.EXTENDED_DESCRIPTION[0] + levins() + cardStrings.EXTENDED_DESCRIPTION[1];
        this.initializeDescription();
    }

    @Override
    public void onMoveToDiscard() {
        this.rawDescription = cardStrings.DESCRIPTION;
        this.initializeDescription();
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        super.calculateCardDamage(mo);
        this.rawDescription = cardStrings.DESCRIPTION;
        this.rawDescription += cardStrings.EXTENDED_DESCRIPTION[0] + levins() + cardStrings.EXTENDED_DESCRIPTION[1];
        this.initializeDescription();
    }

    @Override
    public void exEnhanceUse(AbstractPlayer p, AbstractMonster m) {

    }

    @Override
    public void enhanceUse(AbstractPlayer p, AbstractMonster m) {
        if (p.hand.group.stream().anyMatch(card -> card instanceof Albert)) {
            addToBot(new SFXAction(ID.replace("shadowverse:", "") + "_Co"));
        } else {
            addToBot(new SFXAction(ID.replace("shadowverse:", "") + "_Eh"));
        }
        addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        for (int i = 0; i < levins(); i++) {
            addToBot(new GainBlockAction(p, p, this.block));
        }
    }

    @Override
    public void baseUse(AbstractPlayer p, AbstractMonster m) {
        if (p.hand.group.stream().anyMatch(card -> card instanceof Albert)) {
            addToBot(new SFXAction(ID.replace("shadowverse:", "") + "_Co"));
        } else {
            addToBot(new SFXAction(ID.replace("shadowverse:", "")));
        }
        addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        for (int i = 0; i < levins(); i++) {
            addToBot(new GainBlockAction(p, p, this.block));
        }
    }


    @Override
    public AbstractCard makeCopy() {
        return new Lounes();
    }
}




