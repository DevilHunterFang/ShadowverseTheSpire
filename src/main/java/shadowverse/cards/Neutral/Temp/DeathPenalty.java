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
 import com.megacrit.cardcrawl.core.Settings;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
 import com.megacrit.cardcrawl.localization.CardStrings;
 import com.megacrit.cardcrawl.monsters.AbstractMonster;
 import com.megacrit.cardcrawl.powers.AbstractPower;
 import com.megacrit.cardcrawl.powers.GainStrengthPower;
 import com.megacrit.cardcrawl.powers.StrengthPower;
 import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
 import com.megacrit.cardcrawl.vfx.combat.ShockWaveEffect;
 import shadowverse.characters.Nemesis;
 import shadowverse.powers.DeathPenaltyPower;


 public class DeathPenalty
   extends CustomCard
 {
   public static final String ID = "shadowverse:DeathPenalty";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:DeathPenalty");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/DeathPenalty.png";

   public DeathPenalty() {
     super(ID, NAME, IMG_PATH, 0, DESCRIPTION, CardType.SKILL, Nemesis.Enums.COLOR_SKY, CardRarity.SPECIAL, CardTarget.ALL_ENEMY);
     this.baseMagicNumber = 10;
     this.magicNumber = this.baseMagicNumber;
     this.exhaust = true;
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeMagicNumber(2);
     } 
   }
 
   
   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
       addToBot((AbstractGameAction)new SFXAction("IC4"));
       addToBot((AbstractGameAction)new VFXAction((AbstractCreature)abstractPlayer, (AbstractGameEffect)new ShockWaveEffect(abstractPlayer.hb.cX, this.hb.cY, Settings.GREEN_TEXT_COLOR, ShockWaveEffect.ShockWaveType.CHAOTIC), 1.0F));
       AbstractMonster m = AbstractDungeon.getMonsters().getRandomMonster(null, true, AbstractDungeon.cardRandomRng);
       if (m != null){
           addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)m, (AbstractCreature)abstractPlayer, (AbstractPower)new DeathPenaltyPower((AbstractCreature)m)));
           addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)m, (AbstractCreature)abstractPlayer, (AbstractPower)new StrengthPower((AbstractCreature)m, -this.magicNumber), -this.magicNumber, true, AbstractGameAction.AttackEffect.NONE));
           if (!m.hasPower("Artifact")) {
               addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)m, (AbstractCreature)abstractPlayer, (AbstractPower)new GainStrengthPower((AbstractCreature)m, this.magicNumber), this.magicNumber, true, AbstractGameAction.AttackEffect.NONE));
           }
       }
   }
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new DeathPenalty();
   }
 }

