package shadowverse.cards.Nemesis.Resonance;


import basemod.abstracts.CustomCard;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.SweepingBeamEffect;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Nemesis;


public class MagnaZero extends CustomCard {
    public static final String ID = "shadowverse:MagnaZero";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:MagnaZero");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/MagnaZero.png";
    private static final Texture LEADER_SKIN_VERSION = ImageMaster.loadImage("img/cards/MagnaZero_L.png");

    public MagnaZero() {
        super(ID, NAME, IMG_PATH, 3, DESCRIPTION, CardType.ATTACK, Nemesis.Enums.COLOR_SKY, CardRarity.RARE, CardTarget.ALL_ENEMY);
        this.baseDamage = 16;
        this.baseMagicNumber = 46;
        this.magicNumber = this.baseMagicNumber;
        this.tags.add(AbstractShadowversePlayer.Enums.MACHINE);
        this.jokePortrait = new TextureAtlas.AtlasRegion(LEADER_SKIN_VERSION, 0, 0, LEADER_SKIN_VERSION.getWidth(), LEADER_SKIN_VERSION.getHeight());
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeDamage(4);
            upgradeMagicNumber(4);
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


    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        if ((UnlockTracker.betaCardPref.getBoolean(this.cardID, false)))
            addToBot((AbstractGameAction) new SFXAction("MagnaZero_L"));
        else
            addToBot((AbstractGameAction) new SFXAction("MagnaZero"));
        addToBot((AbstractGameAction) new VFXAction((AbstractCreature) abstractPlayer, (AbstractGameEffect) new SweepingBeamEffect(AbstractDungeon.player.hb.cX, AbstractDungeon.player.hb.cY, AbstractDungeon.player.flipHorizontal), 0.4F));
        int count = 0;
        if (AbstractDungeon.player instanceof AbstractShadowversePlayer)
            count = ((AbstractShadowversePlayer) AbstractDungeon.player).resonanceCount;
        if (count >= 20) {
            addToBot((AbstractGameAction) new DamageAllEnemiesAction((AbstractCreature) abstractPlayer, DamageInfo.createDamageMatrix((this.damage + this.magicNumber) * 2, true), DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.NONE, true));
        } else if (count >= 10) {
            addToBot((AbstractGameAction) new DamageAllEnemiesAction((AbstractCreature) abstractPlayer, DamageInfo.createDamageMatrix(this.damage + this.magicNumber, true), DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.NONE, true));
        } else {
            addToBot((AbstractGameAction) new DamageAllEnemiesAction((AbstractCreature) abstractPlayer, DamageInfo.createDamageMatrix(this.damage, true), DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.NONE, true));
        }
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new MagnaZero();
    }
}
