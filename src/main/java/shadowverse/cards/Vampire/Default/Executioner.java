 package shadowverse.cards.Vampire.Default;

 import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.characters.Vampire;

import java.util.ArrayList;


 public class Executioner
   extends CustomCard
 {
   public static final String ID = "shadowverse:Executioner";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Executioner");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/Executioner.png";

   public Executioner() {
     super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.ATTACK, Vampire.Enums.COLOR_SCARLET, CardRarity.COMMON, CardTarget.ENEMY);
     this.baseDamage = 5;
     this.baseBlock = 5;
     this.baseMagicNumber = 5;
     this.magicNumber = this.baseMagicNumber;
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeDamage(2);
       upgradeBlock(2);
       upgradeMagicNumber(3);
     } 
   }
 
   
   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
     int type = 0;
     ArrayList<String> tmp = new ArrayList<>();
     for (AbstractCard c : abstractPlayer.drawPile.group) {
       if (!tmp.contains(c.cardID)) {
           tmp.add(c.cardID);
           type++;
       }
     } 
     if (type>=10) {
         addToBot((AbstractGameAction) new DamageAction((AbstractCreature) abstractMonster, new DamageInfo((AbstractCreature) abstractPlayer, this.damage+this.magicNumber, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_VERTICAL));
         addToBot((AbstractGameAction)new GainBlockAction(abstractPlayer,this.block+this.magicNumber));
     }else {
         addToBot((AbstractGameAction) new DamageAction((AbstractCreature) abstractMonster, new DamageInfo((AbstractCreature) abstractPlayer, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_VERTICAL));
         addToBot((AbstractGameAction)new GainBlockAction(abstractPlayer,this.block));
     }
   }
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new Executioner();
   }
 }

