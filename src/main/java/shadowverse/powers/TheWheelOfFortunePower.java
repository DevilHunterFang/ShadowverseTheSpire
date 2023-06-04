package shadowverse.powers;

import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.mod.stslib.actions.common.StunMonsterAction;
import com.evacipated.cardcrawl.mod.stslib.powers.abstracts.TwoAmountPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import shadowverse.cards.Neutral.Temp.AnalyzeArtifact;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class TheWheelOfFortunePower
        extends TwoAmountPower {
    public static final String POWER_ID = "shadowverse:TheWheelOfFortunePower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("shadowverse:TheWheelOfFortunePower");
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private ArrayList<AbstractGameAction> toApply(){
        ArrayList<AbstractGameAction> list = new ArrayList<>();
        list.add((AbstractGameAction)new MakeTempCardInHandAction((AbstractCard)new AnalyzeArtifact()));
        list.add((AbstractGameAction)new ApplyPowerAction(this.owner,this.owner,(AbstractPower)new StrengthPower(this.owner,-2),-2));
        list.add((AbstractGameAction)new StunMonsterAction((AbstractMonster) this.owner,this.owner));
        return list;
    }
    private static List<Integer> sList = Arrays.asList(0,1,2);
    private List<Integer> list = new ArrayList<>();

    public TheWheelOfFortunePower(AbstractCreature owner, int amount,int amount2) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        this.amount2 = amount2;
        this.type = PowerType.DEBUFF;
        updateDescription();
        this.img = new Texture("img/powers/TheWheelOfFortunePower.png");
        for (Integer i : sList){
            list.add(i);
        }
    }

    public void stackPower(int stackAmount) {
        this.amount += stackAmount;
        if (this.amount >= 999)
            this.amount = 999;
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount*this.amount2 + DESCRIPTIONS[1];
    }


    @Override
    public void atStartOfTurn() {
            if (this.amount2 > 0) {
                this.amount2--;
                addToBot((AbstractGameAction)new SFXAction("TheWheelOfFortunePower"));
                if (list.size()>0){
                    Collections.shuffle(list);
                    addToBot(toApply().get(list.get(0)));
                    list.remove(0);
                }
                if (this.amount2 == 0){
                    if (this.amount>1) {
                        this.amount2 = 3;
                        for (Integer i : sList){
                            list.add(i);
                        }
                    }
                    addToBot((AbstractGameAction)new ReducePowerAction(this.owner,this.owner,this,1));
                }
            }
        updateDescription();
    }

}

