package shadowverse.cards.Nemesis.Artifact;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.action.DestroyAction;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Nemesis;

public class Robopup
        extends CustomCard {
    public static final String ID = "shadowverse:Robopup";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Robopup");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/Robopup.png";

    public Robopup() {
        super(ID, NAME, IMG_PATH, 0, DESCRIPTION, CardType.ATTACK, Nemesis.Enums.COLOR_SKY, CardRarity.COMMON, CardTarget.ENEMY);
        this.baseDamage = 3;
        this.tags.add(AbstractShadowversePlayer.Enums.ARTIFACT);
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeDamage(3);
        }
    }


    public void use(AbstractPlayer p, AbstractMonster abstractMonster) {
        addToBot(new DestroyAction(1, new DrawCardAction(1)));
        addToBot((AbstractGameAction)new DamageAction(abstractMonster,new DamageInfo(p,this.damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_VERTICAL));
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new Robopup();
    }
}


