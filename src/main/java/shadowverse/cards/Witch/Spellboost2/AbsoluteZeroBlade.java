 package shadowverse.cards.Witch.Spellboost2;
 
 import basemod.abstracts.CustomCard;
 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.actions.animations.VFXAction;
 import com.megacrit.cardcrawl.actions.common.DrawCardAction;
 import com.megacrit.cardcrawl.actions.utility.SFXAction;
 import com.megacrit.cardcrawl.actions.watcher.JudgementAction;
 import com.megacrit.cardcrawl.cards.AbstractCard;
 import com.megacrit.cardcrawl.characters.AbstractPlayer;
 import com.megacrit.cardcrawl.core.AbstractCreature;
 import com.megacrit.cardcrawl.core.CardCrawlGame;
 import com.megacrit.cardcrawl.core.Settings;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
 import com.megacrit.cardcrawl.localization.CardStrings;
 import com.megacrit.cardcrawl.monsters.AbstractMonster;
 import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
 import com.megacrit.cardcrawl.vfx.combat.BlizzardEffect;
 import shadowverse.characters.AbstractShadowversePlayer;
 import shadowverse.characters.Witchcraft;
 
 
 public class AbsoluteZeroBlade
   extends CustomCard
 {
   public static final String ID = "shadowverse:AbsoluteZeroBlade";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:AbsoluteZeroBlade");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/AbsoluteZeroBlade.png";
   
   public AbsoluteZeroBlade() {
     super("shadowverse:AbsoluteZeroBlade", NAME, "img/cards/AbsoluteZeroBlade.png", 1, DESCRIPTION, CardType.SKILL, Witchcraft.Enums.COLOR_BLUE, CardRarity.UNCOMMON, CardTarget.ENEMY);
     this.tags.add(AbstractShadowversePlayer.Enums.SPELL_BOOST_ATTACK);
     this.baseMagicNumber = 0;
     this.magicNumber = this.baseMagicNumber;
   }
 
   
   public void triggerOnOtherCardPlayed(AbstractCard c) {
     if (c.type == CardType.SKILL) {
       flash();
       addToBot((AbstractGameAction)new SFXAction("spell_boost"));
       
       this.magicNumber = ++this.baseMagicNumber;
     } 
   }
 
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
       initializeDescription();
     } 
   }
 
   
   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
     addToBot((AbstractGameAction)new DrawCardAction((AbstractCreature)abstractPlayer, 1));
     if (Settings.FAST_MODE) {
       addToBot((AbstractGameAction)new VFXAction((AbstractGameEffect)new BlizzardEffect(this.magicNumber + 1, 
               AbstractDungeon.getMonsters().shouldFlipVfx()), 0.25F));
     } else {
       addToBot((AbstractGameAction)new VFXAction((AbstractGameEffect)new BlizzardEffect(this.magicNumber + 1, AbstractDungeon.getMonsters().shouldFlipVfx()), 1.0F));
     } 
     if (this.upgraded) {
       addToBot((AbstractGameAction)new JudgementAction((AbstractCreature)abstractMonster, 10 + this.magicNumber * 5));
     } else {
       addToBot((AbstractGameAction)new JudgementAction((AbstractCreature)abstractMonster, 10 + this.magicNumber * 3));
     } 
     this.baseMagicNumber = 0;
     this.magicNumber = this.baseMagicNumber;
   }
   
   public void applyPowers() {
     super.applyPowers();
     int count = this.magicNumber;
     if (this.upgraded) {
       this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
     } else {
       this.rawDescription = cardStrings.DESCRIPTION;
     } 
     this.rawDescription += cardStrings.EXTENDED_DESCRIPTION[0] + count;
     this.rawDescription += cardStrings.EXTENDED_DESCRIPTION[1];
     initializeDescription();
   }
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new AbsoluteZeroBlade();
   }
 }

