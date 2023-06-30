package shadowverse.powers;

import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.mod.stslib.actions.defect.EvokeSpecificOrbAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.powers.AbstractPower;
import shadowverse.action.PlaceAmulet;
import shadowverse.cards.Neutral.Temp.*;
import shadowverse.orbs.AmuletOrb;

import java.util.ArrayList;
import java.util.List;

public class ErisPower extends AbstractPower {
    public static final String POWER_ID = "shadowverse:ErisPower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("shadowverse:ErisPower");
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    boolean upgraded;

    public ErisPower(AbstractCreature owner,boolean upgraded) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = -1;
        this.upgraded = upgraded;
        this.type = PowerType.BUFF;
        updateDescription();
        this.img = new Texture("img/powers/ErisPower.png");
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        if (isPlayer){
            List<AbstractCard> list = new ArrayList<>();
            AbstractCard p = new RelicPrism();
            AbstractCard t = new RelicTorus();
            AbstractCard pl = new RelicPlaton();
            boolean hasSphere = false;
            boolean hasGod = false;
            if (upgraded){
                p.upgrade();
                t.upgrade();
                pl.upgrade();
            }
            List<AbstractCard> toPlace = new ArrayList<AbstractCard>();
            toPlace.add(p);
            toPlace.add(t);
            toPlace.add(pl);
            for (AbstractOrb o : AbstractDungeon.player.orbs){
                if (o instanceof AmuletOrb){
                    list.add(((AmuletOrb) o).amulet);
                }
            }
            if (list.size()>0){
                for (AbstractCard c:list){
                    if (c instanceof RelicPrism)
                        toPlace.remove(p);
                    if (c instanceof RelicTorus)
                        toPlace.remove(t);
                    if (c instanceof RelicPlaton)
                        toPlace.remove(pl);
                    if (c instanceof RelicSphere)
                        hasSphere = true;
                    if (c instanceof RelicGod)
                        hasGod = true;
                }
                if (toPlace.size() == 1 && hasSphere && !hasGod){
                    toPlace.clear();
                    for (AbstractOrb o: AbstractDungeon.player.orbs){
                        if (o instanceof AmuletOrb){
                            if (((AmuletOrb) o).amulet instanceof RelicPrism || ((AmuletOrb) o).amulet instanceof RelicTorus ||((AmuletOrb) o).amulet instanceof RelicPlaton ||((AmuletOrb) o).amulet instanceof RelicSphere)
                                addToBot(new EvokeSpecificOrbAction(o));
                        }
                    }
                    AbstractCard god = new RelicGod();
                    if (upgraded)
                        god.upgrade();
                    addToBot(new PlaceAmulet(god,null));
                    addToBot(new ApplyPowerAction(AbstractDungeon.player,AbstractDungeon.player,new HeavenlyAegisPower(AbstractDungeon.player)));
                }
                if (toPlace.size()>0){
                    AbstractCard card = toPlace.get(AbstractDungeon.cardRandomRng.random(toPlace.size()-1));
                    if (card instanceof RelicPrism)
                        addToBot(new SFXAction("RelicPrism"));
                    if (card instanceof RelicTorus)
                        addToBot(new SFXAction("RelicTorus"));
                    if (card instanceof RelicPlaton)
                        addToBot(new SFXAction("RelicPlaton"));
                    addToBot(new PlaceAmulet(card,null));
                }
            }
        }
    }

    public void updateDescription() {
        if (this.upgraded){
            this.description = DESCRIPTIONS[1];
        }else {
            this.description = DESCRIPTIONS[0];
        }
    }
}
