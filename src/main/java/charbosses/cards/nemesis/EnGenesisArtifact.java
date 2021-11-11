package charbosses.cards.nemesis;

import charbosses.actions.common.EnemyMakeTempCardInDrawPileAction;
import charbosses.actions.common.EnemyMakeTempCardInHandAction;
import charbosses.cards.AbstractBossCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.ArtifactPower;
import shadowverse.cards.Temp.CannonArtifact;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Nemesis;

public class EnGenesisArtifact extends AbstractBossCard {
    public static final String ID = "shadowverse:EnGenesisArtifact";

    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:EnGenesisArtifact");

    public static final String IMG_PATH = "img/cards/GenesisArtifact.png";

    public EnGenesisArtifact() {
        super(ID, cardStrings.NAME, IMG_PATH, 2, cardStrings.DESCRIPTION, CardType.ATTACK, Nemesis.Enums.COLOR_SKY, CardRarity.RARE, CardTarget.SELF, AbstractMonster.Intent.DEFEND_BUFF);
        this.baseBlock = 12;
        this.exhaust = true;
        this.tags.add(AbstractShadowversePlayer.Enums.ARTIFACT);
        this.cardsToPreview = new CannonArtifact();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot((AbstractGameAction)new GainBlockAction(m,this.block));
        addToBot((AbstractGameAction)new EnemyMakeTempCardInHandAction(new EnDefectArtifact()));
        addToBot((AbstractGameAction)new EnemyMakeTempCardInHandAction(new EnGuardArtifact()));
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBlock(6);
        }
    }

    public AbstractCard makeCopy() {
        return (AbstractCard)new EnGenesisArtifact();
    }
}
