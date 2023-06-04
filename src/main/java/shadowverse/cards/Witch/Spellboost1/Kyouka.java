package shadowverse.cards.Witch.Spellboost1;

import basemod.abstracts.CustomCard;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.ReduceCostAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.MindblastEffect;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Witchcraft;

public class Kyouka extends CustomCard {
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Kyouka");
    public static final String ID = "shadowverse:Kyouka";
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/Kyouka.png";
    private static final Texture LEADER_SKIN_VERSION = ImageMaster.loadImage("img/cards/Kyouka_L.png");

    public Kyouka(int upgrades) {
        super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.ATTACK, Witchcraft.Enums.COLOR_BLUE, CardRarity.UNCOMMON, CardTarget.ALL_ENEMY);
        this.baseDamage = 21;
        this.baseBlock = 6;
        this.baseMagicNumber = 4;
        this.magicNumber = this.baseMagicNumber;
        this.timesUpgraded = upgrades;
        this.jokePortrait = new TextureAtlas.AtlasRegion(LEADER_SKIN_VERSION, 0, 0, LEADER_SKIN_VERSION.getWidth(), LEADER_SKIN_VERSION.getHeight());
    }

    @Override
    public void upgrade() {
        if (this.magicNumber>0){
            upgradeMagicNumber(-1);
        }
        this.timesUpgraded++;
        this.upgraded = true;
        this.name = cardStrings.NAME + "+" + this.timesUpgraded;
        initializeTitle();
    }

    @Override
    public boolean canUpgrade() {
        if (this.timesUpgraded<3){
            return true;
        }else {
            return  false;
        }
    }

    @Override
    public void atTurnStart(){
        if (this.baseMagicNumber>0){
            this.baseMagicNumber--;
            this.magicNumber = this.baseMagicNumber;
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot((AbstractGameAction)new GainBlockAction(p,this.block));
        //增幅
        for (AbstractCard c : AbstractDungeon.player.hand.group) {
            if (c.hasTag(AbstractShadowversePlayer.Enums.SPELL_BOOST)) {
                for (int i = 0; i < 2; i++) {
                    c.flash();
                    addToBot((AbstractGameAction)new SFXAction("spell_boost"));
                    addToBot((AbstractGameAction)new ReduceCostAction(c));
                }  continue;
            }
            if (c.hasTag(AbstractShadowversePlayer.Enums.SPELL_BOOST_ATTACK)) {
                for (int i = 0; i < 2; i++) {
                    c.flash();

                    c.magicNumber = ++c.baseMagicNumber;
                    addToBot((AbstractGameAction)new SFXAction("spell_boost"));
                }
            }
        }
        if (this.magicNumber>0){
            addToBot((AbstractGameAction)new SFXAction("Kyouka"));
        }else {
            addToBot((AbstractGameAction)new SFXAction("Kyouka_UB"));
            addToBot((AbstractGameAction)new VFXAction((AbstractCreature)p, (AbstractGameEffect)new MindblastEffect(p.dialogX, p.dialogY, p.flipHorizontal), 0.1F));
            addToBot((AbstractGameAction)new DamageAllEnemiesAction((AbstractCreature)p, DamageInfo.createDamageMatrix(this.damage, true), DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.BLUNT_HEAVY, true));
        }
    }

    public AbstractCard makeCopy() {
        return new Kyouka(this.timesUpgraded);
    }
}
