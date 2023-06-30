package shadowverse.monsters;

import basemod.abstracts.CustomMonster;
import basemod.animations.SpriterAnimation;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.ShoutAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.utility.HideHealthBarAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.status.Dazed;
import com.megacrit.cardcrawl.cards.status.Wound;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.MonsterStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.beyond.Exploder;
import com.megacrit.cardcrawl.monsters.city.Centurion;
import com.megacrit.cardcrawl.monsters.city.Healer;
import com.megacrit.cardcrawl.monsters.city.SnakePlant;
import com.megacrit.cardcrawl.monsters.exordium.*;
import com.megacrit.cardcrawl.powers.*;
import com.megacrit.cardcrawl.vfx.combat.InflameEffect;
import com.megacrit.cardcrawl.vfx.combat.VerticalImpactEffect;
import shadowverse.powers.HeroOfTheHuntPower;
import shadowverse.powers.TaketsumiPower;

import java.util.HashMap;

public class TaketsumiBOSS extends CustomMonster implements SpriteCreature {
    public static final String ID = "shadowverse:TaketsumiBOSS";
    private static final MonsterStrings monsterStrings = CardCrawlGame.languagePack.getMonsterStrings("shadowverse:TaketsumiBOSS");
    public static final String NAME = monsterStrings.NAME;

    public static final String[] MOVES = monsterStrings.MOVES;

    public static final String[] DIALOG = monsterStrings.DIALOG;

    private int heavyDmg;

    private int multiDmg;

    private int strAmount;

    private int debuffAmount;

    private int blockAmount;

    private float spawnX = -100.0F;

    private HashMap<Integer, AbstractMonster> enemySlots = new HashMap<>();

    private static final String MEMORY = MOVES[0];

    private static final String HUNT = MOVES[1];

    private static final String SLASH = MOVES[2];

    @Override
    public void setAnimation(SpriterAnimation animation) {
        this.animation = animation;
    }

    @Override
    public SpriterAnimation getAnimation() {
        return (SpriterAnimation) this.animation;
    }

    public TaketsumiBOSS() {
        super(NAME, ID, 1200, 0.0F, -30F, 340.0F, 420.0F, null, 60.0F, 130.0F);
        this.animation = new SpriterAnimation("img/monsters/Taketsumi/Taketsumi.scml");
        this.dialogX = -100.0F * Settings.scale;
        this.dialogY = 10.0F * Settings.scale;
        this.type = EnemyType.BOSS;
        if (AbstractDungeon.ascensionLevel >= 9) {
            setHp(1440);
        } else {
            setHp(1200);
        }
        if (AbstractDungeon.ascensionLevel >= 19) {
            this.heavyDmg = 24;
            this.multiDmg = 3;
            this.strAmount = 3;
            this.debuffAmount = 2;
            this.blockAmount = 20;
        } else if (AbstractDungeon.ascensionLevel >= 4) {
            this.heavyDmg = 24;
            this.multiDmg = 2;
            this.strAmount = 2;
            this.debuffAmount = 2;
            this.blockAmount = 18;
        } else {
            this.heavyDmg = 22;
            this.multiDmg = 2;
            this.strAmount = 2;
            this.debuffAmount = 1;
            this.blockAmount = 15;
        }
        this.damage.add(new DamageInfo(this, this.heavyDmg));
        this.damage.add(new DamageInfo(this, this.multiDmg));
    }

    @Override
    public void usePreBattleAction() {
        CardCrawlGame.music.unsilenceBGM();
        AbstractDungeon.scene.fadeOutAmbiance();
        AbstractDungeon.getCurrRoom().playBgmInstantly("Ametsuchi");
        addToBot(new SFXAction("Taketsumi_Start"));
        addToBot(new ShoutAction(this, DIALOG[0], 1.0F, 2.0F));
    }


