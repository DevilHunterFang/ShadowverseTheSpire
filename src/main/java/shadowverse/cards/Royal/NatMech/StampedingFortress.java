package shadowverse.cards.Royal.NatMech;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.cards.Neutral.Temp.ProductMachine;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Royal;
import shadowverse.relics.KagemitsuSword;

public class StampedingFortress extends CustomCard {
    public static final String ID = "shadowverse:StampedingFortress";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:StampedingFortress");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/StampedingFortress.png";
    public static final String IMG_PATH_EV = "img/cards/StampedingFortress_Ev.png";


    public StampedingFortress() {
        super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.ATTACK, Royal.Enums.COLOR_YELLOW, CardRarity.UNCOMMON, CardTarget.ENEMY);
        this.tags.add(AbstractShadowversePlayer.Enums.MACHINE);
        this.baseDamage = 6;
        this.baseBlock = 6;
        this.baseMagicNumber = this.magicNumber = 3;
        this.cardsToPreview = new ProductMachine();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeDamage(1);
            upgradeBlock(1);
            this.textureImg = IMG_PATH_EV;
            this.loadCardImage(IMG_PATH_EV);
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }

    public void degrade() {
        if (this.upgraded) {
            degradeName();
            this.textureImg = IMG_PATH;
            this.loadCardImage(IMG_PATH);
            this.rawDescription = cardStrings.DESCRIPTION;
            initializeDescription();
            this.superFlash();
            this.applyPowers();
        }
    }

    public void degradeName() {
        --this.timesUpgraded;
        this.upgraded = false;
        this.name = NAME;
        this.initializeTitle();
    }


    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (this.upgraded) {
            addToBot(new SFXAction(ID.replace("shadowverse:", "") + "_Ev"));
        } else {
            addToBot(new SFXAction(ID.replace("shadowverse:", "")));
        }
        addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        addToBot(new GainBlockAction(p, p, this.block));
        if (this.upgraded) {
            boolean has = false;
            for (AbstractCard c : AbstractDungeon.player.hand.group) {
                if (c != this && (c.hasTag(AbstractShadowversePlayer.Enums.MACHINE) )) {
                    has = true;
                }
            }
            if (has) {
                this.addToTop(new MakeTempCardInHandAction(new ProductMachine(), 3));
            }
            this.degrade();
            if(p.hasRelic(KagemitsuSword.ID)){
                this.upgrade();
            }
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new StampedingFortress();
    }
}


