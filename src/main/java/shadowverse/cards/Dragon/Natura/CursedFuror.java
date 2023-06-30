package shadowverse.cards.Dragon.Natura;

import basemod.abstracts.CustomCard;
import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.mod.stslib.powers.abstracts.TwoAmountPower;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.actions.watcher.ChooseOneAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.EnergizedPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.vfx.combat.ClawEffect;
import shadowverse.cards.Neutral.Temp.*;
import shadowverse.characters.Dragon;
import shadowverse.powers.OverflowPower;

import java.util.ArrayList;


public class CursedFuror
        extends CustomCard {
    public static final String ID = "shadowverse:CursedFuror";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:CursedFuror");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/CursedFuror.png";

    public CursedFuror() {
        super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.SKILL, Dragon.Enums.COLOR_BROWN, CardRarity.UNCOMMON, CardTarget.NONE);
        this.baseMagicNumber = 2;
        this.magicNumber = this.baseMagicNumber;
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBaseCost(0);
        }
    }


    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        boolean overflow = false;
        if (abstractPlayer.hasPower(OverflowPower.POWER_ID)){
            TwoAmountPower p = (TwoAmountPower) abstractPlayer.getPower(OverflowPower.POWER_ID);
            if (p.amount2 > 0){
                overflow = true;
            }
        }
        if (overflow){
            addToBot(new SFXAction("CursedFuror"));
            addToBot(new ApplyPowerAction(AbstractDungeon.player,AbstractDungeon.player,new EnergizedPower(AbstractDungeon.player,this.magicNumber)));
            addToBot(new ApplyPowerAction(AbstractDungeon.player,AbstractDungeon.player,new OverflowPower(AbstractDungeon.player,1)));
            AbstractCreature m = AbstractDungeon.getMonsters().getRandomMonster(null, true, AbstractDungeon.cardRandomRng);
            if (m != null){
                addToBot(new VFXAction(new ClawEffect(m.hb.cX, m.hb.cY, Color.PURPLE, Color.ORANGE), 0.1F));
                addToBot(new ApplyPowerAction(m,AbstractDungeon.player,new VulnerablePower(m,this.magicNumber,false)));
                addToBot(new ApplyPowerAction(m,AbstractDungeon.player,new WeakPower(m,this.magicNumber,false)));
            }
        }else{
            ArrayList<AbstractCard> stanceChoices = new ArrayList<>();
            AbstractCard m = new RowenRoar();
            AbstractCard e = new ValdainClaw();
            stanceChoices.add(m);
            stanceChoices.add(e);
            addToBot( new ChooseOneAction(stanceChoices));
        }

    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new CursedFuror();
    }
}

