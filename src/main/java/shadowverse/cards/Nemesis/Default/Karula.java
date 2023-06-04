package shadowverse.cards.Nemesis.Default;


import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.tempCards.Miracle;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.BorderFlashEffect;
import com.megacrit.cardcrawl.vfx.combat.MiracleEffect;
import shadowverse.cards.AbstractEnhanceCard;
import shadowverse.cards.Neutral.Status.EvolutionPoint;
import shadowverse.characters.Nemesis;
import shadowverse.powers.KarulaPower;


public class Karula extends AbstractEnhanceCard {
    public static final String ID = "shadowverse:Karula";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Karula");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/Karula.png";

    public Karula() {
        super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.ATTACK, Nemesis.Enums.COLOR_SKY, CardRarity.RARE, CardTarget.SELF, 2);
        this.baseBlock = 9;
        this.baseMagicNumber = 1;
        this.magicNumber = this.baseMagicNumber;
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBlock(3);
            upgradeMagicNumber(1);
        }
    }


    @Override
    public void exEnhanceUse(AbstractPlayer p, AbstractMonster m) {

    }

    @Override
    public void enhanceUse(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p, this.block));
        addToBot(new SFXAction("Karula_EH"));
        addToBot(new VFXAction(new BorderFlashEffect(Color.ROYAL, true)));
        addToBot(new VFXAction(new MiracleEffect(Color.LIGHT_GRAY.cpy(), Color.SKY.cpy(), "HEAL_3")));
        addToBot(new ApplyPowerAction(p, p, new KarulaPower(p, this.magicNumber), this.magicNumber));
        addToBot(new MakeTempCardInHandAction(new EvolutionPoint()));
        addToBot(new MakeTempCardInHandAction(new Miracle()));
    }

    @Override
    public void baseUse(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p, this.block));
        addToBot(new SFXAction("Karula"));
        addToBot(new VFXAction(new BorderFlashEffect(Color.ROYAL, true)));
        addToBot(new VFXAction(new MiracleEffect(Color.LIGHT_GRAY.cpy(), Color.SKY.cpy(), "HEAL_3")));
        addToBot(new ApplyPowerAction(p, p, new KarulaPower(p, this.magicNumber), this.magicNumber));
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new Karula();
    }
}
