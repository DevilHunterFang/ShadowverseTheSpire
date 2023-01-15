package shadowverse.cards.Rare;

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
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DrawCardNextTurnPower;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.ThrowDaggerEffect;
import com.megacrit.cardcrawl.vfx.combat.WhirlwindEffect;
import shadowverse.Shadowverse;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Elf;

public class Wimael extends CustomCard {
    public static final String ID = "shadowverse:Wimael";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Wimael");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/Wimael.png";

    public Wimael() {
        super(ID, NAME, IMG_PATH, 3, DESCRIPTION, CardType.ATTACK, Elf.Enums.COLOR_GREEN, CardRarity.RARE, CardTarget.ENEMY);
        this.baseDamage = 20;
        this.tags.add(AbstractShadowversePlayer.Enums.CONDEMNED);
        this.selfRetain = true;
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeDamage(4);
        }
    }

    @Override
    public void onRetained() {
        addToBot(new ReduceCostAction(this));
    }

    @Override
    public void triggerWhenDrawn() {
        addToBot(new MakeTempCardInHandAction(this.makeStatEquivalentCopy(),1));
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new SFXAction("Wimael"));
        addToBot(new VFXAction(new WhirlwindEffect(new Color(1.0F, 0.9F, 0.4F, 1.0F), true)));
        for (int i = 0; i < 4; i++) {
            if (m != null)
                addToTop(new VFXAction(new ThrowDaggerEffect(m.hb.cX, m.hb.cY),0.2F));
        }
        addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HEAVY));
    }

    public AbstractCard makeCopy() {
        return new Wimael();
    }
}
