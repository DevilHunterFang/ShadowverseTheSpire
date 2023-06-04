package shadowverse.powers;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.powers.AbstractPower;
import shadowverse.action.PlaceAmulet;
import shadowverse.cards.Neutral.Temp.RelicPlaton;
import shadowverse.cards.Neutral.Temp.RelicPrism;
import shadowverse.cards.Neutral.Temp.RelicTorus;
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
                }
                if (toPlace.size()>0){
                    AbstractCard card = toPlace.get(AbstractDungeon.cardRandomRng.random(toPlace.size()-1));
                    if (card instanceof RelicPrism)
                        addToBot((AbstractGameAction)new SFXAction("RelicPrism"));
                    if (card instanceof RelicTorus)
                        addToBot((AbstractGameAction)new SFXAction("RelicTorus"));
                    if (card instanceof RelicPlaton)
                        addToBot((AbstractGameAction)new SFXAction("RelicPlaton"));
                    addToBot((AbstractGameAction)new PlaceAmulet(card,null));
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
