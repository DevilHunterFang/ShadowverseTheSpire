package shadowverse.cards.Bishop.Default;

import basemod.abstracts.CustomCard;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.actions.watcher.PressEndTurnButtonAction;
import com.megacrit.cardcrawl.actions.watcher.SkipEnemiesTurnAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.DrawCardNextTurnPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.MiracleEffect;
import rs.lazymankits.interfaces.cards.BranchableUpgradeCard;
import rs.lazymankits.interfaces.cards.UpgradeBranch;
import shadowverse.action.ReduceAllCountDownAction;
import shadowverse.cards.Neutral.Temp.FlowOfDispair;
import shadowverse.characters.Bishop;
import shadowverse.powers.RealmOfReposePower;

import java.util.ArrayList;
import java.util.List;

public class OmenOfRepose
        extends CustomCard implements BranchableUpgradeCard {
    public static final String ID = "shadowverse:OmenOfRepose";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:OmenOfRepose");
    public static CardStrings cardStrings2 = CardCrawlGame.languagePack.getCardStrings("shadowverse:OmenOfRepose2");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/OmenOfRepose.png";
    public static final String IMG_PATH2 = "img/cards/OmenOfRepose2.png";

    public OmenOfRepose() {
        super(ID, NAME, IMG_PATH, 3, DESCRIPTION, CardType.ATTACK, Bishop.Enums.COLOR_WHITE, CardRarity.RARE, CardTarget.SELF);
        this.exhaust = true;
    }


    public void upgrade() {
        ((UpgradeBranch) ((BranchableUpgradeCard) this).possibleBranches().get(chosenBranch())).upgrade();
    }


    public void use(AbstractPlayer p, AbstractMonster abstractMonster) {
        switch (chosenBranch()){
            case 0:
                addToBot(new SFXAction("OmenOfRepose"));
                addToBot(new VFXAction((AbstractGameEffect)new MiracleEffect(Color.GOLD.cpy(),Color.WHITE.cpy(),"HEAL_3")));
                addToBot(new HealAction(p,p,1));
                addToBot(new ApplyPowerAction(p,p,new DrawCardNextTurnPower(p,1),1));
                addToBot(new ReduceAllCountDownAction(1));
                addToBot(new ApplyPowerAction(p,p,new RealmOfReposePower(p,1),1));
                addToBot(new SkipEnemiesTurnAction());
                addToBot(new PressEndTurnButtonAction());
                break;
            case 1:
                addToBot(new SFXAction("OmenOfRepose2"));
                int amt = 0;
                for (AbstractCard c : AbstractDungeon.player.hand.group) {
                    if (c.type != CardType.SKILL && c.type != CardType.POWER){
                        addToTop(new ExhaustSpecificCardAction(c, AbstractDungeon.player.hand));
                        amt++;
                    }
                }
                for (AbstractMonster mo : (AbstractDungeon.getCurrRoom()).monsters.monsters) {
                    if (!mo.isDeadOrEscaped()){
                        amt++;
                        addToBot(new ApplyPowerAction(mo, p, new WeakPower(mo, 4, false), 4, true, AbstractGameAction.AttackEffect.NONE));
                        addToBot(new ApplyPowerAction(mo, p, new VulnerablePower(mo, 4, false), 4, true, AbstractGameAction.AttackEffect.NONE));
                    }
                }
                if (amt>=4){
                    addToBot(new MakeTempCardInHandAction(new FlowOfDispair()));
                }
                break;
        }
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new OmenOfRepose();
    }

    @Override
    public List<UpgradeBranch> possibleBranches() {
            ArrayList<UpgradeBranch> list = new ArrayList<UpgradeBranch>();
            list.add(new UpgradeBranch() {
                @Override
                public void upgrade() {
                    ++OmenOfRepose.this.timesUpgraded;
                    OmenOfRepose.this.upgraded = true;
                    OmenOfRepose.this.name = NAME + "+";
                    OmenOfRepose.this.initializeTitle();
                    OmenOfRepose.this.upgradeBaseCost(2);
                }
            });
            list.add(new UpgradeBranch() {
                @Override
                public void upgrade() {
                    ++OmenOfRepose.this.timesUpgraded;
                    OmenOfRepose.this.upgraded = true;
                    OmenOfRepose.this.textureImg = IMG_PATH2;
                    OmenOfRepose.this.loadCardImage(IMG_PATH2);
                    OmenOfRepose.this.name = cardStrings2.NAME;
                    OmenOfRepose.this.initializeTitle();
                    OmenOfRepose.this.rawDescription = cardStrings2.DESCRIPTION;
                    OmenOfRepose.this.initializeDescription();
                    OmenOfRepose.this.cardsToPreview = new FlowOfDispair();
                    OmenOfRepose.this.upgradeBaseCost(2);
                }
            });
            return list;
    }
}


