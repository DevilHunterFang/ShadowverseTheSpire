 package shadowverse.cards.Elf.Fairy;


 import basemod.abstracts.CustomCard;
 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
 import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
 import com.megacrit.cardcrawl.actions.utility.SFXAction;
 import com.megacrit.cardcrawl.cards.AbstractCard;
 import com.megacrit.cardcrawl.characters.AbstractPlayer;
 import com.megacrit.cardcrawl.core.AbstractCreature;
 import com.megacrit.cardcrawl.core.CardCrawlGame;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
 import com.megacrit.cardcrawl.localization.CardStrings;
 import com.megacrit.cardcrawl.monsters.AbstractMonster;
 import com.megacrit.cardcrawl.powers.AbstractPower;
 import com.megacrit.cardcrawl.vfx.stance.StanceAuraEffect;
 import shadowverse.cards.Neutral.Temp.Fairy;
 import shadowverse.cards.Neutral.Temp.Whisp;
 import shadowverse.characters.Elf;
 import shadowverse.powers.AriaPower;


 public class Aria extends CustomCard {
   public static final String ID = "shadowverse:Aria";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Aria");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/Aria.png";

   public Aria() {
     super(ID, NAME, IMG_PATH, 2, DESCRIPTION, CardType.POWER, Elf.Enums.COLOR_GREEN, CardRarity.RARE, CardTarget.SELF);
     this.cardsToPreview = (AbstractCard)new Whisp();
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeBaseCost(1);
     } 
   }
 
   
   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
       AbstractDungeon.effectsQueue.add(new StanceAuraEffect("Divinity"));
       addToBot((AbstractGameAction)new SFXAction("Aria"));
     AbstractCard c = this.cardsToPreview.makeStatEquivalentCopy();
     AbstractCard a = (AbstractCard)new Fairy().makeStatEquivalentCopy();
     addToBot((AbstractGameAction)new MakeTempCardInHandAction(c,1));
       addToBot((AbstractGameAction)new MakeTempCardInHandAction(a,1));
       if (!abstractPlayer.hasPower("shadowverse:AriaPower"))
     addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)abstractPlayer, (AbstractCreature)abstractPlayer, (AbstractPower)new AriaPower((AbstractCreature)abstractPlayer)));
   }
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new Aria();
   }
 }

