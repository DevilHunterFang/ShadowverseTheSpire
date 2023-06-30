package shadowverse.cards.Vampire.Wrath;

import basemod.abstracts.CustomCard;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.BiteEffect;
import shadowverse.characters.Vampire;
import shadowverse.powers.EpitaphPower;
import shadowverse.powers.WrathPower;


public class OmnifacedArchdemon
        extends CustomCard {
    public static final String ID = "shadowverse:OmnifacedArchdemon";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:OmnifacedArchdemon");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/OmnifacedArchdemon.png";

    public OmnifacedArchdemon() {
        super(ID, NAME, IMG_PATH, 3, DESCRIPTION, CardType.ATTACK, Vampire.Enums.COLOR_SCARLET, CardRarity.RARE, CardTarget.ALL);
        this.baseBlock = 25;
        this.baseDamage = 7;
        this.baseMagicNumber = 2;
        this.magicNumber = this.baseMagicNumber;
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBlock(3);
            upgradeDamage(3);
        }
    }


    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new SFXAction("OmnifacedArchdemon"));
        addToBot(new GainBlockAction(abstractPlayer,this.block));
        int amt = 1;
        if (abstractPlayer.hasPower(EpitaphPower.POWER_ID)||abstractPlayer.hasPower(WrathPower.POWER_ID)){
            amt = 3;
        }
        for (int i=0;i<amt;i++){
            AbstractCreature m = AbstractDungeon.getMonsters().getRandomMonster(null, true, AbstractDungeon.cardRandomRng);
            if (m != null && !m.isDeadOrEscaped()){
                addToBot(new HealAction(abstractPlayer,abstractPlayer,this.magicNumber));
                addToBot(new VFXAction(new BiteEffect(m.hb.cX, m.hb.cY - 40.0F * Settings.scale, Color.SCARLET.cpy()), 0.2F));
                addToBot(new DamageAction(m, new DamageInfo(abstractPlayer, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.NONE));
            }
        }
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new OmnifacedArchdemon();
    }
}


