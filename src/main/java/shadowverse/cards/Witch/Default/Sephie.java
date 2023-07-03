package shadowverse.cards.Witch.Default;

import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsInHandAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import com.megacrit.cardcrawl.vfx.BorderFlashEffect;
import com.megacrit.cardcrawl.vfx.stance.StanceChangeParticleGenerator;
import shadowverse.cards.AbstractRightClickCard2;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Witchcraft;


public class Sephie extends AbstractRightClickCard2 {
    public static final String ID = "shadowverse:Sephie";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Sephie");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/Sephie.png";
    private boolean hasFusion = false;
    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("ExhaustAction");
    public static final String[] TEXT = uiStrings.TEXT;

    public Sephie() {
        super(ID, NAME, IMG_PATH, 2, DESCRIPTION, CardType.ATTACK, Witchcraft.Enums.COLOR_BLUE, CardRarity.RARE, CardTarget.ENEMY);
        this.baseDamage = 15;
        this.baseMagicNumber = 10;
        this.magicNumber = this.baseMagicNumber;
        this.tags.add(AbstractShadowversePlayer.Enums.CONDEMNED);
        this.selfRetain = true;
        this.cardsToPreview = new VolunteerTestSubject();
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeDamage(5);
            upgradeMagicNumber(5);
        }
    }

    @Override
    protected void onRightClick() {
        if (!this.hasFusion && AbstractDungeon.player!=null){
            addToBot(new SelectCardsInHandAction(9,TEXT[0],true,true, card -> {
                return CardLibrary.getCard(card.cardID).type != CardType.SKILL && card != this;
            }, abstractCards -> {
                if (abstractCards.size()>0){
                    this.hasFusion = true;
                }
                for(AbstractCard c:abstractCards){
                    addToBot(new ExhaustSpecificCardAction(c,AbstractDungeon.player.hand));
                }
                AbstractCard t = this.cardsToPreview.makeStatEquivalentCopy();
                t.setCostForTurn(0);
                if (abstractCards.size()>1){
                    if (abstractCards.stream().anyMatch(ca -> ca.hasTag(AbstractShadowversePlayer.Enums.CONDEMNED))){
                        addToBot(new MakeTempCardInHandAction(t, 1));
                    }else if (EnergyPanel.getCurrentEnergy() > 0){
                        EnergyPanel.useEnergy(1);
                        addToBot(new MakeTempCardInHandAction(t, 1));
                    }
                }
            }));
        }
    }

    @Override
    public void atTurnStart(){
        hasFusion = false;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new SFXAction("Sephie"));
        addToBot(new VFXAction(new BorderFlashEffect(Color.BLUE,true)));
        addToBot(new VFXAction(new StanceChangeParticleGenerator(p.hb.cX, p.hb.cY, "Divinity")));
        addToBot(new DamageAction(m,new DamageInfo(p,this.damage,this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        AbstractCard t = this.cardsToPreview.makeStatEquivalentCopy();
        t.setCostForTurn(0);
        int count = 0;
        for (AbstractCard c : AbstractDungeon.actionManager.cardsPlayedThisCombat) {
            if (c instanceof VolunteerTestSubject)
                count++;
        }
        if (count > 5){
            t.baseDamage += this.magicNumber;
            t.applyPowers();
            t.superFlash();
            for (AbstractCard ca : AbstractDungeon.player.hand.group) {
                if (ca instanceof VolunteerTestSubject) {
                    ca.baseDamage += this.magicNumber;
                    ca.applyPowers();
                    ca.superFlash();
                }
            }
        }
        addToBot(new MakeTempCardInHandAction(t, 1));
    }


    public AbstractCard makeCopy() {
        return new Sephie();
    }
}
