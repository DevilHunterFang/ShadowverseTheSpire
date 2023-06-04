package shadowverse.patch;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.actions.common.ReduceCostAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import shadowverse.cards.AbstractAmuletCard;
import shadowverse.cards.AbstractCrystalizeCard;
import shadowverse.cards.AbstractNoCountDownAmulet;
import shadowverse.cards.Bishop.Recover2.ExecutorOfTheOath;
import shadowverse.orbs.AmuletOrb;

public class AmuletOrbPatch {
    @SpirePatch(clz = AbstractCreature.class, method = "addBlock")
    public static class AmuletPatch {
        @SpirePostfixPatch
        public static void triggerBlock(AbstractCreature c,int blockAmt){
            if (blockAmt>0 && c instanceof AbstractPlayer){
                for (AbstractOrb o : ((AbstractPlayer) c).orbs){
                    if (o instanceof AmuletOrb){
                        if (((AmuletOrb) o).amulet instanceof AbstractAmuletCard){
                            ((AbstractAmuletCard)(((AmuletOrb) o).amulet)).onGainedBlock(blockAmt,(AmuletOrb)o);
                        }
                        if (((AmuletOrb) o).amulet instanceof AbstractCrystalizeCard){
                            ((AbstractCrystalizeCard)(((AmuletOrb) o).amulet)).onGainedBlock(blockAmt,(AmuletOrb)o);
                        }
                        if (((AmuletOrb) o).amulet instanceof AbstractNoCountDownAmulet){
                            ((AbstractNoCountDownAmulet)(((AmuletOrb) o).amulet)).onGainedBlock(blockAmt,(AmuletOrb)o);
                        }
                    }
                }
            }
        }
    }

    @SpirePatch(clz = AbstractCreature.class, method = "heal",paramtypes = "int")
    public static class AmuletPatch2{
        @SpirePostfixPatch
        public static void triggerOnHeal(AbstractCreature c,int healAmount){
            if (c instanceof AbstractPlayer){
                for (AbstractOrb o : ((AbstractPlayer) c).orbs){
                    if (o instanceof AmuletOrb){
                        if (((AmuletOrb) o).amulet instanceof AbstractAmuletCard){
                            ((AbstractAmuletCard)(((AmuletOrb) o).amulet)).onHeal(healAmount,(AmuletOrb)o);
                        }
                        if (((AmuletOrb) o).amulet instanceof AbstractCrystalizeCard){
                            ((AbstractCrystalizeCard)(((AmuletOrb) o).amulet)).onHeal(healAmount,(AmuletOrb)o);
                        }
                        if (((AmuletOrb) o).amulet instanceof AbstractNoCountDownAmulet){
                            ((AbstractNoCountDownAmulet)(((AmuletOrb) o).amulet)).onHeal(healAmount,(AmuletOrb)o);
                        }
                    }
                }
                for (AbstractCard ca : ((AbstractPlayer) c).hand.group){
                    if (ca instanceof ExecutorOfTheOath){
                        AbstractDungeon.actionManager.addToBottom(new ReduceCostAction(ca));
                    }
                }
            }
        }
    }

}
