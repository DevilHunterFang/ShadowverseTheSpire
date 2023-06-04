package shadowverse.cards.Vampire.Academic;


import basemod.abstracts.CustomCard;
import com.evacipated.cardcrawl.mod.stslib.actions.common.StunMonsterAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.RemoveAllBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Vampire;
import shadowverse.powers.EpitaphPower;
import shadowverse.stance.Vengeance;


public class DemonEyeGangster extends CustomCard {
    public static final String ID = "shadowverse:DemonEyeGangster";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:DemonEyeGangster");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/DemonEyeGangster.png";
    public boolean triggered;

    public DemonEyeGangster() {
        super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.ATTACK, Vampire.Enums.COLOR_SCARLET, CardRarity.COMMON, CardTarget.ENEMY);
        this.baseBlock = 8;
        this.tags.add(AbstractShadowversePlayer.Enums.ACADEMIC);
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBlock(3);
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }


    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new GainBlockAction(abstractPlayer,this.block));
        if (abstractPlayer.stance.ID.equals(Vengeance.STANCE_ID) || abstractPlayer.hasPower(EpitaphPower.POWER_ID)) {
            addToBot(new RemoveAllBlockAction(abstractMonster,abstractPlayer));
            if (upgraded && !triggered){
                addToBot(new StunMonsterAction(abstractMonster,abstractPlayer));
                triggered = true;
            }
        }
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new DemonEyeGangster();
    }
}

