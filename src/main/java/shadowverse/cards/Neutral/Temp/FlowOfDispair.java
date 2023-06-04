package shadowverse.cards.Neutral.Temp;

import basemod.abstracts.CustomCard;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.InstantKillAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.MiracleEffect;
import com.megacrit.cardcrawl.vfx.combat.ThrowDaggerEffect;
import shadowverse.characters.Bishop;

import java.math.BigDecimal;

public class FlowOfDispair
        extends CustomCard {
    public static final String ID = "shadowverse:FlowOfDispair";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:FlowOfDispair");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/FlowOfDispair.png";

    public FlowOfDispair() {
        super(ID, NAME, IMG_PATH, 0, DESCRIPTION, CardType.SKILL, Bishop.Enums.COLOR_WHITE, CardRarity.SPECIAL, CardTarget.ENEMY);
        this.exhaust = true;
    }



    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
        }
    }


    public void use(AbstractPlayer p, AbstractMonster abstractMonster) {
        addToBot((AbstractGameAction)new SFXAction("FlowOfDispair"));
        addToBot((AbstractGameAction)new VFXAction((AbstractGameEffect)new MiracleEffect(Color.SKY.cpy(),Color.WHITE.cpy(),"HEAL_3")));
        for (int i=0;i<4;i++){
            addToTop((AbstractGameAction)new VFXAction((AbstractGameEffect)new ThrowDaggerEffect(abstractMonster.hb.cX, abstractMonster.hb.cY),0.3F));
        }
        BigDecimal amt = new BigDecimal(abstractMonster.maxHealth*0.4);
        BigDecimal ft = new BigDecimal(40);
        if (amt.compareTo(ft)<0)
            amt = ft;
        if (amt.compareTo(BigDecimal.valueOf(abstractMonster.currentHealth))>=0){
            addToBot((AbstractGameAction)new InstantKillAction(abstractMonster));
        }
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new FlowOfDispair();
    }
}


