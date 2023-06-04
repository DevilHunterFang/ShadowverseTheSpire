package shadowverse.cards.Bishop.Evil;

import com.badlogic.gdx.Gdx;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import shadowverse.cards.AbstractAmuletCard;
import shadowverse.cards.Neutral.Curse.EvilWorship;
import shadowverse.cards.Neutral.Neutral.OldSatan;
import shadowverse.cards.Neutral.Neutral.Satan;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Bishop;
import shadowverse.orbs.AmuletOrb;

import java.util.ArrayList;

public class TempleOfHeresy extends AbstractAmuletCard {
    public static final String ID = "shadowverse:TempleOfHeresy";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:TempleOfHeresy");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/TempleOfHeresy.png";
    private float rotationTimer;
    private int previewIndex;
    public static ArrayList<AbstractCard> returnChoice() {
        ArrayList<AbstractCard> list = new ArrayList<>();
        list.add(new Satan());
        list.add(new OldSatan());
        list.add(new EvilWorship());
        return list;
    }

    public TempleOfHeresy() {
        super(ID, NAME, IMG_PATH, 0, DESCRIPTION, Bishop.Enums.COLOR_WHITE, CardRarity.UNCOMMON, CardTarget.SELF);
        this.countDown = 6;
        this.tags.add(AbstractShadowversePlayer.Enums.AMULET_FOR_ONECE);
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
        for (AbstractOrb o:AbstractDungeon.player.orbs){
            if (o instanceof AmuletOrb){
                if (((AmuletOrb) o).amulet.type == CardType.CURSE){
                    if (paramOrb.passiveAmount > 0) {
                        paramOrb.passiveAmount--;
                        paramOrb.evokeAmount--;
                        paramOrb.updateDescription();
                    }
                    if (paramOrb.passiveAmount <= 0){
                        break;
                    }
                }
            }
        }
    }

    @Override
    public void onEvoke(AmuletOrb paramOrb) {
        AbstractCard stan = returnChoice().get(AbstractDungeon.cardRandomRng.random(1)).makeStatEquivalentCopy();
        stan.costForTurn = 0;
        stan.isCostModifiedForTurn = true;
        stan.cost = 0;
        stan.isCostModified = true;
        addToBot((AbstractGameAction)new MakeTempCardInHandAction(stan));
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
            this.isInnate = true;
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot((AbstractGameAction)new MakeTempCardInDrawPileAction(new EvilWorship(),1,true,true,false));
    }
}
