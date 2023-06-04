 package shadowverse.cards.Vampire.Vengeance;


 import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
 import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
 import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
 import com.megacrit.cardcrawl.stances.AbstractStance;
 import shadowverse.characters.Vampire;
import shadowverse.powers.AzazelPower;
import shadowverse.powers.EpitaphPower;
import shadowverse.stance.Vengeance;


 public class Azazel extends CustomCard {
   public static final String ID = "shadowverse:Azazel";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Azazel");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/Azazel.png";

   public Azazel() {
     super(ID, NAME, IMG_PATH, 2, DESCRIPTION, CardType.ATTACK, Vampire.Enums.COLOR_SCARLET, CardRarity.UNCOMMON, CardTarget.ENEMY);
     this.baseDamage = 6;
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeDamage(3);
     } 
   }
 
   
   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
     addToBot((AbstractGameAction)new SFXAction("Azazel"));
       int half = AbstractDungeon.player.maxHealth/2;
       int current = AbstractDungeon.player.currentHealth;
       if (current>half&&current>1){
           AbstractDungeon.player.currentHealth = half;
           AbstractDungeon.player.update();
       }
       if (!abstractPlayer.hasPower(AzazelPower.POWER_ID)){
           addToBot((AbstractGameAction) new ApplyPowerAction((AbstractCreature)AbstractDungeon.player, (AbstractCreature)AbstractDungeon.player, (AbstractPower) new AzazelPower((AbstractCreature) AbstractDungeon.player,half)));
       }
       if (!AbstractDungeon.player.stance.ID.equals(Vengeance.STANCE_ID)){
           AbstractDungeon.actionManager.addToBottom((AbstractGameAction) new ChangeStanceAction((AbstractStance) new Vengeance()));
       }
     if (abstractPlayer.stance.ID.equals(Vengeance.STANCE_ID)||abstractPlayer.hasPower(EpitaphPower.POWER_ID)){
       addToBot((AbstractGameAction)new DamageAction((AbstractCreature)abstractMonster, new DamageInfo((AbstractCreature)abstractPlayer, this.damage*3, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HEAVY));
     }else
       addToBot((AbstractGameAction)new DamageAction((AbstractCreature)abstractMonster, new DamageInfo((AbstractCreature)abstractPlayer, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HEAVY));
   }
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new Azazel();
   }
 }

