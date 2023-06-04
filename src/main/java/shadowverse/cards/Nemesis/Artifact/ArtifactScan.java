package shadowverse.cards.Nemesis.Artifact;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Nemesis;

import java.util.ArrayList;
import java.util.Collections;

public class ArtifactScan
        extends CustomCard {
    public static final String ID = "shadowverse:ArtifactScan";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:ArtifactScan");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/ArtifactScan.png";

    public ArtifactScan() {
        super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.SKILL, Nemesis.Enums.COLOR_SKY, CardRarity.COMMON, CardTarget.SELF);
        this.isEthereal = true;
    }



    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBaseCost(0);
            this.isEthereal = false;
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }


    public void use(AbstractPlayer p, AbstractMonster abstractMonster) {
        ArrayList<AbstractCard> list = new ArrayList<>();
        ArrayList<String> dup = new ArrayList<>();
        for (AbstractCard c: p.exhaustPile.group){
            if (c.hasTag(AbstractShadowversePlayer.Enums.ARTIFACT)&&!dup.contains(c.cardID)){
                dup.add(c.cardID);
                AbstractCard card = c.makeCopy();
                list.add(card);
            }
        }
        if (list.size()>0){
            Collections.shuffle(list);
            for (AbstractCard ca:list){
                if (list.size()>=6)
                    ca.setCostForTurn(0);
            }
            addToBot((AbstractGameAction)new MakeTempCardInHandAction(list.get(0)));
            if (list.size()>1)
                addToBot((AbstractGameAction)new MakeTempCardInHandAction(list.get(1)));
        }
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new ArtifactScan();
    }
}


