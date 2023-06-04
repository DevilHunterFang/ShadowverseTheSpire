package shadowverse.events;

import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.AbstractEvent;
import com.megacrit.cardcrawl.events.AbstractImageEvent;
import com.megacrit.cardcrawl.localization.EventStrings;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;
import shadowverse.cards.Bishop.Amulet1.*;
import shadowverse.cards.Bishop.MechLion.*;
import shadowverse.cards.Elf.NatMech.MachineClaw;
import shadowverse.cards.Elf.NatMech.AvatarOfFruition;
import shadowverse.cards.Elf.NatMech.GuardOfMachinatree;
import shadowverse.cards.Elf.NatMech.IrongliderElf;
import shadowverse.cards.Elf.NatMech.Cleft;
import shadowverse.cards.Elf.NatMech.Damian;
import shadowverse.cards.Elf.NatMech.Ladica;
import shadowverse.cards.Elf.NatMech.NaturalMana;
import shadowverse.cards.Elf.NatMech.RoboticBagworm;
import shadowverse.cards.Elf.NatMech.ScaryTrend;
import shadowverse.cards.Necromancer.Mech.*;
import shadowverse.cards.Necromancer.Natura.*;
import shadowverse.cards.Nemesis.Artifact.RoboticUser;
import shadowverse.cards.Nemesis.Condemned.Invasion;
import shadowverse.cards.Nemesis.Condemned.Kyrzael;
import shadowverse.cards.Nemesis.Condemned.WardenOfTrigger;
import shadowverse.cards.Nemesis.Default.CannonHermitCrab;
import shadowverse.cards.Nemesis.Default.Ralmia;
import shadowverse.cards.Nemesis.Default.TechnologyMana;
import shadowverse.cards.Nemesis.Mech.*;
import shadowverse.cards.Nemesis.Pile.Prototype;
import shadowverse.cards.Nemesis.Resonance.MagnaGiant;
import shadowverse.cards.Nemesis.Resonance.MagnaZero;
import shadowverse.cards.Neutral.Neutral.*;
import shadowverse.cards.Royal.NatMech.BrothersUnited;
import shadowverse.cards.Royal.NatMech.Cybercannoneer;
import shadowverse.cards.Royal.Default.GloriousCore;
import shadowverse.cards.Royal.NatMech.Grayson;
import shadowverse.cards.Royal.NatMech.Johann;
import shadowverse.cards.Royal.NatMech.MistolinaBayleon;
import shadowverse.cards.Royal.NatMech.Patrick;
import shadowverse.cards.Royal.NatMech.StampedingFortress;
import shadowverse.cards.Royal.NatMech.SunnyDayEncounter;
import shadowverse.cards.Royal.NatMech.TemperedMana;
import shadowverse.cards.Vampire.NatMech.ArmoredBat;
import shadowverse.cards.Vampire.Avarice.Cradle;
import shadowverse.cards.Vampire.NatMech.CorruptedBat;
import shadowverse.cards.Vampire.NatMech.LunaticMana;
import shadowverse.cards.Vampire.NatMech.MechaforgeDevil;
import shadowverse.cards.Vampire.NatMech.CreepingMadness;
import shadowverse.cards.Vampire.NatMech.MechashotDevil;
import shadowverse.cards.Vampire.NatMech.Mono;
import shadowverse.cards.Vampire.NatMech.Neun;
import shadowverse.cards.Vampire.NatMech.RuinwebSpider;
import shadowverse.cards.Vampire.NatMech.Slayn;
import shadowverse.cards.Vampire.Default.Ravening;
import shadowverse.cards.Vampire.Bat.UnleashTheNightmare;
import shadowverse.cards.Witch.Mech.*;
import shadowverse.cards.Witch.Natura.*;
import shadowverse.characters.*;

public class NaturalMachine extends AbstractImageEvent {
    public static final String ID = "shadowverse:NaturalMachine";

    private static final EventStrings eventStrings = CardCrawlGame.languagePack.getEventString(ID);

    public static final String NAME = eventStrings.NAME;

    public static final String[] DESCRIPTIONS = eventStrings.DESCRIPTIONS;

    public static final String[] OPTIONS = eventStrings.OPTIONS;

    private boolean pickCard = false;

    private int screenNum = 0;

    private int healAmt;

