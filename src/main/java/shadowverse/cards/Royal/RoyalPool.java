package shadowverse.cards.Royal;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import shadowverse.cards.AbstractBanPool;
import shadowverse.cards.BanGroup;
import shadowverse.cards.Royal.Ambush.*;
import shadowverse.cards.Royal.Default.*;
import shadowverse.cards.Royal.Evolve.*;
import shadowverse.cards.Royal.Festive.*;
import shadowverse.cards.Royal.Hero.*;
import shadowverse.cards.Royal.Levin.*;
import shadowverse.cards.Royal.Loot.*;
import shadowverse.cards.Royal.Minion.*;
import shadowverse.cards.Royal.NatMech.*;
import shadowverse.cards.Royal.Rally.*;
import shadowverse.characters.Royal;

import java.util.ArrayList;

public class RoyalPool  extends AbstractBanPool {
    static String ID;
    public static CardColor color;
    static ArrayList<BanGroup> pool;
    static int groupCount;
    static int activeCount;

    public RoyalPool(int code) {
        super(getName(code),getImg(code),color,code);
    }

    public static String getName(int code) {
        return CardCrawlGame.languagePack.getCardStrings(ID).EXTENDED_DESCRIPTION[code];
    }

    public static String getImg(int code) {
        return "img/cards/" + pool.get(code).imgName + ".png";
    }

    public ArrayList<BanGroup> getPool() {
        return pool;
    }

    @Override
    public int getGroupCount() {
        return groupCount;
    }

    @Override
    public int getActiveCount() {
        return activeCount;
    }

    public AbstractCard makeCopy() {
        return new RoyalPool(this.code);
    }

    public AbstractBanPool copy(int code) {
        return new RoyalPool(code);
    }

