package shadowverse.cards.Temp;



import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Vampire;


public class WolflingStruggle extends CustomCard {
    public static final String ID = "shadowverse:WolflingStruggle";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/WolflingStruggle.png";

    public WolflingStruggle() {
        super(ID, NAME, IMG_PATH, 0, DESCRIPTION, CardType.SKILL, Vampire.Enums.COLOR_SCARLET, CardRarity.SPECIAL, CardTarget.ENEMY);
        this.baseDamage = 2;
        this.exhaust = true;
        this.tags.add(AbstractShadowversePlayer.Enums.FES);
    }


    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeDamage(2);
        }
    }


    public void applyPowers() {
        super.applyPowers();
        int count = 0;
        if (AbstractDungeon.player instanceof AbstractShadowversePlayer){
            count = ((AbstractShadowversePlayer) AbstractDungeon.player).upgradedThisCombat;
        }
        this.rawDescription = cardStrings.DESCRIPTION;
        this.rawDescription += cardStrings.EXTENDED_DESCRIPTION[0] + count;
        this.rawDescription += cardStrings.EXTENDED_DESCRIPTION[1];
        initializeDescription();
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new SFXAction("WolflingStruggle"));
        addToBot(new DrawCardAction(1));
        int dmg = this.damage;
        if (abstractPlayer instanceof AbstractShadowversePlayer){
            if (((AbstractShadowversePlayer) abstractPlayer).upgradedThisCombat >= 5){
                dmg *= 4;
            }
        }
        addToBot(new DamageAction(abstractMonster, new DamageInfo(abstractPlayer, dmg, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
    }


    @Override
    public AbstractCard makeCopy() {
        return new WolflingStruggle();
    }
}

