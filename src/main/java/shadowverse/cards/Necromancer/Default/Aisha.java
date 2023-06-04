package shadowverse.cards.Necromancer.Default;


import basemod.abstracts.CustomCard;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.ReduceCostForTurnAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.utility.DiscardToHandAction;
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
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import shadowverse.action.InvocationAction;
import shadowverse.action.NecromanceAction;
import shadowverse.characters.Necromancer;
import shadowverse.powers.Cemetery;


public class Aisha
        extends CustomCard {
    public static final String ID = "shadowverse:Aisha";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Aisha");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/Aisha.png";
    private static final Texture LEADER_SKIN_VERSION = ImageMaster.loadImage("img/cards/Aisha_L.png");
    public static boolean dupCheck = true;

    public Aisha() {
        super(ID, NAME, IMG_PATH, 2, DESCRIPTION, CardType.ATTACK, Necromancer.Enums.COLOR_PURPLE, CardRarity.RARE, CardTarget.ENEMY);
        this.baseDamage = 20;
        this.jokePortrait = new TextureAtlas.AtlasRegion(LEADER_SKIN_VERSION, 0, 0, LEADER_SKIN_VERSION.getWidth(), LEADER_SKIN_VERSION.getHeight());
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeDamage(8);
        }
    }

    public void atTurnStart() {
        int playerNecromance = 0;
        if (AbstractDungeon.player.hasPower(Cemetery.POWER_ID)) {
            for (AbstractPower p : AbstractDungeon.player.powers) {
                if (p.ID.equals(Cemetery.POWER_ID))
                    playerNecromance = p.amount;
            }
        }
        if (playerNecromance >= 16 && dupCheck && !AbstractDungeon.player.hand.group.contains(this)) {
            dupCheck = false;
            addToBot((AbstractGameAction) new ReducePowerAction(AbstractDungeon.player, AbstractDungeon.player, Cemetery.POWER_ID, 8));
            if (AbstractDungeon.player.discardPile.contains((AbstractCard) this)) {
                addToBot((AbstractGameAction) new ReduceCostForTurnAction((AbstractCard) this, 9));
                addToBot((AbstractGameAction) new DiscardToHandAction((AbstractCard) this));
            } else if (AbstractDungeon.player.drawPile.contains((AbstractCard) this)) {
                addToBot((AbstractGameAction) new ReduceCostForTurnAction((AbstractCard) this, 9));
                addToBot((AbstractGameAction) new InvocationAction((AbstractCard) this));
            }
        } else if (playerNecromance < 16) {
            dupCheck = true;
        }
    }

    @Override
    public void triggerOnCardPlayed(AbstractCard cardPlayed) {
        dupCheck = true;
    }

    @Override
    public void triggerOnOtherCardPlayed(AbstractCard c) {
        dupCheck = true;
    }

    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        if ((UnlockTracker.betaCardPref.getBoolean(this.cardID, false)))
            addToBot((AbstractGameAction) new SFXAction("Aisha_L"));
        else
            addToBot((AbstractGameAction) new SFXAction("Aisha"));
        addToBot((AbstractGameAction) new DamageAction((AbstractCreature) abstractMonster, new DamageInfo((AbstractCreature) abstractPlayer, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HEAVY));
        addToBot((AbstractGameAction) new NecromanceAction(10, null, (AbstractGameAction) new DamageAction((AbstractCreature) abstractMonster, new DamageInfo((AbstractCreature) abstractPlayer, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HEAVY)));
        dupCheck = true;
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new Aisha();
    }
}

