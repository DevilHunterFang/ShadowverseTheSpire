package shadowverse.cards.Nemesis.Tokens;

import basemod.abstracts.CustomCard;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.StarBounceEffect;
import com.megacrit.cardcrawl.vfx.combat.ViolentAttackEffect;
import rs.lazymankits.interfaces.cards.BranchableUpgradeCard;
import rs.lazymankits.interfaces.cards.UpgradeBranch;
import shadowverse.cards.Neutral.Temp.PurgationBlade;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Nemesis;

import java.util.ArrayList;
import java.util.List;

public class Maisha
        extends CustomCard implements BranchableUpgradeCard {
    public static final String ID = "shadowverse:Maisha";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Maisha");
    public static CardStrings cardStrings2 = CardCrawlGame.languagePack.getCardStrings("shadowverse:Maisha2");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/Maisha.png";
    public static final String IMG_PATH2 = "img/cards/Maisha2.png";

    public Maisha() {
        super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.ATTACK, Nemesis.Enums.COLOR_SKY, CardRarity.UNCOMMON, CardTarget.SELF);
        this.cardsToPreview = (AbstractCard) new PurgationBlade();
        this.exhaust = true;
        this.baseMagicNumber = 1;
        this.magicNumber = this.baseMagicNumber;
        this.tags.add(AbstractShadowversePlayer.Enums.HERO);
    }


    public void upgrade() {
        ((UpgradeBranch) ((BranchableUpgradeCard) this).possibleBranches().get(chosenBranch())).upgrade();
    }


    public void use(AbstractPlayer p, AbstractMonster m) {
        switch (chosenBranch()){
            case 0:
                addToBot((AbstractGameAction) new SFXAction("Maisha"));
                addToBot((AbstractGameAction) new DrawCardAction(this.magicNumber));
                addToBot((AbstractGameAction) new MakeTempCardInHandAction(this.cardsToPreview.makeStatEquivalentCopy()));
                break;
            case 1:
                addToBot((AbstractGameAction) new SFXAction("Maisha2"));
                int dmg = 0;
                for (AbstractCard c:p.exhaustPile.group){
                    if (c.type ==CardType.ATTACK){
                        dmg++;
                    }
                }
                addToBot((AbstractGameAction)new VFXAction((AbstractGameEffect)new ViolentAttackEffect(m.hb.cX, m.hb.cY, Color.BLUE), 0.8F));
                for (int i = 0; i < 5; i++)
                    addToBot((AbstractGameAction)new VFXAction((AbstractGameEffect)new StarBounceEffect(m.hb.cX, m.hb.cY)));
                if (EnergyPanel.getCurrentEnergy()>2){
                    addToBot(new DamageAction(m,new DamageInfo(p,dmg*this.magicNumber,this.damageTypeForTurn), AbstractGameAction.AttackEffect.NONE));
                }else {
                    addToBot(new DamageAction(m,new DamageInfo(p,dmg,this.damageTypeForTurn), AbstractGameAction.AttackEffect.NONE));
                }
                break;
        }
    }

    @Override
    public List<UpgradeBranch> possibleBranches() {
        ArrayList<UpgradeBranch> list = new ArrayList<UpgradeBranch>();
        list.add(new UpgradeBranch() {
            @Override
            public void upgrade() {
                ++Maisha.this.timesUpgraded;
                Maisha.this.upgraded = true;
                Maisha.this.name = NAME + "+";
                Maisha.this.initializeTitle();
                Maisha.this.baseMagicNumber = 2;
                Maisha.this.magicNumber = Maisha.this.baseMagicNumber;
                Maisha.this.upgradedMagicNumber = true;
                Maisha.this.cardsToPreview.upgrade();
                Maisha.this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
                Maisha.this.initializeDescription();
            }
        });
        list.add(new UpgradeBranch() {
            @Override
            public void upgrade() {
                ++Maisha.this.timesUpgraded;
                Maisha.this.upgraded = true;
                Maisha.this.textureImg = IMG_PATH2;
                Maisha.this.loadCardImage(IMG_PATH2);
                Maisha.this.name = cardStrings2.NAME;
                Maisha.this.baseMagicNumber = 5;
                Maisha.this.magicNumber = Maisha.this.baseMagicNumber;
                Maisha.this.upgradedMagicNumber = true;
                Maisha.this.initializeTitle();
                Maisha.this.rawDescription = cardStrings2.DESCRIPTION;
                Maisha.this.initializeDescription();
                Maisha.this.cardsToPreview = null;
                Maisha.this.rarity = CardRarity.RARE;
                Maisha.this.setDisplayRarity(Maisha.this.rarity);
                Maisha.this.target = CardTarget.ENEMY;
                Maisha.this.exhaust = false;
            }
        });
        return list;
    }
}

