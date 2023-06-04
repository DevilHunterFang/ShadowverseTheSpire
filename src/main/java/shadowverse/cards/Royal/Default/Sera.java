package shadowverse.cards.Royal.Default;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import shadowverse.action.MinionSummonAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ArtifactPower;
import shadowverse.characters.Royal;
import shadowverse.orbs.ShieldGuardian;

public class Sera extends CustomCard {
    public static final String ID = "shadowverse:Sera";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Sera");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/Sera.png";
    public static final String IMG_PATH_EV = "img/cards/Sera_Ev.png";

    public Sera() {
        super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.ATTACK, Royal.Enums.COLOR_YELLOW, CardRarity.RARE, CardTarget.SELF);
        this.tags.add(CardTags.HEALING);
        this.baseBlock = 12;
        this.baseMagicNumber = this.magicNumber = 3;
        this.exhaust = true;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            this.textureImg = IMG_PATH_EV;
            this.loadCardImage(IMG_PATH_EV);
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }


    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (this.upgraded) {
            addToBot(new SFXAction(ID.replace("shadowverse:", "") + "_Ev"));
        } else {
            addToBot(new SFXAction(ID.replace("shadowverse:", "")));
        }
        AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, this.block));
        this.addToBot(new ApplyPowerAction(p, p, new ArtifactPower(p, 1), 1));
        if (this.upgraded) {
            for (int i = 0; i < 3; i++) {
                AbstractDungeon.actionManager.addToBottom(new MinionSummonAction(new ShieldGuardian()));
                addToBot(new HealAction(p, p, 1));
            }
        }
    }


    @Override
    public AbstractCard makeCopy() {
        return new Sera();
    }
}
