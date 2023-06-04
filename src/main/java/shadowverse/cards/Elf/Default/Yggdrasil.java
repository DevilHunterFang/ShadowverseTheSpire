package shadowverse.cards.Elf.Default;


import basemod.abstracts.CustomCard;
import com.badlogic.gdx.Gdx;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.unique.VampireDamageAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import rs.lazymankits.interfaces.cards.BranchableUpgradeCard;
import rs.lazymankits.interfaces.cards.UpgradeBranch;
import shadowverse.action.ChoiceAction2;
import shadowverse.cards.Neutral.Temp.*;
import shadowverse.characters.Elf;

import java.util.ArrayList;
import java.util.List;


public class Yggdrasil extends CustomCard implements BranchableUpgradeCard {
    public static final String ID = "shadowverse:Yggdrasil";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Yggdrasil");
    public static CardStrings cardStrings2 = CardCrawlGame.languagePack.getCardStrings("shadowverse:Yggdrasil2");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/Yggdrasil.png";
    public static final String IMG_PATH2 = "img/cards/Yggdrasil2.png";
    private int branchPreview = 0;
    private float rotationTimer;
    private int previewIndex;

    public static ArrayList<AbstractCard> returnChoice() {
        ArrayList<AbstractCard> list = new ArrayList<>();
        list.add(new BlessingsOfCreation());
        list.add(new WrathOfNature());
        return list;
    }

    public static AbstractCard blessing = new NatureBlessing();


    public Yggdrasil() {
        super(ID, NAME, IMG_PATH, 2, DESCRIPTION, CardType.ATTACK, Elf.Enums.COLOR_GREEN, CardRarity.RARE, CardTarget.ENEMY);
        this.baseBlock = 12;
        this.baseDamage = 2;
    }


    public void upgrade() {
        ((UpgradeBranch) ((BranchableUpgradeCard) this).possibleBranches().get(chosenBranch())).upgrade();
    }

    @Override
    public void update() {
        super.update();
        if (this.branchPreview == 0) {
            if (this.hb.hovered)
                if (this.rotationTimer <= 0.0F) {
                    this.rotationTimer = 2.0F;
                    this.cardsToPreview = (AbstractCard) returnChoice().get(previewIndex).makeCopy();
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
        } else {
            this.cardsToPreview = blessing;
        }
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p, this.block));
        switch (chosenBranch()) {
            case 0:
                AbstractCard b = new BlessingsOfCreation();
                AbstractCard w = new WrathOfNature();
                if (this.upgraded) {
                    b.upgrade();
                    w.upgrade();
                }
                addToBot(new SFXAction("Yggdrasil"));
                addToBot(new VampireDamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
                addToBot(new ChoiceAction2(b, w));
                break;
            case 1:
                addToBot(new SFXAction("Yggdrasil2"));
                addToBot(new MakeTempCardInHandAction(new NatureBlessing()));
                break;

        }
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new Yggdrasil();
    }

    @Override
    public List<UpgradeBranch> possibleBranches() {
        ArrayList<UpgradeBranch> list = new ArrayList<UpgradeBranch>();
        list.add(new UpgradeBranch() {
            @Override
            public void upgrade() {
                ++Yggdrasil.this.timesUpgraded;
                Yggdrasil.this.upgraded = true;
                Yggdrasil.this.name = NAME + "+";
                Yggdrasil.this.initializeTitle();
                Yggdrasil.this.baseBlock = 16;
                ;
                Yggdrasil.this.upgradedBlock = true;
                Yggdrasil.this.baseDamage = 4;
                Yggdrasil.this.upgradedDamage = true;
                Yggdrasil.this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
                Yggdrasil.this.initializeDescription();
            }
        });
        list.add(new UpgradeBranch() {
            @Override
            public void upgrade() {
                ++Yggdrasil.this.timesUpgraded;
                Yggdrasil.this.upgraded = true;
                Yggdrasil.this.target = CardTarget.SELF;
                Yggdrasil.this.textureImg = IMG_PATH2;
                Yggdrasil.this.loadCardImage(IMG_PATH2);
                Yggdrasil.this.name = cardStrings2.NAME;
                Yggdrasil.this.upgradeBaseCost(1);
                Yggdrasil.this.initializeTitle();
                Yggdrasil.this.rawDescription = cardStrings2.DESCRIPTION;
                Yggdrasil.this.initializeDescription();
                Yggdrasil.this.branchPreview = 1;
            }
        });

        return list;
    }
}
