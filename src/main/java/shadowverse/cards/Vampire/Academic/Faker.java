package shadowverse.cards.Vampire.Academic;


import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.characters.Vampire;
import shadowverse.powers.EpitaphPower;
import shadowverse.powers.ExitVengeancePower;
import shadowverse.powers.VengeanceBuffPower;
import shadowverse.stance.Vengeance;


public class Faker
        extends CustomCard {
    public static final String ID = "shadowverse:Faker";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Faker");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/Faker.png";

    public Faker() {
        super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.ATTACK, Vampire.Enums.COLOR_SCARLET, CardRarity.UNCOMMON, CardTarget.SELF);
        this.isEthereal = true;
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            this.isEthereal = false;
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }

    @Override
    public void triggerOnManualDiscard() {
        addToBot(new ChangeStanceAction(new Vengeance()));
        addToBot(new ApplyPowerAction(AbstractDungeon.player,AbstractDungeon.player,new ExitVengeancePower(AbstractDungeon.player,2)));
    }

    public void use(AbstractPlayer p, AbstractMonster abstractMonster) {
        addToBot((AbstractGameAction) new SFXAction("Faker"));
        if (p.stance.ID.equals(Vengeance.STANCE_ID)||p.hasPower(EpitaphPower.POWER_ID)){
            addToBot(new ApplyPowerAction(p,p,new VengeanceBuffPower(p,1)));
        }
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new Faker();
    }
}

