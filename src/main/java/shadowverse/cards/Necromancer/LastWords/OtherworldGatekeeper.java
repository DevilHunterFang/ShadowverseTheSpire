 package shadowverse.cards.Necromancer.LastWords;
 


import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.action.BetterAutoPlayCardAction;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Necromancer;

import java.util.ArrayList;
import java.util.Collections;


 public class OtherworldGatekeeper extends CustomCard {
   public static final String ID = "shadowverse:OtherworldGatekeeper";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:OtherworldGatekeeper");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/OtherworldGatekeeper.png";

   public OtherworldGatekeeper() {
     super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.ATTACK, Necromancer.Enums.COLOR_PURPLE, CardRarity.UNCOMMON, CardTarget.ENEMY);
     this.baseDamage = 1;
     this.baseMagicNumber = 1;
     this.magicNumber = this.baseMagicNumber;
     this.tags.add(AbstractShadowversePlayer.Enums.LASTWORD);
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeDamage(2);
       upgradeMagicNumber(1);
     } 
   }

   @Override
   public void triggerOnExhaust() {
     addToBot((AbstractGameAction)new HealAction(AbstractDungeon.player,AbstractDungeon.player,1));
   }

   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
     addToBot((AbstractGameAction)new SFXAction("OtherworldGatekeeper"));
     addToBot((AbstractGameAction)new DamageAction((AbstractCreature)abstractMonster, new DamageInfo((AbstractCreature)abstractPlayer, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
     ArrayList<AbstractCard> list = new ArrayList<>();
     for (AbstractCard card:abstractPlayer.drawPile.group){
       if (card.type == AbstractCard.CardType.ATTACK && card.hasTag(AbstractShadowversePlayer.Enums.LASTWORD) && card.costForTurn<=1){
         list.add(card);
       }
     }
     for (int i = 0; i< this.magicNumber; i++){
       if (list.size()>i){
         Collections.shuffle(list);
         list.get(i).setCostForTurn(0);
         addToBot((AbstractGameAction)new BetterAutoPlayCardAction(list.get(i),abstractPlayer.drawPile));
       }
     }
   }
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new OtherworldGatekeeper();
   }
 }

