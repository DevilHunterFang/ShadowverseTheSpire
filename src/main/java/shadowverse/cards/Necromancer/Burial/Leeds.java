package shadowverse.cards.Necromancer.Burial;


import basemod.abstracts.CustomCard;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageRandomEnemyAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import shadowverse.action.BurialAction;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Necromancer;
import shadowverse.powers.MementoPower;


public class Leeds extends CustomCard {
    public static final String ID = "shadowverse:Leeds";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Leeds");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/Leeds.png";
    private static final Texture LEADER_SKIN_VERSION = ImageMaster.loadImage("img/cards/Leeds_L.png");

    public Leeds() {
        super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.ATTACK, Necromancer.Enums.COLOR_PURPLE, CardRarity.COMMON, CardTarget.SELF);
        this.baseBlock = 5;
        this.baseDamage = 9;
        this.tags.add(AbstractShadowversePlayer.Enums.ACADEMIC);
        this.tags.add(AbstractShadowversePlayer.Enums.LASTWORD);
        this.jokePortrait = new TextureAtlas.AtlasRegion(LEADER_SKIN_VERSION, 0, 0, LEADER_SKIN_VERSION.getWidth(), LEADER_SKIN_VERSION.getHeight());
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBlock(3);
        }
    }

    public void applyPowers() {
        super.applyPowers();
        if (AbstractDungeon.player instanceof AbstractShadowversePlayer) {
            AbstractShadowversePlayer w = (AbstractShadowversePlayer) AbstractDungeon.player;
            int count = w.burialCount;
            this.rawDescription = cardStrings.DESCRIPTION;
            this.rawDescription += cardStrings.EXTENDED_DESCRIPTION[0] + count;
            this.rawDescription += cardStrings.EXTENDED_DESCRIPTION[1];
            initializeDescription();
        }
    }

    @Override
    public void triggerOnExhaust() {
        addToBot(new DrawCardAction(1));
    }

    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        if ((UnlockTracker.betaCardPref.getBoolean(this.cardID, false))) {
            addToBot(new SFXAction("Leeds_L"));
        } else {
            addToBot(new SFXAction("Leeds"));
        }
        addToBot(new GainBlockAction(abstractPlayer, this.block));
        addToBot(new BurialAction(1, new DamageRandomEnemyAction(new DamageInfo(abstractPlayer, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL)));
        int attackAmt = 0;
        for (AbstractCard c : abstractPlayer.hand.group) {
            if (c != this && c.type == CardType.ATTACK)
                attackAmt++;
        }
        if (attackAmt >= 2) {
            if (abstractPlayer.hasPower(MementoPower.POWER_ID))
                addToBot(new DamageRandomEnemyAction(new DamageInfo(abstractPlayer, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        }
        if (AbstractDungeon.player instanceof AbstractShadowversePlayer) {
            AbstractShadowversePlayer w = (AbstractShadowversePlayer) AbstractDungeon.player;
            int count = w.burialCount;
            if (count > 4) {
                addToBot(new GainBlockAction(abstractPlayer, this.block));
            }
        }
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new Leeds();
    }
}
