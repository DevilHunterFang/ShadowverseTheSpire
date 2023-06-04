 package shadowverse.cards.Necromancer.Necromancy;
 


import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.WhirlwindEffect;
import shadowverse.action.NecromanceAction;
import shadowverse.characters.Necromancer;


 public class FoulTempest extends CustomCard {
   public static final String ID = "shadowverse:FoulTempest";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:FoulTempest");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/FoulTempest.png";

   public FoulTempest() {
     super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.SKILL, Necromancer.Enums.COLOR_PURPLE, CardRarity.UNCOMMON, CardTarget.ALL_ENEMY);
     this.baseDamage = 5;
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeDamage(3);
     } 
   }
 
   
   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
     addToBot((AbstractGameAction)new VFXAction((AbstractGameEffect)new WhirlwindEffect(), 0.0F));
     addToBot((AbstractGameAction)new NecromanceAction(6,
             (AbstractGameAction)new DamageAllEnemiesAction((AbstractCreature)abstractPlayer, DamageInfo.createDamageMatrix(this.damage, true), DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.NONE, true),
             (AbstractGameAction)new DamageAllEnemiesAction((AbstractCreature)abstractPlayer, DamageInfo.createDamageMatrix(this.damage*3, true), DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.NONE, true)
             ));
   }
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new FoulTempest();
   }
 }