    static {
        ID = "shadowverse:RoyalPool";
        color = Royal.Enums.COLOR_YELLOW;
        pool = new ArrayList<>();
        BanGroup Default = new BanGroup("Nahtnaught");
        BanGroup Ambush = new BanGroup("Leod");
        BanGroup Evolve = new BanGroup("Seofon");
        BanGroup Festive = new BanGroup("JiemonThiefLord");
        BanGroup Hero = new BanGroup("ValiantFencer");
        BanGroup Levin = new BanGroup("Albert");
        BanGroup Loot = new BanGroup("Octrice");
        BanGroup Minion = new BanGroup("Mars");
        BanGroup NatMech = new BanGroup("MistolinaBayleon");
        BanGroup Rally = new BanGroup("FrenziedCorpsmaster");

        Default.addCard(new Alyaska());
        Default.addCard(new HonoredFrontguardGeneral());
        Default.addCard(new Ilmisuna());
        Default.addCard(new LeciaSkySaber());
        Default.addCard(new LuxbladeArriet());
        Default.addCard(new Nahtnaught());
        Default.addCard(new PompousSummons());
        Default.addCard(new Reinhardt());
        Default.addCard(new Sera());
        Default.addCard(new Spector());
        Default.addCard(new TheChariot());
        Default.addCard(new Valse());
        Default.addCard(new Weiss());
        Default.addCard(new EnduringHope());
        Default.addCard(new DramaticRetreat());
        Default.addCard(new Alexander());
        Default.addCard(new Arthur());
        Default.addCard(new UsurpingSpineblade());
        Default.addCard(new EleganceInAction());
        Default.addCard(new MonochromeEndgame());
        Default.addCard(new PrudentGeneral());
        Default.addCard(new StrikeproneGuardian());
        Default.addCard(new RadicalGunslinger());
        Default.addCard(new LuminousMage());
        Default.addCard(new DanceOfUsurpation());
        Default.addCard(new Troya());

        Ambush.addCard(new AmbushBuff());
        Ambush.addCard(new BladeDance());
        Ambush.addCard(new Dualblade());
        Ambush.addCard(new Erika());
        Ambush.addCard(new GracefulManeuver());
        Ambush.addCard(new Leod());
        Ambush.addCard(new ShadowedMemories());
        Ambush.addCard(new ShieldOfFlame());
        Ambush.addCard(new ShinobiTanuki());
        Ambush.addCard(new Tsubaki());
        Ambush.addCard(new Kagemitsu());

        Evolve.addCard(new CatAdmiral());
        Evolve.addCard(new Charlotta());
        Evolve.addCard(new Eahta());
        Evolve.addCard(new FortressStrategist());
        Evolve.addCard(new RadiantLuminousMage());
        Evolve.addCard(new Mirin());
        Evolve.addCard(new Seofon());
        Evolve.addCard(new WardenOfHonor());
        Evolve.addCard(new WeeMerchantsAppraisal());
        Evolve.addCard(new Radiel());
        Evolve.addCard(new CourtlyDance());

        Festive.addCard(new FrontDeskFrog());
        Festive.addCard(new HonorableThief());
        Festive.addCard(new JiemonThiefLord());
        Festive.addCard(new MasterfulMusician());
        Festive.addCard(new NightOnTheTown());
        Festive.addCard(new NobleShieldmaiden());
        Festive.addCard(new OpulentStrategist());
        Festive.addCard(new ReturnFromTheBrink());
        Festive.addCard(new SuaveBandit());
        Festive.addCard(new Taketsumi());
        Festive.addCard(new MasterDealer());

        Hero.addCard(new AmerroSpearKnight());
        Hero.addCard(new FlameSoldier());
        Hero.addCard(new HeroicEntry());
        Hero.addCard(new HeroOfAntiquity());
        Hero.addCard(new IronwroughtDefender());
        Hero.addCard(new MachKnight());
        Hero.addCard(new MorgensternMaid());
        Hero.addCard(new ValiantFencer());
        Hero.addCard(new Windslasher());
        Hero.addCard(new Icyclone());
        Hero.addCard(new FighterFortitude());

        Levin.addCard(new Albert());
        Levin.addCard(new Jeno());
        Levin.addCard(new LevinArcher());
        Levin.addCard(new LevinBeastmaster());
        Levin.addCard(new LevinJustice());
        Levin.addCard(new LevinScholar());
        Levin.addCard(new Lounes());
        Levin.addCard(new MeetTheLevinSisters());
        Levin.addCard(new MirrorImage());
        Levin.addCard(new WarriorWing());
        Levin.addCard(new MusketeersVow());

        Loot.addCard(new Alwida());
        Loot.addCard(new ApostleOfUsurpation());
        Loot.addCard(new Barbaros());
        Loot.addCard(new DeepSeaScout());
        Loot.addCard(new DiscipleOfUsurpation());
        Loot.addCard(new GrandAcquisition());
        Loot.addCard(new Octrice());
        Loot.addCard(new TidalGunner());
        Loot.addCard(new UltimateHollow());
        Loot.addCard(new Rogers());
        Loot.addCard(new AdherentOfHollow());

        Minion.addCard(new CaptainWalfrid());
        Minion.addCard(new EmpressOfSerenity());
        Minion.addCard(new FrontlineInstructor());
        Minion.addCard(new Latham());
        Minion.addCard(new Leonidas());
        Minion.addCard(new Mars());
        Minion.addCard(new RoyalBanner());
        Minion.addCard(new SageCommander());
        Minion.addCard(new ShieldPhalanx());
        Minion.addCard(new Lvbu());
        Minion.addCard(new GloriousCore());

        NatMech.addCard(new BrothersUnited());
        NatMech.addCard(new Cybercannoneer());
        NatMech.addCard(new Garven());
        NatMech.addCard(new Grayson());
        NatMech.addCard(new Johann());
        NatMech.addCard(new MistolinaBayleon());
        NatMech.addCard(new Patrick());
        NatMech.addCard(new StampedingFortress());
        NatMech.addCard(new StrokeOfConviction());
        NatMech.addCard(new SunnyDayEncounter());
        NatMech.addCard(new TemperedMana());
        NatMech.addCard(new Bayleon());

        Rally.addCard(new EnragedGeneral());
        Rally.addCard(new DualbladeKnight());
        Rally.addCard(new Ernesta());
        Rally.addCard(new FlyingMessengerSquirrel());
        Rally.addCard(new FrenziedCorpsmaster());
        Rally.addCard(new Gawain());
        Rally.addCard(new GeneralMaximus());
        Rally.addCard(new StormWrackedFirstMate());
        Rally.addCard(new WhitePaladin());
        Rally.addCard(new DashingDuelist());
        Rally.addCard(new UntoldKick());
        pool.add(Default);
        pool.add(Ambush);
        pool.add(Evolve);
        pool.add(Festive);
        pool.add(Hero);
        pool.add(Levin);
        pool.add(Loot);
        pool.add(Minion);
        pool.add(NatMech);
        pool.add(Rally);
        groupCount = pool.size();
        activeCount = 5;
    }
}
