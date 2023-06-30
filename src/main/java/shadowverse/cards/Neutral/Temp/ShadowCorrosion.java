 package shadowverse.cards.Neutral.Temp;

 import basemod.abstracts.CustomCard;
 import com.badlogic.gdx.graphics.Color;
 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.actions.animations.VFXAction;
 import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
 import com.megacrit.cardcrawl.actions.common.DamageAction;
 import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
 import com.megacrit.cardcrawl.cards.DamageInfo;
 import com.megacrit.cardcrawl.characters.AbstractPlayer;
 import com.megacrit.cardcrawl.core.AbstractCreature;
 import com.megacrit.cardcrawl.core.CardCrawlGame;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
 import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
 import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
 import com.megacrit.cardcrawl.vfx.combat.ClawEffect;
 import shadowverse.characters.AbstractShadowversePlayer;
 import shadowverse.characters.Dragon;
 import shadowverse.powers.ShadowCorrosionPower;

 public class ShadowCorrosion
   extends CustomCard {
   public static final String ID = "shadowverse:ShadowCorrosion";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:ShadowCorrosion");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/ShadowCorrosion.png";

   public ShadowCorrosion() {
     super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.SKILL, Dragon.Enums.COLOR_BROWN, CardRarity.SPECIAL, CardTarget.ENEMY);
     this.baseMagicNumber = 0;
     this.magicNumber = this.baseMagicNumber;
     this.exhaust = true;
     this.tags.add(AbstractShadowversePlayer.Enums.NATURAL);
   }
 

 
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeBaseCost(0);
     } 
   }
 
   
   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
     addToBot(new SFXAction("ShadowCorrosion"));
     int treeAmt = 0;
     for (AbstractCard c: AbstractDungeon.actionManager.cardsPlayedThisCombat){
       if (c instanceof NaterranGreatTree)
         treeAmt++;
     }
     if (!abstractMonster.hasPower(ShadowCorrosionPower.POWER_ID)){
       addToBot(new ApplyPowerAction(abstractMonster,abstractMonster,new ShadowCorrosionPower(abstractMonster,treeAmt*4),treeAmt*4));
     }else {
       addToBot(new DamageAction(abstractMonster, new DamageInfo(abstractPlayer, abstractMonster.getPower(ShadowCorrosionPower.POWER_ID).amount, this.damageTypeForTurn), AbstractGameAction.AttackEffect.NONE));
       addToBot(new VFXAction(new ClawEffect(abstractMonster.hb.cX, abstractMonster.hb.cY, Color.BLACK, Color.PURPLE), 0.6F));
     }
   }
 
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new ShadowCorrosion();
   }
 }

