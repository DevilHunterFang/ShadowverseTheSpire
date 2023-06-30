package shadowverse.patch;

import basemod.ReflectionHacks;
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.powers.DiscardPower;

public class DiscardPatch {
    public static int amount = 0;
    @SpirePatch(clz = DiscardAction.class,method = "update")
    public static class DiscardActionPatch{
        @SpireInsertPatch(rloc = 7)
        public static void triggerBeforeDiscard(DiscardAction discardAction){
            if (!(boolean)ReflectionHacks.getPrivate(discardAction,DiscardAction.class,"endTurn")){
                if (AbstractDungeon.player.hand.size() > 0){
                    if (AbstractDungeon.player instanceof AbstractShadowversePlayer){
                        ((AbstractShadowversePlayer) AbstractDungeon.player).discardCount++;
                    }
                    for (AbstractPower power : AbstractDungeon.player.powers){
                        if (power instanceof DiscardPower){
                            ((DiscardPower) power).triggerOnDiscard(AbstractDungeon.player.hand.size());
                        }
                    }
                }
            }
        }

        @SpireInsertPatch(rloc = 58)
        public static void triggerDiscard(DiscardAction discardAction){
            if (!(boolean)ReflectionHacks.getPrivate(discardAction,DiscardAction.class,"endTurn")){
                if (AbstractDungeon.player.hand.size() > 0){
                    if (AbstractDungeon.player instanceof AbstractShadowversePlayer){
                        ((AbstractShadowversePlayer) AbstractDungeon.player).discardCount++;
                    }
                    for (AbstractPower power : AbstractDungeon.player.powers){
                        if (power instanceof DiscardPower){
                            ((DiscardPower) power).triggerOnDiscard(discardAction.amount);
                        }
                    }
                }
            }
        }
    }
}
