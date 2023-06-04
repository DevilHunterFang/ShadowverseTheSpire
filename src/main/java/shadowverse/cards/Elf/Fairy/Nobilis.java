 package shadowverse.cards.Elf.Fairy;
 


import basemod.abstracts.CustomCard;
import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.HeartMegaDebuffEffect;
import shadowverse.cardmods.NobilisMod;
import shadowverse.cards.Neutral.Temp.Fairy;
import shadowverse.characters.Elf;


 public class Nobilis extends CustomCard {
   public static final String ID = "shadowverse:Nobilis";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Nobilis");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/Nobilis.png";

   public Nobilis() {
     super(ID, NAME, IMG_PATH, 2, DESCRIPTION, CardType.ATTACK, Elf.Enums.COLOR_GREEN, CardRarity.RARE, CardTarget.ENEMY);
     this.baseDamage = 2;
     this.cardsToPreview = new Fairy();
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeDamage(2);
       this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
       initializeDescription();
     } 
   }
 
   
   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
     addToBot(new SFXAction("Nobilis"));
     addToBot(new VFXAction(new HeartMegaDebuffEffect()));
     AbstractCard fairy = this.cardsToPreview.makeStatEquivalentCopy();
     fairy.baseDamage += 4;
     CardModifierManager.addModifier(fairy, new NobilisMod());
     fairy.applyPowers();
     addToBot(new MakeTempCardInHandAction(fairy,2));
     addToBot(new DamageAction(abstractMonster, new DamageInfo(abstractPlayer, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
     for (AbstractCard c : AbstractDungeon.player.hand.group) {
       if (c instanceof Fairy) {
         c.baseDamage += 4;
         CardModifierManager.addModifier(c, new NobilisMod());
         c.applyPowers();
       }
     }
     if (this.upgraded){
       addToBot(new GainEnergyAction(1));
     }
   }
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new Nobilis();
   }
 }

