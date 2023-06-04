package shadowverse.cards.Bishop.Amulet2;

import basemod.abstracts.CustomCard;
import com.evacipated.cardcrawl.mod.stslib.actions.common.MoveCardsAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.cards.AbstractAmuletCard;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Bishop;

import java.util.ArrayList;
import java.util.Collections;

public class WardenOfTheWings extends CustomCard {
    public static final String ID = "shadowverse:WardenOfTheWings";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:WardenOfTheWings");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/WardenOfTheWings.png";


    public WardenOfTheWings() {
        super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.ATTACK, Bishop.Enums.COLOR_WHITE, CardRarity.UNCOMMON, CardTarget.ENEMY);
        this.baseDamage = 12;
        this.tags.add(AbstractShadowversePlayer.Enums.CONDEMNED);
    }



    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeDamage(5);
        }
    }

    public void applyPowers() {
        super.applyPowers();
        int amt = 0;
        if (AbstractDungeon.player instanceof AbstractShadowversePlayer){
            AbstractShadowversePlayer w = (AbstractShadowversePlayer) AbstractDungeon.player;
            amt = w.amuletCount;
        }
        this.rawDescription = cardStrings.DESCRIPTION;
        this.rawDescription += cardStrings.EXTENDED_DESCRIPTION[0]+amt;
        this.rawDescription += cardStrings.EXTENDED_DESCRIPTION[1];
        this.initializeDescription();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster abstractMonster) {
        addToBot(new DamageAction(abstractMonster,new DamageInfo(p,this.damage,this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        ArrayList<AbstractCard> list = new ArrayList<>();
        for (AbstractCard c : p.drawPile.group){
            if (c instanceof AbstractAmuletCard && c.cost == 1){
                list.add(c);
            }
        }
        if (list.size() > 0){
            Collections.shuffle(list);
            AbstractCard toAdd = list.get(0);
            addToBot(new MoveCardsAction(p.hand,p.drawPile,card -> card == toAdd));
        }
        int count = 0;
        if (p instanceof AbstractShadowversePlayer){
            count= ((AbstractShadowversePlayer) p).amuletCount;
        }
        if (count>=5){
            addToBot(new GainEnergyAction(1));
        }
    }


    @Override
    public AbstractCard makeCopy() {
        return new WardenOfTheWings();
    }
}
