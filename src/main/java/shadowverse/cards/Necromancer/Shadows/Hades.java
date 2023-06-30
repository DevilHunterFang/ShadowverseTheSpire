package shadowverse.cards.Necromancer.Shadows;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import shadowverse.Shadowverse;
import shadowverse.cards.Necromancer.Burial.DemonicProcession;
import shadowverse.cards.Necromancer.Mech.HungrySlash;
import shadowverse.cards.Necromancer.Burial.SpiritCurator;
import shadowverse.cards.Necromancer.Ghosts.Ferry;
import shadowverse.cards.Necromancer.Burial.TheLovers;
import shadowverse.cards.Neutral.Neutral.Path;
import shadowverse.cards.Neutral.Temp.InstantPotion;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Necromancer;
import shadowverse.powers.Cemetery;
import shadowverse.powers.PathPower;

public class Hades extends CustomCard {
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Hades");
    public static final String ID = "shadowverse:Hades";
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION; public static final String IMG_PATH = "img/cards/Hades.png";

    public Hades() {
        super(ID, NAME, IMG_PATH, 5, DESCRIPTION, CardType.ATTACK, Necromancer.Enums.COLOR_PURPLE, CardRarity.RARE, CardTarget.ALL_ENEMY);
        this.baseDamage = 16;
        this.isMultiDamage = true;
        this.baseMagicNumber = 6;
        this.magicNumber = this.baseMagicNumber;
        this.baseBlock = 40;
        this.tags.add(AbstractShadowversePlayer.Enums.LASTWORD);
        this.tags.add(AbstractShadowversePlayer.Enums.ACCELERATE);
        this.exhaust = true;
        this.cardsToPreview = (AbstractCard)new Path();
    }

    @Override
    public void upgrade() {
        if(!this.upgraded){
            upgradeName();
            upgradeDamage(6);
            upgradeBlock(10);
        }
    }

    @Override
    public void triggerOnExhaust() {
        addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)AbstractDungeon.player, (AbstractCreature)AbstractDungeon.player, (AbstractPower)new PathPower((AbstractCreature)AbstractDungeon.player, 42), 42));
        if (this.type==CardType.ATTACK)
            addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)AbstractDungeon.player,(AbstractCreature)AbstractDungeon.player,(AbstractPower)new Cemetery((AbstractCreature)AbstractDungeon.player,this.magicNumber),this.magicNumber));
    }

    public void triggerOnOtherCardPlayed(AbstractCard c) {
        if (c instanceof DemonicProcession ||c instanceof TheLovers ||c instanceof HungrySlash ||c instanceof SpiritCurator ||c instanceof Ferry ||c instanceof InstantPotion){
            this.type = CardType.ATTACK;
            this.resetAttributes();
        }
    }

    @Override
    public void update() {
        if (AbstractDungeon.currMapNode != null && (AbstractDungeon.getCurrRoom()).phase == AbstractRoom.RoomPhase.COMBAT&&
                Shadowverse.Accelerate(this)){
            setCostForTurn(2);
            this.type = CardType.SKILL;
        }else {
            if (this.type==CardType.SKILL){
                setCostForTurn(5);
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


    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        if (Shadowverse.Accelerate((AbstractCard)this) && this.type == CardType.SKILL) {
            addToBot((AbstractGameAction)new SFXAction("Hades_Acc"));
            addToBot((AbstractGameAction)new DamageAllEnemiesAction((AbstractCreature)abstractPlayer, this.multiDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.FIRE));
        }else {
            addToBot((AbstractGameAction)new SFXAction("Hades"));
            addToBot((AbstractGameAction)new GainBlockAction(abstractPlayer,abstractPlayer,this.block));
        }
    }


    public AbstractCard makeCopy() {
        return new Hades();
    }
}
