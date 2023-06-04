package shadowverse.monsters;

import basemod.abstracts.CustomMonster;
import basemod.animations.SpriterAnimation;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.MonsterStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.cards.Neutral.Temp.Horse;

public class Henchman extends CustomMonster {
    public static final String ID = "shadowverse:Henchman";
    private static final MonsterStrings monsterStrings = CardCrawlGame.languagePack.getMonsterStrings("shadowverse:Henchman");
    public static final String NAME = monsterStrings.NAME;

    public static final String[] MOVES = monsterStrings.MOVES;

    public static final String[] DIALOG = monsterStrings.DIALOG;

    public static final int HP_MIN = 24;

    public static final int HP_MAX = 30;

    public static final int A_2_HP_MIN = 24;

    public static final int A_2_HP_MAX = 30;

    private int shootDmg;
    private int shootAmt;
    private static final String START = MOVES[0];

    public Henchman(float x, float y) {
        super(NAME, ID, AbstractDungeon.monsterHpRng.random(20, 24), -5.0F, -20.0F, 145.0F, 330.0F, null, x, y);
        this.animation = new SpriterAnimation("img/monsters/Henchman/Henchman.scml");
        if (AbstractDungeon.ascensionLevel >= 7) {
            setHp(HP_MIN, HP_MAX);
        } else {
            setHp(A_2_HP_MIN, A_2_HP_MAX);
        }
        if (AbstractDungeon.ascensionLevel >= 17) {
            this.shootDmg = 6;
            this.shootAmt = 1;
        } else if (AbstractDungeon.ascensionLevel >= 2) {
            this.shootDmg = 5;
            this.shootAmt = 1;
        } else {
            this.shootDmg = 4;
            this.shootAmt = 1;
        }
        this.damage.add(new DamageInfo(this, this.shootDmg));
    }

    @Override
    public void takeTurn() {
        for (int i = 0; i < this.shootAmt; i++) {
            AbstractDungeon.actionManager.addToBottom(new DamageAction(AbstractDungeon.player, this.damage
                    .get(0), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
        }
        AbstractDungeon.actionManager.addToBottom(new RollMoveAction(this));
    }

    @Override
    protected void getMove(int num) {
        setMove(START, (byte) 1, Intent.ATTACK, this.damage.get(0).base, this.shootAmt, false);
    }

    public void getMove() {
        getMove(1);
    }

    @Override
    public void die() {
        AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(new Horse()));
        super.die();
        for (AbstractMonster m : (AbstractDungeon.getCurrRoom()).monsters.monsters) {
            if (m.isDying || m.isDead) {
                continue;
            }
            if (m.hasPower("shadowverse:NahtPower")) {
                AbstractDungeon.actionManager.addToBottom(new RollMoveAction(m));
            }
        }
    }
}
