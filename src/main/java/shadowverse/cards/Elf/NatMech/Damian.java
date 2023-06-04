 package shadowverse.cards.Elf.NatMech;


 import basemod.abstracts.CustomCard;
 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.actions.animations.VFXAction;
 import com.megacrit.cardcrawl.actions.common.DamageAction;
 import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
 import com.megacrit.cardcrawl.actions.utility.SFXAction;
 import com.megacrit.cardcrawl.cards.AbstractCard;
 import com.megacrit.cardcrawl.cards.DamageInfo;
 import com.megacrit.cardcrawl.characters.AbstractPlayer;
 import com.megacrit.cardcrawl.core.AbstractCreature;
 import com.megacrit.cardcrawl.core.CardCrawlGame;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
 import com.megacrit.cardcrawl.localization.CardStrings;
 import com.megacrit.cardcrawl.monsters.AbstractMonster;
 import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
 import com.megacrit.cardcrawl.vfx.combat.CleaveEffect;
 import shadowverse.characters.AbstractShadowversePlayer;
 import shadowverse.characters.Elf;


 public class Damian extends CustomCard {
   public static final String ID = "shadowverse:Damian";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Damian");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/Damian.png";

   public Damian() {
     super(ID, NAME, IMG_PATH, 2, DESCRIPTION, CardType.ATTACK, Elf.Enums.COLOR_GREEN, CardRarity.RARE, CardTarget.ENEMY);
     this.baseDamage = 20;
     this.baseMagicNumber = 7;
     this.magicNumber = this.baseMagicNumber;
     this.tags.add(AbstractShadowversePlayer.Enums.MACHINE);
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeDamage(5);
       upgradeMagicNumber(2);
     } 
   }

   
   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
       boolean mCheck = false;
       int mCount = 0;
       addToBot((AbstractGameAction)new SFXAction("Damian"));
       for (AbstractCard c : AbstractDungeon.actionManager.cardsPlayedThisTurn) {
           if (c.hasTag(AbstractShadowversePlayer.Enums.MACHINE)&&c!=this){
               mCheck = true;
           }
           if (c.hasTag(AbstractShadowversePlayer.Enums.MACHINE)&&c.type == CardType.ATTACK){
               mCount++;
           }
       }
       if (mCheck){
           addToBot((AbstractGameAction)new DamageAction((AbstractCreature)abstractMonster, new DamageInfo((AbstractCreature)abstractPlayer, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
           addToBot((AbstractGameAction)new DamageAllEnemiesAction((AbstractCreature)abstractPlayer, DamageInfo.createDamageMatrix(this.magicNumber*mCount, true), DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.NONE, true));
           addToBot((AbstractGameAction)new SFXAction("ATTACK_HEAVY"));
           addToBot((AbstractGameAction)new VFXAction((AbstractCreature)abstractPlayer, (AbstractGameEffect)new CleaveEffect(), 0.1F));
       }
   }
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new Damian();
   }
 }
