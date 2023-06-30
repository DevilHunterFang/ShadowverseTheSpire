package shadowverse.cards.Royal.NatMech;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Royal;

public class Garven extends CustomCard {
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Garven");
    public static final String ID = "shadowverse:Garven";
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/Garven.png";


    public Garven() {
        super(ID, NAME, IMG_PATH, 3, DESCRIPTION, CardType.ATTACK, Royal.Enums.COLOR_YELLOW, CardRarity.RARE, CardTarget.ENEMY);
        this.baseDamage = 14;
        this.baseMagicNumber = this.magicNumber = 2;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeMagicNumber(1);
        }
    }

    public int types() {
        int[] t = {0, 0, 0, 0, 0, 0, 0};
        AbstractCard.CardTags[] type = {
                AbstractShadowversePlayer.Enums.MACHINE,
                AbstractShadowversePlayer.Enums.NATURAL,
                AbstractShadowversePlayer.Enums.LEVIN,
                AbstractShadowversePlayer.Enums.MYSTERIA,
                AbstractShadowversePlayer.Enums.ARTIFACT,
                AbstractShadowversePlayer.Enums.HERO,
                AbstractShadowversePlayer.Enums.LUMINOUS,
                AbstractShadowversePlayer.Enums.CONDEMNED,
                AbstractShadowversePlayer.Enums.CHESS,
                AbstractShadowversePlayer.Enums.ACADEMIC,
                AbstractShadowversePlayer.Enums.ARMED
        };
        for (int i = 0; i < t.length; i++) {
            for (AbstractCard c : AbstractDungeon.actionManager.cardsPlayedThisCombat) {
                if (c.hasTag(type[i])) {
                    t[i] = 1;
                    break;
                }
            }
        }
        int s = 0;
        for (int j : t) {
            if (j > 0) {
                s++;
            }
        }
        return s;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new SFXAction(ID.replace("shadowverse:", "")));
        for (int i = 0; i < this.magicNumber + types(); i++) {
            addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HEAVY));
        }
    }

    @Override
    public void applyPowers() {
        super.applyPowers();
        this.rawDescription = cardStrings.DESCRIPTION;
        this.rawDescription += cardStrings.EXTENDED_DESCRIPTION[0] + (types() + this.magicNumber) + cardStrings.EXTENDED_DESCRIPTION[1];
        this.initializeDescription();
    }

    @Override
    public void onMoveToDiscard() {
        applyPowers();
        this.rawDescription = cardStrings.DESCRIPTION;
        this.initializeDescription();
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        super.calculateCardDamage(mo);
        this.rawDescription = cardStrings.DESCRIPTION;
        this.rawDescription += cardStrings.EXTENDED_DESCRIPTION[0] + (types() + this.magicNumber) + cardStrings.EXTENDED_DESCRIPTION[1];
        this.initializeDescription();
    }

    @Override
    public AbstractCard makeCopy() {
        return new Garven();
    }
}
