package shadowverse.cards.Rare;


import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.common.SuicideAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.actions.watcher.JudgementAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.MinionPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.BloodShotEffect;
import com.megacrit.cardcrawl.vfx.combat.WeightyImpactEffect;
import shadowverse.cards.Temp.UndyingResolve;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Necromancer;


public class Anisage
        extends CustomCard {
    public static final String ID = "shadowverse:Anisage";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Anisage");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/Anisage.png";

    public Anisage() {
        super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.ATTACK, Necromancer.Enums.COLOR_PURPLE, CardRarity.RARE, CardTarget.ENEMY);
        this.baseDamage = 6;
        this.cardsToPreview = new UndyingResolve();
        this.tags.add(AbstractShadowversePlayer.Enums.FES);
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeDamage(3);
            this.cardsToPreview.upgrade();
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }


    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new SFXAction("Anisage"));
        if (upgraded){
            if (m.currentHealth>this.damage){
                m.currentHealth -= Math.min(this.damage, m.currentHealth - 1);
                addToBot(new VFXAction(new BloodShotEffect(p.hb.cX,p.hb.cY,m.hb.cX,m.hb.cY,1)));
            }else {
                addToBot((AbstractGameAction)new JudgementAction((AbstractCreature)m, this.damage));
            }
        }else {
            addToBot(new DamageAction(m,new DamageInfo(p,this.damage,this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        }
        addToBot(new MakeTempCardInHandAction(this.cardsToPreview.makeStatEquivalentCopy()));
   }
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new Anisage();
   }
 }

