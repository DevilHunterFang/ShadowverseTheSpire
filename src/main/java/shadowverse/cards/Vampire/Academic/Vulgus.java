 package shadowverse.cards.Vampire.Academic;


 import basemod.abstracts.CustomCard;
 import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
 import com.megacrit.cardcrawl.actions.common.GainBlockAction;
 import com.megacrit.cardcrawl.actions.utility.SFXAction;
 import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
 import com.megacrit.cardcrawl.cards.AbstractCard;
 import com.megacrit.cardcrawl.characters.AbstractPlayer;
 import com.megacrit.cardcrawl.core.CardCrawlGame;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
 import com.megacrit.cardcrawl.localization.CardStrings;
 import com.megacrit.cardcrawl.monsters.AbstractMonster;
 import com.megacrit.cardcrawl.powers.PenNibPower;
 import com.megacrit.cardcrawl.stances.AbstractStance;
 import shadowverse.characters.AbstractShadowversePlayer;
 import shadowverse.characters.Vampire;
 import shadowverse.powers.AzazelPower;
 import shadowverse.powers.EpitaphPower;
 import shadowverse.stance.Vengeance;


 public class Vulgus extends CustomCard {
   public static final String ID = "shadowverse:Vulgus";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Vulgus");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/Vulgus.png";

   public Vulgus() {
     super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.ATTACK, Vampire.Enums.COLOR_SCARLET, CardRarity.RARE, CardTarget.SELF);
     this.baseBlock = 9;
     this.tags.add(AbstractShadowversePlayer.Enums.ACADEMIC);
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeBlock(3);
     } 
   }
 
   
   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
     addToBot(new SFXAction("Vulgus"));
       int half = AbstractDungeon.player.maxHealth/2;
       int current = AbstractDungeon.player.currentHealth;
       if (current>half&&current>1){
           AbstractDungeon.player.currentHealth = half;
           AbstractDungeon.player.update();
       }
       if (!abstractPlayer.hasPower(AzazelPower.POWER_ID)){
           addToBot( new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new AzazelPower( AbstractDungeon.player,half)));
       }
       if (!AbstractDungeon.player.stance.ID.equals(Vengeance.STANCE_ID)){
           AbstractDungeon.actionManager.addToBottom( new ChangeStanceAction((AbstractStance) new Vengeance()));
       }
     if (abstractPlayer.stance.ID.equals(Vengeance.STANCE_ID)||abstractPlayer.hasPower(EpitaphPower.POWER_ID)){
         addToBot(new ApplyPowerAction(abstractPlayer,abstractPlayer,new PenNibPower(abstractPlayer,1),1));
     }
     addToBot(new GainBlockAction(abstractPlayer,this.block));
   }
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new Vulgus();
   }
 }

