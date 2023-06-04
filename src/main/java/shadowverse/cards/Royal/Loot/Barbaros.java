package shadowverse.cards.Royal.Loot;

import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsInHandAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.vfx.BorderFlashEffect;
import shadowverse.Shadowverse;
import shadowverse.cards.AbstractRightClickCard2;
import shadowverse.cards.Neutral.Temp.DreadPirateFlag;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Royal;
import shadowverse.powers.CutthroatPower;
import shadowverse.relics.KagemitsuSword;

public class Barbaros
        extends AbstractRightClickCard2 {
    public static final String ID = "shadowverse:Barbaros";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Barbaros");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/Barbaros.png";
    public static final String IMG_PATH_EV = "img/cards/Barbaros_Ev.png";
    private boolean hasFusion = false;



    public Barbaros() {
        super(ID, NAME, IMG_PATH, 2, DESCRIPTION, CardType.ATTACK, Royal.Enums.COLOR_YELLOW, CardRarity.RARE, CardTarget.ENEMY);
        this.baseDamage = 10;
        this.tags.add(AbstractShadowversePlayer.Enums.CONDEMNED);
        this.cardsToPreview = new DreadPirateFlag();
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeDamage(7);
            this.textureImg = IMG_PATH_EV;
            this.loadCardImage(IMG_PATH_EV);
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }

    @Override
    public void update() {
        if (AbstractDungeon.currMapNode != null && (AbstractDungeon.getCurrRoom()).phase == AbstractRoom.RoomPhase.COMBAT &&
                Shadowverse.Enhance(3)) {
            setCostForTurn(3);
        } else {
            if (this.costForTurn != 0) {
                setCostForTurn(2);
            }
        }
        super.update();
    }

    @Override
    protected void onRightClick() {
            if (!this.hasFusion && AbstractDungeon.player!=null){
                addToBot(new SelectCardsInHandAction(9,TEXT[0],true,true, card -> {
                    return card.hasTag(AbstractShadowversePlayer.Enums.GILDED);
                }, abstractCards -> {
                    if (abstractCards.size()>0){
                        this.upgrade();
                        this.superFlash();
                    }
                    for (AbstractCard c : abstractCards){
                        addToBot(new ExhaustSpecificCardAction(c,AbstractDungeon.player.hand));
                    }
                }));
            this.hasFusion = true;
        }
    }


    @Override
    public void atTurnStart(){
        hasFusion = false;
    }

    public void degrade() {
        if (this.upgraded) {
            degradeName();
            this.textureImg = IMG_PATH;
            this.loadCardImage(IMG_PATH);
            this.rawDescription = cardStrings.DESCRIPTION;
            initializeDescription();
            this.superFlash();
            this.applyPowers();
        }
    }


    public void degradeName() {
        --this.timesUpgraded;
        this.upgraded = false;
        this.name = NAME;
        this.initializeTitle();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.effectsQueue.add(new BorderFlashEffect(Color.BLUE, true));
        int hasGilded = 0;
        for (AbstractCard card:p.exhaustPile.group){
            if (card.hasTag(AbstractShadowversePlayer.Enums.GILDED))
                hasGilded++;
        }
        if (hasGilded>=7){
            addToBot(new GainEnergyAction(2));
        }
        addToBot(new MakeTempCardInHandAction(new DreadPirateFlag(),1));
        if (this.costForTurn == 3) {
            if (p.hasPower(CutthroatPower.POWER_ID)){
                addToBot(new GainEnergyAction(2));
            }
            addToBot(new SFXAction("Barbaros_Eh"));
            addToBot(new DamageAction(m,new DamageInfo(p,this.damage*2,this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
        }else {
            addToBot(new SFXAction("Barbaros"));
            addToBot(new DamageAction(m,new DamageInfo(p,this.damage,this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
        }
        this.degrade();
        if(p.hasRelic(KagemitsuSword.ID)){
            this.upgrade();
        }
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new Barbaros();
    }
}

