 package shadowverse.cards.Elf.NatMech;


 import basemod.abstracts.CustomCard;
 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.actions.common.GainBlockAction;
 import com.megacrit.cardcrawl.actions.utility.SFXAction;
 import com.megacrit.cardcrawl.cards.AbstractCard;
 import com.megacrit.cardcrawl.characters.AbstractPlayer;
 import com.megacrit.cardcrawl.core.CardCrawlGame;
 import com.megacrit.cardcrawl.localization.CardStrings;
 import com.megacrit.cardcrawl.monsters.AbstractMonster;
 import shadowverse.action.DrawPileToHandAction_Tag;
 import shadowverse.characters.AbstractShadowversePlayer;
 import shadowverse.characters.Elf;


 public class IrongliderElf extends CustomCard {
   public static final String ID = "shadowverse:IrongliderElf";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:IrongliderElf");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/IrongliderElf.png";

   public IrongliderElf() {
     super(ID, NAME, IMG_PATH, 2, DESCRIPTION, CardType.ATTACK, Elf.Enums.COLOR_GREEN, CardRarity.UNCOMMON, CardTarget.SELF);
     this.baseBlock = 10;
     this.tags.add(AbstractShadowversePlayer.Enums.MACHINE);
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeBlock(3);
     } 
   }

   
   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
       addToBot((AbstractGameAction)new SFXAction("IrongliderElf"));
       addToBot((AbstractGameAction)new DrawPileToHandAction_Tag(2,AbstractShadowversePlayer.Enums.MACHINE,null));
       addToBot((AbstractGameAction)new GainBlockAction(abstractPlayer,this.block));
   }
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new IrongliderElf();
   }
 }
