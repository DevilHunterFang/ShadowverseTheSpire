package shadowverse.cards.Bishop.Recover2;


import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.action.DrawPileToHandAction_Tag;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Bishop;
import shadowverse.powers.AgentOfTheCommandmentsPower;
import shadowverse.powers.RagingCommanderPower;


public class AgentOfTheCommandments
        extends CustomCard {
    public static final String ID = "shadowverse:AgentOfTheCommandments";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:AgentOfTheCommandments");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/AgentOfTheCommandments.png";

    public AgentOfTheCommandments() {
        super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.ATTACK, Bishop.Enums.COLOR_WHITE, CardRarity.COMMON, CardTarget.ENEMY);
        this.baseDamage = 8;
        this.tags.add(AbstractShadowversePlayer.Enums.CONDEMNED);
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeDamage(3);
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }


    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new SFXAction("AgentOfTheCommandments"));
        if (p.hasPower(RagingCommanderPower.POWER_ID)){
            addToBot(new ApplyPowerAction(p,p,new AgentOfTheCommandmentsPower(p,1,0)));
        }else {
            addToBot(new ApplyPowerAction(p,p,new AgentOfTheCommandmentsPower(p,1,2)));
        }
        addToBot(new DamageAction(m,new DamageInfo(p,this.damage,this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        if (this.upgraded){
            addToBot(new DrawPileToHandAction_Tag(1,AbstractShadowversePlayer.Enums.CONDEMNED,null));
        }
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new AgentOfTheCommandments();
    }
}

