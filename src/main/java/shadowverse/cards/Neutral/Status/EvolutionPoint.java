package shadowverse.cards.Neutral.Status;

import basemod.abstracts.CustomCard;
import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsInHandAction;
import com.megacrit.cardcrawl.actions.common.UpgradeSpecificCardAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.action.AnimationAction;
import shadowverse.characters.*;
import shadowverse.patch.CharacterSelectScreenPatches;

public class EvolutionPoint extends CustomCard {
    public static final String ID = "shadowverse:EvolutionPoint";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:EvolutionPoint");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/EvolutionPoint.png";
    public static final String[] TEXT = CardCrawlGame.languagePack.getUIString("ArmamentsAction").TEXT;

    public EvolutionPoint() {
        super(ID, NAME, IMG_PATH, 0, DESCRIPTION, CardType.STATUS, CardColor.COLORLESS, CardRarity.COMMON, CardTarget.SELF);
        this.baseMagicNumber = 1;
        this.magicNumber = this.baseMagicNumber;
        this.exhaust = true;
        this.isEthereal = true;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.selfRetain = true;
            this.isEthereal = false;
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }
    }


    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new SelectCardsInHandAction(1, TEXT[0], false, false, card -> card.type != CardType.SKILL && card.canUpgrade(), abstractCards -> {
            for (AbstractCard c : abstractCards) {
                int roll = AbstractDungeon.cardRandomRng.random(3);
                if (abstractPlayer.chosenClass == Witchcraft.Enums.WITCHCRAFT && (CharacterSelectScreenPatches.characters[0]).reskinCount == 0) {
                    addToBot(new SFXAction("Witchcraft_Evolve" + roll % 3));
                    addToBot(new AnimationAction(Witchcraft.getBigAnimation(), "extra", 2.833F));
                } else if (abstractPlayer.chosenClass == Elf.Enums.Elf && (CharacterSelectScreenPatches.characters[1]).reskinCount == 0) {
                    addToBot(new SFXAction("Elf_Evolve" + roll % 3));
                    addToBot(new AnimationAction(Elf.getBigAnimation(), "extra", 2.833F));
                } else if (abstractPlayer.chosenClass == Necromancer.Enums.Necromancer && (CharacterSelectScreenPatches.characters[2]).reskinCount == 0) {
                    addToBot(new SFXAction("Necromancer_Evolve" + roll % 3));
                    addToBot(new AnimationAction(Necromancer.getBigAnimation(), "extra", 2.833F));
                } else if (abstractPlayer.chosenClass == Vampire.Enums.Vampire && (CharacterSelectScreenPatches.characters[4]).reskinCount == 0) {
                    addToBot(new SFXAction("Vampire_Evolve" + roll % 3));
                    addToBot(new AnimationAction(Vampire.getBigAnimation(), "extra", 2.833F));
                } else if (abstractPlayer.chosenClass == Nemesis.Enums.Nemesis && (CharacterSelectScreenPatches.characters[6]).reskinCount == 0) {
                    addToBot(new SFXAction("Nemesis_Evolve" + roll % 3));
                    addToBot(new AnimationAction(Nemesis.getBigAnimation(), "extra", 2.833F));
                } else if (abstractPlayer.chosenClass == Royal.Enums.Royal && (CharacterSelectScreenPatches.characters[3]).reskinCount == 0) {
                    addToBot(new SFXAction("Royal_Evolve" + roll % 3));
                    addToBot(new AnimationAction(Royal.getBigAnimation(), "extra", 2.833F));
                }

                addToBot(new UpgradeSpecificCardAction(c));
            }
        }));
    }

    @Override
    public AbstractCard makeCopy() {
        return new EvolutionPoint();
    }
}
