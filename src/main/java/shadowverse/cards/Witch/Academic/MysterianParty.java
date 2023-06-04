package shadowverse.cards.Witch.Academic;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import shadowverse.action.DrawPileToHandAction_Tag;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Witchcraft;


public class MysterianParty
        extends CustomCard {
    public static final String ID = "shadowverse:MysterianParty";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:MysterianParty");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/MysterianParty.png";

    public MysterianParty() {
        super(ID, NAME, IMG_PATH, 0, DESCRIPTION, CardType.SKILL, Witchcraft.Enums.COLOR_BLUE, CardRarity.UNCOMMON, CardTarget.NONE);
        this.tags.add(AbstractShadowversePlayer.Enums.MYSTERIA);
        this.tags.add(AbstractShadowversePlayer.Enums.ACADEMIC);
        this.exhaust = true;
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
            this.exhaust = false;
        }
    }


    public void applyPowers() {
        super.applyPowers();
        if (AbstractDungeon.player instanceof AbstractShadowversePlayer) {
            AbstractShadowversePlayer w = (AbstractShadowversePlayer) AbstractDungeon.player;
            int count = w.mysteriaCount;
            if (this.upgraded) {
                this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            } else {
                this.rawDescription = cardStrings.DESCRIPTION;
            }
            this.rawDescription += cardStrings.EXTENDED_DESCRIPTION[0] + count;
            this.rawDescription += cardStrings.EXTENDED_DESCRIPTION[1];
            initializeDescription();
        }
    }
    

    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new SFXAction("MysterianParty"));
        if (AbstractDungeon.player instanceof AbstractShadowversePlayer) {
            ((AbstractShadowversePlayer) AbstractDungeon.player).mysteriaCount++;
        }
        addToBot(new DrawPileToHandAction_Tag(1,AbstractShadowversePlayer.Enums.MYSTERIA,null));
        if (AbstractDungeon.player instanceof AbstractShadowversePlayer) {
            AbstractShadowversePlayer w = (AbstractShadowversePlayer) AbstractDungeon.player;
            int count = w.mysteriaCount;
            if (count > 10){
                addToBot(new ApplyPowerAction(abstractPlayer,abstractPlayer,new StrengthPower(abstractPlayer,1),1));
                addToBot(new ApplyPowerAction(abstractPlayer,abstractPlayer,new DexterityPower(abstractPlayer,1),1));
            }
        }
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new MysterianParty();
    }
}


