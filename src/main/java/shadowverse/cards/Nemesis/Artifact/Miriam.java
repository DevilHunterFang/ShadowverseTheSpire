package shadowverse.cards.Nemesis.Artifact;


import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import shadowverse.Shadowverse;
import shadowverse.action.DestroyAction;
import shadowverse.cards.Neutral.Temp.ParadigmShift;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Nemesis;
import shadowverse.powers.MiriamPower;
import shadowverse.stance.Resonance;


public class Miriam extends CustomCard {
    public static final String ID = "shadowverse:Miriam";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Miriam");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/Miriam.png";

    public Miriam() {
        super(ID, NAME, IMG_PATH, 2, DESCRIPTION, CardType.ATTACK, Nemesis.Enums.COLOR_SKY, CardRarity.UNCOMMON, CardTarget.SELF);
        this.baseBlock = 12;
        this.baseMagicNumber = 4;
        this.magicNumber = this.baseMagicNumber;
        this.tags.add(AbstractShadowversePlayer.Enums.ACCELERATE);
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBlock(3);
            upgradeMagicNumber(2);
        }
    }

    @Override
    public void update() {
        if (AbstractDungeon.currMapNode != null && (AbstractDungeon.getCurrRoom()).phase == AbstractRoom.RoomPhase.COMBAT&&
                Shadowverse.Accelerate(this)){
            setCostForTurn(0);
            this.type = CardType.SKILL;
        }else {
            if (this.type==CardType.SKILL){
                setCostForTurn(2);
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


    public void use(AbstractPlayer p, AbstractMonster abstractMonster) {
        if (Shadowverse.Accelerate((AbstractCard) this) && this.type == CardType.SKILL) {
            addToBot((AbstractGameAction) new SFXAction("Miriam_Acc"));
            addToBot((AbstractGameAction)new DestroyAction(1,(AbstractGameAction)new MakeTempCardInHandAction((AbstractCard)new ParadigmShift())));
        } else {
            addToBot((AbstractGameAction) new SFXAction("Miriam"));
            addToBot((AbstractGameAction) new GainBlockAction(p, p, this.block));
            addToBot((AbstractGameAction)new ApplyPowerAction(p,p,(AbstractPower)new MiriamPower(p,this.magicNumber),this.magicNumber));
            if (p.stance.ID.equals(Resonance.STANCE_ID)){
                addToBot((AbstractGameAction)new GainEnergyAction(1));
            }
        }
    }

    public AbstractCard makeCopy() {
        return (AbstractCard) new Miriam();
    }
}
