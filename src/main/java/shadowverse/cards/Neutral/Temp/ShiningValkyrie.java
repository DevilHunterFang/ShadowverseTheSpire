 package shadowverse.cards.Neutral.Temp;


 import basemod.abstracts.CustomCard;
 import com.badlogic.gdx.graphics.Color;
 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.actions.animations.VFXAction;
 import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
 import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
 import com.megacrit.cardcrawl.actions.utility.SFXAction;
 import com.megacrit.cardcrawl.cards.AbstractCard;
 import com.megacrit.cardcrawl.characters.AbstractPlayer;
 import com.megacrit.cardcrawl.core.AbstractCreature;
 import com.megacrit.cardcrawl.core.CardCrawlGame;
 import com.megacrit.cardcrawl.localization.CardStrings;
 import com.megacrit.cardcrawl.monsters.AbstractMonster;
 import com.megacrit.cardcrawl.powers.AbstractPower;
 import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
 import com.megacrit.cardcrawl.vfx.BorderFlashEffect;
 import shadowverse.characters.AbstractShadowversePlayer;
 import shadowverse.characters.Elf;
 import shadowverse.powers.ShiningValkyriePower;


 public class ShiningValkyrie extends CustomCard {
   public static final String ID = "shadowverse:ShiningValkyrie";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:ShiningValkyrie");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/ShiningValkyrie.png";

   public ShiningValkyrie() {
     super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.POWER, Elf.Enums.COLOR_GREEN, CardRarity.SPECIAL, CardTarget.SELF);
     this.baseMagicNumber = 1;
     this.magicNumber = this.baseMagicNumber;
     this.cardsToPreview = new Fairy();
       this.tags.add(AbstractShadowversePlayer.Enums.LEGEND);
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeMagicNumber(1);
     } 
   }
 
   
   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
       addToBot((AbstractGameAction)new SFXAction("ShiningValkyrie"));
       addToBot((AbstractGameAction)new VFXAction((AbstractGameEffect)new BorderFlashEffect(Color.GOLD, true),1.0f));
       addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)abstractPlayer, (AbstractCreature)abstractPlayer, (AbstractPower)new ShiningValkyriePower((AbstractCreature)abstractPlayer)));
       addToBot((AbstractGameAction)new MakeTempCardInHandAction(this.cardsToPreview.makeStatEquivalentCopy(),this.magicNumber));
   }
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new ShiningValkyrie();
   }
 }

