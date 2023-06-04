 package shadowverse.cards.Elf.Long;


 import basemod.abstracts.CustomCard;
 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.actions.animations.VFXAction;
 import com.megacrit.cardcrawl.actions.common.DamageAction;
 import com.megacrit.cardcrawl.cards.AbstractCard;
 import com.megacrit.cardcrawl.cards.DamageInfo;
 import com.megacrit.cardcrawl.characters.AbstractPlayer;
 import com.megacrit.cardcrawl.core.AbstractCreature;
 import com.megacrit.cardcrawl.core.CardCrawlGame;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
 import com.megacrit.cardcrawl.localization.CardStrings;
 import com.megacrit.cardcrawl.monsters.AbstractMonster;
 import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
 import com.megacrit.cardcrawl.vfx.combat.VerticalImpactEffect;
 import shadowverse.characters.Elf;


 public class SkyDevouring extends CustomCard {
   public static final String ID = "shadowverse:SkyDevouring";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:SkyDevouring");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/SkyDevouring.png";

   public SkyDevouring() {
     super(ID, NAME, IMG_PATH, 4, DESCRIPTION, CardType.ATTACK, Elf.Enums.COLOR_GREEN, CardRarity.RARE, CardTarget.ENEMY);
     this.baseDamage = 42;
     this.baseMagicNumber = 49;
     this.magicNumber = this.baseMagicNumber;
     this.selfRetain = true;
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeDamage(10);
     } 
   }

   @Override
   public void onRetained() {
       int count = 0;
       for (AbstractCard c : AbstractDungeon.actionManager.cardsPlayedThisTurn){
           count++;
       }
       if (count>=7){
           upgradeDamage(this.magicNumber);
           this.superFlash();
       }
   }
   
   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
       addToBot((AbstractGameAction)new DamageAction((AbstractCreature)abstractMonster, new DamageInfo((AbstractCreature)abstractPlayer, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
       addToBot((AbstractGameAction)new VFXAction((AbstractGameEffect)new VerticalImpactEffect(abstractMonster.hb.cX + abstractMonster.hb.width / 4.0F, abstractMonster.hb.cY - abstractMonster.hb.height / 4.0F)));
   }
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new SkyDevouring();
   }
 }

