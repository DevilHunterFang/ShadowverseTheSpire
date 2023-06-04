 package shadowverse.cards.Witch.Academic;
 


import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.common.ReduceCostAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.random.Random;
import shadowverse.cards.Neutral.Temp.MysterianCircle;
import shadowverse.cards.Neutral.Temp.MysterianMissile;
import shadowverse.cards.Neutral.Temp.MysterianRite;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Witchcraft;
import shadowverse.relics.AnneBOSS;

import java.util.ArrayList;


 public class Miranda
   extends CustomCard {
   public static final String ID = "shadowverse:Miranda";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Miranda");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/Miranda.png";
   public static final int BASE_COST = 4;
   public static AbstractCard returnTrulyRandomMysterianInCombat(Random rng) {
     ArrayList<AbstractCard> list = new ArrayList<>();
     MysterianCircle mysterianCircle = new MysterianCircle();
     MysterianMissile mysterianMissile = new MysterianMissile();
     list.add(mysterianCircle);
     list.add(mysterianMissile);
     return list.get(rng.random(list.size() - 1));
   }

   public Miranda() {
     super(ID, NAME, IMG_PATH, BASE_COST, DESCRIPTION, CardType.ATTACK, Witchcraft.Enums.COLOR_BLUE, CardRarity.UNCOMMON, CardTarget.SELF);
     this.cardsToPreview = (AbstractCard)new MysterianRite();
     this.baseBlock = 8;
     this.tags.add(AbstractShadowversePlayer.Enums.MYSTERIA);
   }

   public void triggerOnOtherCardPlayed(AbstractCard c) {
     if (c.hasTag(AbstractShadowversePlayer.Enums.MYSTERIA)||(AbstractDungeon.player.hasRelic(AnneBOSS.ID)&&c.type==CardType.SKILL)) {
       flash();
       addToBot((AbstractGameAction)new SFXAction("spell_boost"));
       addToBot((AbstractGameAction)new ReduceCostAction((AbstractCard)this));
     }
   }
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeBlock(3);
       this.cardsToPreview.upgrade();
       this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
       initializeDescription();
     } 
   }
 
   
   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
     AbstractCard a = (AbstractCard)new MysterianRite();
     AbstractCard c = returnTrulyRandomMysterianInCombat(AbstractDungeon.cardRandomRng).makeCopy();
     ((AbstractShadowversePlayer)abstractPlayer).mysteriaCount++;
     addToBot((AbstractGameAction)new SFXAction("Miranda"));
     addToBot((AbstractGameAction)new GainBlockAction((AbstractCreature)abstractPlayer,this.block));
     if (this.upgraded){
       a.upgrade();
     }
     addToBot((AbstractGameAction)new MakeTempCardInHandAction(c, 1));
     addToBot((AbstractGameAction)new MakeTempCardInHandAction(a, 1));
     this.cost = BASE_COST;
   }
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new Miranda();
   }
 }


