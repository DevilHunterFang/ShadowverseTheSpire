 package shadowverse.cards.Dragon.Armed;
 


import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.actions.watcher.WallopAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.cards.Neutral.Temp.DragonWeapon;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Dragon;


 public class LanceLizard extends CustomCard {
   public static final String ID = "shadowverse:LanceLizard";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:LanceLizard");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/LanceLizard.png";

   public LanceLizard() {
     super(ID, NAME, IMG_PATH, 2, DESCRIPTION, CardType.ATTACK, Dragon.Enums.COLOR_BROWN, CardRarity.COMMON, CardTarget.ENEMY);
     this.baseDamage = 15;
     this.tags.add(AbstractShadowversePlayer.Enums.ARMED);
     this.cardsToPreview = new DragonWeapon();
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeDamage(3);
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
     addToBot(new MakeTempCardInHandAction(this.cardsToPreview.makeStatEquivalentCopy()));
     int count = 0;
     for (AbstractCard c : AbstractDungeon.actionManager.cardsPlayedThisCombat){
       if (c.type == CardType.ATTACK && c.hasTag(AbstractShadowversePlayer.Enums.ARMED)){
         count++;
       }
     }
     if (count > 4){
       addToBot(new WallopAction(abstractMonster, new DamageInfo(abstractPlayer, this.damage, this.damageTypeForTurn)));
     }else {
       addToBot(new DamageAction(abstractMonster, new DamageInfo(abstractPlayer, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
     }
   }
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new LanceLizard();
   }
 }

