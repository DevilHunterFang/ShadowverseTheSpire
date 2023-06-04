 package shadowverse.cards.Witch.Natura;
 
 import basemod.abstracts.CustomCard;
 import com.evacipated.cardcrawl.mod.stslib.actions.defect.EvokeSpecificOrbAction;
 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.actions.common.*;



 import com.megacrit.cardcrawl.actions.utility.SFXAction;
 import com.megacrit.cardcrawl.cards.AbstractCard;
 import com.megacrit.cardcrawl.cards.DamageInfo;
 import com.megacrit.cardcrawl.characters.AbstractPlayer;
 import com.megacrit.cardcrawl.core.AbstractCreature;
 import com.megacrit.cardcrawl.core.CardCrawlGame;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
 import com.megacrit.cardcrawl.localization.CardStrings;
 import com.megacrit.cardcrawl.monsters.AbstractMonster;
 import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.powers.AbstractPower;
 import com.megacrit.cardcrawl.powers.PlatedArmorPower;
import shadowverse.cards.Neutral.Temp.NaterranGreatTree;
 import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Witchcraft;
import shadowverse.orbs.AmuletOrb;

 
 public class Stormelementalist extends CustomCard {
   public static final String ID = "shadowverse:Stormelementalist";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Stormelementalist");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/Stormelementalist.png";
   
   public Stormelementalist() {
     super("shadowverse:Stormelementalist", NAME, "img/cards/Stormelementalist.png", 1, DESCRIPTION, CardType.ATTACK, Witchcraft.Enums.COLOR_BLUE, CardRarity.UNCOMMON, CardTarget.SELF);
     this.baseBlock = 7;
     this.cardsToPreview = (AbstractCard)new NaterranGreatTree();
     this.tags.add(AbstractShadowversePlayer.Enums.NATURAL);
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeBlock(3);
     } 
   }
 
   
   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
     boolean powerExists = false;
     AbstractCard c = this.cardsToPreview.makeStatEquivalentCopy();
     addToBot((AbstractGameAction)new SFXAction("Stormelementalist"));
     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new GainBlockAction((AbstractCreature)abstractPlayer, (AbstractCreature)abstractPlayer, this.block));
     for (AbstractPower pow : abstractPlayer.powers) {
       if (pow.ID.equals("shadowverse:NaterranTree")) {
         powerExists = true;
         break;
       } 
     } 
     if (powerExists) {
        for (AbstractOrb o:abstractPlayer.orbs){
            if (o instanceof AmuletOrb){
                if (((AmuletOrb) o).amulet instanceof NaterranGreatTree){
                    addToBot((AbstractGameAction) new EvokeSpecificOrbAction(o));
                }
            }
        }
       addToBot((AbstractGameAction)new RemoveSpecificPowerAction((AbstractCreature)abstractPlayer, (AbstractCreature)abstractPlayer, "shadowverse:NaterranTree"));
             addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)abstractPlayer, (AbstractCreature)abstractPlayer, (AbstractPower)new PlatedArmorPower((AbstractCreature)abstractPlayer, 2), 2));
       addToBot((AbstractGameAction)new DamageRandomEnemyAction(new DamageInfo((AbstractCreature)abstractPlayer, 4, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
       addToBot((AbstractGameAction)new MakeTempCardInHandAction(c, 1));
     } 
   }
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new Stormelementalist();
   }
 }

