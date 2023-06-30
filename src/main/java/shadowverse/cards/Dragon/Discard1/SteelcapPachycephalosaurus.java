 package shadowverse.cards.Dragon.Discard1;
 


import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.unique.GainEnergyIfDiscardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.cards.Neutral.Status.EvolutionPoint;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Dragon;


 public class SteelcapPachycephalosaurus extends CustomCard {
   public static final String ID = "shadowverse:SteelcapPachycephalosaurus";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:SteelcapPachycephalosaurus");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/SteelcapPachycephalosaurus.png";

   public SteelcapPachycephalosaurus() {
     super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.ATTACK, Dragon.Enums.COLOR_BROWN, CardRarity.UNCOMMON, CardTarget.ENEMY);
     this.baseDamage = 8;
     this.tags.add(AbstractShadowversePlayer.Enums.NATURAL);
     this.cardsToPreview = new EvolutionPoint();
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeDamage(4);
     } 
   }

   public void triggerOnGlowCheck() {
     this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
     if (GameActionManager.totalDiscardedThisTurn > 0)
       this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
   }

   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
     addToBot(new DamageAction(abstractMonster, new DamageInfo(abstractPlayer, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
     addToBot(new GainEnergyIfDiscardAction(1));
     if (GameActionManager.totalDiscardedThisTurn > 0){
       addToBot(new MakeTempCardInHandAction(this.cardsToPreview.makeStatEquivalentCopy()));
     }
   }
 
   
   public AbstractCard makeCopy() {
     return new SteelcapPachycephalosaurus();
   }
 }