    @Override
    public void takeTurn() {
        addToBot(new SFXAction("MONSTER_COLLECTOR_SUMMON"));
        for (int i = 1; i < 4; i++) {
            if (enemySlots.get(i) != null && !enemySlots.get(i).isDying){
                addToBot(new ApplyPowerAction(enemySlots.get(i),this,new StrengthPower(enemySlots.get(i),strAmount),strAmount));
                addToBot(new ApplyPowerAction(enemySlots.get(i),this,new RegenerateMonsterPower(enemySlots.get(i),strAmount),strAmount));
            }else {
                int rnd = AbstractDungeon.aiRng.random(6);
                AbstractMonster m = null;
                switch (rnd){
                    case 0:
                        m = new Cultist(this.spawnX - 50 - 85.0F * (i + enemySlots.size()), MathUtils.random(-5.0F, 25.0F));
                        break;
                    case 1:
                        m = new SlaverBlue(this.spawnX - 50 - 85.0F * (i + enemySlots.size()), MathUtils.random(-5.0F, 25.0F));
                        break;
                    case 2:
                        m = new SlaverRed(this.spawnX - 50 - 85.0F * (i + enemySlots.size()), MathUtils.random(-5.0F, 25.0F));
                        break;
                    case 3:
                        m = new JawWorm(this.spawnX - 50 - 85.0F * (i + enemySlots.size()), MathUtils.random(-5.0F, 25.0F));
                        break;
                    case 4:
                        m = new Exploder(this.spawnX - 50 - 85.0F * (i + enemySlots.size()), MathUtils.random(-5.0F, 25.0F));
                        break;
                    case 5:
                        m = new Healer(this.spawnX - 50 - 85.0F * (i + enemySlots.size()), MathUtils.random(-5.0F, 25.0F));
                        break;
                    case 6:
                        m = new SpikeSlime_M(this.spawnX - 50 - 85.0F * (i + enemySlots.size()), MathUtils.random(-5.0F, 25.0F));
                        break;
                }
                if (enemySlots.get(i) == null){
                    addToBot(new SpawnMonsterAction(m, true));
                    addToBot(new ApplyPowerAction(m,this,new StrengthPower(m,strAmount),strAmount));
                    addToBot(new ApplyPowerAction(m,this,new RegenerateMonsterPower(m,strAmount),strAmount));
                    addToBot(new ApplyPowerAction(m,this, new TaketsumiPower(m,this)));
                    this.enemySlots.put(i, m);
                }else {
                    if (enemySlots.get(i).isDying){
                        enemySlots.remove(i);
                        addToBot(new SpawnMonsterAction(m, true));
                        addToBot(new ApplyPowerAction(m,this,new StrengthPower(m,strAmount),strAmount));
                        addToBot(new ApplyPowerAction(m,this,new RegenerateMonsterPower(m,strAmount),strAmount));
                        addToBot(new ApplyPowerAction(m,this, new TaketsumiPower(m,this)));
                        this.enemySlots.put(i, m);
                    }
                }
            }
        }
        switch (this.nextMove) {
            case 1:
                addToBot(new ShoutAction(this, DIALOG[1], 1.0F, 2.0F));
                addToBot(new SFXAction("Taketsumi_A3"));
                addToBot(new MakeTempCardInDrawPileAction(new Dazed(),2,true,true,false));
                addToBot(new MakeTempCardInDiscardAction(new Dazed(),2));
                addToBot(new ApplyPowerAction(AbstractDungeon.player,this,new WeakPower(AbstractDungeon.player,debuffAmount,true)));
                break;
            case 2:
                addToBot(new ShoutAction(this, DIALOG[2], 1.0F, 2.0F));
                addToBot(new SFXAction("HeroOfTheHunt"));
                addToBot(new DamageAction(AbstractDungeon.player,this.damage.get(0), AbstractGameAction.AttackEffect.SLASH_HEAVY));
                addToBot(new VFXAction(new VerticalImpactEffect(AbstractDungeon.player.hb.cX + AbstractDungeon.player.hb.width / 4.0F, AbstractDungeon.player.hb.cY - AbstractDungeon.player.hb.height / 4.0F)));
                addToBot(new ApplyPowerAction(this,this,new HeroOfTheHuntPower(this,1)));
                break;
            case 3:
                addToBot(new ShoutAction(this, DIALOG[3], 1.0F, 2.0F));
                addToBot(new SFXAction("Taketsumi_A1"));
                for (int i = 0;i < 6; i++){
                    addToBot(new DamageAction(AbstractDungeon.player,this.damage.get(1), AbstractGameAction.AttackEffect.SLASH_HEAVY));
                }
                addToBot(new MakeTempCardInDiscardAction(new Wound(),debuffAmount));
                addToBot(new ApplyPowerAction(AbstractDungeon.player,this,new DrawReductionPower(AbstractDungeon.player,1),1));
                break;
            case 4:
                addToBot(new ShoutAction(this, DIALOG[4], 1.0F, 2.0F));
                addToBot(new SFXAction("Taketsumi_A2"));
                addToBot(new GainBlockAction(this,blockAmount));
                addToBot(new ApplyPowerAction(this,this,new StrengthPower(this,strAmount),strAmount));
                break;
            default:
                System.out.println("ERROR: Default Take Turn was called on " + this.name);
                break;
        }
        AbstractDungeon.actionManager.addToBottom(new RollMoveAction(this));
    }

    @Override
    protected void getMove(int i) {
        if (i<30 && !lastMove((byte)1)) {
            setMove(MEMORY,(byte)1, Intent.STRONG_DEBUFF);
            return;
        }
        if (i<50&& !lastTwoMoves((byte)2)){
            setMove(HUNT,(byte)2, Intent.ATTACK_BUFF,(this.damage.get(0)).base);
            return;
        }
        if (!lastMove((byte)3)&& !lastMove((byte)3)){
            setMove(SLASH,(byte)3, Intent.ATTACK_DEBUFF,(this.damage.get(1)).base, 6, true);
            return;
        }
        setMove((byte)4, Intent.DEFEND);
    }

    @Override
    public void die() {
        super.die();
        useFastShakeAnimation(5.0F);
        CardCrawlGame.screenShake.rumble(4.0F);
        for (AbstractMonster m : (AbstractDungeon.getCurrRoom()).monsters.monsters) {
            if (m.isDying || m.isDead) {
                continue;
            }
            AbstractDungeon.actionManager.addToTop(new HideHealthBarAction(m));
            AbstractDungeon.actionManager.addToTop(new SuicideAction(m));
            AbstractDungeon.actionManager.addToTop(new VFXAction(m, new InflameEffect(m), 0.2F));
        }
        onBossVictoryLogic();
        onFinalBossVictoryLogic();
    }
}
