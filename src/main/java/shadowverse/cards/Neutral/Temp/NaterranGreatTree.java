package shadowverse.cards.Neutral.Temp;

import basemod.abstracts.CustomCard;
import com.evacipated.cardcrawl.mod.stslib.powers.abstracts.TwoAmountPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import shadowverse.cards.AbstractNoCountDownAmulet;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.orbs.AmuletOrb;
import shadowverse.powers.NaterranTree;
import shadowverse.powers.UnerielPower;

public class NaterranGreatTree
        extends CustomCard implements AbstractNoCountDownAmulet {
    public static final String ID = "shadowverse:NaterranGreatTree";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:NaterranGreatTree");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/NaterranGreatTree.png";

    public NaterranGreatTree() {
        super("shadowverse:NaterranGreatTree", NAME, "img/cards/NaterranGreatTree.png", 0, DESCRIPTION, CardType.POWER, CardColor.COLORLESS, CardRarity.SPECIAL, CardTarget.SELF);
        this.baseMagicNumber = 1;
        this.magicNumber = this.baseMagicNumber;
        this.tags.add(AbstractShadowversePlayer.Enums.NATURAL);
        this.tags.add(AbstractShadowversePlayer.Enums.LASTWORD);
    }


    public void upgrade() {
    }

    public boolean canUpgrade() {
        return false;
    }

    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        boolean powerExists = false;
        if (abstractPlayer instanceof AbstractShadowversePlayer) {
            ((AbstractShadowversePlayer) abstractPlayer).naterranCount++;
        }
        for (AbstractPower pow : abstractPlayer.powers) {
            if (pow.ID.equals("shadowverse:NaterranTree")) {
                powerExists = true;
                break;
            }
        }
        if (!powerExists) {
            addToBot(new ApplyPowerAction(abstractPlayer, abstractPlayer, new NaterranTree(abstractPlayer)));
        } else {
            addToBot(new DrawCardAction(abstractPlayer, this.magicNumber));
            if (AbstractDungeon.player instanceof AbstractShadowversePlayer) {
                ((AbstractShadowversePlayer) AbstractDungeon.player).amuletCount++;
            }
            if (AbstractDungeon.player.hasPower(UnerielPower.POWER_ID)){
                AbstractPower uPower = AbstractDungeon.player.getPower(UnerielPower.POWER_ID);
                for (int i = 0; i < uPower.amount; i++){
                    AbstractDungeon.actionManager.addToBottom(new SFXAction("UnerielPower2"));
                    AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction(AbstractDungeon.player, DamageInfo.createDamageMatrix(((TwoAmountPower)uPower).amount2, true), DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.FIRE, true));
                }
            }
        }
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new NaterranGreatTree();
    }

    @Override
    public void onStartOfTurn(AmuletOrb paramOrb) {

    }

    @Override
    public void onEvoke(AmuletOrb paramOrb) {

    }

    @Override
    public void endOfTurn(AmuletOrb paramOrb) {

    }

    @Override
    public void onGainedBlock(int blockAmt, AmuletOrb paramOrb) {

    }

    @Override
    public void onOtherCardPlayed(AbstractCard c, AmuletOrb paramOrb) {

    }

    @Override
    public int onHeal(int healAmount, AmuletOrb paramOrb) {
        return 0;
    }

}

