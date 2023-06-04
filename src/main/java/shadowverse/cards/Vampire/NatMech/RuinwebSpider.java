package shadowverse.cards.Vampire.NatMech;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.actions.watcher.SkipEnemiesTurnAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import com.megacrit.cardcrawl.vfx.combat.EntangleEffect;
import shadowverse.Shadowverse;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Vampire;
import shadowverse.powers.RuinwebSpiderPower;


public class RuinwebSpider
        extends CustomCard {
    public static final String ID = "shadowverse:RuinwebSpider";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:RuinwebSpider");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/RuinwebSpider.png";

    public RuinwebSpider() {
        super(ID, NAME, IMG_PATH, 5, DESCRIPTION, CardType.ATTACK, Vampire.Enums.COLOR_SCARLET, CardRarity.RARE, CardTarget.ALL_ENEMY);
        this.baseMagicNumber = 6;
        this.magicNumber = this.baseMagicNumber;
        this.tags.add(AbstractShadowversePlayer.Enums.NATURAL);
        this.tags.add(AbstractShadowversePlayer.Enums.CRYSTALLIZE);
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeMagicNumber(-1);
            this.isInnate = true;
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }

    @Override
    public void update() {
        if (AbstractDungeon.currMapNode != null && (AbstractDungeon.getCurrRoom()).phase == AbstractRoom.RoomPhase.COMBAT&&
                Shadowverse.Accelerate(this)){
            setCostForTurn(0);
            this.type = CardType.POWER;
        }else {
            if (this.type==CardType.POWER){
                setCostForTurn(5);
                this.type = CardType.ATTACK;
            }
        }
        super.update();
    }

    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        if (Shadowverse.Accelerate( this) && this.type == CardType.POWER) {
            addToBot(new ApplyPowerAction(abstractPlayer,abstractPlayer,(AbstractPower)new RuinwebSpiderPower(abstractPlayer,this.magicNumber),this.magicNumber));
        } else {
            addToBot(new SkipEnemiesTurnAction());
            for (AbstractMonster mo : (AbstractDungeon.getCurrRoom()).monsters.monsters) {
                addToBot(new VFXAction(new EntangleEffect(abstractPlayer.hb.cX - 70.0F * Settings.scale, abstractPlayer.hb.cY + 10.0F * Settings.scale, mo.hb.cX, mo.hb.cY), 0.5F));
            }
            if (EnergyPanel.getCurrentEnergy() < 10){
                addToBot(new MakeTempCardInDiscardAction(this.makeStatEquivalentCopy(),1));
            }
        }
    }


    public AbstractCard makeCopy() {
        return  new RuinwebSpider();
    }
}



