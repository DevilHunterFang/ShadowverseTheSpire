 package shadowverse.cards.Elf.Default;


 import basemod.abstracts.CustomCard;
 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.actions.animations.VFXAction;
 import com.megacrit.cardcrawl.actions.common.DamageAction;
 import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
 import com.megacrit.cardcrawl.cards.AbstractCard;
 import com.megacrit.cardcrawl.cards.DamageInfo;
 import com.megacrit.cardcrawl.characters.AbstractPlayer;
 import com.megacrit.cardcrawl.core.AbstractCreature;
 import com.megacrit.cardcrawl.core.CardCrawlGame;
 import com.megacrit.cardcrawl.localization.CardStrings;
 import com.megacrit.cardcrawl.monsters.AbstractMonster;
 import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
 import com.megacrit.cardcrawl.vfx.combat.ThrowDaggerEffect;
 import shadowverse.action.BounceAction;
 import shadowverse.characters.Elf;


 public class AirboundBarrage extends CustomCard {
   public static final String ID = "shadowverse:AirboundBarrage";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:AirboundBarrage");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/AirboundBarrage.png";

   public AirboundBarrage() {
     super(ID, NAME, IMG_PATH, 0, DESCRIPTION, CardType.SKILL, Elf.Enums.COLOR_GREEN, CardRarity.COMMON, CardTarget.ENEMY);
     this.baseDamage = 3;
     this.exhaust = true;
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeDamage(2);
       this.exhaust = false;
       this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
       initializeDescription();
     } 
   }

     public boolean canUse(AbstractPlayer p, AbstractMonster m) {
         boolean canUse = super.canUse(p, m);
         if (!canUse)
             return false;
         boolean hasAttack = false;
         for (AbstractCard c : p.discardPile.group) {
             if (c.type == AbstractCard.CardType.ATTACK || c.type == CardType.POWER)
                 hasAttack = true;
         }
         if (!hasAttack) {
             this.cantUseMessage = cardStrings.EXTENDED_DESCRIPTION[0];
             canUse = false;
         }
         return canUse;
     }
   
   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
     addToBot(new BounceAction(1));
     addToTop(new VFXAction((AbstractGameEffect)new ThrowDaggerEffect(abstractMonster.hb.cX, abstractMonster.hb.cY),0.3F));
     if (abstractMonster.hasPower("Flight")){
         addToBot(new RemoveSpecificPowerAction(abstractMonster,abstractPlayer,"Flight"));
     }
     addToBot(new DamageAction(abstractMonster, new DamageInfo(abstractPlayer, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.NONE));
   }
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new AirboundBarrage();
   }
 }
