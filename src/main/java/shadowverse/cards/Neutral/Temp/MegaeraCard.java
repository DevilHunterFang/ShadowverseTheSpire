 package shadowverse.cards.Neutral.Temp;


 import basemod.abstracts.CustomCard;
 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.actions.animations.VFXAction;
 import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
 import com.megacrit.cardcrawl.actions.common.GainBlockAction;
 import com.megacrit.cardcrawl.actions.utility.SFXAction;
 import com.megacrit.cardcrawl.cards.AbstractCard;
 import com.megacrit.cardcrawl.characters.AbstractPlayer;
 import com.megacrit.cardcrawl.core.AbstractCreature;
 import com.megacrit.cardcrawl.core.CardCrawlGame;
 import com.megacrit.cardcrawl.localization.CardStrings;
 import com.megacrit.cardcrawl.monsters.AbstractMonster;
 import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
 import com.megacrit.cardcrawl.vfx.combat.MindblastEffect;
 import shadowverse.characters.AbstractShadowversePlayer;
 import shadowverse.characters.Nemesis;


 public class MegaeraCard
   extends CustomCard {
   public static final String ID = "shadowverse:MegaeraCard";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:MegaeraCard");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/MegaeraCard.png";

   public MegaeraCard() {
     super(ID, NAME, IMG_PATH, 3, DESCRIPTION, CardType.ATTACK, Nemesis.Enums.COLOR_SKY, CardRarity.SPECIAL, CardTarget.ALL);
     this.baseDamage = 24;
     this.baseBlock = 11;
     this.tags.add(AbstractShadowversePlayer.Enums.MACHINE);
     this.isMultiDamage = true;
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeDamage(6);
       upgradeBlock(5);
     } 
   }
 
   
   public void use(AbstractPlayer p, AbstractMonster abstractMonster) {
     addToBot((AbstractGameAction)new SFXAction("MegaeraCard"));
     addToBot((AbstractGameAction)new GainBlockAction(p,this.block));
     addToBot((AbstractGameAction)new SFXAction("ATTACK_HEAVY"));
     addToBot((AbstractGameAction)new VFXAction((AbstractCreature)p, (AbstractGameEffect)new MindblastEffect(p.dialogX, p.dialogY, p.flipHorizontal), 0.1F));
     addToBot((AbstractGameAction)new DamageAllEnemiesAction((AbstractCreature)p, this.multiDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.NONE));
   }
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new MegaeraCard();
   }
 }

