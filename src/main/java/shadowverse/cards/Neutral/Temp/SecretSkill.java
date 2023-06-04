package shadowverse.cards.Neutral.Temp;

import basemod.abstracts.CustomCard;
import com.evacipated.cardcrawl.mod.stslib.actions.common.StunMonsterAction;
import com.evacipated.cardcrawl.mod.stslib.actions.defect.EvokeSpecificOrbAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.cards.AbstractNoCountDownAmulet;
import shadowverse.characters.Royal;
import shadowverse.orbs.AmuletOrb;

public class SecretSkill extends CustomCard implements AbstractNoCountDownAmulet {
    public static final String ID = "shadowverse:SecretSkill";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:SecretSkill");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/SecretSkill.png";


    public SecretSkill() {
        super(ID, NAME, IMG_PATH, 0, DESCRIPTION,CardType.POWER, Royal.Enums.COLOR_YELLOW, CardRarity.SPECIAL, CardTarget.SELF);
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
        }
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new SFXAction("SecretSkill"));
    }

    @Override
    public void onStartOfTurn(AmuletOrb paramOrb) {

    }

    @Override
    public void onEvoke(AmuletOrb paramOrb) {

    }

    @Override
    public void endOfTurn(AmuletOrb paramOrb) {
        for (AbstractMonster m : (AbstractDungeon.getCurrRoom()).monsters.monsters){
            if (m.getIntentDmg()>0){
                addToBot(new SFXAction("SecretSkill_Eff"));
                addToBot(new StunMonsterAction(m,AbstractDungeon.player));
                addToBot(new EvokeSpecificOrbAction(paramOrb));
                break;
            }
        }
    }

    @Override
    public void onGainedBlock(int blockAmt, AmuletOrb paramOrb) {

    }

    @Override
    public void onOtherCardPlayed(AbstractCard c, AmuletOrb paramOrb) {

    }

    @Override
    public int onHeal(int healAmount, AmuletOrb paramOrb) {
        return healAmount;
    }
}
