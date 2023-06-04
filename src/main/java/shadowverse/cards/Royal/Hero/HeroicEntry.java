package shadowverse.cards.Royal.Hero;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.vfx.combat.CleaveEffect;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Royal;

import static shadowverse.characters.AbstractShadowversePlayer.Enums.HERO;

public class HeroicEntry extends CustomCard {
    public static final String ID = "shadowverse:HeroicEntry";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:HeroicEntry");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/HeroicEntry.png";
    private static final String TEXT = CardCrawlGame.languagePack.getUIString("shadowverse:Exhaust").TEXT[0];

    public HeroicEntry() {
        super(ID, NAME, IMG_PATH, 2, DESCRIPTION, CardType.SKILL, Royal.Enums.COLOR_YELLOW, CardRarity.UNCOMMON, CardTarget.ALL_ENEMY);
        this.cardsToPreview = new MachKnight();
        this.tags.add(AbstractShadowversePlayer.Enums.HERO);
        this.isMultiDamage = true;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }

    public boolean inDanger() {
        AbstractPlayer p = AbstractDungeon.player;
        if (p.currentHealth <= p.maxHealth / 4) {
            return true;
        }
        if (p instanceof AbstractShadowversePlayer) {
            if (((AbstractShadowversePlayer) p).wrathLastTurn > 0) {
                return true;
            }
        }
        for (AbstractPower pow : p.powers) {
            if (pow.type == AbstractPower.PowerType.DEBUFF) {
                return true;
            }
        }
        for (AbstractCard c : p.hand.group) {
            if (c.type == CardType.CURSE || (c.type == CardType.STATUS && !"shadowverse:EvolutionPoint".equals(c.cardID))) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster abstractMonster) {
        addToBot(new SFXAction(ID.replace("shadowverse:", "")));
        if (p.hand.group.size() < 7) {
            addToBot(new DrawCardAction(7 - p.hand.group.size()));
        }
        if (inDanger()) {
            addToBot(new AbstractGameAction() {
                @Override
                public void update() {
                    AbstractCard c = cardsToPreview.makeStatEquivalentCopy();
                    c.exhaustOnUseOnce = true;
                    c.exhaust = true;
                    c.isEthereal = true;
                    c.rawDescription += " NL " + TEXT + " 。";
                    c.costForTurn = 0;
                    c.isCostModified = true;
                    c.initializeDescription();
                    c.applyPowers();
                    p.hand.addToTop(c);
                    this.isDone = true;
                }
            });
        }
        addToBot(new VFXAction(p, new CleaveEffect(), 0.1F));
        addToBot(new DamageAllEnemiesAction(p, DamageInfo.createDamageMatrix(4, true), DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.NONE, true));
        addToBot(new AbstractGameAction() {
            @Override
            public void update() {
                for (AbstractCard c : p.hand.group) {
                    if (c.hasTag(HERO)) {
                        addToBot(new VFXAction(p, new CleaveEffect(), 0.1F));
                        addToBot(new DamageAllEnemiesAction(p, DamageInfo.createDamageMatrix(4, true), DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.NONE, true));
                        if (upgraded && !c.retain && !c.selfRetain) {
                            c.retain = true;
                            c.rawDescription += " NL " + TEXT;
                            c.initializeDescription();
                            c.applyPowers();
                        }
                    }
                }
                this.isDone = true;
            }
        });
    }

    @Override
    public AbstractCard makeCopy() {
        return new HeroicEntry();
    }
}