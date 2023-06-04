package shadowverse.cards.Neutral.Temp;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.ReduceCostAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.ScreenOnFireEffect;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Witchcraft;
import shadowverse.relics.AnneBOSS;

import java.util.ArrayList;


public class MajesticSorcery
        extends CustomCard {
    public static final String ID = "shadowverse:MajesticSorcery";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:MajesticSorcery");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/MajesticSorcery.png";

    public MajesticSorcery() {
        super(ID, NAME, IMG_PATH, 12, DESCRIPTION, CardType.SKILL, Witchcraft.Enums.COLOR_BLUE, CardRarity.SPECIAL, CardTarget.ALL_ENEMY);
        this.baseDamage = 8;
        this.tags.add(AbstractShadowversePlayer.Enums.MYSTERIA);
        this.tags.add(AbstractShadowversePlayer.Enums.SPELL_BOOST_ATTACK);
        this.exhaust = true;
        this.selfRetain = true;
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeDamage(2);
        }
    }

    @Override
    public void triggerOnOtherCardPlayed(AbstractCard c) {
        if (c.type == CardType.SKILL) {
            flash();
            addToBot(new SFXAction("spell_boost"));
            addToBot(new ReduceCostAction(this));
            if (AbstractDungeon.player.hasRelic(AnneBOSS.ID) && !c.hasTag(AbstractShadowversePlayer.Enums.MYSTERIA)){
                addToBot(new SFXAction("spell_boost"));
                addToBot(new ReduceCostAction(this));
            }
        }
        if (c.hasTag(AbstractShadowversePlayer.Enums.MYSTERIA)){
            flash();
            addToBot(new SFXAction("spell_boost"));
            addToBot(new ReduceCostAction(this));
        }
    }

    public void applyPowers() {
        super.applyPowers();
        int count = 0;
        ArrayList<String> tmp = new ArrayList<>();
        for (AbstractCard c : AbstractDungeon.actionManager.cardsPlayedThisCombat) {
            if (!tmp.contains(c.cardID)) {
                if (c.type == CardType.ATTACK && c.hasTag(AbstractShadowversePlayer.Enums.MYSTERIA)) {
                    count++;
                }
            }
            tmp.add(c.cardID);
        }
        this.rawDescription = cardStrings.DESCRIPTION;
        this.rawDescription += cardStrings.EXTENDED_DESCRIPTION[0] + count;
        this.rawDescription += cardStrings.EXTENDED_DESCRIPTION[1];
        initializeDescription();
    }


    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new SFXAction("MajesticSorcery"));
        addToBot((new VFXAction(abstractPlayer, new ScreenOnFireEffect(), 1.0F)));
        int count = 0;
        ArrayList<String> tmp = new ArrayList<>();
        for (AbstractCard c : AbstractDungeon.actionManager.cardsPlayedThisCombat) {
            if (!tmp.contains(c.cardID)) {
                if (c.type == CardType.ATTACK && c.hasTag(AbstractShadowversePlayer.Enums.MYSTERIA)) {
                    count++;
                }
            }
            tmp.add(c.cardID);
        }
        for (int i = 0; i < count;i++){
            addToBot(new DamageAllEnemiesAction(abstractPlayer, DamageInfo.createDamageMatrix(this.damage, true), DamageInfo.DamageType.NORMAL, AbstractGameAction.AttackEffect.FIRE));
        }
        addToBot(new DamageAllEnemiesAction(abstractPlayer, DamageInfo.createDamageMatrix(this.damage, true), DamageInfo.DamageType.NORMAL, AbstractGameAction.AttackEffect.FIRE));
        if (AbstractDungeon.player instanceof AbstractShadowversePlayer) {
            ((AbstractShadowversePlayer) AbstractDungeon.player).mysteriaCount++;
        }
        this.cost = 12;
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new MajesticSorcery();
    }
}


