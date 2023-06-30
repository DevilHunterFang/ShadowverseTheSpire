 package shadowverse.cards.Dragon.Default;
 


import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Dragon;


 public class ScorchedEarthTyrant extends CustomCard {
   public static final String ID = "shadowverse:ScorchedEarthTyrant";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:ScorchedEarthTyrant");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/ScorchedEarthTyrant.png";

   public ScorchedEarthTyrant() {
     super(ID, NAME, IMG_PATH, 2, DESCRIPTION, CardType.ATTACK, Dragon.Enums.COLOR_BROWN, CardRarity.UNCOMMON, CardTarget.ALL);
     this.baseBlock = 9;
     this.baseDamage = 8;
     this.isMultiDamage = true;
     this.tags.add(AbstractShadowversePlayer.Enums.CONDEMNED);
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeBlock(3);
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
     addToBot(new SFXAction("ScorchedEarthTyrant"));
     addToBot(new GainBlockAction(abstractPlayer,this.block));
     addToBot(new DamageAllEnemiesAction(abstractPlayer, this.multiDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.FIRE));
     if (!AbstractDungeon.actionManager.cardsPlayedThisCombat.isEmpty() && (AbstractDungeon.actionManager.cardsPlayedThisCombat
             .get(AbstractDungeon.actionManager.cardsPlayedThisCombat
                     .size() - 1)).hasTag(AbstractShadowversePlayer.Enums.CONDEMNED)){
       addToBot(new DamageAllEnemiesAction(abstractPlayer, this.multiDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.FIRE));
     }
   }
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new ScorchedEarthTyrant();
   }
 }

