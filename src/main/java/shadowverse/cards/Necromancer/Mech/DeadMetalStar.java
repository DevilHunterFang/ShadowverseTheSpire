package shadowverse.cards.Necromancer.Mech;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.vfx.SpotlightPlayerEffect;
import shadowverse.Shadowverse;
import shadowverse.action.ReanimateAction;
import shadowverse.cards.Necromancer.Burial.DemonicProcession;
import shadowverse.cards.Necromancer.Burial.SpiritCurator;
import shadowverse.cards.Necromancer.Ghosts.Ferry;
import shadowverse.cards.Necromancer.Burial.TheLovers;
import shadowverse.cards.Neutral.Temp.InstantPotion;
import shadowverse.cards.Neutral.Temp.ProductMachine;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Necromancer;


public class DeadMetalStar
        extends CustomCard {
    public static final String ID = "shadowverse:DeadMetalStar";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:DeadMetalStar");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/DeadMetalStar.png";

    public DeadMetalStar() {
        super(ID, NAME, IMG_PATH, 4, DESCRIPTION, CardType.ATTACK, Necromancer.Enums.COLOR_PURPLE, CardRarity.RARE, CardTarget.ENEMY);
        this.baseDamage = 25;
        this.baseBlock = 25;
        this.tags.add(AbstractShadowversePlayer.Enums.ACCELERATE);
        this.tags.add(AbstractShadowversePlayer.Enums.LASTWORD);
        this.tags.add(AbstractShadowversePlayer.Enums.MACHINE);
        this.cardsToPreview = (AbstractCard) new ProductMachine();
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeDamage(5);
            upgradeBlock(5);
        }
    }

    @Override
    public void update() {
        if (AbstractDungeon.currMapNode != null && (AbstractDungeon.getCurrRoom()).phase == AbstractRoom.RoomPhase.COMBAT &&
                Shadowverse.Accelerate(this)) {
            setCostForTurn(1);
            this.type = CardType.SKILL;
        } else {
            if (this.type == CardType.SKILL) {
                setCostForTurn(4);
                this.type = CardType.ATTACK;
            }
        }
        super.update();
    }

    public void triggerOnGlowCheck() {
        if (Shadowverse.Accelerate(this)) {
            this.glowColor = AbstractCard.GREEN_BORDER_GLOW_COLOR.cpy();
        } else {
            this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        }
    }


    public void triggerOnOtherCardPlayed(AbstractCard c) {
        if (c instanceof DemonicProcession || c instanceof TheLovers || c instanceof HungrySlash || c instanceof SpiritCurator || c instanceof Ferry || c instanceof InstantPotion) {
            this.type = CardType.ATTACK;
            this.resetAttributes();
        }
    }

    @Override
    public void triggerOnExhaust() {
        addToBot((AbstractGameAction) new ReanimateAction(3));
    }

    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        AbstractCard c = this.cardsToPreview.makeStatEquivalentCopy();
        if (Shadowverse.Accelerate((AbstractCard) this) && this.type == CardType.SKILL) {
            addToBot((AbstractGameAction) new SFXAction("DeadMetalStar_Acc"));
            addToBot((AbstractGameAction) new MakeTempCardInHandAction(c, 3));
        } else {
            AbstractDungeon.effectsQueue.add(new SpotlightPlayerEffect());
            addToBot((AbstractGameAction) new SFXAction("DeadMetalStar"));
            addToBot((AbstractGameAction) new DamageAction((AbstractCreature) abstractMonster, new DamageInfo((AbstractCreature) abstractPlayer, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
            addToBot((AbstractGameAction) new GainBlockAction(abstractPlayer, this.block));
        }
    }



    public AbstractCard makeCopy() {
        return (AbstractCard) new DeadMetalStar();
    }
}

