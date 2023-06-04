 package shadowverse.cards.Necromancer.Natura;


 import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
 import com.megacrit.cardcrawl.actions.common.DrawCardAction;
 import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.HeartBuffEffect;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Necromancer;
import shadowverse.powers.ThothPower;


 public class Thoth
   extends CustomCard {
   public static final String ID = "shadowverse:Thoth";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Thoth");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/Thoth.png";

   public Thoth() {
     super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.POWER, Necromancer.Enums.COLOR_PURPLE, CardRarity.RARE, CardTarget.SELF);
     this.baseMagicNumber = 1;
     this.magicNumber = this.baseMagicNumber;
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeBaseCost(0);
     } 
   }

     public void applyPowers() {
         super.applyPowers();
         int count = 0;
         for (AbstractCard c:AbstractDungeon.actionManager.cardsPlayedThisCombat){
             if (c.hasTag(AbstractShadowversePlayer.Enums.LASTWORD))
                 count++;
         }
         this.rawDescription = cardStrings.DESCRIPTION;
         this.rawDescription += cardStrings.EXTENDED_DESCRIPTION[0] + count;
         this.rawDescription += cardStrings.EXTENDED_DESCRIPTION[1];
         initializeDescription();
     }

   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
     addToBot((AbstractGameAction)new SFXAction("Thoth"));
       addToBot((AbstractGameAction)new DrawCardAction(1));
     int count = 0;
     for (AbstractCard c:AbstractDungeon.actionManager.cardsPlayedThisCombat){
         if (c.hasTag(AbstractShadowversePlayer.Enums.LASTWORD))
             count++;
     }
     if (count>=10){
         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new VFXAction((AbstractGameEffect)new HeartBuffEffect(abstractPlayer.hb.cX, abstractPlayer.hb.cY)));
         addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)abstractPlayer, (AbstractCreature)abstractPlayer, (AbstractPower)new ThothPower((AbstractCreature)abstractPlayer)));
     }
   }
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new Thoth();
   }
 }
