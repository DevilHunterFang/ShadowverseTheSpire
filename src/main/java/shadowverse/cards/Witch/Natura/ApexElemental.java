 package shadowverse.cards.Witch.Natura;
 
 import basemod.abstracts.CustomCard;
 import com.evacipated.cardcrawl.mod.stslib.actions.defect.EvokeSpecificOrbAction;
 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.actions.animations.VFXAction;
 import com.megacrit.cardcrawl.actions.common.DamageAction;
 import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
 import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
 import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
 import com.megacrit.cardcrawl.cards.AbstractCard;
 import com.megacrit.cardcrawl.cards.DamageInfo;
 import com.megacrit.cardcrawl.characters.AbstractPlayer;
 import com.megacrit.cardcrawl.core.AbstractCreature;
 import com.megacrit.cardcrawl.core.CardCrawlGame;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
 import com.megacrit.cardcrawl.localization.CardStrings;
 import com.megacrit.cardcrawl.monsters.AbstractMonster;
 import com.megacrit.cardcrawl.orbs.AbstractOrb;
 import com.megacrit.cardcrawl.powers.AbstractPower;
 import com.megacrit.cardcrawl.rooms.AbstractRoom;
 import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
 import com.megacrit.cardcrawl.vfx.combat.CleaveEffect;

 import shadowverse.Shadowverse;
 import shadowverse.cards.Neutral.Temp.NaterranGreatTree;
 import shadowverse.characters.AbstractShadowversePlayer;
 import shadowverse.characters.Witchcraft;
 import shadowverse.orbs.AmuletOrb;

 public class ApexElemental
   extends CustomCard {
   public static final String ID = "shadowverse:ApexElemental";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:ApexElemental");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/ApexElemental.png";
   public ApexElemental() {
     super(ID, NAME, IMG_PATH, 3, DESCRIPTION, CardType.ATTACK, Witchcraft.Enums.COLOR_BLUE, CardRarity.RARE, CardTarget.ENEMY);
     this.baseDamage = 15;
     this.cardsToPreview = (AbstractCard)new NaterranGreatTree();
     this.tags.add(AbstractShadowversePlayer.Enums.NATURAL);
     this.tags.add(AbstractShadowversePlayer.Enums.ACCELERATE);
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeDamage(5);
     } 
   }

     @Override
     public void update() {
         if (AbstractDungeon.currMapNode != null && (AbstractDungeon.getCurrRoom()).phase == AbstractRoom.RoomPhase.COMBAT&&
                 Shadowverse.Accelerate(this)){
             setCostForTurn(0);
             this.type = CardType.SKILL;
         }else {
             if (this.type==CardType.SKILL){
                 setCostForTurn(3);
                 this.type = CardType.ATTACK;
             }
         }
         super.update();
     }

     public void triggerOnGlowCheck() {
         if (Shadowverse.Accelerate(this)) {
             this.glowColor = AbstractCard.GREEN_BORDER_GLOW_COLOR.cpy();
         } else {
             this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
         }
     }
   
   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
     boolean powerExists = false;
     AbstractCard c = this.cardsToPreview.makeStatEquivalentCopy();
     for (AbstractPower pow : abstractPlayer.powers) {
       if (pow.ID.equals("shadowverse:NaterranTree")) {
         powerExists = true;
         break;
       } 
     } 
     if (Shadowverse.Accelerate((AbstractCard)this) && this.type == CardType.SKILL) {
       if (powerExists) {
           for (AbstractOrb o:abstractPlayer.orbs){
               if (o instanceof AmuletOrb){
                   if (((AmuletOrb) o).amulet instanceof NaterranGreatTree){
                       addToBot((AbstractGameAction) new EvokeSpecificOrbAction(o));
                   }
               }
           }
         addToBot((AbstractGameAction)new DamageAction((AbstractCreature)abstractMonster, new DamageInfo((AbstractCreature)abstractPlayer, 9, this.damageTypeForTurn), AbstractGameAction.AttackEffect.FIRE));
         addToBot((AbstractGameAction)new RemoveSpecificPowerAction((AbstractCreature)abstractPlayer, (AbstractCreature)abstractPlayer, "shadowverse:NaterranTree"));
         addToBot((AbstractGameAction)new MakeTempCardInHandAction(c, 1));
       } else {
         addToBot((AbstractGameAction)new DamageAction((AbstractCreature)abstractMonster, new DamageInfo((AbstractCreature)abstractPlayer, 6, this.damageTypeForTurn), AbstractGameAction.AttackEffect.FIRE));
       }
     
     } else if (powerExists) {
         for (AbstractOrb o:abstractPlayer.orbs){
             if (o instanceof AmuletOrb){
                 if (((AmuletOrb) o).amulet instanceof NaterranGreatTree){
                     addToBot((AbstractGameAction) new EvokeSpecificOrbAction(o));
                 }
             }
         }
       addToBot((AbstractGameAction)new RemoveSpecificPowerAction((AbstractCreature)abstractPlayer, (AbstractCreature)abstractPlayer, "shadowverse:NaterranTree"));
       addToBot((AbstractGameAction)new VFXAction((AbstractCreature)abstractPlayer, (AbstractGameEffect)new CleaveEffect(), 0.1F));
       addToBot((AbstractGameAction)new DamageAllEnemiesAction((AbstractCreature)abstractPlayer, DamageInfo.createDamageMatrix(this.damage * 2, true), this.damageTypeForTurn, AbstractGameAction.AttackEffect.FIRE, true));
       addToBot((AbstractGameAction)new MakeTempCardInHandAction(c, 1));
     } else {
       addToBot((AbstractGameAction)new VFXAction((AbstractCreature)abstractPlayer, (AbstractGameEffect)new CleaveEffect(), 0.1F));
       addToBot((AbstractGameAction)new DamageAllEnemiesAction((AbstractCreature)abstractPlayer, DamageInfo.createDamageMatrix(this.damage, true), this.damageTypeForTurn, AbstractGameAction.AttackEffect.FIRE, true));
     } 
   }
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new ApexElemental();
   }
 }

