package shadowverse.cards.Neutral.Temp;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageRandomEnemyAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.cards.Neutral.Status.EvolutionPoint;
import shadowverse.characters.Royal;
import shadowverse.powers.NextTurnV;

public class DesperadosShot extends CustomCard {
    public static final String ID = "shadowverse:DesperadosShot";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:DesperadosShot");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/DesperadosShot.png";

    public DesperadosShot() {
        super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.SKILL, Royal.Enums.COLOR_YELLOW, CardRarity.SPECIAL, CardTarget.ENEMY);
        this.baseDamage = 9;
        this.exhaust = true;
        this.cardsToPreview = new V();
    }


    public void upgrade() {
        if (!this.upgraded){
            upgradeName();
            upgradeDamage(3);
        }
    }



    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot((AbstractGameAction)new SFXAction("DesperadosShot"));
        addToBot((AbstractGameAction) new DamageAction((AbstractCreature) m, new DamageInfo((AbstractCreature) p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
        for (AbstractCard c:p.hand.group){
            if (c instanceof EvolutionPoint){
                addToBot((AbstractGameAction) new ExhaustSpecificCardAction(c,p.hand));
                addToBot((AbstractGameAction) new DamageRandomEnemyAction(new DamageInfo(AbstractDungeon.player, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
                addToBot((AbstractGameAction) new ApplyPowerAction(p,p,new NextTurnV(p,1)));
                break;
            }
        }
    }

    public AbstractCard makeCopy() {
        return new DesperadosShot();
    }
}
