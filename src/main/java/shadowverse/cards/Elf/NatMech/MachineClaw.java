 package shadowverse.cards.Elf.NatMech;


 import basemod.abstracts.CustomCard;
 import com.badlogic.gdx.graphics.Color;
 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.actions.animations.VFXAction;
 import com.megacrit.cardcrawl.actions.common.DamageAction;
 import com.megacrit.cardcrawl.actions.common.GainBlockAction;
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
 import com.megacrit.cardcrawl.vfx.combat.ClawEffect;
 import shadowverse.characters.AbstractShadowversePlayer;
 import shadowverse.characters.Elf;


 public class MachineClaw extends CustomCard {
   public static final String ID = "shadowverse:MachineClaw";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:MachineClaw");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/MachineClaw.png";

   public MachineClaw() {
     super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.ATTACK, Elf.Enums.COLOR_GREEN, CardRarity.COMMON, CardTarget.ENEMY);
     this.baseDamage = 9;
     this.baseBlock = 6;
     this.tags.add(AbstractShadowversePlayer.Enums.MACHINE);
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeDamage(3);
       upgradeBlock(3);
     } 
   }

   
   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
       addToBot((AbstractGameAction)new GainBlockAction(abstractPlayer,this.block));
       boolean mCheck = false;
       int mCount = 0;
       addToBot((AbstractGameAction)new SFXAction("MachineClaw"));
       for (AbstractCard c : AbstractDungeon.actionManager.cardsPlayedThisTurn) {
           if (c.hasTag(AbstractShadowversePlayer.Enums.MACHINE)){
               mCount++;
           }
       }
       if (mCount>1)
           mCheck=true;
       if (mCheck){
           addToBot((AbstractGameAction)new DamageAction((AbstractCreature)abstractMonster, new DamageInfo((AbstractCreature)abstractPlayer, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.NONE));
           addToBot((AbstractGameAction)new VFXAction((AbstractGameEffect)new ClawEffect(abstractMonster.hb.cX, abstractMonster.hb.cY, Color.CYAN, Color.WHITE), 0.1F));
       }
   }
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new MachineClaw();
   }
 }
