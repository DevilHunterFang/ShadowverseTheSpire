package shadowverse.cards.Vampire.Wrath;


import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.action.DrawPileToHandAction_Tag;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Vampire;
import shadowverse.powers.RagingCommanderPower;


public class RagingCommander
        extends CustomCard {
    public static final String ID = "shadowverse:RagingCommander";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:RagingCommander");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/RagingCommander.png";

    public RagingCommander() {
        super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.ATTACK, Vampire.Enums.COLOR_SCARLET, CardRarity.UNCOMMON, CardTarget.ENEMY);
        this.baseDamage = 9;
        this.baseMagicNumber = 3;
        this.magicNumber = this.baseMagicNumber;
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
        addToBot(new SFXAction("RagingCommander"));
        if (p.hasPower(RagingCommanderPower.POWER_ID)){
            addToBot(new ApplyPowerAction(p,p,new RagingCommanderPower(p,this.magicNumber,0),this.magicNumber));
        }else {
            addToBot(new ApplyPowerAction(p,p,new RagingCommanderPower(p,this.magicNumber,8),this.magicNumber));
        }
        addToBot(new LoseHPAction(p, p, 1));
        addToBot(new DamageAction(m,new DamageInfo(p,this.damage,this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        addToBot(new DrawPileToHandAction_Tag(1,AbstractShadowversePlayer.Enums.CONDEMNED,null));
        if (this.upgraded){
            addToBot(new LoseHPAction(p, p, 1));
            addToBot(new DrawPileToHandAction_Tag(1,AbstractShadowversePlayer.Enums.CONDEMNED,null));
        }
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new RagingCommander();
    }
}

