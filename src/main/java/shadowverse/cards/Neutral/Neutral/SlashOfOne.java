 package shadowverse.cards.Neutral.Neutral;
 


import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.VerticalImpactEffect;
import shadowverse.cards.AbstractNeutralCard;


 public class SlashOfOne extends AbstractNeutralCard {
   public static final String ID = "shadowverse:SlashOfOne";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:SlashOfOne");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/SlashOfOne.png";
   public boolean check = true;
   
   public SlashOfOne() {
     super(ID, NAME, IMG_PATH, 2, DESCRIPTION, CardType.SKILL, CardColor.COLORLESS, CardRarity.UNCOMMON, CardTarget.ENEMY);
     this.baseDamage = 18;
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeDamage(8);
     } 
   }
 
   @Override
   public void triggerOnOtherCardPlayed(AbstractCard c){
       if (c.type == CardType.ATTACK && check){
           setCostForTurn(1);
           check = false;
           applyPowers();
       }else if (c.type == CardType.ATTACK && !check){
           resetAttributes();
           applyPowers();
       }
   }


     public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
       addToBot((AbstractGameAction)new SFXAction("SlashOfOne"));
       check = true;
         if (abstractMonster != null)
             addToBot((AbstractGameAction)new VFXAction((AbstractGameEffect)new VerticalImpactEffect(abstractMonster.hb.cX + abstractMonster.hb.width / 4.0F, abstractMonster.hb.cY - abstractMonster.hb.height / 4.0F)));
         addToBot((AbstractGameAction)new DamageAction((AbstractCreature)abstractMonster, new DamageInfo((AbstractCreature)abstractPlayer, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HEAVY));
   }
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new SlashOfOne();
   }
 }


