 package shadowverse.cards.Vampire.Default;

 import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainGoldAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import shadowverse.characters.Vampire;
import shadowverse.effect.BetterRainingGoldEffect;
import shadowverse.powers.VolteoPower;

import java.util.ArrayList;


 public class Volteo
   extends CustomCard
 {
   public static final String ID = "shadowverse:Volteo";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Volteo");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/Volteo.png";

   public Volteo() {
     super(ID, NAME, IMG_PATH, 3, DESCRIPTION, CardType.POWER, Vampire.Enums.COLOR_SCARLET, CardRarity.RARE, CardTarget.SELF);
     this.baseMagicNumber = 25;
     this.magicNumber = this.baseMagicNumber;
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeMagicNumber(10);
     } 
   }
 
   
   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
     boolean powerExists = false;
     boolean deckCheck = true;
     ArrayList<String> tmp = new ArrayList<>();
     for (AbstractPower pow : abstractPlayer.powers) {
       if (pow.ID.equals("shadowverse:VolteoPower")) {
         powerExists = true;
         break;
       } 
     } 
     for (AbstractCard c : abstractPlayer.drawPile.group) {
       if (tmp.contains(c.cardID)) {
         deckCheck = false;
         break;
       } 
       tmp.add(c.cardID);
     } 
     if (!powerExists && deckCheck) {
       AbstractDungeon.effectList.add(new BetterRainingGoldEffect(this.magicNumber * 2, true));
       addToBot((AbstractGameAction)new SFXAction("Volteo"));
         addToBot((AbstractGameAction)new GainGoldAction(this.magicNumber));
       addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)abstractPlayer, (AbstractCreature)abstractPlayer, (AbstractPower)new VolteoPower((AbstractCreature)abstractPlayer)));
     } 
   }
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new Volteo();
   }
 }

