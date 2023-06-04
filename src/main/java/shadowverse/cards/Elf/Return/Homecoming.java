 package shadowverse.cards.Elf.Return;

 import basemod.abstracts.CustomCard;
 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.actions.common.RollMoveAction;
 import com.megacrit.cardcrawl.actions.utility.DiscardToHandAction;
 import com.megacrit.cardcrawl.cards.AbstractCard;
 import com.megacrit.cardcrawl.characters.AbstractPlayer;
 import com.megacrit.cardcrawl.core.CardCrawlGame;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
 import com.megacrit.cardcrawl.localization.CardStrings;
 import com.megacrit.cardcrawl.monsters.AbstractMonster;
 import shadowverse.characters.Elf;

 import java.util.ArrayList;

 public class Homecoming
   extends CustomCard {
   public static final String ID = "shadowverse:Homecoming";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Homecoming");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/Homecoming.png";

   public Homecoming() {
     super(ID, NAME, IMG_PATH, 2, DESCRIPTION, CardType.SKILL, Elf.Enums.COLOR_GREEN, CardRarity.UNCOMMON, CardTarget.ALL_ENEMY);
     this.exhaust = true;
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
         upgradeName();
         upgradeBaseCost(1);
     } 
   }

 
   
   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
       for (AbstractMonster mo : (AbstractDungeon.getCurrRoom()).monsters.monsters){
           if (!mo.isDeadOrEscaped()){
               rollIntent(mo);
           }
       }
       for (AbstractCard card : abstractPlayer.discardPile.group){
           if (card.type==CardType.ATTACK){
               addToBot((AbstractGameAction)new DiscardToHandAction(card));
           }
       }
   }

   private void rollIntent(AbstractMonster m) {
       ArrayList<AbstractGameAction> actions = new ArrayList<>();
       actions.addAll(AbstractDungeon.actionManager.actions);
       m.takeTurn();
       AbstractDungeon.actionManager.actions.clear();
       AbstractDungeon.actionManager.actions.addAll(actions);
       AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new RollMoveAction(m));
       AbstractDungeon.actionManager.addToBottom(new AbstractGameAction()
       {
           public void update() {
               AbstractDungeon.getMonsters().showIntent();
               this.isDone = true;
           }
       });
   }
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new Homecoming();
   }
 }


