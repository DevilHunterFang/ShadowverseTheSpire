package shadowverse.cards.Witch.Earth1;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.tempCards.Miracle;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import rs.lazymankits.interfaces.cards.BranchableUpgradeCard;
import rs.lazymankits.interfaces.cards.UpgradeBranch;
import shadowverse.cards.Neutral.Status.EvolutionPoint;
import shadowverse.cards.Neutral.Temp.FireBall;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Witchcraft;
import shadowverse.powers.EarthEssence;

import java.util.ArrayList;
import java.util.List;

public class MasterMageLevi extends CustomCard implements BranchableUpgradeCard {
    public static final String ID = "shadowverse:MasterMageLevi";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:MasterMageLevi");
    public static CardStrings cardStrings2 = CardCrawlGame.languagePack.getCardStrings("shadowverse:MasterMageLevi2");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/MasterMageLevi.png";
    public static final String IMG_PATH2 = "img/cards/MasterMageLevi2.png";

    public MasterMageLevi() {
        super("shadowverse:MasterMageLevi", NAME, "img/cards/MasterMageLevi.png", 2, DESCRIPTION, CardType.ATTACK, Witchcraft.Enums.COLOR_BLUE, CardRarity.UNCOMMON, CardTarget.ENEMY);
        this.baseDamage = 16;
        this.tags.add(AbstractShadowversePlayer.Enums.EARTH_RITE);
        this.baseMagicNumber = 1;
        this.magicNumber = this.baseMagicNumber;
    }


    public void upgrade() {
        ((UpgradeBranch) ((BranchableUpgradeCard) this).possibleBranches().get(chosenBranch())).upgrade();
    }


    public void applyPowers() {
        super.applyPowers();
        if (chosenBranch() == 1){
            int count = 0;
            if (AbstractDungeon.player instanceof AbstractShadowversePlayer){
                count = ((AbstractShadowversePlayer) AbstractDungeon.player).earthCount;
            }
            this.rawDescription = cardStrings.DESCRIPTION;
            this.rawDescription += cardStrings.EXTENDED_DESCRIPTION[0] + count;
            this.rawDescription += cardStrings.EXTENDED_DESCRIPTION[1];
            initializeDescription();
        }
    }



    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        switch (chosenBranch()){
            case 0:
                addToBot(new SFXAction("MasterMageLevi"));
                addToBot(new DamageAction(abstractMonster, new DamageInfo(abstractPlayer, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
                boolean powerExists = false;
                for (AbstractPower pow : abstractPlayer.powers) {
                    if (pow.ID.equals("shadowverse:EarthEssence")) {
                        powerExists = true;
                        break;
                    }
                }
                if (powerExists) {
                    if (abstractPlayer instanceof AbstractShadowversePlayer) {
                        ((AbstractShadowversePlayer) abstractPlayer).earthCount++;
                    }
                    addToBot(new DamageAllEnemiesAction(abstractPlayer, DamageInfo.createDamageMatrix(this.damage, true), DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.FIRE, true));
                    addToBot(new ApplyPowerAction(abstractPlayer, abstractPlayer, new EarthEssence(abstractPlayer, -this.magicNumber), -this.magicNumber));
                }
                break;
            case 1:
                addToBot(new SFXAction("MasterMageLevi2"));
                addToBot(new MakeTempCardInHandAction(this.cardsToPreview.makeStatEquivalentCopy()));
                if (abstractPlayer instanceof AbstractShadowversePlayer){
                    int count = ((AbstractShadowversePlayer) abstractPlayer).earthCount;
                    if (count >= 7){
                        addToBot(new MakeTempCardInHandAction(new Miracle()));
                        addToBot(new MakeTempCardInHandAction(new EvolutionPoint()));
                    }
                }
                if (abstractPlayer.hasPower(EarthEssence.POWER_ID)){
                    AbstractPower p = abstractPlayer.getPower(EarthEssence.POWER_ID);
                    addToBot(new DamageAction(abstractMonster, new DamageInfo(abstractPlayer, 3 * p.amount, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
                    if (p.amount > 2){
                        if (abstractPlayer instanceof AbstractShadowversePlayer) {
                            ((AbstractShadowversePlayer) abstractPlayer).earthCount+=2;
                            addToBot(new ApplyPowerAction(abstractPlayer, abstractPlayer, new EarthEssence(abstractPlayer, -2), -2));
                            addToBot(new DamageAllEnemiesAction(abstractPlayer, DamageInfo.createDamageMatrix(this.damage, true), DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.FIRE, true));
                        }
                    }
                }
                break;
        }
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new MasterMageLevi();
    }

    @Override
    public List<UpgradeBranch> possibleBranches() {
        ArrayList<UpgradeBranch> list = new ArrayList<UpgradeBranch>();
        list.add(new UpgradeBranch() {
            @Override
            public void upgrade() {
                ++MasterMageLevi.this.timesUpgraded;
                MasterMageLevi.this.upgraded = true;
                MasterMageLevi.this.name = NAME + "+";
                MasterMageLevi.this.initializeTitle();
                MasterMageLevi.this.baseDamage = 20;
                MasterMageLevi.this.upgradedDamage = true;
            }
        });
        list.add(new UpgradeBranch() {
            @Override
            public void upgrade() {
                ++MasterMageLevi.this.timesUpgraded;
                MasterMageLevi.this.baseDamage = 36;
                MasterMageLevi.this.upgradedDamage = true;
                MasterMageLevi.this.upgraded = true;
                MasterMageLevi.this.textureImg = IMG_PATH2;
                MasterMageLevi.this.loadCardImage(IMG_PATH2);
                MasterMageLevi.this.name = cardStrings2.NAME;
                MasterMageLevi.this.initializeTitle();
                MasterMageLevi.this.rawDescription = cardStrings2.DESCRIPTION;
                MasterMageLevi.this.initializeDescription();
                MasterMageLevi.this.cardsToPreview = new FireBall();
                MasterMageLevi.this.rarity = CardRarity.RARE;
                MasterMageLevi.this.setDisplayRarity(MasterMageLevi.this.rarity);
            }
        });
        return list;
    }
}

