package shadowverse.cards.Bishop.Amulet1;

import basemod.abstracts.CustomCard;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.BorderFlashEffect;
import com.megacrit.cardcrawl.vfx.combat.RipAndTearEffect;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Bishop;
import shadowverse.powers.UnerielPower;

public class Uneriel extends CustomCard {
    public static final String ID = "shadowverse:Uneriel";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Uneriel");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/Uneriel.png";


    public Uneriel() {
        super(ID, NAME, IMG_PATH, 3, DESCRIPTION, CardType.ATTACK, Bishop.Enums.COLOR_WHITE, CardRarity.RARE, CardTarget.SELF);
        this.tags.add(AbstractShadowversePlayer.Enums.CONDEMNED);
        this.baseBlock = 24;
        this.baseMagicNumber = 10;
        this.magicNumber = this.baseMagicNumber;
    }



    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBlock(4);
            upgradeMagicNumber(5);
        }
    }

    public void applyPowers() {
        super.applyPowers();
        int amt = 0;
        if (AbstractDungeon.player instanceof AbstractShadowversePlayer){
            AbstractShadowversePlayer w = (AbstractShadowversePlayer) AbstractDungeon.player;
            amt = w.amuletCount;
        }
        this.rawDescription = cardStrings.DESCRIPTION;
        this.rawDescription += cardStrings.EXTENDED_DESCRIPTION[0]+amt;
        this.rawDescription += cardStrings.EXTENDED_DESCRIPTION[1];
        this.initializeDescription();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster abstractMonster) {
        addToBot(new SFXAction("Uneriel"));
        addToTop(new VFXAction(new RipAndTearEffect(p.hb.cX,p.hb.cY, Color.WHITE,Color.GRAY)));
        addToBot(new VFXAction(new BorderFlashEffect(com.badlogic.gdx.graphics.Color.GOLD, true)));
        addToBot(new GainBlockAction(p, this.block));
        int count = 0;
        if (p instanceof AbstractShadowversePlayer){
            count= ((AbstractShadowversePlayer) p).amuletCount;
        }
        if (count>=5){
            addToBot(new GainEnergyAction(2));
        }
        if (p.hasPower(UnerielPower.POWER_ID)){
            addToBot(new ApplyPowerAction(p,p,new UnerielPower(p,1,0)));
        }else {
            addToBot(new ApplyPowerAction(p,p,new UnerielPower(p,1,this.magicNumber)));
        }
    }


    @Override
    public AbstractCard makeCopy() {
        return new Uneriel();
    }
}
