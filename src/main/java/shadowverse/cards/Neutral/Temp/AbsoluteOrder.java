package shadowverse.cards.Neutral.Temp;

import basemod.abstracts.CustomCard;
import com.badlogic.gdx.Gdx;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DoubleDamagePower;
import shadowverse.action.ChoiceAction2;
import shadowverse.characters.*;

import java.util.ArrayList;

public class AbsoluteOrder extends CustomCard {
    public static final String ID = "shadowverse:AbsoluteOrder";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:AbsoluteOrder");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/TyrantsOrder.png";
    private float rotationTimer;
    private int previewIndex;
    public static ArrayList<AbstractCard> returnChoice() {
        ArrayList<AbstractCard> list = new ArrayList<>();
        list.add(new Sekka_Story());
        list.add(new Dracu_Story());
        list.add(new Kagero_Story());
        return list;
    }

    public AbsoluteOrder() {
        super(ID, NAME, IMG_PATH, 0, DESCRIPTION, CardType.SKILL, Royal.Enums.COLOR_YELLOW, CardRarity.SPECIAL, CardTarget.SELF);
        this.exhaust = true;
        this.selfRetain = true;
    }

    public void update() {
        super.update();
        if (this.hb.hovered)
            if (this.rotationTimer <= 0.0F) {
                this.rotationTimer = 2.0F;
                this.cardsToPreview = (AbstractCard)returnChoice().get(previewIndex).makeCopy();
                if (this.previewIndex == returnChoice().size() - 1) {
                    this.previewIndex = 0;
                } else {
                    this.previewIndex++;
                }
            } else {
                this.rotationTimer -= Gdx.graphics.getDeltaTime();
            }
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
        }
    }


    public void use(AbstractPlayer p, AbstractMonster m) {
        int count = 0;
        ArrayList<AbstractCard> token = returnChoice();
        if (p.chosenClass== Elf.Enums.Elf){
            token.remove(0);
        }else if (p.chosenClass == Necromancer.Enums.Necromancer){
            token.remove(2);
        }
        for (AbstractCard c:AbstractDungeon.actionManager.cardsPlayedThisCombat){
            if (c instanceof AbsoluteOrder){
                count++;
            }
        }
        if (count<3){
            addToBot((AbstractGameAction)new ChoiceAction2(token.toArray(new AbstractCard[token.size()])));
        }else {
            for (AbstractCard card:token){
                card.upgrade();
                addToBot(new MakeTempCardInHandAction(card));
            }
            addToBot((AbstractGameAction)new ApplyPowerAction(p,p,new DoubleDamagePower(p,1,false)));
        }
    }


    public AbstractCard makeCopy() {
        return new AbsoluteOrder();
    }
}
