 package shadowverse.cards.Witch.Earth1;
 
 import basemod.abstracts.CustomCard;
 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
 import com.megacrit.cardcrawl.actions.common.AttackDamageRandomEnemyAction;
 import com.megacrit.cardcrawl.actions.common.GainBlockAction;
 import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
 import com.megacrit.cardcrawl.cards.AbstractCard;
 import com.megacrit.cardcrawl.characters.AbstractPlayer;
 import com.megacrit.cardcrawl.core.AbstractCreature;
 import com.megacrit.cardcrawl.core.CardCrawlGame;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
 import com.megacrit.cardcrawl.localization.CardStrings;
 import com.megacrit.cardcrawl.monsters.AbstractMonster;
 import com.megacrit.cardcrawl.powers.AbstractPower;
 import com.megacrit.cardcrawl.rooms.AbstractRoom;

 import shadowverse.Shadowverse;
 import shadowverse.cards.Neutral.Temp.VeridicRitual;
 import shadowverse.characters.AbstractShadowversePlayer;
 import shadowverse.characters.Witchcraft;
 import shadowverse.powers.EarthEssence;
 import shadowverse.powers.NextTurnEarthEssence;
 
 public class OrichalcumGolem
   extends CustomCard
 {
   public static final String ID = "shadowverse:OrichalcumGolem";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:OrichalcumGolem");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/OrichalcumGolem.png";

   public OrichalcumGolem() {
     super(ID, NAME, IMG_PATH, 2, DESCRIPTION, CardType.ATTACK, Witchcraft.Enums.COLOR_BLUE, CardRarity.RARE, CardTarget.SELF);
     this.baseBlock = 18;
     this.tags.add(AbstractShadowversePlayer.Enums.EARTH_RITE);
     this.tags.add(AbstractShadowversePlayer.Enums.ACCELERATE);
     this.baseDamage = 7;
     this.baseMagicNumber = 2;
     this.magicNumber = this.baseMagicNumber;
     this.cardsToPreview = (AbstractCard)new VeridicRitual();
   }
 
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeBlock(2);
       this.cardsToPreview.upgrade();
       upgradeDamage(3);
       this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
       initializeDescription();
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
                 setCostForTurn(2);
                 this.type = CardType.ATTACK;
             }
         }
         super.update();
     }

     public void rand(int[] l, int n, int m)
     {
         int i;
         for(i=0;i<n-1;i++)
         {
             l[i] = AbstractDungeon.cardRandomRng.random(2 * m / (n - i));
             m -= l[i];
         }
         l[i] = m;
     }

   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
     AbstractCard c = this.cardsToPreview.makeStatEquivalentCopy();
     if (Shadowverse.Accelerate((AbstractCard)this) && this.type == CardType.SKILL) {
       boolean powerExists = false;
       AbstractPower earthEssence = null;
       for (AbstractPower pow : abstractPlayer.powers) {
         if (pow.ID.equals("shadowverse:EarthEssence")) {
           earthEssence = pow;
           powerExists = true;
           break;
         } 
       } 
       if (powerExists) {
           if (abstractPlayer instanceof  AbstractShadowversePlayer){
               ((AbstractShadowversePlayer)abstractPlayer).earthCount += earthEssence.amount;
           }
           int [] l = new int[3];
           rand(l,3,earthEssence.amount);
           int x = l[0];
           int y = l[1];
           int z = l[2];
         if (this.upgraded) {
           addToBot((AbstractGameAction)new GainBlockAction((AbstractCreature)abstractPlayer, (AbstractCreature)abstractPlayer, x * 8));
         } else {
           addToBot((AbstractGameAction)new GainBlockAction((AbstractCreature)abstractPlayer, (AbstractCreature)abstractPlayer, x * 6));
         } 
         addToBot((AbstractGameAction)new MakeTempCardInHandAction(c, y));
         for (int i = 0; i < z; i++) {
           addToBot((AbstractGameAction)new AttackDamageRandomEnemyAction((AbstractCard)this, AbstractGameAction.AttackEffect.LIGHTNING));
         }
         addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)abstractPlayer, (AbstractCreature)abstractPlayer, (AbstractPower)new EarthEssence((AbstractCreature)abstractPlayer, -earthEssence.amount), -earthEssence.amount));
       } 
     } else {
       addToBot((AbstractGameAction)new GainBlockAction((AbstractCreature)abstractPlayer, (AbstractCreature)abstractPlayer, this.block));
       addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)abstractPlayer, (AbstractCreature)abstractPlayer, (AbstractPower)new NextTurnEarthEssence((AbstractCreature)abstractPlayer, this.magicNumber), this.magicNumber));
     } 
   }
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new OrichalcumGolem();
   }
 }

