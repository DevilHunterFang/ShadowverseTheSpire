package shadowverse.cards.Necromancer.Necromancy;


import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.LoseStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import shadowverse.action.NecromanceAction;
import shadowverse.cards.Neutral.Temp.Zombie;
import shadowverse.characters.Necromancer;
import shadowverse.powers.Cemetery;


public class Hector
        extends CustomCard {
    public static final String ID = "shadowverse:Hector";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Hector");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/Hector.png";

    public Hector() {
        super(ID, NAME, IMG_PATH, 3, DESCRIPTION, CardType.ATTACK, Necromancer.Enums.COLOR_PURPLE, CardRarity.RARE, CardTarget.ENEMY);
        this.baseDamage = 20;
        this.cardsToPreview = (AbstractCard)new Zombie();
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeDamage(4);
            this.cardsToPreview.upgrade();
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }


    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new SFXAction("Hector"));
        addToBot(new SFXAction("ATTACK_HEAVY"));
        addToBot(new DamageAction(abstractMonster, new DamageInfo(abstractPlayer, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HEAVY));
        addToBot(new ApplyPowerAction(abstractPlayer, abstractPlayer, new StrengthPower(abstractPlayer, 2), 2));
        addToBot(new ApplyPowerAction(abstractPlayer, abstractPlayer, new LoseStrengthPower(abstractPlayer, 2), 2));
        int canGenerateAmt = 11-abstractPlayer.hand.group.size();
        int generateAmt = 0;
        int playerNecromance = 0;
        if (abstractPlayer.hasPower(Cemetery.POWER_ID)){
            for (AbstractPower p :abstractPlayer.powers){
                if (p.ID.equals(Cemetery.POWER_ID))
                    playerNecromance = p.amount;
            }
        }
        if (playerNecromance/3>=canGenerateAmt){
            generateAmt = canGenerateAmt;
        }else {
            generateAmt = playerNecromance/3;
        }
        for (int i =0;i<generateAmt;i++){
            addToBot(new NecromanceAction(3,null,
                    new MakeTempCardInHandAction(this.cardsToPreview.makeStatEquivalentCopy())));
        }

    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new Hector();
    }
}

