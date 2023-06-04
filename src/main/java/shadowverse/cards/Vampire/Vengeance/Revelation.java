package shadowverse.cards.Vampire.Vengeance;

import basemod.abstracts.CustomCard;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.status.Wound;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.StarBounceEffect;
import com.megacrit.cardcrawl.vfx.combat.ViolentAttackEffect;
import shadowverse.characters.Vampire;
import shadowverse.powers.EpitaphPower;
import shadowverse.stance.Vengeance;

public class Revelation extends CustomCard {
    public static final String ID = "shadowverse:Revelation";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Revelation");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/Revelation.png";

    public Revelation() {
        super(ID, NAME, IMG_PATH, 3, DESCRIPTION, CardType.SKILL, Vampire.Enums.COLOR_SCARLET, CardRarity.UNCOMMON, CardTarget.ALL_ENEMY);
        this.baseDamage = 24;
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeDamage(6);
        }
    }

    @Override
    public void applyPowers(){
        super.applyPowers();
        if (AbstractDungeon.player.hasPower(EpitaphPower.POWER_ID)||AbstractDungeon.player.stance.ID.equals(Vengeance.STANCE_ID)){
            this.setCostForTurn(2);
            this.isCostModified = true;
        }
    }

    public void use(AbstractPlayer p, AbstractMonster monster) {
        for (AbstractMonster m : (AbstractDungeon.getCurrRoom()).monsters.monsters) {
            if (Settings.FAST_MODE) {
                addToBot((AbstractGameAction)new VFXAction((AbstractGameEffect)new ViolentAttackEffect(m.hb.cX, m.hb.cY, Color.RED)));
                for (int i = 0; i < 5; i++)
                    addToBot((AbstractGameAction)new VFXAction((AbstractGameEffect)new StarBounceEffect(m.hb.cX, m.hb.cY)));
            } else {
                addToBot((AbstractGameAction)new VFXAction((AbstractGameEffect)new ViolentAttackEffect(m.hb.cX, m.hb.cY, Color.RED), 0.4F));
                for (int i = 0; i < 5; i++)
                    addToBot((AbstractGameAction)new VFXAction((AbstractGameEffect)new StarBounceEffect(m.hb.cX, m.hb.cY)));
            }
        }
        addToBot((AbstractGameAction)new DamageAllEnemiesAction((AbstractCreature)p, DamageInfo.createDamageMatrix(this.damage, true), DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.NONE, true));
        addToBot((AbstractGameAction)new MakeTempCardInDiscardAction((AbstractCard)new Wound(), 1));
    }

    public AbstractCard makeCopy() {
        return new Revelation();
    }
}
