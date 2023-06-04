package shadowverse.cards.Nemesis.Condemned;


import basemod.abstracts.CustomCard;
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
import com.megacrit.cardcrawl.powers.BufferPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import shadowverse.Shadowverse;
import shadowverse.cards.Neutral.Status.EvolutionPoint;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Nemesis;
import shadowverse.powers.CutthroatPower;


public class Judith extends CustomCard {
    public static final String ID = "shadowverse:Judith";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Judith");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/Judith.png";

    public Judith() {
        super(ID, NAME, IMG_PATH, 0, DESCRIPTION, CardType.ATTACK, Nemesis.Enums.COLOR_SKY, CardRarity.RARE, CardTarget.ENEMY);
        this.baseDamage = 6;
        this.baseMagicNumber = 1;
        this.magicNumber = this.baseMagicNumber;
        this.tags.add(AbstractShadowversePlayer.Enums.CONDEMNED);
        this.tags.add(AbstractShadowversePlayer.Enums.MACHINE);
        this.tags.add(AbstractShadowversePlayer.Enums.CHESS);
        this.tags.add(AbstractShadowversePlayer.Enums.ARTIFACT);
        this.tags.add(AbstractShadowversePlayer.Enums.FES);
        this.tags.add(AbstractShadowversePlayer.Enums.NATURAL);
        this.tags.add(AbstractShadowversePlayer.Enums.HERO);
        this.tags.add(AbstractShadowversePlayer.Enums.LEVIN);
        this.tags.add(AbstractShadowversePlayer.Enums.MYSTERIA);
        this.tags.add(AbstractShadowversePlayer.Enums.GILDED);
        this.tags.add(AbstractShadowversePlayer.Enums.ACADEMIC);
        this.tags.add(CardTags.STRIKE);
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeDamage(3);
            upgradeMagicNumber(1);
        }
    }

    @Override
    public void update() {
        if (AbstractDungeon.currMapNode != null && (AbstractDungeon.getCurrRoom()).phase == AbstractRoom.RoomPhase.COMBAT
                ) {
            if (Shadowverse.Enhance(5)){
                setCostForTurn(5);
            }
            else if (Shadowverse.Enhance(4)){
                setCostForTurn(4);
            }else if (Shadowverse.Enhance(3)){
                setCostForTurn(3);
            }else if (Shadowverse.Enhance(2)){
                setCostForTurn(2);
            }else if (Shadowverse.Enhance(1)){
                setCostForTurn(1);
            }
        }
        else {
            setCostForTurn(0);
        }
        super.update();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m,new DamageInfo(p,this.damage,this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        if (this.costForTurn == 5) {
            addToBot(new SFXAction("Judith7"));
            addToBot(new ApplyPowerAction(m,p,new VulnerablePower(m,this.magicNumber,false),this.magicNumber));
            addToBot(new DrawCardAction(this.magicNumber));
            addToBot(new HealAction(p,p,2));
            addToBot(new ApplyPowerAction(p,p,new BufferPower(p,1),1));
            addToBot(new DamageAction(m,new DamageInfo(p,this.damage,this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
            addToBot(new MakeTempCardInHandAction(new EvolutionPoint()));
            if (p.hasPower(CutthroatPower.POWER_ID)){
                addToBot(new GainEnergyAction(4));
            }
        }else if (this.costForTurn == 4){
            addToBot(new SFXAction("Judith6"));
            addToBot(new ApplyPowerAction(m,p,new VulnerablePower(m,this.magicNumber,false),this.magicNumber));
            addToBot(new DrawCardAction(this.magicNumber));
            addToBot(new HealAction(p,p,2));
            addToBot(new ApplyPowerAction(p,p,new BufferPower(p,1),1));
            if (p.hasPower(CutthroatPower.POWER_ID)){
                addToBot(new GainEnergyAction(3));
            }
        }else if (this.costForTurn == 3){
            addToBot(new SFXAction("Judith5"));
            addToBot(new ApplyPowerAction(m,p,new VulnerablePower(m,this.magicNumber,false),this.magicNumber));
            addToBot(new DrawCardAction(this.magicNumber));
            addToBot(new HealAction(p,p,2));
            if (p.hasPower(CutthroatPower.POWER_ID)){
                addToBot(new GainEnergyAction(2));
            }
        }else if (this.costForTurn == 2){
            addToBot(new ApplyPowerAction(m,p,new VulnerablePower(m,this.magicNumber,false),this.magicNumber));
            addToBot(new DrawCardAction(this.magicNumber));
            if (p.hasPower(CutthroatPower.POWER_ID)){
                addToBot(new GainEnergyAction(1));
            }
        }else if (this.costForTurn == 1){
            addToBot(new ApplyPowerAction(m,p,new VulnerablePower(m,this.magicNumber,false),this.magicNumber));
        }
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new Judith();
    }
}
