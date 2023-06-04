 package shadowverse.cards.Bishop.Default;
 


import basemod.abstracts.CustomCard;
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
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.VerticalImpactEffect;
import shadowverse.characters.Bishop;


 public class HeavenlyKnight extends CustomCard {
   public static final String ID = "shadowverse:HeavenlyKnight";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:HeavenlyKnight");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/HeavenlyKnight.png";

   public HeavenlyKnight() {
     super(ID, NAME, IMG_PATH, 3, DESCRIPTION, CardType.ATTACK, Bishop.Enums.COLOR_WHITE, CardRarity.RARE, CardTarget.ENEMY);
     this.baseBlock = 21;
     this.baseMagicNumber = 2;
     this.magicNumber = this.baseMagicNumber;
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeBlock(7);
       upgradeMagicNumber(1);
     } 
   }

   public void applyPowers() {
     AbstractPower dex = AbstractDungeon.player.getPower(DexterityPower.POWER_ID);
     if (dex != null)
       dex.amount *= this.magicNumber;
     super.applyPowers();
     if (dex != null)
       dex.amount /= this.magicNumber;
   }

   public void calculateCardDamage(AbstractMonster mo) {
     AbstractPower dex = AbstractDungeon.player.getPower(DexterityPower.POWER_ID);
     if (dex != null)
       dex.amount *= this.magicNumber;
     super.calculateCardDamage(mo);
     if (dex != null)
       dex.amount /= this.magicNumber;
   }
   
   public void use(AbstractPlayer abstractPlayer, AbstractMonster m) {
     addToBot((AbstractGameAction)new SFXAction("HeavenlyKnight"));
     addToBot((AbstractGameAction)new GainBlockAction(abstractPlayer,this.block));
     if (m != null)
       addToBot((AbstractGameAction)new VFXAction((AbstractGameEffect)new VerticalImpactEffect(m.hb.cX + m.hb.width / 4.0F, m.hb.cY - m.hb.height / 4.0F)));
     addToBot((AbstractGameAction)new DamageAction((AbstractCreature)m, new DamageInfo((AbstractCreature)abstractPlayer, this.block, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
   }
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new HeavenlyKnight();
   }
 }

