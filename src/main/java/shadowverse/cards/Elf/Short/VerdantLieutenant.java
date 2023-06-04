package shadowverse.cards.Elf.Short;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Elf;
import shadowverse.relics.KagemitsuSword;

public class VerdantLieutenant extends CustomCard {
    public static final String ID = "shadowverse:VerdantLieutenant";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:VerdantLieutenant");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/VerdantLieutenant.png";

    public VerdantLieutenant() {
        super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.ATTACK, Elf.Enums.COLOR_GREEN, CardRarity.UNCOMMON, CardTarget.ENEMY);
        this.baseBlock = 5;
        this.baseDamage = 5;
        this.tags.add(AbstractShadowversePlayer.Enums.CONDEMNED);
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBlock(3);
            upgradeDamage(3);
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }

    public void degrade() {
        if (this.upgraded) {
            degradeName();
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

    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.cardsPlayedThisTurn.add(this);
        addToBot(new SFXAction("VerdantLieutenant"));
        addToBot(new GainBlockAction(p,this.block));
        addToBot(new DamageAction(m,new DamageInfo(p,this.damage,this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_VERTICAL));
        if (this.upgraded){
            this.degrade();
            if(p.hasRelic(KagemitsuSword.ID)){
                this.upgrade();
            }
        }
    }

    @Override
    public void triggerOnOtherCardPlayed(AbstractCard c) {
        if (!this.upgraded){
            if (AbstractDungeon.actionManager.cardsPlayedThisTurn.size() >= 4){
                addToBot(new SFXAction("VerdantLieutenantPower"));
                addToBot(new DrawCardAction(1));
                this.upgrade();
                this.superFlash();
            }
        }
    }

    public AbstractCard makeCopy() {
        return new VerdantLieutenant();
    }
}
