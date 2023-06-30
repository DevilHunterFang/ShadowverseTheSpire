package shadowverse.cards.Bishop;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import shadowverse.cards.AbstractBanPool;
import shadowverse.cards.BanGroup;
import shadowverse.cards.Bishop.Amulet1.*;
import shadowverse.cards.Bishop.Amulet2.*;
import shadowverse.cards.Bishop.Default.*;
import shadowverse.cards.Bishop.Evil.*;
import shadowverse.cards.Bishop.MechLion.*;
import shadowverse.cards.Bishop.Recover1.*;
import shadowverse.cards.Bishop.Recover2.*;
import shadowverse.cards.Bishop.Ward.*;
import shadowverse.characters.Bishop;

import java.util.ArrayList;

public class BishopPool extends AbstractBanPool {
    static String ID;
    public static CardColor color;
    static ArrayList<BanGroup> pool;
    static int groupCount;
    static int activeCount;

    public BishopPool(int code) {
        super(getName(code), getImg(code), color, code);
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
        return new BishopPool(this.code);
    }

    public AbstractBanPool copy(int code) {
        return new BishopPool(code);
    }

    static {
        ID = "shadowverse:BishopPool";
        color = Bishop.Enums.COLOR_WHITE;
        pool = new ArrayList<>();
        BanGroup Default = new BanGroup("Eris");
        BanGroup Amulet1 = new BanGroup("MusePrincess");
        BanGroup Amulet2 = new BanGroup("Jatelant");
        BanGroup Evil = new BanGroup("HereticPriest");
        BanGroup MechLion = new BanGroup("SalvationLion");
        BanGroup Recover1 = new BanGroup("Elana");
        BanGroup Recover2 = new BanGroup("Elluvia");
        BanGroup Ward = new BanGroup("Wilbert");
        Default.addCard(new Abdiel());
        Default.addCard(new AcolyteLight());
        Default.addCard(new AdherentOfDispair());
        Default.addCard(new EnstatuedSeraph());
        Default.addCard(new Eris());
        Default.addCard(new HeavenlyAegis());
        Default.addCard(new HeavenlyKnight());
        Default.addCard(new Jeanne());
        Default.addCard(new Marlone());
        Default.addCard(new OmenOfRepose());
        Default.addCard(new PrimalShipwright());
        Default.addCard(new Ra());
        Default.addCard(new RadiantAngel());
        Default.addCard(new RealmOfRepose());
        Default.addCard(new SacredSheep());
        Default.addCard(new SummitTemple());
        Default.addCard(new ThemisDecree());
        Default.addCard(new TheStrength());
        Default.addCard(new SacredPlea());
        Amulet1.addCard(new Agnes());
        Amulet1.addCard(new AngelRat());
        Amulet1.addCard(new Charaton());
        Amulet1.addCard(new GoldenCity());
        Amulet1.addCard(new JusticeMana());
        Amulet1.addCard(new MajorPrayers());
        Amulet1.addCard(new MusePrincess());
        Amulet1.addCard(new SneakAttack());
        Amulet1.addCard(new Uneriel());
        Amulet1.addCard(new UnlikelyFellowship());
        Amulet1.addCard(new WhiteEagleBaptism());
        Amulet1.addCard(new PunishmentSniper());
        Amulet1.addCard(new WardenOfTheWings());
        Amulet2.addCard(new BeastCallAria());
        Amulet2.addCard(new DivineBirdSong());
        Amulet2.addCard(new Garuda());
        Amulet2.addCard(new GodLovingSmite());
        Amulet2.addCard(new HallowedDogma());
        Amulet2.addCard(new Jatelant());
        Amulet2.addCard(new Saren(0));
        Amulet2.addCard(new Selena());
        Amulet2.addCard(new SkullFane());
        Amulet2.addCard(new HolyLightningBird());
        Amulet2.addCard(new PinionPrayer());
        Amulet2.addCard(new JudgmentSpearMaster());
        Evil.addCard(new AllFeeling());
        Evil.addCard(new DarkOffering());
        Evil.addCard(new DirtyPriest());
        Evil.addCard(new HereticPriest());
        Evil.addCard(new ImpiousBishop());
        Evil.addCard(new MoriaeEncomium());
        Evil.addCard(new TarnishedGrail());
        Evil.addCard(new TempleOfHeresy());
        Evil.addCard(new TheUntrueGod());
        Evil.addCard(new Tribunal());
        MechLion.addCard(new IronknuckleNun());
        MechLion.addCard(new Limonia());
        MechLion.addCard(new LionCrystal());
        MechLion.addCard(new LionTemple());
        MechLion.addCard(new PeaceWeaver());
        MechLion.addCard(new PrismSwing());
        MechLion.addCard(new RobowhipReverend());
        MechLion.addCard(new RobowingPrecant());
        MechLion.addCard(new SalvationLion());
        MechLion.addCard(new Vice());
        Recover1.addCard(new BenevolentBlight());
        Recover1.addCard(new Elana());
        Recover1.addCard(new EternalDispair());
        Recover1.addCard(new HealingPrayer());
        Recover1.addCard(new KLT());
        Recover1.addCard(new Lorena());
        Recover1.addCard(new PegasusSculpture());
        Recover1.addCard(new QueenOfHope());
        Recover1.addCard(new Tenko());
        Recover1.addCard(new WhitefangTemple());
        Recover2.addCard(new AgentOfTheCommandments());
        Recover2.addCard(new Elluvia());
        Recover2.addCard(new Erralde());
        Recover2.addCard(new ExecutorOfTheOath());
        Recover2.addCard(new Kira());
        Recover2.addCard(new Lou_Bishop());
        Recover2.addCard(new MarkUnleashed());
        Recover2.addCard(new OrchidExaminationHall());
        Recover2.addCard(new PureflameLady());
        Recover2.addCard(new PureflowerMaiden());
        Recover2.addCard(new Verdilia());
        Ward.addCard(new Anvelt());
        Ward.addCard(new BejeweledShrine());
        Ward.addCard(new DiamondMaster());
        Ward.addCard(new GemstoneWingy());
        Ward.addCard(new HallowedCave());
        Ward.addCard(new HolyEnchanter());
        Ward.addCard(new HolyHector());
        Ward.addCard(new SacredCounter());
        Ward.addCard(new SapphirePriestess());
        Ward.addCard(new Shiro());
        Ward.addCard(new Wilbert());
        pool.add(Default);
        pool.add(Amulet1);
        pool.add(Amulet2);
        pool.add(Evil);
        pool.add(MechLion);
        pool.add(Recover1);
        pool.add(Recover2);
        pool.add(Ward);
        groupCount = pool.size();
        activeCount = 5;
    }
}
