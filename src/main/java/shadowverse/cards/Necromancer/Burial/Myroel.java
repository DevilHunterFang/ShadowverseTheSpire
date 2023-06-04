package shadowverse.cards.Necromancer.Burial;


import basemod.abstracts.CustomCard;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.BorderFlashEffect;
import com.megacrit.cardcrawl.vfx.combat.MiracleEffect;
import shadowverse.action.BurialAction;
import shadowverse.action.ReanimateAction;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Necromancer;
import shadowverse.powers.MementoPower;
import shadowverse.powers.MyroelPower;

public class Myroel extends CustomCard {
    public static final String ID = "shadowverse:Myroel";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Myroel");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/Myroel.png";

    public Myroel() {
        super(ID, NAME, IMG_PATH, 2, DESCRIPTION, CardType.POWER, Necromancer.Enums.COLOR_PURPLE, CardRarity.RARE, CardTarget.SELF);
        this.tags.add(AbstractShadowversePlayer.Enums.CONDEMNED);
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
    public void triggerOnExhaust() {
        addToBot( new DrawCardAction(2));
    }

    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot( new SFXAction("Myroel"));
        addToBot(new VFXAction(new BorderFlashEffect(Color.PURPLE, true)));
        addToBot(new VFXAction(new MiracleEffect(Color.LIGHT_GRAY.cpy(),Color.WHITE.cpy(),"HEAL_3")));
        int attackAmt = 0;
        for (AbstractCard c : abstractPlayer.hand.group) {
            if (c != this && c.type == CardType.ATTACK)
                attackAmt++;
        }
        if (attackAmt >= 2){
            addToBot( new ApplyPowerAction(abstractPlayer,abstractPlayer,new MyroelPower(abstractPlayer,1)));
            addToBot(new DamageAllEnemiesAction(abstractPlayer, DamageInfo.createDamageMatrix(10, true), DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.FIRE, true));
            if (this.costForTurn > 0){
                addToBot( new BurialAction(2, null));
                addToBot( new ReanimateAction(1));
                addToBot( new ReanimateAction(2));
                if (abstractPlayer.hasPower(MementoPower.POWER_ID)){
                    addToBot( new ReanimateAction(1));
                    addToBot( new ReanimateAction(2));
                }
            }
        }else {
            addToBot( new ApplyPowerAction(abstractPlayer,abstractPlayer,new MyroelPower(abstractPlayer,3)));
        }
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new Myroel();
    }
}

