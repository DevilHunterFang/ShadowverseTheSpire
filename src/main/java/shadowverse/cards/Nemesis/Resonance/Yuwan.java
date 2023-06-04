package shadowverse.cards.Nemesis.Resonance;

import basemod.abstracts.CustomCard;
import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsInHandAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.stances.AbstractStance;
import com.megacrit.cardcrawl.stances.NeutralStance;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.BorderFlashEffect;
import com.megacrit.cardcrawl.vfx.combat.IntenseZoomEffect;
import shadowverse.cards.Neutral.Temp.AnalyzeArtifact;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Nemesis;
import shadowverse.powers.YuwanPower;
import shadowverse.stance.Resonance;

public class Yuwan
        extends CustomCard {
    public static final String ID = "shadowverse:Yuwan";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Yuwan");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/Yuwan.png";
    public static final String[] TEXT = (CardCrawlGame.languagePack.getUIString("shadowverse:applyEffect")).TEXT;

    public Yuwan() {
        super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.ATTACK, Nemesis.Enums.COLOR_SKY, CardRarity.RARE, CardTarget.SELF);
        this.baseBlock = 6;
        this.baseMagicNumber = 20;
        this.magicNumber = this.baseMagicNumber;
    }



    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBlock(3);
            upgradeMagicNumber(2);
            this.cardsToPreview = new AnalyzeArtifact();
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }

    public void applyPowers() {
        super.applyPowers();
        int count = 0;
        if (AbstractDungeon.player instanceof AbstractShadowversePlayer)
            count = ((AbstractShadowversePlayer) AbstractDungeon.player).resonanceCount;
        this.rawDescription = cardStrings.DESCRIPTION;
        this.rawDescription += cardStrings.EXTENDED_DESCRIPTION[0] + count;
        this.rawDescription += cardStrings.EXTENDED_DESCRIPTION[1];
        initializeDescription();
    }

    public void use(AbstractPlayer p, AbstractMonster abstractMonster) {
        addToBot((AbstractGameAction)new SFXAction("Yuwan"));
        addToBot((AbstractGameAction)new VFXAction((AbstractGameEffect)new BorderFlashEffect(Color.ROYAL, true)));
        addToBot((AbstractGameAction)new VFXAction((AbstractCreature) p, (AbstractGameEffect)new IntenseZoomEffect(p.hb.cX, p.hb.cY, false), 0.5F, true));
        addToBot((AbstractGameAction)new GainBlockAction(p,this.block));
        addToBot((AbstractGameAction)new ApplyPowerAction(p,p,(AbstractPower)new YuwanPower(p,this.magicNumber)));
        addToBot((AbstractGameAction)new SelectCardsInHandAction(1,TEXT[0],false,false,card -> {
            return true;
        }, abstractCards ->{
            for (AbstractCard c:abstractCards){
                addToBot((AbstractGameAction)new ExhaustSpecificCardAction(c,p.hand));
                addToBot((AbstractGameAction)new MakeTempCardInDrawPileAction(c.makeStatEquivalentCopy(),1,true,true,false));
            }
        } ));
        if ((p.drawPile.size()+1)%2==0&&!p.stance.ID.equals(Resonance.STANCE_ID)){
            addToBot((AbstractGameAction)new ChangeStanceAction((AbstractStance)new Resonance()));
            if (p instanceof AbstractShadowversePlayer)
                ((AbstractShadowversePlayer) p).resonanceCount++;
        }else if ((p.drawPile.size()+1)%2!=0&& p.stance.ID.equals(Resonance.STANCE_ID)){
            addToBot((AbstractGameAction)new ChangeStanceAction((AbstractStance)new NeutralStance()));
            if (p instanceof AbstractShadowversePlayer)
                ((AbstractShadowversePlayer) p).resonanceCount--;
        }
        addToBot((AbstractGameAction)new DrawCardAction(1));
        if (this.upgraded){
            addToBot((AbstractGameAction)new MakeTempCardInHandAction(new AnalyzeArtifact()));
        }
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new Yuwan();
    }
}


