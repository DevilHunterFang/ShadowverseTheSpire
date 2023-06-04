 package shadowverse.cards.Neutral.Temp;
 


import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Elf;


 public class Packing
   extends CustomCard
 {
   public static final String ID = "shadowverse:Packing";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Packing");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/Packing.png";

   public Packing() {
     super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.SKILL, Elf.Enums.COLOR_GREEN, CardRarity.SPECIAL, CardTarget.ENEMY);
     this.baseMagicNumber = 3;
     this.magicNumber = this.baseMagicNumber;
     this.exhaust = true;
     this.tags.add(AbstractShadowversePlayer.Enums.NATURAL);
     this.selfRetain = true;
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeMagicNumber(1);
     } 
   }
 
   
   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
     addToBot((AbstractGameAction)new SFXAction("Packing"));
       AbstractDungeon.actionManager.cardsPlayedThisTurn.add(this);
       AbstractDungeon.actionManager.cardsPlayedThisTurn.add(this);
     if(abstractMonster.hasPower("Artifact")){
         addToBot((AbstractGameAction) new RemoveSpecificPowerAction(abstractMonster,abstractPlayer,"Artifact"));
     }else {
         for (AbstractPower pow : abstractMonster.powers){
             if (pow.type == AbstractPower.PowerType.BUFF && pow.ID!="Invincible" &&pow.ID!="Mode Shift"&&pow.ID!="Split"&&pow.ID!="Unawakened"&&pow.ID!="Life Link"&&pow.ID!="Fading"&&pow.ID!="Stasis"&&pow.ID!="Minion"&&pow.ID!="Shifting"&&pow.ID!="shadowverse:chushouHealPower"){
                 addToBot((AbstractGameAction) new RemoveSpecificPowerAction(pow.owner,abstractPlayer,pow.ID));
                 break;
             }
     }
     }
       addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)abstractMonster, (AbstractCreature)abstractPlayer, (AbstractPower)new WeakPower((AbstractCreature)abstractMonster, this.magicNumber, false), this.magicNumber, true, AbstractGameAction.AttackEffect.NONE));
       addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)abstractMonster, (AbstractCreature)abstractPlayer, (AbstractPower)new VulnerablePower((AbstractCreature)abstractMonster, this.magicNumber, false), this.magicNumber, true, AbstractGameAction.AttackEffect.NONE));
   }
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new Packing();
   }
 }

