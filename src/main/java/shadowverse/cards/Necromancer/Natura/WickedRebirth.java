 package shadowverse.cards.Necromancer.Natura;


 import basemod.abstracts.CustomCard;
 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
 import com.megacrit.cardcrawl.actions.common.DrawCardAction;
 import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
 import com.megacrit.cardcrawl.cards.AbstractCard;
 import com.megacrit.cardcrawl.characters.AbstractPlayer;
 import com.megacrit.cardcrawl.core.AbstractCreature;
 import com.megacrit.cardcrawl.core.CardCrawlGame;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
 import com.megacrit.cardcrawl.localization.CardStrings;
 import com.megacrit.cardcrawl.monsters.AbstractMonster;
 import com.megacrit.cardcrawl.powers.AbstractPower;
 import com.megacrit.cardcrawl.powers.watcher.VigorPower;
 import shadowverse.action.DestroyAction;
 import shadowverse.characters.AbstractShadowversePlayer;
 import shadowverse.characters.Necromancer;

 import java.util.ArrayList;


 public class WickedRebirth
   extends CustomCard {
   public static final String ID = "shadowverse:WickedRebirth";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:WickedRebirth");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/WickedRebirth.png";

   public WickedRebirth() {
     super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.SKILL, Necromancer.Enums.COLOR_PURPLE, CardRarity.UNCOMMON, CardTarget.SELF);
     this.baseMagicNumber = 2;
     this.magicNumber = this.baseMagicNumber;
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeMagicNumber(1);
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
     int count = 0;
       ArrayList<AbstractCard> rebirth = new ArrayList<>();
     for (AbstractCard c:AbstractDungeon.actionManager.cardsPlayedThisCombat){
         if (c.hasTag(AbstractShadowversePlayer.Enums.LASTWORD)){
             count++;
             if (c.cost<=1&&c.type==CardType.ATTACK){
                 rebirth.add(c);
             }
         }
     }
     if (count>=10){
         addToBot((AbstractGameAction)new DestroyAction(1,(AbstractGameAction)new DrawCardAction(this.magicNumber)));
         addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)abstractPlayer, (AbstractCreature)abstractPlayer, (AbstractPower)new VigorPower((AbstractCreature)abstractPlayer, this.magicNumber), this.magicNumber));
         if (rebirth.size()>0){
             AbstractCard r1 = rebirth.get(AbstractDungeon.cardRandomRng.random(rebirth.size()-1));
             r1.setCostForTurn(0);
             AbstractCard r2 = rebirth.get(AbstractDungeon.cardRandomRng.random(rebirth.size()-1));
             r2.setCostForTurn(0);
             addToBot((AbstractGameAction) new MakeTempCardInHandAction(r1));
             addToBot((AbstractGameAction) new MakeTempCardInHandAction(r2));
         }
     }else if (count>=5){
         addToBot((AbstractGameAction)new DestroyAction(1,(AbstractGameAction)new DrawCardAction(this.magicNumber)));
         addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)abstractPlayer, (AbstractCreature)abstractPlayer, (AbstractPower)new VigorPower((AbstractCreature)abstractPlayer, this.magicNumber), this.magicNumber));
         if (rebirth.size()>0){
             AbstractCard r = rebirth.get(AbstractDungeon.cardRandomRng.random(rebirth.size()-1));
             r.setCostForTurn(0);
             addToBot((AbstractGameAction) new MakeTempCardInHandAction(r));
         }
     }else {
         addToBot((AbstractGameAction)new DestroyAction(1,(AbstractGameAction)new ApplyPowerAction((AbstractCreature)abstractPlayer, (AbstractCreature)abstractPlayer, (AbstractPower)new VigorPower((AbstractCreature)abstractPlayer, this.magicNumber), this.magicNumber)));
         if (rebirth.size()>0){
             AbstractCard r = rebirth.get(AbstractDungeon.cardRandomRng.random(rebirth.size()-1));
             r.setCostForTurn(0);
             addToBot((AbstractGameAction) new MakeTempCardInHandAction(r));
         }
     }
   }
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new WickedRebirth();
   }
 }
