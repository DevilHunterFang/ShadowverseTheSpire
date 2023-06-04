package shadowverse.cards.Royal.Minion;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.WhirlwindEffect;
import rs.lazymankits.interfaces.cards.BranchableUpgradeCard;
import rs.lazymankits.interfaces.cards.UpgradeBranch;
import shadowverse.characters.Royal;
import shadowverse.powers.CaptainWalfridPower;

import java.util.ArrayList;
import java.util.List;

public class CaptainWalfrid extends CustomCard implements BranchableUpgradeCard {
    public static final String ID = "shadowverse:CaptainWalfrid";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:CaptainWalfrid");
    public static CardStrings cardStrings2 = CardCrawlGame.languagePack.getCardStrings("shadowverse:Walfrid");
    public static final String NAME = cardStrings.NAME;
    public static final String NAME2 = cardStrings2.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String DESCRIPTION2 = cardStrings2.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/CaptainWalfrid.png";
    public static final String IMG_PATH2 = "img/cards/Walfrid.png";

    public CaptainWalfrid() {
        super(ID, NAME, IMG_PATH, 5, DESCRIPTION, CardType.POWER, Royal.Enums.COLOR_YELLOW, CardRarity.RARE, CardTarget.SELF);
        this.magicNumber = this.baseMagicNumber = 2;
    }

    @Override
    public void upgrade() {
        ((UpgradeBranch)((BranchableUpgradeCard)this).possibleBranches().get(chosenBranch())).upgrade();
    }


    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        switch (chosenBranch()){
            case 0:
                addToBot(new SFXAction(ID.replace("shadowverse:", "")));
                addToBot(new ApplyPowerAction(p, p, new CaptainWalfridPower(p, this.magicNumber),this.magicNumber));
                break;
            case 1:
                boolean deckCheck = true;
                if (p.drawPile.group.stream().anyMatch(card -> {
                    return card.color!=Royal.Enums.COLOR_YELLOW||card.type!=CardType.ATTACK||card.hasTag(CardTags.STRIKE);
                })){
                    deckCheck = false;
                }
                if (deckCheck){
                    addToBot((AbstractGameAction)new VFXAction((AbstractGameEffect)new WhirlwindEffect(), 0.1F));
                    addToBot(new ApplyPowerAction(p, p, new CaptainWalfridPower(p, this.magicNumber),this.magicNumber));
                }
                break;
        }
        addToBot(new MakeTempCardInDiscardAction(this.makeStatEquivalentCopy(), 1));
    }

    @Override
    public List<UpgradeBranch> possibleBranches() {
        ArrayList<UpgradeBranch> list = new ArrayList<UpgradeBranch>();
        list.add(new UpgradeBranch() {
            @Override
            public void upgrade() {
                ++CaptainWalfrid.this.timesUpgraded;
                CaptainWalfrid.this.upgraded = true;
                CaptainWalfrid.this.name = NAME + "+";
                CaptainWalfrid.this.initializeTitle();
                CaptainWalfrid.this.upgradeBaseCost(4);
            }
        });
        list.add(new UpgradeBranch() {
            @Override
            public void upgrade() {
                ++CaptainWalfrid.this.timesUpgraded;
                CaptainWalfrid.this.upgraded = true;
                CaptainWalfrid.this.textureImg = IMG_PATH2;
                CaptainWalfrid.this.loadCardImage(IMG_PATH2);
                CaptainWalfrid.this.name = cardStrings2.NAME;
                CaptainWalfrid.this.initializeTitle();
                CaptainWalfrid.this.upgradeBaseCost(2);
                CaptainWalfrid.this.rawDescription = cardStrings2.DESCRIPTION;
                CaptainWalfrid.this.initializeDescription();
            }
        });
        return list;
    }
}

