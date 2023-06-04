package shadowverse.cards.Necromancer.Mech;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.actions.watcher.ChooseOneAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.action.NecromanceAction;
import shadowverse.action.ReanimateAction;
import shadowverse.cards.Neutral.Temp.AeneaFriendship;
import shadowverse.cards.Neutral.Temp.GenerateNine;
import shadowverse.cards.Neutral.Temp.LunaGame;
import shadowverse.characters.Necromancer;

import java.util.ArrayList;


public class FriendsForever
        extends CustomCard {
    public static final String ID = "shadowverse:FriendsForever";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:FriendsForever");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/FriendsForever.png";

    public FriendsForever() {
        super(ID, NAME, IMG_PATH, 2, DESCRIPTION, CardType.SKILL, Necromancer.Enums.COLOR_PURPLE, CardRarity.UNCOMMON, CardTarget.SELF);
        this.exhaust = true;
        this.isEthereal = true;
        this.cardsToPreview = (AbstractCard)new GenerateNine();
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            this.isEthereal = false;
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }


    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        ArrayList<AbstractCard> stanceChoices = new ArrayList<>();
        stanceChoices.add(new LunaGame());
        stanceChoices.add(new AeneaFriendship());
        AbstractGameAction[] gameActions = new AbstractGameAction[]{
                (AbstractGameAction) new ReanimateAction(2),
                (AbstractGameAction) new ReanimateAction(1),
                (AbstractGameAction) new ReanimateAction(0),
                (AbstractGameAction) new HealAction(abstractPlayer, AbstractDungeon.player, 4),
                (AbstractGameAction) new MakeTempCardInHandAction(this.cardsToPreview.makeStatEquivalentCopy()),
                (AbstractGameAction) new SFXAction("FriendsForever")
        };
        addToBot((AbstractGameAction) new NecromanceAction(4, (AbstractGameAction) new ChooseOneAction(stanceChoices), gameActions));
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new FriendsForever();
    }
}

