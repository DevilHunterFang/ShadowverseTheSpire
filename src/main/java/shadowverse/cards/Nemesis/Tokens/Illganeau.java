package shadowverse.cards.Nemesis.Tokens;

import basemod.abstracts.CustomCard;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.VerticalAuraEffect;
import shadowverse.cards.Neutral.Temp.VoidRealize;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Nemesis;


public class Illganeau
        extends CustomCard {
    public static final String ID = "shadowverse:Illganeau";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Illganeau");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/Illganeau.png";

    public Illganeau() {
        super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.ATTACK, Nemesis.Enums.COLOR_SKY, CardRarity.RARE, CardTarget.SELF);
        this.cardsToPreview = (AbstractCard) new VoidRealize();
        this.tags.add(AbstractShadowversePlayer.Enums.LASTWORD);
        this.baseBlock = 5;
        this.exhaust = true;
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBlock(3);
        }
    }

    @Override
    public void triggerOnExhaust() {
        int dex = 0;
        for (AbstractPower p : AbstractDungeon.player.powers) {
            if (p.ID.equals(DexterityPower.POWER_ID))
                dex = p.amount;
        }
        if (dex >= 3)
            addToBot((AbstractGameAction) new MakeTempCardInHandAction(this.cardsToPreview.makeStatEquivalentCopy()));
        else{
            addToBot((AbstractGameAction)new ApplyPowerAction(AbstractDungeon.player,AbstractDungeon.player,(AbstractPower)new DexterityPower(AbstractDungeon.player,1),1));
            addToBot((AbstractGameAction) new MakeTempCardInHandAction(this.makeStatEquivalentCopy()));
        }
    }

    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot((AbstractGameAction) new SFXAction("Illganeau"));
        addToBot((AbstractGameAction)new GainBlockAction(abstractPlayer,this.block));
        addToBot((AbstractGameAction)new VFXAction((AbstractCreature)abstractPlayer, (AbstractGameEffect)new VerticalAuraEffect(Color.BLACK, abstractPlayer.hb.cX,abstractPlayer.hb.cY), 0.33F));
        addToBot((AbstractGameAction)new VFXAction((AbstractCreature)abstractPlayer, (AbstractGameEffect)new VerticalAuraEffect(Color.PURPLE, abstractPlayer.hb.cX, abstractPlayer.hb.cY), 0.33F));
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new Illganeau();
    }
}


