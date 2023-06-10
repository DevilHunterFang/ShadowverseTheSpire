package shadowverse.cards.Elf.Return;

import basemod.abstracts.CustomCard;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.ThrowDaggerEffect;
import com.megacrit.cardcrawl.vfx.combat.WhirlwindEffect;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Elf;
import shadowverse.powers.WimaelPower;

public class Wimael extends CustomCard {
    public static final String ID = "shadowverse:Wimael";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Wimael");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/Wimael.png";

    public Wimael() {
        super(ID, NAME, IMG_PATH, 4, DESCRIPTION, CardType.ATTACK, Elf.Enums.COLOR_GREEN, CardRarity.RARE, CardTarget.ENEMY);
        this.baseDamage = 20;
        this.tags.add(AbstractShadowversePlayer.Enums.CONDEMNED);
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeDamage(4);
        }
    }

    @Override
    public void triggerOnOtherCardPlayed(AbstractCard c) {
        if (c.type == CardType.SKILL && c.baseBlock == 0){
            flash();
            addToBot(new SFXAction("spell_boost"));
            addToBot(new ReduceCostAction(this));
        }
    }


    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new SFXAction("Wimael"));
        addToBot(new VFXAction(new WhirlwindEffect(new Color(1.0F, 0.9F, 0.4F, 1.0F), true)));
        for (int i = 0; i < 4; i++) {
            if (m != null)
                addToTop(new VFXAction(new ThrowDaggerEffect(m.hb.cX, m.hb.cY),0.2F));
        }
        addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HEAVY));
        addToBot(new ApplyPowerAction(p,p,new WimaelPower(p,1),1));
        this.cost = 3;
    }

    public AbstractCard makeCopy() {
        return new Wimael();
    }
}
