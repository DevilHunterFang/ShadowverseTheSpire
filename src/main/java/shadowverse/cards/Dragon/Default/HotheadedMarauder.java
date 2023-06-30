 package shadowverse.cards.Dragon.Default;
 


import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Dragon;
import shadowverse.powers.OverflowPower;


 public class HotheadedMarauder extends CustomCard {
   public static final String ID = "shadowverse:HotheadedMarauder";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:HotheadedMarauder");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/HotheadedMarauder.png";

   public HotheadedMarauder() {
     super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.ATTACK, Dragon.Enums.COLOR_BROWN, CardRarity.COMMON, CardTarget.ENEMY);
     this.baseDamage = 9;
     this.tags.add(AbstractShadowversePlayer.Enums.CONDEMNED);
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeDamage(3);
     } 
   }

   public void triggerOnGlowCheck() {
     if (!AbstractDungeon.actionManager.cardsPlayedThisCombat.isEmpty() && (AbstractDungeon.actionManager.cardsPlayedThisCombat
             .get(AbstractDungeon.actionManager.cardsPlayedThisCombat
                     .size() - 1)).hasTag(AbstractShadowversePlayer.Enums.CONDEMNED)) {
       this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
     } else {
       this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
     }
   }
   
   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
     addToBot(new SFXAction("HotheadedMarauder"));
     addToBot(new DamageAction(abstractMonster, new DamageInfo(abstractPlayer, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
     if (!AbstractDungeon.actionManager.cardsPlayedThisCombat.isEmpty() && (AbstractDungeon.actionManager.cardsPlayedThisCombat
             .get(AbstractDungeon.actionManager.cardsPlayedThisCombat
                     .size() - 1)).hasTag(AbstractShadowversePlayer.Enums.CONDEMNED)){
       addToBot(new ApplyPowerAction(abstractPlayer,abstractPlayer,new OverflowPower(abstractPlayer,1)));
     }
   }
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new HotheadedMarauder();
   }
 }

