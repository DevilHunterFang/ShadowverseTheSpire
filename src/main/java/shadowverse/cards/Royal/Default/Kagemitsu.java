package shadowverse.cards.Royal.Default;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.action.KagemitsuAction;
import shadowverse.cards.Neutral.Temp.LostSamurai;
import shadowverse.characters.Royal;
import shadowverse.powers.NextKagemitsu;

public class Kagemitsu extends CustomCard {
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Kagemitsu");
    public static final String ID = "shadowverse:Kagemitsu";
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/Kagemitsu.png";


    public Kagemitsu(int upgrades) {
        this();
        for (int i = 0; i < upgrades; i++) {
            this.upgrade();
        }
    }

    public Kagemitsu() {
        super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.ATTACK, Royal.Enums.COLOR_YELLOW, CardRarity.RARE, CardTarget.ENEMY);
        this.baseDamage = 7;
        this.exhaust = true;
        this.cardsToPreview = new LostSamurai();
    }

    @Override
    public void upgrade() {
        this.timesUpgraded++;
        this.upgradeDamage(7);
        this.upgraded = true;
        this.name = cardStrings.NAME + "+" + this.timesUpgraded;
        initializeTitle();
    }

    @Override
    public boolean canUpgrade() {
        return true;
    }

    @Override
    public void triggerOnExhaust() {
        addToBot(new ApplyPowerAction(AbstractDungeon.player,AbstractDungeon.player,new NextKagemitsu(AbstractDungeon.player,1,new LostSamurai(this.timesUpgraded))));
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new SFXAction(ID.replace("shadowverse:", "")));
        addToBot(new KagemitsuAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), this));
    }

    @Override
    public AbstractCard makeCopy() {
        return new Kagemitsu(this.timesUpgraded);
    }
}
