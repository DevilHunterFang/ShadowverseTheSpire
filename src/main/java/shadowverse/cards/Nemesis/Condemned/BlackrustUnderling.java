package shadowverse.cards.Nemesis.Condemned;


import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.cards.AbstractEnhanceCard;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Nemesis;


public class BlackrustUnderling
        extends AbstractEnhanceCard {
    public static final String ID = "shadowverse:BlackrustUnderling";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:BlackrustUnderling");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/BlackrustUnderling.png";

    public BlackrustUnderling() {
        super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.ATTACK, Nemesis.Enums.COLOR_SKY, CardRarity.COMMON, CardTarget.ENEMY, 2,3);
        this.baseDamage = 9;
        this.tags.add(AbstractShadowversePlayer.Enums.CONDEMNED);
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeDamage(4);
        }
    }


    @Override
    public void exEnhanceUse(AbstractPlayer p, AbstractMonster m) {
        addToBot(new SFXAction("BlackrustUnderling_EXEH"));
        for (int i = 0; i < 3; i++){
            addToBot(new DamageAction(m,new DamageInfo(p,this.damage,this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_VERTICAL));
        }
    }

    @Override
    public void enhanceUse(AbstractPlayer p, AbstractMonster m) {
        addToBot(new SFXAction("BlackrustUnderling_EH"));
        for (int i = 0; i < 2; i++){
            addToBot(new DamageAction(m,new DamageInfo(p,this.damage,this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_VERTICAL));
        }
    }

    @Override
    public void baseUse(AbstractPlayer p, AbstractMonster m) {
        addToBot(new SFXAction("BlackrustUnderling"));
        addToBot(new DamageAction(m,new DamageInfo(p,this.damage,this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_VERTICAL));
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new BlackrustUnderling();
    }
}

