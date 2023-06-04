package shadowverse.cards.Witch.Natura;

import basemod.abstracts.CustomCard;
import com.evacipated.cardcrawl.mod.stslib.actions.defect.EvokeSpecificOrbAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.CleaveEffect;
import shadowverse.cards.Neutral.Temp.NaterranGreatTree;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Witchcraft;
import shadowverse.orbs.AmuletOrb;

public class Pyromancer extends CustomCard {
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Pyromancer");
    public static final String ID = "shadowverse:Pyromancer";
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/Pyromancer.png";

    public Pyromancer() {
        super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.ATTACK, Witchcraft.Enums.COLOR_BLUE, CardRarity.UNCOMMON, CardTarget.ALL_ENEMY);
        this.baseDamage = 9;
        this.cardsToPreview = (AbstractCard) new NaterranGreatTree();
        this.tags.add(AbstractShadowversePlayer.Enums.NATURAL);
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeDamage(3);
        }
    }


    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        boolean powerExists = false;
        AbstractCard c = this.cardsToPreview.makeStatEquivalentCopy();
        addToBot((AbstractGameAction) new SFXAction("Pyromancer"));
        for (AbstractPower pow : abstractPlayer.powers) {
            if (pow.ID.equals("shadowverse:NaterranTree")) {
                powerExists = true;
                break;
            }
        }
        if (powerExists) {
            for (AbstractOrb o : abstractPlayer.orbs) {
                if (o instanceof AmuletOrb) {
                    if (((AmuletOrb) o).amulet instanceof NaterranGreatTree) {
                        addToBot((AbstractGameAction) new EvokeSpecificOrbAction(o));
                    }
                }
            }
            addToBot((AbstractGameAction) new RemoveSpecificPowerAction((AbstractCreature) abstractPlayer, (AbstractCreature) abstractPlayer, "shadowverse:NaterranTree"));
            addToBot((AbstractGameAction) new VFXAction((AbstractCreature) abstractPlayer, (AbstractGameEffect) new CleaveEffect(), 0.1F));
            addToBot((AbstractGameAction) new DamageAllEnemiesAction((AbstractCreature) abstractPlayer, DamageInfo.createDamageMatrix(this.damage * 2, true), this.damageTypeForTurn, AbstractGameAction.AttackEffect.FIRE, true));
            addToBot((AbstractGameAction) new MakeTempCardInHandAction(c, 1));
        } else {
            addToBot((AbstractGameAction) new VFXAction((AbstractCreature) abstractPlayer, (AbstractGameEffect) new CleaveEffect(), 0.1F));
            addToBot((AbstractGameAction) new DamageAllEnemiesAction((AbstractCreature) abstractPlayer, DamageInfo.createDamageMatrix(this.damage, true), this.damageTypeForTurn, AbstractGameAction.AttackEffect.FIRE, true));
        }
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new Pyromancer();
    }
}

