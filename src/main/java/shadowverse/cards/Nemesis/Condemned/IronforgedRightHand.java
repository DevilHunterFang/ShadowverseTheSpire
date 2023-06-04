package shadowverse.cards.Nemesis.Condemned;


import com.evacipated.cardcrawl.mod.stslib.actions.common.FetchAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.tempCards.Miracle;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.cards.AbstractEnhanceCard;
import shadowverse.cards.Neutral.Status.EvolutionPoint;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Nemesis;

import java.util.ArrayList;


public class IronforgedRightHand
        extends AbstractEnhanceCard {
    public static final String ID = "shadowverse:IronforgedRightHand";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:IronforgedRightHand");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/IronforgedRightHand.png";

    public IronforgedRightHand() {
        super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.ATTACK, Nemesis.Enums.COLOR_SKY, CardRarity.UNCOMMON, CardTarget.ENEMY, 2, 3);
        this.baseDamage = 10;
        this.baseMagicNumber = 8;
        this.magicNumber = this.baseMagicNumber;
        this.tags.add(AbstractShadowversePlayer.Enums.CONDEMNED);
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeDamage(3);
            upgradeMagicNumber(2);
        }
    }


    @Override
    public void exEnhanceUse(AbstractPlayer p, AbstractMonster m) {
        addToBot(new SFXAction("IronforgedRightHand_EXEH"));
        boolean deckCheck = true;
        ArrayList<String> tmp = new ArrayList<>();
        for (AbstractCard c : p.drawPile.group) {
            if (tmp.contains(c.cardID)) {
                deckCheck = false;
                break;
            }
            if (!c.hasTag(AbstractShadowversePlayer.Enums.CONDEMNED))
                tmp.add(c.cardID);
        }
        if (deckCheck)
            addToBot(new FetchAction(p.drawPile,card -> card.cost == 0,1));
        addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        addToBot(new DamageAllEnemiesAction(p, DamageInfo.createDamageMatrix(this.magicNumber, true), DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.SLASH_DIAGONAL, true));
        addToBot(new MakeTempCardInHandAction(new EvolutionPoint()));
        addToBot(new MakeTempCardInHandAction(new Miracle()));
    }

    @Override
    public void enhanceUse(AbstractPlayer p, AbstractMonster m) {
        addToBot(new SFXAction("IronforgedRightHand_EH"));
        boolean deckCheck = true;
        ArrayList<String> tmp = new ArrayList<>();
        for (AbstractCard c : p.drawPile.group) {
            if (tmp.contains(c.cardID)) {
                deckCheck = false;
                break;
            }
            if (!c.hasTag(AbstractShadowversePlayer.Enums.CONDEMNED))
                tmp.add(c.cardID);
        }
        if (deckCheck)
            addToBot(new FetchAction(p.drawPile,card -> card.cost == 0,1));
        addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        addToBot(new DamageAllEnemiesAction(p, DamageInfo.createDamageMatrix(this.magicNumber, true), DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.SLASH_DIAGONAL, true));
    }

    @Override
    public void baseUse(AbstractPlayer p, AbstractMonster m) {
        addToBot(new SFXAction("IronforgedRightHand"));
        boolean deckCheck = true;
        ArrayList<String> tmp = new ArrayList<>();
        for (AbstractCard c : p.drawPile.group) {
            if (tmp.contains(c.cardID)) {
                deckCheck = false;
                break;
            }
            if (!c.hasTag(AbstractShadowversePlayer.Enums.CONDEMNED))
                tmp.add(c.cardID);
        }
        if (deckCheck)
            addToBot(new FetchAction(p.drawPile,card -> card.cost == 0,1));
        addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new IronforgedRightHand();
    }
}

