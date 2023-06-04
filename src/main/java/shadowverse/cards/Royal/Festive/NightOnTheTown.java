package shadowverse.cards.Royal.Festive;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.cards.Neutral.Temp.GlitteringGold;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Royal;

public class NightOnTheTown extends CustomCard {
    public static final String ID = "shadowverse:NightOnTheTown";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/NightOnTheTown.png";

    public NightOnTheTown() {
        super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.SKILL, Royal.Enums.COLOR_YELLOW, CardRarity.UNCOMMON, CardTarget.ENEMY);
        this.baseDamage = 6;
        this.cardsToPreview = new GlitteringGold();
        this.tags.add(AbstractShadowversePlayer.Enums.FES);
    }


    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeDamage(3);
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }


    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new SFXAction(ID.replace("shadowverse:", "")));
        addToBot(new DamageAction(abstractMonster, new DamageInfo(abstractPlayer, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        if(this.upgraded){
            for(AbstractCard card: AbstractDungeon.player.hand.group){
                if(card.cardID.equals(this.cardsToPreview.cardID)){
                    card.cost = 0;
                    card.costForTurn = 0;
                    card.isCostModified = true;
                }
            }
        }
        AbstractCard c = this.cardsToPreview.makeStatEquivalentCopy();
        c.cost = 0;
        c.costForTurn = 0;
        c.isCostModified = true;
        addToBot(new MakeTempCardInHandAction(c, 1));

    }


    @Override
    public AbstractCard makeCopy() {
        return new NightOnTheTown();
    }
}

