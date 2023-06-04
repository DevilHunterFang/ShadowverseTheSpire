 package shadowverse.cards.Neutral.Temp;


 import basemod.abstracts.CustomCard;
 import com.badlogic.gdx.graphics.Color;
 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.actions.animations.VFXAction;
 import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
 import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
 import com.megacrit.cardcrawl.actions.utility.SFXAction;
 import com.megacrit.cardcrawl.cards.AbstractCard;
 import com.megacrit.cardcrawl.characters.AbstractPlayer;
 import com.megacrit.cardcrawl.core.CardCrawlGame;
 import com.megacrit.cardcrawl.localization.CardStrings;
 import com.megacrit.cardcrawl.monsters.AbstractMonster;
 import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
 import com.megacrit.cardcrawl.vfx.BorderFlashEffect;
 import com.megacrit.cardcrawl.vfx.combat.MiracleEffect;
 import shadowverse.action.FirstOneAction;
 import shadowverse.cards.Vampire.NatMech.Mono;
 import shadowverse.characters.AbstractShadowversePlayer;
 import shadowverse.characters.Vampire;


 public class FirstOne
   extends CustomCard {
   public static final String ID = "shadowverse:FirstOne";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:FirstOne");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/FirstOne.png";

   public FirstOne() {
     super(ID, NAME, IMG_PATH, 2, DESCRIPTION, CardType.SKILL, Vampire.Enums.COLOR_SCARLET, CardRarity.SPECIAL, CardTarget.NONE);
     this.exhaust = true;
     this.tags.add(AbstractShadowversePlayer.Enums.MACHINE);
     this.cardsToPreview = (AbstractCard)new Mono_Unlock();
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeBaseCost(1);
     } 
   }
 
   
   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
     addToBot((AbstractGameAction)new SFXAction("FirstOne"));
     addToBot((AbstractGameAction)new VFXAction((AbstractGameEffect)new BorderFlashEffect(Color.SCARLET, true)));
     addToBot((AbstractGameAction)new VFXAction((AbstractGameEffect)new MiracleEffect(Color.SCARLET.cpy(),Color.WHITE.cpy(),"HEAL_3")));
     addToBot((AbstractGameAction)new FirstOneAction());
     for (AbstractCard c : abstractPlayer.discardPile.group){
       if (c instanceof Mono){
         addToBot((AbstractGameAction)new ExhaustSpecificCardAction(c,abstractPlayer.discardPile));
         addToBot((AbstractGameAction)new MakeTempCardInHandAction((this.cardsToPreview.makeStatEquivalentCopy())));
       }
     }
   }
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new FirstOne();
   }
 }

