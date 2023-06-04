package shadowverse.cards.Royal.Rally;

import basemod.abstracts.CustomCard;
import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.action.MinionSummonAction;
import shadowverse.characters.Royal;
import shadowverse.orbs.HeavyKnight;
import shadowverse.orbs.Knight;
import shadowverse.orbs.ShieldGuardian;

public class GeneralMaximus extends CustomCard {
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:GeneralMaximus");
    public static final String ID = "shadowverse:GeneralMaximus";
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/GeneralMaximus.png";


    public GeneralMaximus() {
        super(ID, NAME, IMG_PATH, 2, DESCRIPTION, CardType.ATTACK, Royal.Enums.COLOR_YELLOW, CardRarity.RARE, CardTarget.ENEMY);
        this.baseDamage = 12;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            this.upgradeDamage(6);
        }
    }


    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new SFXAction(ID.replace("shadowverse:", "")));
        addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        addToBot(new SelectCardsAction(p.drawPile.group,3,TEXT[0],true, card -> type != CardType.SKILL, abstractCards ->
        {
            for (AbstractCard c : abstractCards) {
                addToBot(new ExhaustSpecificCardAction(c,p.drawPile));
            }
            if (abstractCards.size()>0){
                addToBot(new MinionSummonAction(new Knight()));
            }
            if (abstractCards.size()>1){
                addToBot(new MinionSummonAction(new ShieldGuardian()));
            }
            if (abstractCards.size()>2){
                addToBot(new MinionSummonAction(new HeavyKnight()));
            }
        }));
    }

    @Override
    public AbstractCard makeCopy() {
        return new GeneralMaximus();
    }
}
