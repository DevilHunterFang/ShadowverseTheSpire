package shadowverse.cards.Royal.Loot;

import basemod.abstracts.CustomCard;
import com.badlogic.gdx.Gdx;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.cards.Neutral.Temp.*;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Royal;
import shadowverse.powers.TidalGunnerPower;

import java.util.ArrayList;

public class TidalGunner extends CustomCard {
    public static final String ID = "shadowverse:TidalGunner";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:TidalGunner");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/TidalGunner.png";
    public static final String IMG_PATH_EV = "img/cards/TidalGunner_Ev.png";
    private float rotationTimer;
    private int previewIndex;

    public static ArrayList<AbstractCard> returnProphecy() {
        ArrayList<AbstractCard> list = new ArrayList<>();
        list.add(new GildedGoblet());
        list.add(new DreadPirateFlag());
        return list;
    }

    public TidalGunner() {
        super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.ATTACK, Royal.Enums.COLOR_YELLOW, CardRarity.COMMON, CardTarget.ENEMY);
        this.baseDamage = 8;
        this.baseMagicNumber = 2;
        this.magicNumber = this.baseMagicNumber;
        this.tags.add(AbstractShadowversePlayer.Enums.CONDEMNED);
    }

    @Override
    public void update() {
        super.update();
        if (this.hb.hovered) {
            if (this.rotationTimer <= 0.0F) {
                this.rotationTimer = 2.0F;
                this.cardsToPreview = returnProphecy().get(previewIndex).makeCopy();
                if (this.previewIndex == returnProphecy().size() - 1) {
                    this.previewIndex = 0;
                } else {
                    this.previewIndex++;
                }
            } else {
                this.rotationTimer -= Gdx.graphics.getDeltaTime();
            }
        }
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeDamage(3);
            upgradeMagicNumber(1);
            textureImg = IMG_PATH_EV;
            loadCardImage(IMG_PATH_EV);
        }
    }


    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new SFXAction(ID.replace("shadowverse:", "")));
        addToBot(new DamageAction(m,new DamageInfo(p,this.damage,this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
        int hasGilded = 0;
        for (AbstractCard card:p.exhaustPile.group){
            if (card.hasTag(AbstractShadowversePlayer.Enums.GILDED))
                hasGilded++;
        }
        if (hasGilded>=7){
            addToBot(new MakeTempCardInHandAction(new DreadPirateFlag(),1));
        }else {
            addToBot(new MakeTempCardInHandAction(new GildedGoblet(),1));
        }
        addToBot(new ApplyPowerAction(p, p, new TidalGunnerPower(p, this.magicNumber)));
    }


    @Override
    public AbstractCard makeCopy() {
        return new TidalGunner();
    }
}