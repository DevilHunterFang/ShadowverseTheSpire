 package shadowverse.cards.Dragon.Armed;
 


import basemod.abstracts.CustomCard;
import com.badlogic.gdx.Gdx;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.action.ChoiceAction;
import shadowverse.cards.Neutral.Status.EvolutionPoint;
import shadowverse.cards.Neutral.Temp.*;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Dragon;

import java.util.ArrayList;


 public class LavateinnDragon extends CustomCard {
   public static final String ID = "shadowverse:LavateinnDragon";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:LavateinnDragon");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/LavateinnDragon.png";
   private float rotationTimer;
   private int previewIndex;

   public static ArrayList<AbstractCard> returnChoice() {
     ArrayList<AbstractCard> list = new ArrayList<>();
     list.add(new DragonWeapon());
     list.add(new LavateinnDragonAttackForm());
     list.add(new LavateinnDragonDefenseForm());
     list.add(new LavateinnDragonBlastForm());
     return list;
   }

   public LavateinnDragon() {
     super(ID, NAME, IMG_PATH, 2, DESCRIPTION, CardType.ATTACK, Dragon.Enums.COLOR_BROWN, CardRarity.RARE, CardTarget.ENEMY);
     this.baseDamage = 20;
     this.tags.add(AbstractShadowversePlayer.Enums.ARMED);
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeDamage(5);
       this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
       initializeDescription();
     } 
   }

   public void update() {
     super.update();
     if (this.hb.hovered)
       if (this.rotationTimer <= 0.0F) {
         this.rotationTimer = 2.0F;
         this.cardsToPreview = returnChoice().get(previewIndex).makeCopy();
         if (this.previewIndex == returnChoice().size() - 1) {
           this.previewIndex = 0;
         } else {
           this.previewIndex++;
         }
       } else {
         this.rotationTimer -= Gdx.graphics.getDeltaTime();
       }
   }

   public void applyPowers() {
     super.applyPowers();
     int count = 0;
     for (AbstractCard c : AbstractDungeon.actionManager.cardsPlayedThisCombat){
       if (c.type == CardType.ATTACK && c.hasTag(AbstractShadowversePlayer.Enums.ARMED)){
         count++;
       }
     }
     this.rawDescription = cardStrings.DESCRIPTION;
     this.rawDescription += cardStrings.EXTENDED_DESCRIPTION[0] + count;
     this.rawDescription += cardStrings.EXTENDED_DESCRIPTION[1];
     initializeDescription();
   }
 
   
   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
     for (AbstractCard card : abstractPlayer.hand.group){
       if (card instanceof DragonWeapon){
         addToBot(new ExhaustSpecificCardAction(card,abstractPlayer.hand));
         addToBot(new MakeTempCardInHandAction(new EvolutionPoint()));
         break;
       }
     }
     int count = 0;
     for (AbstractCard c : AbstractDungeon.actionManager.cardsPlayedThisCombat){
       if (c.type == CardType.ATTACK && c.hasTag(AbstractShadowversePlayer.Enums.ARMED)){
         count++;
       }
     }
     if (count > 4){
       AbstractCard attack = new LavateinnDragonAttackForm();
       AbstractCard defense = new LavateinnDragonDefenseForm();
       AbstractCard blast = new LavateinnDragonBlastForm();
       addToBot(new ChoiceAction(attack,defense,blast));
     }else {
       addToBot(new DamageAction(abstractMonster,new DamageInfo(abstractPlayer,this.damage,this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HEAVY));
       if (upgraded){
         addToBot(new MakeTempCardInHandAction(new DragonWeapon()));
       }
     }
   }
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new LavateinnDragon();
   }
 }

