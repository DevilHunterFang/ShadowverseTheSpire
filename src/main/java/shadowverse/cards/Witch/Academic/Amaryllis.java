 package shadowverse.cards.Witch.Academic;
 


import basemod.abstracts.CustomCard;
import com.evacipated.cardcrawl.mod.stslib.actions.tempHp.AddTemporaryHPAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.cards.Neutral.Temp.CurseOfSuffering;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Witchcraft;
import shadowverse.powers.AmaryllisPower;


 public class Amaryllis
   extends CustomCard {
   public static final String ID = "shadowverse:Amaryllis";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Amaryllis");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/Amaryllis.png";

   public Amaryllis() {
     super(ID, NAME, IMG_PATH, 0, DESCRIPTION, CardType.ATTACK, Witchcraft.Enums.COLOR_BLUE, CardRarity.RARE, CardTarget.SELF);
     this.tags.add(AbstractShadowversePlayer.Enums.ACADEMIC);
     this.baseBlock = 3;
     this.cardsToPreview = new CurseOfSuffering();
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeBlock(4);
       this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
       initializeDescription();
     } 
   }
 
   
   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
     addToBot(new SFXAction("Amaryllis"));
     addToBot(new AddTemporaryHPAction(abstractPlayer,abstractPlayer,this.block));
     addToBot(new ApplyPowerAction(abstractPlayer,abstractPlayer,new AmaryllisPower(abstractPlayer,upgraded)));
     addToBot(new MakeTempCardInHandAction(this.cardsToPreview.makeStatEquivalentCopy()));
   }
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new Amaryllis();
   }
 }

