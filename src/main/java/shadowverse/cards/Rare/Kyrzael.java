 package shadowverse.cards.Rare;


 import basemod.abstracts.CustomCard;
 import com.badlogic.gdx.graphics.Color;
 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.actions.animations.VFXAction;
 import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
 import com.megacrit.cardcrawl.actions.common.DamageAction;
 import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
 import com.megacrit.cardcrawl.actions.utility.SFXAction;
 import com.megacrit.cardcrawl.cards.AbstractCard;
 import com.megacrit.cardcrawl.cards.DamageInfo;
 import com.megacrit.cardcrawl.characters.AbstractPlayer;
 import com.megacrit.cardcrawl.core.CardCrawlGame;
 import com.megacrit.cardcrawl.localization.CardStrings;
 import com.megacrit.cardcrawl.monsters.AbstractMonster;
 import com.megacrit.cardcrawl.powers.MetallicizePower;
 import com.megacrit.cardcrawl.vfx.BorderFlashEffect;
 import com.megacrit.cardcrawl.vfx.combat.HeartBuffEffect;
 import shadowverse.cards.Uncommon.WardenOfTrigger;
 import shadowverse.characters.AbstractShadowversePlayer;
 import shadowverse.characters.Nemesis;


 public class Kyrzael extends CustomCard {
   public static final String ID = "shadowverse:Kyrzael";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Kyrzael");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/Kyrzael.png";

   public Kyrzael() {
     super(ID, NAME, IMG_PATH, 3, DESCRIPTION, CardType.ATTACK, Nemesis.Enums.COLOR_SKY, CardRarity.RARE, CardTarget.ENEMY);
     this.baseDamage = 25;
     this.tags.add(AbstractShadowversePlayer.Enums.MACHINE);
     this.tags.add(AbstractShadowversePlayer.Enums.CONDEMNED);
     this.cardsToPreview = new WardenOfTrigger();
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeDamage(5);;
     } 
   }

   
   public void use(AbstractPlayer p, AbstractMonster m) {
       addToBot(new SFXAction("Kyrzael"));
       addToBot(new VFXAction(new HeartBuffEffect(p.hb.cX, p.hb.cY)));
       addToBot(new VFXAction(new BorderFlashEffect(Color.BLUE, true)));
       addToBot(new DamageAction(m,new DamageInfo(p,this.damage,this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
       AbstractCard c = this.cardsToPreview.makeStatEquivalentCopy();
       c.setCostForTurn(0);
       addToBot(new MakeTempCardInHandAction(c,2));
       int amt = 4;
       for (AbstractCard ca : p.hand.group){
           if (ca != this && ca.hasTag(AbstractShadowversePlayer.Enums.MACHINE)){
               amt += 2;
           }
       }
       addToBot(new ApplyPowerAction(p,p,new MetallicizePower(p,amt),amt));
   }
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new Kyrzael();
   }
 }
