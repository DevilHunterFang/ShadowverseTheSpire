package shadowverse.cards.Necromancer.Default;

import basemod.abstracts.CustomCard;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.vfx.BorderFlashEffect;
import shadowverse.action.CorneliusAction;
import shadowverse.cards.Neutral.Temp.CorpseKingCommand;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Necromancer;

public class Cornelius
        extends CustomCard {
    public static final String ID = "shadowverse:Cornelius";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Cornelius");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/Cornelius.png";

    public Cornelius() {
        super(ID, NAME, IMG_PATH, 3, DESCRIPTION, CardType.ATTACK, Necromancer.Enums.COLOR_PURPLE, CardRarity.RARE, CardTarget.SELF);
        this.baseBlock = 20;
        this.tags.add(AbstractShadowversePlayer.Enums.ACADEMIC);
        this.exhaust = true;
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBlock(4);
            this.cardsToPreview = new CorpseKingCommand();
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }


    public void use(AbstractPlayer p, AbstractMonster abstractMonster) {
        {
            addToBot(new SFXAction("Cornelius"));
            addToBot(new GainBlockAction(p,this.block));
            addToBot(new VFXAction(new BorderFlashEffect(Color.PURPLE, true), 1.0f));
            for (AbstractMonster mo : (AbstractDungeon.getCurrRoom()).monsters.monsters) {
                if (mo != null && !mo.isDeadOrEscaped()) {
                    addToBot(new ApplyPowerAction(mo, p, new WeakPower(mo, 2, false), 2, true, AbstractGameAction.AttackEffect.NONE));
                    addToBot(new ApplyPowerAction(mo, p, new VulnerablePower(mo, 2, false), 2, true, AbstractGameAction.AttackEffect.NONE));
                }
            }
            addToBot(new CorneliusAction());
            if (upgraded){
                addToBot(new MakeTempCardInHandAction(this.cardsToPreview.makeStatEquivalentCopy()));
            }
        }
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new Cornelius();
    }
}


