package shadowverseCharbosses.cards.bishop;

import shadowverseCharbosses.cards.AbstractBossCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.random.Random;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.ClashEffect;
import shadowverse.cards.Neutral.Curse.*;
import shadowverse.characters.Bishop;

import java.util.ArrayList;

public class EnHereticalInquiry extends AbstractBossCard {
    public static final String ID = "shadowverse:EnHereticalInquiry";

    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:EnHereticalInquiry");

    public static final String IMG_PATH = "img/cards/HereticalInquiry.png";
    public static ArrayList<AbstractCard> rndCurse() {
        ArrayList<AbstractCard> list = new ArrayList<>();
        list.add(new KMRGaze());
        list.add(new Altersphere());
        list.add(new WUP());
        list.add(new Death());
        list.add(new Rowen());
        list.add(new ShadowversePain());
        list.add(new Betray());
        list.add(new MadnessCurse());
        return list;
    }

    public AbstractCard returnRndCurse(Random rng) {
        return rndCurse().get(rng.random(rndCurse().size() - 1));
    }

    public EnHereticalInquiry() {
        super(ID, cardStrings.NAME, IMG_PATH, 1, cardStrings.DESCRIPTION, CardType.POWER, Bishop.Enums.COLOR_WHITE, CardRarity.COMMON, CardTarget.ENEMY, AbstractMonster.Intent.STRONG_DEBUFF);
        this.baseMagicNumber = 2;
        this.magicNumber = this.baseMagicNumber;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot((AbstractGameAction)new VFXAction((AbstractGameEffect)new ClashEffect(p.hb.cX, p.hb.cY), 0.1F));
        for (int i=0;i<this.magicNumber;i++){
            AbstractCard c = returnRndCurse(AbstractDungeon.cardRng);
            addToBot((AbstractGameAction)new MakeTempCardInDrawPileAction(c,1,true,true,false));
        }
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeMagicNumber(1);
        }
    }

    public AbstractCard makeCopy() {
        return (AbstractCard)new EnHereticalInquiry();
    }
}
