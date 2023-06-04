package shadowverse.cards.Vampire.Default;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageRandomEnemyAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.actions.watcher.ChooseOneAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.action.GetEPAction;
import shadowverse.cards.Neutral.Temp.MonoResolve;
import shadowverse.cards.Neutral.Temp.UriasRevelry;
import shadowverse.characters.Vampire;
import shadowverse.powers.EpitaphPower;
import shadowverse.stance.Vengeance;

import java.util.ArrayList;


public class GarnetWaltz
        extends CustomCard {
    public static final String ID = "shadowverse:GarnetWaltz";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:GarnetWaltz");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/GarnetWaltz.png";

    public GarnetWaltz() {
        super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.SKILL, Vampire.Enums.COLOR_SCARLET, CardRarity.UNCOMMON, CardTarget.SELF);
        this.exhaust = true;
        this.cardsToPreview = (AbstractCard)new UriasRevelry();
        this.baseMagicNumber = 12;
        this.magicNumber = this.baseMagicNumber;
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBaseCost(0);
        }
    }


    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        ArrayList<AbstractCard> stanceChoices = new ArrayList<>();
        stanceChoices.add(new UriasRevelry());
        stanceChoices.add(new MonoResolve());
        if (abstractPlayer.hasPower(EpitaphPower.POWER_ID)||abstractPlayer.stance.ID.equals(Vengeance.STANCE_ID)){
            addToBot((AbstractGameAction)new SFXAction("GarnetWaltz"));
            addToBot((AbstractGameAction) new DamageRandomEnemyAction(new DamageInfo(AbstractDungeon.player, this.magicNumber, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
            addToBot((AbstractGameAction)new GetEPAction(true,1));
        }else {
            addToBot((AbstractGameAction)new ChooseOneAction(stanceChoices));
        }
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new GarnetWaltz();
    }
}

