package shadowverse.cards.Nemesis.Mech;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.tempCards.Miracle;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.SmallLaserEffect;
import shadowverse.cards.Neutral.Status.EvolutionPoint;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Nemesis;

public class MegaEnforcer
        extends CustomCard {
    public static final String ID = "shadowverse:MegaEnforcer";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:MegaEnforcer");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/MegaEnforcer.png";

    public MegaEnforcer() {
        super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.ATTACK, Nemesis.Enums.COLOR_SKY, CardRarity.UNCOMMON, CardTarget.ENEMY);
        this.baseDamage = 8;
        this.baseBlock = 6;
        this.tags.add(AbstractShadowversePlayer.Enums.MACHINE);
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeDamage(3);
            upgradeBlock(3);
        }
    }




    public void use(AbstractPlayer p, AbstractMonster abstractMonster) {
        addToBot(new SFXAction("MegaEnforcer"));
        addToBot(new GainBlockAction(p,this.block));
        int mCount = 0;
        for (AbstractCard c : p.hand.group) {
            if (c.hasTag(AbstractShadowversePlayer.Enums.MACHINE)&&c!=this){
                mCount++;
            }
        }
        if (mCount>0){
            addToBot(new DrawCardAction(1));
            addToBot(new VFXAction(new SmallLaserEffect(abstractMonster.hb.cX, abstractMonster.hb.cY, p.hb.cX, p.hb.cY), 0.1F));
            addToBot( new DamageAction(abstractMonster, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.NONE));
        }
        if (mCount>1){
            addToBot(new MakeTempCardInHandAction(new EvolutionPoint()));
            addToBot(new MakeTempCardInHandAction(new Miracle()));
        }
    }


    public AbstractCard makeCopy() {
        return  new MegaEnforcer();
    }
}


