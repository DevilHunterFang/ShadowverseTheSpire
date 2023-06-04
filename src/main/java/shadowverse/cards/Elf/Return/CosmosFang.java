 package shadowverse.cards.Elf.Return;
 


import basemod.abstracts.CustomCard;
import com.evacipated.cardcrawl.mod.stslib.actions.common.MoveCardsAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.RollMoveAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.BlurPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import shadowverse.characters.Elf;
import shadowverse.powers.CosmosFangPower;

import java.util.ArrayList;


 public class CosmosFang extends CustomCard {
   public static final String ID = "shadowverse:CosmosFang";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:CosmosFang");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/CosmosFang.png";

   public CosmosFang() {
     super(ID, NAME, IMG_PATH, 3, DESCRIPTION, CardType.ATTACK, Elf.Enums.COLOR_GREEN, CardRarity.RARE, CardTarget.ALL);
     this.baseBlock = 18;
     this.exhaust = true;
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeBlock(4);
       this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
       initializeDescription();
     } 
   }
 
   
   public void use(AbstractPlayer p, AbstractMonster m) {
     addToBot(new SFXAction("CosmosFang"));
     addToBot(new GainBlockAction(p,this.block));
     addToBot(new ApplyPowerAction(p,p,new BlurPower(p,1),1));
     addToBot(new ApplyPowerAction(p,p,new CosmosFangPower(p)));
     addToBot(new MoveCardsAction(p.drawPile,p.discardPile, card -> {
       return card.color==Elf.Enums.COLOR_GREEN&&card.type==CardType.ATTACK&&!card.hasTag(CardTags.STRIKE);
     },11-p.hand.group.size(),abstractCards -> {
       for (AbstractCard ca:abstractCards){
         addToBot(new GainBlockAction(p,3));
         addToBot(new ApplyPowerAction(p,p,new StrengthPower(p,1),1));
       }
     }));
     if (this.upgraded){
       for (AbstractMonster mo : (AbstractDungeon.getCurrRoom()).monsters.monsters){
         if (!mo.isDeadOrEscaped()){
           rollIntent(mo);
           addToBot(new GainBlockAction(p,3));
           addToBot(new ApplyPowerAction(p,p,new StrengthPower(p,1),1));
         }
       }
     }
   }

   private void rollIntent(AbstractMonster m) {
     ArrayList<AbstractGameAction> actions = new ArrayList<>();
     actions.addAll(AbstractDungeon.actionManager.actions);
     m.takeTurn();
     AbstractDungeon.actionManager.actions.clear();
     AbstractDungeon.actionManager.actions.addAll(actions);
     AbstractDungeon.actionManager.addToBottom(new RollMoveAction(m));
     AbstractDungeon.actionManager.addToBottom(new AbstractGameAction()
     {
       public void update() {
         AbstractDungeon.getMonsters().showIntent();
         this.isDone = true;
       }
     });
   }

   public AbstractCard makeCopy() {
     return (AbstractCard)new CosmosFang();
   }
 }

