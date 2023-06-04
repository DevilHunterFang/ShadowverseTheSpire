package shadowverse.cards.Bishop.Ward;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
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
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.characters.Bishop;

public class Shiro extends CustomCard {
    public static final String ID = "shadowverse:Shiro";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Shiro");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/Shiro.png";


    public Shiro() {
        super(ID, NAME, IMG_PATH, 3, DESCRIPTION, CardType.ATTACK, Bishop.Enums.COLOR_WHITE, CardRarity.UNCOMMON, CardTarget.ALL);
        this.baseBlock = 15;
        this.selfRetain = true;
    }

    @Override
    public void onRetained() {
        if (AbstractDungeon.player.currentBlock>0){
            int costToReduce = AbstractDungeon.player.currentBlock/10;
            if (costToReduce > 3)
                costToReduce = 3;
            for (int i=0;i<costToReduce;i++){
                addToBot((AbstractGameAction)new ReduceCostAction((AbstractCard)this));
            }
        }
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBlock(5);
        }
    }


    @Override
    public void use(AbstractPlayer p, AbstractMonster abstractMonster) {
        addToBot((AbstractGameAction)new SFXAction("Shiro"));
        addToBot((AbstractGameAction)new GainBlockAction(p,this.block));
        int blk = (p.currentBlock+this.block)/2;
        addToBot((AbstractGameAction) new DamageAllEnemiesAction((AbstractCreature) p, DamageInfo.createDamageMatrix(blk, true), DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.FIRE, true));
        this.cost = 3;
    }


    @Override
    public AbstractCard makeCopy() {
        return new Shiro();
    }
}
