package shadowverse.cards.Royal.NatMech;

import basemod.abstracts.CustomCard;
import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsInHandAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.cards.Neutral.Temp.*;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Royal;

public class MistolinaBayleon extends CustomCard {
    public static final String ID = "shadowverse:MistolinaBayleon";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:MistolinaBayleon");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/MistolinaBayleon.png";
    public static final String[] TEXT = (CardCrawlGame.languagePack.getUIString("shadowverse:applyEffect")).TEXT;

    public MistolinaBayleon() {
        super(ID, NAME, IMG_PATH, 2, DESCRIPTION, CardType.ATTACK, Royal.Enums.COLOR_YELLOW, CardRarity.RARE, CardTarget.ENEMY);
        this.cardsToPreview = new NaterranGreatTree();
        this.tags.add(AbstractShadowversePlayer.Enums.NATURAL);
        this.baseDamage = 8;
        this.baseBlock = 12;
        this.baseMagicNumber = 8;
        this.magicNumber = this.baseMagicNumber;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeDamage(2);
            upgradeBlock(6);
            upgradeMagicNumber(2);
        }
    }


    @Override
    public void use(AbstractPlayer p, AbstractMonster abstractMonster) {
        addToBot(new SFXAction(ID.replace("shadowverse:", "")));
        addToBot(new SelectCardsInHandAction(1, TEXT[0], false, false, card -> card.type == CardType.POWER, abstractCards -> {
            for (AbstractCard c : abstractCards) {
                if (c instanceof NaterranGreatTree) {
                    this.addToTop(new GainEnergyAction(2));
                }
                addToBot(new ExhaustSpecificCardAction(c, p.hand));
                this.baseDamage += this.magicNumber;
                this.damage += this.magicNumber;
                this.baseBlock += this.magicNumber;
                this.block += this.magicNumber;
            }
        }));
        addToBot(new DamageAction(abstractMonster, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, this.block));
    }


    @Override
    public AbstractCard makeCopy() {
        return new MistolinaBayleon();
    }
}
