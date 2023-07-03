package shadowverse.cards.Dragon.Buff;


import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import shadowverse.cards.Neutral.Temp.HellFlameDragon;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Dragon;


public class GrandSlamTamer
        extends CustomCard {
    public static final String ID = "shadowverse:GrandSlamTamer";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:GrandSlamTamer");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/GrandSlamTamer.png";
    private boolean trigger;

    public GrandSlamTamer() {
        super(ID, NAME, IMG_PATH, 2, DESCRIPTION, CardType.ATTACK, Dragon.Enums.COLOR_BROWN, CardRarity.UNCOMMON, CardTarget.SELF);
        this.baseBlock = 12;
        this.cardsToPreview = new HellFlameDragon();
        this.tags.add(AbstractShadowversePlayer.Enums.ACADEMIC);
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBlock(3);
        }
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new SFXAction("GrandSlamTamer"));
        addToBot(new GainBlockAction(abstractPlayer, this.block));
        int amt = 1;
        AbstractCard h = null;
        if (abstractPlayer.hasPower(DexterityPower.POWER_ID) && abstractPlayer.getPower(DexterityPower.POWER_ID).amount >= 2) {
            h = new HellFlameDragon();
        }
        if (abstractPlayer.hasPower(DexterityPower.POWER_ID) && abstractPlayer.getPower(DexterityPower.POWER_ID).amount >= 4) {
            h.baseDamage += 12;
            h.applyPowers();
        }
        if (h != null) {
            addToBot(new MakeTempCardInHandAction(h));
        }
        if (!trigger) {
            trigger = true;
            addToBot(new ApplyPowerAction(abstractPlayer, abstractPlayer, new DexterityPower(abstractPlayer, 1), 1));
        }
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new GrandSlamTamer();
    }
}

