package shadowverse.cards.Nemesis.Default;

import basemod.abstracts.CustomCard;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.BorderFlashEffect;
import com.megacrit.cardcrawl.vfx.combat.MiracleEffect;
import shadowverse.action.BurialAction;
import shadowverse.cards.Neutral.Temp.EdgeArtifact;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Nemesis;
import shadowverse.powers.MementoPower;
import shadowverse.powers.VyrmedeaPower;

import java.util.ArrayList;

public class Vyrmedea
        extends CustomCard {
    public static final String ID = "shadowverse:Vyrmedea";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Vyrmedea");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/Vyrmedea.png";

    public Vyrmedea() {
        super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.ATTACK, Nemesis.Enums.COLOR_SKY, CardRarity.RARE, CardTarget.SELF);
        this.baseBlock = 3;
        this.tags.add(AbstractShadowversePlayer.Enums.ARTIFACT);
        this.cardsToPreview = new EdgeArtifact();
    }



    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBlock(3);
        }
    }


    public void use(AbstractPlayer p, AbstractMonster abstractMonster) {
        ArrayList<String> dup = new ArrayList<>();
        for (AbstractCard c: p.exhaustPile.group){
            if (c.hasTag(AbstractShadowversePlayer.Enums.ARTIFACT)&&!dup.contains(c.cardID)){
                dup.add(c.cardID);
            }
        }
        if (dup.size()>=6){
            addToBot(new ApplyPowerAction(p,p,new VyrmedeaPower(p,1),1));
        }
        addToBot(new SFXAction("Vyrmedea"));
        addToBot(new VFXAction(new BorderFlashEffect(Color.YELLOW, true)));
        addToBot(new VFXAction(new MiracleEffect(Color.LIGHT_GRAY.cpy(),Color.GOLD.cpy(),"HEAL_3")));
        addToBot(new GainBlockAction(p,this.block));
        AbstractCard c = this.cardsToPreview.makeStatEquivalentCopy();
        c.setCostForTurn(0);
        addToBot(new BurialAction(1,new MakeTempCardInHandAction(c)));
        int attackAmt = 0;
        for (AbstractCard card : p.hand.group) {
            if (card != this && card.type == CardType.ATTACK)
                attackAmt++;
        }
        if (attackAmt >= 2) {
            if (p.hasPower(MementoPower.POWER_ID)) {
                addToBot(new MakeTempCardInHandAction(c));
            }
        }
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new Vyrmedea();
    }
}


