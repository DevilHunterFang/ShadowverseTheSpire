package shadowverse.cards.Dragon.Armed;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import shadowverse.Shadowverse;
import shadowverse.cards.AbstractEnhanceCard;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Dragon;
import shadowverse.powers.CutthroatPower;


public class Rola extends AbstractEnhanceCard {
    public static final String ID = "shadowverse:Rola";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Rola");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/Rola.png";
    private static final Texture LEADER_SKIN_VERSION = ImageMaster.loadImage("img/cards/Rola_L.png");

    public Rola() {
        super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.ATTACK, Dragon.Enums.COLOR_BROWN, CardRarity.RARE, CardTarget.SELF,3);
        this.baseBlock = 6;
        this.baseDamage = 18;
        this.tags.add(AbstractShadowversePlayer.Enums.MACHINE);
        this.jokePortrait = new TextureAtlas.AtlasRegion(LEADER_SKIN_VERSION, 0, 0, LEADER_SKIN_VERSION.getWidth(), LEADER_SKIN_VERSION.getHeight());
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBlock(3);
            upgradeDamage(3);
        }
    }

    @Override
    public void update() {
        if (AbstractDungeon.currMapNode != null && (AbstractDungeon.getCurrRoom()).phase == AbstractRoom.RoomPhase.COMBAT &&
                Shadowverse.Enhance(3)) {
            setCostForTurn(3);
        } else {
            if (this.costForTurn != 0) {
                setCostForTurn(1);
            }
        }
        super.update();
    }

    @Override
    public void exEnhanceUse(AbstractPlayer p, AbstractMonster m) {

    }

    @Override
    public void enhanceUse(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p, this.block));
        for (AbstractCard card : p.hand.group) {
            if (card.hasTag(AbstractShadowversePlayer.Enums.MACHINE) && card != this) {
                addToBot(new GainBlockAction(p, this.block));
                break;
            }
        }
        if ((UnlockTracker.betaCardPref.getBoolean(this.cardID, false)))
            addToBot(new SFXAction("Rola_L_EH"));
        else
            addToBot(new SFXAction("Rola_EH"));
        if (p.hasPower(CutthroatPower.POWER_ID)){
            addToBot(new GainEnergyAction(2));
        }
        int count = 0;
        for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
            if (!mo.isDeadOrEscaped())
                count++;
        }
        if (count == 1) {
            addToBot(new DamageAction(AbstractDungeon.getCurrRoom().monsters.getRandomMonster(true), new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.FIRE));
            addToBot(new DamageAction(AbstractDungeon.getCurrRoom().monsters.getRandomMonster(true), new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.FIRE));
        } else {
            addToBot(new DamageAllEnemiesAction(p, DamageInfo.createDamageMatrix(this.damage, true), DamageInfo.DamageType.NORMAL, AbstractGameAction.AttackEffect.FIRE, true));
        }
    }

    @Override
    public void baseUse(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p, this.block));
        for (AbstractCard card : p.hand.group) {
            if (card.hasTag(AbstractShadowversePlayer.Enums.MACHINE) && card != this) {
                addToBot(new GainBlockAction(p, this.block));
                break;
            }
        }
        if ((UnlockTracker.betaCardPref.getBoolean(this.cardID, false)))
            addToBot(new SFXAction("Rola_L"));
        else
            addToBot(new SFXAction("Rola"));
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new Rola();
    }
}
