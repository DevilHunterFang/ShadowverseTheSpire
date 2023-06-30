package shadowverse.cards.Necromancer.LastWords;


import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import shadowverse.Shadowverse;
import shadowverse.cards.Neutral.Temp.AbyssalColonel_Crystalize;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Necromancer;


public class AbyssalColonel
        extends CustomCard {
    public static final String ID = "shadowverse:AbyssalColonel";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:AbyssalColonel");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/AbyssalColonel.png";

    public AbyssalColonel() {
        super(ID, NAME, IMG_PATH, 4, DESCRIPTION, CardType.ATTACK, Necromancer.Enums.COLOR_PURPLE, CardRarity.UNCOMMON, CardTarget.ENEMY);
        this.baseDamage = 36;
        this.baseBlock = 18;
        this.tags.add(AbstractShadowversePlayer.Enums.LASTWORD);
        this.tags.add(AbstractShadowversePlayer.Enums.CONDEMNED);
        this.tags.add(AbstractShadowversePlayer.Enums.CRYSTALLIZE);
        this.cardsToPreview = new AbyssalColonel_Crystalize();
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeDamage(6);
            upgradeBlock(3);
            this.cardsToPreview.upgrade();
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }

    @Override
    public void update() {
        if (AbstractDungeon.currMapNode != null && (AbstractDungeon.getCurrRoom()).phase == AbstractRoom.RoomPhase.COMBAT &&
                Shadowverse.Accelerate(this)) {
            setCostForTurn(1);
            this.type = CardType.POWER;
        } else {
            if (this.type == CardType.POWER) {
                setCostForTurn(4);
                this.type = CardType.ATTACK;
            }
        }
        super.update();
    }

    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        if (this.type == CardType.POWER && this.costForTurn == 1) {
            addToBot(new MakeTempCardInHandAction(this.cardsToPreview.makeStatEquivalentCopy(),1));
        } else {
            addToBot(new GainBlockAction(abstractPlayer, this.block));
            addToBot(new DamageAction(abstractMonster, new DamageInfo(abstractPlayer, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HEAVY));
            if (EnergyPanel.getCurrentEnergy() < 8 && this.costForTurn > 0){
                addToBot(new MakeTempCardInDiscardAction(this.makeStatEquivalentCopy(),1));
            }
        }
    }

    @Override
    public void triggerOnExhaust() {
        addToBot(new HealAction(AbstractDungeon.player, AbstractDungeon.player, 3));
        AbstractMonster mo = AbstractDungeon.getMonsters().getRandomMonster((AbstractMonster) null, true, AbstractDungeon.cardRandomRng);
        addToBot(new ApplyPowerAction(mo, AbstractDungeon.player, new WeakPower(mo, 1, false), 1, true, AbstractGameAction.AttackEffect.NONE));
        addToBot(new ApplyPowerAction(mo, AbstractDungeon.player, new VulnerablePower(mo, 1, false), 1, true, AbstractGameAction.AttackEffect.NONE));
    }

    public AbstractCard makeCopy() {
        return (AbstractCard) new AbyssalColonel();
    }
}

