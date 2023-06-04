 package shadowverse.cards.Neutral.Temp;
 


import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.BlurPower;
import com.megacrit.cardcrawl.powers.DoubleDamagePower;
import shadowverse.action.NecromanceAction;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Necromancer;


 public class CorpseKingCommand
   extends CustomCard {
   public static final String ID = "shadowverse:CorpseKingCommand";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:CorpseKingCommand");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/CorpseKingCommand.png";

   public CorpseKingCommand() {
     super(ID, NAME, IMG_PATH, 0, DESCRIPTION, CardType.SKILL, Necromancer.Enums.COLOR_PURPLE, CardRarity.SPECIAL, CardTarget.SELF);
     this.exhaust = true;
     this.tags.add(AbstractShadowversePlayer.Enums.ACADEMIC);
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       this.selfRetain = true;
       this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
       initializeDescription();
     } 
   }

   
   public void use(AbstractPlayer p, AbstractMonster m) {
     addToBot(new SFXAction("CorpseKingCommand"));
     addToBot(new ApplyPowerAction(p,p,new BlurPower(p,1),1));
     addToBot(new NecromanceAction(10,null,new ApplyPowerAction(p,p,new DoubleDamagePower(p,1,false),1)));
   }
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new CorpseKingCommand();
   }
 }

