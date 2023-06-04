package shadowverse.cards.Bishop.Evil;

import com.badlogic.gdx.Gdx;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.WhirlwindEffect;
import shadowverse.cards.AbstractAmuletCard;
import shadowverse.cards.Neutral.Curse.EvilWorship;
import shadowverse.cards.Neutral.Temp.Astaroth;
import shadowverse.cards.Neutral.Temp.Dis;
import shadowverse.cards.Neutral.Temp.Servant;
import shadowverse.cards.Neutral.Temp.SilentRider;
import shadowverse.characters.Bishop;
import shadowverse.orbs.AmuletOrb;

import java.util.ArrayList;

public class TarnishedGrail extends AbstractAmuletCard {
    public static final String ID = "shadowverse:TarnishedGrail";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:TarnishedGrail");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/TarnishedGrail.png";
    private float rotationTimer;
    private int previewIndex;
    public static ArrayList<AbstractCard> returnChoice() {
        ArrayList<AbstractCard> list = new ArrayList<>();
        list.add(new Servant());
        list.add(new SilentRider());
        list.add(new Dis());
        list.add(new Astaroth());
        list.add(new EvilWorship());
        return list;
    }

    public TarnishedGrail() {
        super(ID, NAME, IMG_PATH, 3, DESCRIPTION, Bishop.Enums.COLOR_WHITE, CardRarity.UNCOMMON, CardTarget.ALL);
        this.countDown = 1;
        this.baseDamage = 12;
        this.isMultiDamage = true;
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
                if (this.upgraded)
                    this.cardsToPreview.upgrade();
            } else {
                this.rotationTimer -= Gdx.graphics.getDeltaTime();
            }
    }

    @Override
    public void onStartOfTurn(AmuletOrb paramOrb) {

    }

    @Override
    public void onEvoke(AmuletOrb paramOrb) {
        int rnd = AbstractDungeon.cardRandomRng.random(3);
        int rnd2 = rnd+1>3?0:rnd+1;
        int rnd3 = rnd2+1>3?0:rnd2+1;
        AbstractCard c1 = returnChoice().get(rnd);
        AbstractCard c2 = returnChoice().get(rnd2);
        AbstractCard c3 = returnChoice().get(rnd3);
        addToBot((AbstractGameAction)new MakeTempCardInHandAction(c1));
        addToBot((AbstractGameAction)new MakeTempCardInHandAction(c2));
        addToBot((AbstractGameAction)new MakeTempCardInHandAction(c3));
    }

    @Override
    public void endOfTurn(AmuletOrb paramOrb) {
    }

    @Override
    public int onHeal(int healAmount, AmuletOrb paramOrb) {
        return healAmount;
    }

    @Override
    public void onOtherCardPlayed(AbstractCard c, AmuletOrb paramOrb) {

    }

    @Override
    public void onGainedBlock(int blockAmt, AmuletOrb paramOrb) {

    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBaseCost(2);
        }
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot((AbstractGameAction)new VFXAction((AbstractGameEffect)new WhirlwindEffect(), 0.1F));
        addToBot(new DamageAllEnemiesAction(abstractPlayer, this.multiDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.NONE));
        addToBot((AbstractGameAction)new MakeTempCardInDiscardAction(new EvilWorship(),2));
    }
}
