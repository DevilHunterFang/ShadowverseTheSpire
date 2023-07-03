package shadowverse.cards.Witch.Default;

import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsInHandAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.*;
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
import shadowverse.action.DrawPileToHandAction_Tag;
import shadowverse.cards.AbstractRightClickCard2;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Witchcraft;


public class ObsessiveScholar extends AbstractRightClickCard2 {
    public static final String ID = "shadowverse:ObsessiveScholar";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:ObsessiveScholar");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/ObsessiveScholar.png";
    private boolean hasFusion = false;
    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("ExhaustAction");
    public static final String[] TEXT = uiStrings.TEXT;

    public ObsessiveScholar() {
        super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.ATTACK, Witchcraft.Enums.COLOR_BLUE, CardRarity.UNCOMMON, CardTarget.ENEMY);
        this.baseDamage = 6;
        this.tags.add(AbstractShadowversePlayer.Enums.CONDEMNED);
        this.cardsToPreview = new VolunteerTestSubject();
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeDamage(3);
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
            }));
        }
    }

    @Override
    public void atTurnStart(){
        hasFusion = false;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new SFXAction("ObsessiveScholar"));
        addToBot(new DamageAction(m,new DamageInfo(p,this.damage,this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        AbstractCard c = this.cardsToPreview.makeStatEquivalentCopy();
        c.setCostForTurn(0);
        addToBot(new MakeTempCardInHandAction(c, 1));
        if (this.hasFusion){
            addToBot(new DrawPileToHandAction_Tag(1,AbstractShadowversePlayer.Enums.CONDEMNED,null));
        }
    }

    public AbstractCard makeCopy() {
        return new ObsessiveScholar();
    }
}
