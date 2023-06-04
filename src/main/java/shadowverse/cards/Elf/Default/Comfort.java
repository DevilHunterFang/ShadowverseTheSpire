 package shadowverse.cards.Elf.Default;

 import basemod.abstracts.CustomCard;
 import com.badlogic.gdx.graphics.Color;
 import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsInHandAction;
 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.actions.animations.VFXAction;
 import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
 import com.megacrit.cardcrawl.actions.unique.FeedAction;
 import com.megacrit.cardcrawl.actions.utility.SFXAction;
 import com.megacrit.cardcrawl.cards.AbstractCard;
 import com.megacrit.cardcrawl.cards.DamageInfo;
 import com.megacrit.cardcrawl.characters.AbstractPlayer;
 import com.megacrit.cardcrawl.core.AbstractCreature;
 import com.megacrit.cardcrawl.core.CardCrawlGame;
 import com.megacrit.cardcrawl.core.Settings;
 import com.megacrit.cardcrawl.localization.CardStrings;
 import com.megacrit.cardcrawl.monsters.AbstractMonster;
 import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
 import com.megacrit.cardcrawl.vfx.combat.BiteEffect;
 import shadowverse.characters.Elf;


 public class Comfort
   extends CustomCard
 {
   public static final String ID = "shadowverse:Comfort";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Comfort");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/Comfort.png";

   public Comfort() {
     super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.SKILL, Elf.Enums.COLOR_GREEN, CardRarity.UNCOMMON, CardTarget.ENEMY);
     this.baseMagicNumber = 2;
     this.magicNumber = this.baseMagicNumber;
     this.baseDamage = 6;
     this.exhaust = true;
   }

   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeMagicNumber(1);
       upgradeDamage(2);
     } 
   }
 
   
   public void use(AbstractPlayer p, AbstractMonster m) {
       addToBot(new SFXAction("Comfort"));
       addToBot((AbstractGameAction)new SelectCardsInHandAction(1,TEXT[0],false,false, card -> {
           return true;
       }, abstractCards ->{
           for (AbstractCard c:abstractCards){
               addToBot((AbstractGameAction)new ExhaustSpecificCardAction(c,p.hand));
               addToBot((AbstractGameAction)new VFXAction((AbstractGameEffect)new BiteEffect(m.hb.cX, m.hb.cY - 40.0F * Settings.scale, Color.GOLD.cpy()), 0.3F));
               addToBot((AbstractGameAction)new FeedAction((AbstractCreature)m, new DamageInfo((AbstractCreature)p, this.damage, this.damageTypeForTurn), this.magicNumber));
           }
       } ));
   }
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new Comfort();
   }
 }

