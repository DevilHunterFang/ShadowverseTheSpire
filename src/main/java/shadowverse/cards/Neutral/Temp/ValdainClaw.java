package shadowverse.cards.Neutral.Temp;

import basemod.abstracts.CustomCard;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.vfx.combat.ClawEffect;
import shadowverse.characters.Dragon;

public class ValdainClaw extends CustomCard {
    public static final String ID = "shadowverse:ValdainClaw";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:ValdainClaw");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/ValdainClaw.png";

    public ValdainClaw() {
        super(ID, NAME, IMG_PATH, -2, DESCRIPTION, CardType.SKILL, Dragon.Enums.COLOR_BROWN, CardRarity.SPECIAL, CardTarget.NONE);
        this.baseMagicNumber = 2;
        this.magicNumber = this.baseMagicNumber;
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeMagicNumber(1);
        }
    }


    public void use(AbstractPlayer p, AbstractMonster m) {
        onChoseThisOption();
    }

    public void onChoseThisOption() {
        addToBot(new SFXAction("ValdainClaw"));
        AbstractCreature m = AbstractDungeon.getMonsters().getRandomMonster(null, true, AbstractDungeon.cardRandomRng);
        if (m != null){
            addToBot(new VFXAction(new ClawEffect(m.hb.cX, m.hb.cY, Color.PURPLE, Color.ORANGE), 0.1F));
            addToBot(new ApplyPowerAction(m,AbstractDungeon.player,new VulnerablePower(m,this.magicNumber,false)));
            addToBot(new ApplyPowerAction(m,AbstractDungeon.player,new WeakPower(m,this.magicNumber,false)));
        }
    }

    public AbstractCard makeCopy() {
        return new ValdainClaw();
    }
}
