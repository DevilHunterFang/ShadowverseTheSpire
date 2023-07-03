package shadowverse.cards.Dragon.Buff;


import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageRandomEnemyAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import shadowverse.Shadowverse;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Dragon;


public class Gunbein
        extends CustomCard {
    public static final String ID = "shadowverse:Gunbein";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Gunbein");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/Gunbein.png";
    private boolean trigger;

    public Gunbein() {
        super(ID, NAME, IMG_PATH, 2, DESCRIPTION, CardType.ATTACK, Dragon.Enums.COLOR_BROWN, CardRarity.COMMON, CardTarget.SELF);
        this.baseBlock = 10;
        this.tags.add(AbstractShadowversePlayer.Enums.ARMED);
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBlock(3);
        }
    }

    @Override
    public void update() {
        if (AbstractDungeon.currMapNode != null && (AbstractDungeon.getCurrRoom()).phase == AbstractRoom.RoomPhase.COMBAT &&
                Shadowverse.Accelerate(this)) {
            setCostForTurn(0);
            this.type = CardType.SKILL;
        } else {
            if (this.type == CardType.SKILL) {
                setCostForTurn(2);
                this.type = CardType.ATTACK;
            }
        }
        super.update();
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        if (Shadowverse.Accelerate(this) && this.type == CardType.SKILL) {
            addToBot(new SFXAction("Gunbein_Acc"));
            if (!trigger) {
                trigger = true;
                addToBot(new ApplyPowerAction(abstractPlayer, abstractPlayer, new DexterityPower(abstractPlayer, 1), 1));
            }
            addToBot(new DamageRandomEnemyAction(new DamageInfo(abstractPlayer, 4, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.FIRE));
        } else {
            addToBot(new SFXAction("Gunbein"));
            addToBot(new GainBlockAction(abstractPlayer, this.block));
            if (abstractPlayer.hasPower(DexterityPower.POWER_ID) && abstractPlayer.getPower(DexterityPower.POWER_ID).amount >= 2) {
                addToBot(new GainBlockAction(abstractPlayer, this.block));
            }
        }
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new Gunbein();
    }
}

