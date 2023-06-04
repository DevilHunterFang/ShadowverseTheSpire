 package shadowverse.cards.Neutral.Temp;
 


import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.unique.ArmamentsAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.InflameEffect;
import shadowverse.cards.Elf.Left.Sekka;
import shadowverse.characters.Elf;


 public class ResolveOfSekka
   extends CustomCard {
   public static final String ID = "shadowverse:ResolveOfSekka";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:ResolveOfSekka");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/ResolveOfSekka.png";

   public ResolveOfSekka() {
     super(ID, NAME, IMG_PATH, 0, DESCRIPTION, CardType.SKILL, Elf.Enums.COLOR_GREEN, CardRarity.SPECIAL, CardTarget.SELF);
     this.baseMagicNumber = 4;
     this.magicNumber = this.baseMagicNumber;
     this.exhaust = true;
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       this.selfRetain = true;
       this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
       initializeDescription();
     } 
   }

   public void applyPowers() {
     super.applyPowers();
     int count = 0;
     for(AbstractCard c: AbstractDungeon.actionManager.cardsPlayedThisCombat){
       if (c.type == CardType.ATTACK&&!(c.hasTag(CardTags.STRIKE))&&c.color==Elf.Enums.COLOR_GREEN){
         count++;
       }
     }
     if(this.upgraded){
       this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
     }else {
       this.rawDescription = cardStrings.DESCRIPTION;
     }
     this.rawDescription += cardStrings.EXTENDED_DESCRIPTION[0] + count;
     this.rawDescription += cardStrings.EXTENDED_DESCRIPTION[1];
     initializeDescription();
   }
   
   public void use(AbstractPlayer p, AbstractMonster m) {
     addToBot((AbstractGameAction)new SFXAction("ResolveOfSekka"));
     addToBot((AbstractGameAction)new ArmamentsAction(true));
     int count = 0;
     for(AbstractCard c: AbstractDungeon.actionManager.cardsPlayedThisCombat){
       if (c.type == CardType.ATTACK&&!(c.hasTag(CardTags.STRIKE))&&c.color==Elf.Enums.COLOR_GREEN){
         count++;
       }
     }
     for (AbstractCard c:p.hand.group){
       if (c instanceof Sekka && count >= 18){
         addToBot((AbstractGameAction)new VFXAction((AbstractCreature)p, (AbstractGameEffect)new InflameEffect((AbstractCreature)p), 1.0F));
         addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)p, (AbstractCreature)p, (AbstractPower)new StrengthPower((AbstractCreature)p, this.magicNumber), this.magicNumber));
         addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)p, (AbstractCreature)p, (AbstractPower)new DexterityPower((AbstractCreature)p, this.magicNumber), this.magicNumber));
         break;
       }
     }
   }
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new ResolveOfSekka();
   }
 }

