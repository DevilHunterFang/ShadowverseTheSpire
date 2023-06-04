package shadowverse.cards.Elf;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import shadowverse.cards.AbstractBanPool;
import shadowverse.cards.BanGroup;
import shadowverse.cards.Elf.Default.*;
import shadowverse.cards.Elf.Fairy.*;
import shadowverse.cards.Elf.Left.*;
import shadowverse.cards.Elf.Long.*;
import shadowverse.cards.Elf.NatMech.*;
import shadowverse.cards.Elf.Return.*;
import shadowverse.cards.Elf.Short.*;
import shadowverse.cards.Elf.Wood.*;
import shadowverse.characters.Elf;

import java.util.ArrayList;

public class ElfPool extends AbstractBanPool {
    static String ID;
    public static CardColor color;
    static ArrayList<BanGroup> pool;
    static int groupCount;
    static int activeCount;

    public ElfPool(int code) {
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
        return new ElfPool(this.code);
    }

    public AbstractBanPool copy(int code) {
        return new ElfPool(code);
    }

    static {
        ID = "shadowverse:ElfPool";
        color = Elf.Enums.COLOR_GREEN;
        pool = new ArrayList<>();
        BanGroup Default = new BanGroup("TheHanged");
        BanGroup Fairy = new BanGroup("Nobilis");
        BanGroup Left = new BanGroup("Sekka");
        BanGroup Long = new BanGroup("SkyDevouring");
        BanGroup NatMech = new BanGroup("Damian");
        BanGroup Return = new BanGroup("Wimael");
        BanGroup Short = new BanGroup("Magachiyo");
        BanGroup Wood = new BanGroup("Lymaga");
        Default.addCard(new AdherentOfAnnihilation());
        Default.addCard(new Aerin());
        Default.addCard(new AirboundBarrage());
        Default.addCard(new BenevolentElf());
        Default.addCard(new BlossomTreant());
        Default.addCard(new Cassiopeia());
        Default.addCard(new Comfort());
        Default.addCard(new CorruptionOfUnkilling());
        Default.addCard(new Korwa());
        Default.addCard(new Lisa());
        Default.addCard(new Lycoris());
        Default.addCard(new MarkOfUnkilling());
        Default.addCard(new Metera());
        Default.addCard(new NaturesGuidance());
        Default.addCard(new OmenOfUnkilling());
        Default.addCard(new ServantOfUnkilling());
        Default.addCard(new TerrorFormer());
        Default.addCard(new TheHanged());
        Default.addCard(new Tweyen());
        Default.addCard(new TwinAssault());
        Default.addCard(new WardOfUnkilling());
        Default.addCard(new Yggdrasil());
        Default.addCard(new ZealotOfUnkilling());
        Fairy.addCard(new Aria());
        Fairy.addCard(new DivineSmithing());
        Fairy.addCard(new FairyCircle());
        Fairy.addCard(new Filly());
        Fairy.addCard(new ForestFairy());
        Fairy.addCard(new Nobilis());
        Fairy.addCard(new PixieParadise());
        Fairy.addCard(new RoseQueen());
        Fairy.addCard(new Verdant());
        Fairy.addCard(new WonderTree());
        Left.addCard(new Amataz());
        Left.addCard(new Bayle());
        Left.addCard(new Castelle());
        Left.addCard(new ElfSong());
        Left.addCard(new Genshin());
        Left.addCard(new MiracleHarvest());
        Left.addCard(new NatureCorroded());
        Left.addCard(new PrimalGigant());
        Left.addCard(new Sekka());
        Left.addCard(new WoodOfBrambles());
        Long.addCard(new Alberta());
        Long.addCard(new AriasWhirlwind());
        Long.addCard(new ElfGuard());
        Long.addCard(new HarvestSeason());
        Long.addCard(new Hero());
        Long.addCard(new Hozumi());
        Long.addCard(new Pamera());
        Long.addCard(new Rhinoceroach());
        Long.addCard(new SkyDevouring());
        Long.addCard(new WindFall());
        NatMech.addCard(new AvatarOfFruition());
        NatMech.addCard(new Cleft());
        NatMech.addCard(new Damian());
        NatMech.addCard(new GuardOfMachinatree());
        NatMech.addCard(new IrongliderElf());
        NatMech.addCard(new Ladica());
        NatMech.addCard(new MachineClaw());
        NatMech.addCard(new NaturalMana());
        NatMech.addCard(new RoboticBagworm());
        NatMech.addCard(new ScaryTrend());
        Return.addCard(new CosmosFang());
        Return.addCard(new Homecoming());
        Return.addCard(new PhantomBloomPredator());
        Return.addCard(new PixieMischief());
        Return.addCard(new RejuvenatingResurrection());
        Return.addCard(new Slade());
        Return.addCard(new ThicketOfGnarledHands());
        Return.addCard(new WardenOfBalms());
        Return.addCard(new WhirlwindRhinoceroach());
        Return.addCard(new Wimael());
        Short.addCard(new BeetleWarrior());
        Short.addCard(new BuddingInitiate());
        Short.addCard(new Kokkoro(0));
        Short.addCard(new LeafshadeAssassin());
        Short.addCard(new Magachiyo());
        Short.addCard(new MiracleOfLove());
        Short.addCard(new Rino(0));
        Short.addCard(new VerdantLieutenant());
        Short.addCard(new WhirlwindAssault());
        Short.addCard(new WindFairy());
        Wood.addCard(new Arisa());
        Wood.addCard(new CrossCombination());
        Wood.addCard(new GreenbrierElf());
        Wood.addCard(new GreenWoodGuardian());
        Wood.addCard(new Loxis());
        Wood.addCard(new Lymaga());
        Wood.addCard(new Sukuna());
        Wood.addCard(new VarmintHunter());
        Wood.addCard(new WildwoodMatriarch());
        Wood.addCard(new WoodlandCleaver());
        pool.add(Default);
        pool.add(Fairy);
        pool.add(Left);
        pool.add(Long);
        pool.add(NatMech);
        pool.add(Return);
        pool.add(Short);
        pool.add(Wood);
        groupCount = pool.size();
        activeCount = 5;
    }
}
