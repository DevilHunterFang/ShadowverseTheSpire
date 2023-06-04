 package shadowverse.cards.Neutral.Temp;

 import basemod.abstracts.CustomCard;
 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.actions.animations.VFXAction;
 import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
 import com.megacrit.cardcrawl.actions.utility.SFXAction;
 import com.megacrit.cardcrawl.cards.AbstractCard;
 import com.megacrit.cardcrawl.characters.AbstractPlayer;
 import com.megacrit.cardcrawl.core.AbstractCreature;
 import com.megacrit.cardcrawl.core.CardCrawlGame;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
 import com.megacrit.cardcrawl.localization.CardStrings;
 import com.megacrit.cardcrawl.monsters.AbstractMonster;
 import com.megacrit.cardcrawl.powers.AbstractPower;
 import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
 import com.megacrit.cardcrawl.vfx.stance.DivinityParticleEffect;
 import shadowverse.characters.Witchcraft;
 import shadowverse.powers.TheFoolPower2;

 public class Ignorant
   extends CustomCard {
   public static final String ID = "shadowverse:Ignorant";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Ignorant");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/Ignorant.png";

   public Ignorant() {
     super(ID, NAME, IMG_PATH, 2, DESCRIPTION, CardType.SKILL, Witchcraft.Enums.COLOR_BLUE, CardRarity.SPECIAL, CardTarget.SELF);
     this.baseMagicNumber = 1;
     this.magicNumber = this.baseMagicNumber;
     this.exhaust = true;
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeBaseCost(1);
     } 
   }
 
   
   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
     addToBot((AbstractGameAction)new SFXAction("Ignorant"));
     addToBot((AbstractGameAction)new VFXAction((AbstractGameEffect)new DivinityParticleEffect()));
     addToTop((AbstractGameAction)new ApplyPowerAction((AbstractCreature)AbstractDungeon.player, (AbstractCreature)AbstractDungeon.player, (AbstractPower)new TheFoolPower2((AbstractCreature)AbstractDungeon.player, this.magicNumber), this.magicNumber));
   }
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new Ignorant();
   }
 }


