package shadowverse.cards.Vampire;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import shadowverse.cards.AbstractBanPool;
import shadowverse.cards.BanGroup;
import shadowverse.cards.Vampire.Academic.*;
import shadowverse.cards.Vampire.Avarice.*;
import shadowverse.cards.Vampire.Bat.*;
import shadowverse.cards.Vampire.Default.*;
import shadowverse.cards.Vampire.Evolve.*;
import shadowverse.cards.Vampire.NatMech.*;
import shadowverse.cards.Vampire.Self.*;
import shadowverse.cards.Vampire.Vengeance.*;
import shadowverse.cards.Vampire.Wrath.*;
import shadowverse.characters.Vampire;

import java.util.ArrayList;

public class VampirePool  extends AbstractBanPool {
    static String ID;
    public static CardColor color;
    static ArrayList<BanGroup> pool;
    static int groupCount;
    static int activeCount;

    public VampirePool(int code) {
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
        return new VampirePool(this.code);
    }

    public AbstractBanPool copy(int code) {
        return new VampirePool(code);
    }

    static {
        ID = "shadowverse:VampirePool";
        color = Vampire.Enums.COLOR_SCARLET;
        pool = new ArrayList<>();
        BanGroup Default = new BanGroup("BloodtrothEpitaph");
        BanGroup Academic = new BanGroup("Galom");
        BanGroup Avarice = new BanGroup("BeastEmpress");
        BanGroup Bat = new BanGroup("OldBloodKing");
        BanGroup Evolve = new BanGroup("Signa");
        BanGroup NatMech = new BanGroup("Mono");
        BanGroup Self = new BanGroup("OmenOfLust");
        BanGroup Vengeance = new BanGroup("SexyVampire");
        BanGroup Wrath = new BanGroup("Garodeth");
        Default.addCard(new AdherentOfDesire());
        Default.addCard(new Aluzard());
        Default.addCard(new Baal());
        Default.addCard(new BloodPact());
        Default.addCard(new BloodtrothEpitaph());
        Default.addCard(new BloodWolf());
        Default.addCard(new CrimsonDesire());
        Default.addCard(new Executioner());
        Default.addCard(new FloodBehemoth());
        Default.addCard(new GarnetWaltz());
        Default.addCard(new Genomuel());
        Default.addCard(new Irya(0));
        Default.addCard(new LoathingDesire());
        Default.addCard(new MaliciousBlader());
        Default.addCard(new Paracelise());
        Default.addCard(new Ravening());
        Default.addCard(new Relinquish());
        Default.addCard(new Shemyaza());
        Default.addCard(new SilverboltHail());
        Default.addCard(new SpiderWebImp());
        Default.addCard(new Urias());
        Default.addCard(new Volteo());
        Default.addCard(new WingsOfLust());
        Default.addCard(new Yurius());
        Default.addCard(new YuriusLevinDuke());
        Academic.addCard(new Arka());
        Academic.addCard(new BadGirlLife());
        Academic.addCard(new DemonEyeGangster());
        Academic.addCard(new Faker());
        Academic.addCard(new Galom());
        Academic.addCard(new Lucius());
        Academic.addCard(new MachSpeedMaron());
        Academic.addCard(new Vulgus());
        Academic.addCard(new Waltz());
        Academic.addCard(new WardenOfDecay());
        Avarice.addCard(new Archdemon());
        Avarice.addCard(new Ayre());
        Avarice.addCard(new BeastEmpress());
        Avarice.addCard(new Cradle());
        Avarice.addCard(new EternalContract());
        Avarice.addCard(new FullMoonLeap());
        Avarice.addCard(new HellSpearWarrior());
        Avarice.addCard(new MaskedMayhem());
        Avarice.addCard(new MoonriseWerewolf());
        Avarice.addCard(new RoomServiceDevil());
        Avarice.addCard(new ShowdownDemon());
        Bat.addCard(new BatNoise());
        Bat.addCard(new DarkfeastBat());
        Bat.addCard(new FellTransformation());
        Bat.addCard(new NightHorde());
        Bat.addCard(new NightVampire());
        Bat.addCard(new OldBloodKing());
        Bat.addCard(new SummoningBloodkin());
        Bat.addCard(new UnleashTheNightmare());
        Bat.addCard(new Vanpi());
        Bat.addCard(new Veight());
        Evolve.addCard(new CrimsonVirtue());
        Evolve.addCard(new DancingMiniSoulDevil());
        Evolve.addCard(new DestructiveSuccubus());
        Evolve.addCard(new Itsurugi());
        Evolve.addCard(new Kali());
        Evolve.addCard(new ScorpionOfTheDepths());
        Evolve.addCard(new Seox());
        Evolve.addCard(new ShadowDevil());
        Evolve.addCard(new Signa());
        Evolve.addCard(new Yuzuki());
        Evolve.addCard(new Gadel());
        Evolve.addCard(new Doublame());
        NatMech.addCard(new ArmoredBat());
        NatMech.addCard(new CorruptedBat());
        NatMech.addCard(new CreepingMadness());
        NatMech.addCard(new DuskshadeBat());
        NatMech.addCard(new LunaticMana());
        NatMech.addCard(new MechaforgeDevil());
        NatMech.addCard(new MechashotDevil());
        NatMech.addCard(new Mono());
        NatMech.addCard(new Neun());
        NatMech.addCard(new RuinwebSpider());
        NatMech.addCard(new Slayn());
        Self.addCard(new BearBerserk());
        Self.addCard(new BloodGarden());
        Self.addCard(new Flauros());
        Self.addCard(new Jormungand());
        Self.addCard(new Lilim());
        Self.addCard(new PrisonOfPain());
        Self.addCard(new RestlessParish());
        Self.addCard(new TheTemperance());
        Self.addCard(new ApostleOfLust());
        Self.addCard(new ServantOfLust());
        Self.addCard(new TheBerserker());
        Wrath.addCard(new AntelopeWarrior());
        Wrath.addCard(new EvilEyeDemon());
        Wrath.addCard(new OmenOfLust());
        Wrath.addCard(new OmnifacedArchdemon());
        Wrath.addCard(new RagingCommander());
        Wrath.addCard(new SadisticDemon());
        Wrath.addCard(new Garodeth());
        Wrath.addCard(new SteamrollingTank());
        Wrath.addCard(new DevilSheep());
        Wrath.addCard(new ViciousBlitzer());
        Vengeance.addCard(new ArkDaemon());
        Vengeance.addCard(new Azazel());
        Vengeance.addCard(new Belphegor());
        Vengeance.addCard(new ChaosShip());
        Vengeance.addCard(new DemonicAssault());
        Vengeance.addCard(new DevilOfVengeance());
        Vengeance.addCard(new DiabolicDrain());
        Vengeance.addCard(new Emeralda());
        Vengeance.addCard(new RageCommander());
        Vengeance.addCard(new Revelation());
        Vengeance.addCard(new SexyVampire());
        pool.add(Default);
        pool.add(Academic);
        pool.add(Avarice);
        pool.add(Bat);
        pool.add(Evolve);
        pool.add(NatMech);
        pool.add(Self);
        pool.add(Vengeance);
        pool.add(Wrath);
        groupCount = pool.size();
        activeCount = 5;
    }
}
