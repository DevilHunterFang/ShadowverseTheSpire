package shadowverse.relics;


import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rewards.RewardItem;
import shadowverse.reward.RareClassReward;


public class FortuneWell extends CustomRelic
{
    public static final String ID = "shadowverse:FortuneWell";
    public static final String IMG = "img/relics/FortuneWell.png";
    public static final String OUTLINE_IMG = "img/relics/outline/FortuneWell_Outline.png";


    public FortuneWell() {
        super(ID, ImageMaster.loadImage(IMG), RelicTier.BOSS, LandingSound.FLAT);
    }

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    public void onEquip() {
        this.counter = 0;
        AbstractDungeon.getCurrRoom().rewards.clear();
        AbstractDungeon.getCurrRoom().rewards.add(new RareClassReward(AbstractDungeon.player.getCardColor()));
        AbstractDungeon.combatRewardScreen.open(this.DESCRIPTIONS[1]);
        AbstractDungeon.getCurrRoom().rewardPopOutTimer = 0.0F;
    }



    public void onVictory() {
        this.counter++;
        if(this.counter==8||this.counter>8&&(this.counter-8)%6==0){
            flash();
            AbstractDungeon.getCurrRoom().addCardReward((RewardItem)new RareClassReward(AbstractDungeon.player.getCardColor()));
        }
    }


    public AbstractRelic makeCopy() {
        return (AbstractRelic)new FortuneWell();
    }
}