    public NaturalMachine() {
        super(NAME, DESCRIPTIONS[0], "img/event/NaturalMachine.png");
        if (AbstractDungeon.ascensionLevel >= 15) {
            this.healAmt = MathUtils.round((float) AbstractDungeon.player.maxHealth * 0.2F);
        } else {
            this.healAmt = MathUtils.round((float) AbstractDungeon.player.maxHealth * 0.33F);
        }
        this.imageEventText.setDialogOption(OPTIONS[0]);
        this.imageEventText.setDialogOption(OPTIONS[1] + this.healAmt + OPTIONS[2]);
    }

    @Override
    public void update() {
        super.update();
        if (this.pickCard && !AbstractDungeon.isScreenUp && !AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
            for (int i = 0; i < 3; i++) {
                AbstractCard c = AbstractDungeon.gridSelectScreen.selectedCards.get(i).makeCopy();
                AbstractDungeon.effectList.add(new ShowCardAndObtainEffect(c, (float) Settings.WIDTH / (i * 2.0F), (float) Settings.HEIGHT / 2.0F));
            }
            AbstractDungeon.gridSelectScreen.selectedCards.clear();
        }

    }

    @Override
    protected void buttonEffect(int buttonPressed) {
        switch (this.screenNum) {
            case 0:
                switch (buttonPressed) {
                    case 0:
                        this.screenNum = 1;
                        this.pickCard = true;
                        this.imageEventText.updateBodyText(DESCRIPTIONS[1]);
                        this.imageEventText.updateDialogOption(0, OPTIONS[4]);
                        this.imageEventText.clearRemainingOptions();
                        AbstractPlayer p = AbstractDungeon.player;
                        CardGroup colorless = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
                        CardGroup white = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
                        CardGroup blue = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
                        CardGroup gold = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
                        colorless.addToBottom(new DualAngle());
                        colorless.addToBottom(new NaterranFuture());
                        colorless.addToBottom(new Mother());
                        colorless.addToBottom(new Technolord());
                        colorless.addToBottom(new Machinus());
                        colorless.addToBottom(new Valdain());
                        colorless.addToBottom(new Rola());
                        if (p.chosenClass != Witchcraft.Enums.WITCHCRAFT) {
                            white.addToBottom(new Geoelementist());
                            blue.addToBottom(new Stormelementalist());
                            blue.addToBottom(new Pyromancer());
                            white.addToBottom(new Elementalmana());
                            blue.addToBottom(new Riley());
                            gold.addToBottom(new ApexElemental());
                            white.addToBottom(new Aeroelementalist());
                            white.addToBottom(new JetBroomWitch());
                            blue.addToBottom(new SorceryInSolidarity());
                            white.addToBottom(new MechanizedLifeform());
                            blue.addToBottom(new MagitechGolem());
                            blue.addToBottom(new MechabookSorcerer());
                            gold.addToBottom(new Tetra());
                            white.addToBottom(new MechastaffSorcerer());

                        }
                        if (p.chosenClass != Elf.Enums.Elf) {
                            white.addToBottom(new NaturalMana());
                            white.addToBottom(new ScaryTrend());
                            gold.addToBottom(new Ladica());
                            blue.addToBottom(new AvatarOfFruition());
                            blue.addToBottom(new GuardOfMachinatree());
                            white.addToBottom(new RoboticBagworm());
                            gold.addToBottom(new Damian());
                            white.addToBottom(new MachineClaw());
                            blue.addToBottom(new IrongliderElf());
                            gold.addToBottom(new Cleft());
                        }
                        if (p.chosenClass != Necromancer.Enums.Necromancer) {
                            white.addToBottom(new ReviveMana());
                            white.addToBottom(new RevenantRam());
                            blue.addToBottom(new ZombieDog());
                            gold.addToBottom(new Lubelle());
                            gold.addToBottom(new Thoth());
                            blue.addToBottom(new Nicola());
                            gold.addToBottom(new DeadMetalStar());
                            white.addToBottom(new BoneDrone());
                            white.addToBottom(new IrongearCorpsman());
                            white.addToBottom(new ForbiddenAndBalance());
                            gold.addToBottom(new GigantSkull());
                            gold.addToBottom(new Aenea());

                        }
                        if (p.chosenClass != Vampire.Enums.Vampire) {
                            blue.addToBottom(new Ravening());
                            white.addToBottom(new LunaticMana());
                            white.addToBottom(new CorruptedBat());
                            blue.addToBottom(new CreepingMadness());
                            gold.addToBottom(new Cradle());
                            gold.addToBottom(new RuinwebSpider());
                            blue.addToBottom(new UnleashTheNightmare());
                            blue.addToBottom(new MechashotDevil());
                            white.addToBottom(new ArmoredBat());
                            white.addToBottom(new MechaforgeDevil());
                            gold.addToBottom(new Mono());
                            gold.addToBottom(new Slayn());
                            gold.addToBottom(new Neun());

                        }
                        if (p.chosenClass != Nemesis.Enums.Nemesis) {
                            white.addToBottom(new TechnologyMana());
                            blue.addToBottom(new LasershellTortoise());
                            gold.addToBottom(new Bearminator());
                            blue.addToBottom(new CannonHermitCrab());
                            gold.addToBottom(new BelphometCard());
                            white.addToBottom(new RoboticUser());
                            white.addToBottom(new Enforcer());
                            blue.addToBottom(new MegaEnforcer());
                            blue.addToBottom(new Invasion());
                            white.addToBottom(new Armadillo());
                            blue.addToBottom(new AerialCraft());
                            white.addToBottom(new Prototype());
                            gold.addToBottom(new MagnaGiant());
                            gold.addToBottom(new MagnaZero());
                            gold.addToBottom(new Ralmia());
                            blue.addToBottom(new WardenOfTrigger());
                            gold.addToBottom(new Kyrzael());
                            white.addToBottom(new SumoMechanic());
                            white.addToBottom(new Gioffre());
                            gold.addToBottom(new MagnaSaber());
                            blue.addToBottom(new RoboticsReporter());
                        }
                        if (p.chosenClass != Royal.Enums.Royal) {
                            white.addToBottom(new Cybercannoneer());
                            white.addToBottom(new BrothersUnited());
                            white.addToBottom(new TemperedMana());
                            blue.addToBottom(new SunnyDayEncounter());
                            white.addToBottom(new GloriousCore());
                            blue.addToBottom(new StampedingFortress());
                            gold.addToBottom(new MistolinaBayleon());
                            gold.addToBottom(new Patrick());
                            gold.addToBottom(new Johann());
                            gold.addToBottom(new Grayson());
                        }
                        if (p.chosenClass !=Bishop.Enums.Bishop){
                            white.addToBottom(new JusticeMana());
                            white.addToBottom(new AngelRat());
                            white.addToBottom(new RobowingPrecant());
                            white.addToBottom(new IronknuckleNun());
                            white.addToBottom(new UnlikelyFellowship());
                            white.addToBottom(new SneakAttack());
                            blue.addToBottom(new Agnes());
                            blue.addToBottom(new RobowhipReverend());
                            gold.addToBottom(new Vice());
                            gold.addToBottom(new Limonia());
                            gold.addToBottom(new MusePrincess());
                        }
                        colorless.shuffle();
                        white.shuffle();
                        blue.shuffle();
                        gold.shuffle();
                        CardGroup group1 = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
                        for (int i = 0; i < 6; i++) {
                            group1.addToBottom(gold.getNCardFromTop(i));
                        }
                        for (int i = 0; i < 6; i++) {
                            group1.addToBottom(blue.getNCardFromTop(i));
                        }
                        for (int i = 0; i < 6; i++) {
                            group1.addToBottom(white.getNCardFromTop(i));
                        }
                        for (int i = 0; i < 2; i++) {
                            group1.addToBottom(colorless.getNCardFromTop(i));
                        }
                        AbstractDungeon.gridSelectScreen.open(group1, 3, OPTIONS[3], false);
                        return;
                    default:
                        this.imageEventText.updateBodyText(DESCRIPTIONS[1]);
                        AbstractDungeon.player.heal(this.healAmt, true);
                        this.screenNum = 1;
                        this.imageEventText.updateDialogOption(0, OPTIONS[4]);
                        this.imageEventText.clearRemainingOptions();
                        return;
                }
            default:
                this.openMap();
        }
    }


    public void logMetric(String actionTaken) {
        AbstractEvent.logMetric(ID, actionTaken);
    }
}
