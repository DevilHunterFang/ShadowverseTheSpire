package shadowverse.patch;

import shadowverse.cards.Bishop.Recover2.OrchidExaminationHall;
import shadowverseCharbosses.actions.RealWaitAction;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.relics.PrismaticShard;
import shadowverse.action.PlaceAmulet;
import shadowverse.cards.AbstractAmuletCard;
import shadowverse.cards.AbstractCrystalizeCard;
import shadowverse.cards.Neutral.Temp.NaterranGreatTree;
import shadowverse.cards.AbstractNoCountDownAmulet;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Bishop;
import shadowverse.characters.Royal;
import shadowverse.orbs.AmuletOrb;
import shadowverse.orbs.ErikaOrb;
import shadowverse.powers.NaterranTree;
import shadowverse.relics.AlterplaneArbiter;

public class PlaceAmuletPatch {
    @SpirePatch(clz = AbstractPlayer.class, method = "useCard")
    public static class placeCardPatch {
        @SpirePostfixPatch
        public static void placeA(AbstractPlayer p, AbstractCard c, AbstractMonster monster, int energyOnUse) {
            if (p instanceof Bishop || p instanceof Royal ||
                    ((AbstractDungeon.player.hasRelic(PrismaticShard.ID)|| (AbstractDungeon.player.hasRelic(AlterplaneArbiter.ID)))&&AbstractDungeon.player instanceof AbstractShadowversePlayer)){
                if (c instanceof AbstractAmuletCard || (c instanceof AbstractCrystalizeCard && c.type== AbstractCard.CardType.POWER)){
                    if (c instanceof OrchidExaminationHall){
                        if (c.costForTurn == 1){
                            return;
                        }
                    }
                    AbstractDungeon.actionManager.addToBottom(new RealWaitAction(0.6F));
                    AbstractDungeon.actionManager.addToBottom(new PlaceAmulet(c,p.hand));
                }
                if (c instanceof AbstractNoCountDownAmulet && !c.hasTag(AbstractShadowversePlayer.Enums.MINION)){
                    if (c instanceof NaterranGreatTree){
                        if (!p.hasPower(NaterranTree.POWER_ID)){
                            AbstractDungeon.actionManager.addToBottom(new RealWaitAction(0.6F));
                            AbstractDungeon.actionManager.addToBottom(new PlaceAmulet(c,p.hand));
                        }
                    }else {
                        AbstractDungeon.actionManager.addToBottom(new RealWaitAction(0.6F));
                        AbstractDungeon.actionManager.addToBottom(new PlaceAmulet(c,p.hand));
                    }
                }
            }
        }

        @SpirePostfixPatch
        public static void trigger(AbstractPlayer p, AbstractCard c, AbstractMonster monster, int energyOnUse){
            for (AbstractOrb o : p.orbs){
                if (o instanceof AmuletOrb){
                    if (((AmuletOrb) o).amulet instanceof AbstractAmuletCard){
                        ((AbstractAmuletCard)(((AmuletOrb) o).amulet)).onOtherCardPlayed(c,(AmuletOrb) o);
                    }
                    if (((AmuletOrb) o).amulet instanceof AbstractCrystalizeCard){
                        ((AbstractCrystalizeCard)(((AmuletOrb) o).amulet)).onOtherCardPlayed(c,(AmuletOrb) o);
                    }
                    if (((AmuletOrb) o).amulet instanceof AbstractNoCountDownAmulet){
                        ((AbstractNoCountDownAmulet)(((AmuletOrb) o).amulet)).onOtherCardPlayed(c,(AmuletOrb) o);
                    }
                }
                if (o instanceof ErikaOrb){
                    ((ErikaOrb)o).onOtherCardPlayed(c,o);
                }
            }
        }
    }
}
