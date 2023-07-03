package shadowverse.cards.Dragon;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import shadowverse.cards.AbstractBanPool;
import shadowverse.cards.BanGroup;
import shadowverse.cards.Bishop.Default.Ra;
import shadowverse.cards.Dragon.Armed.*;
import shadowverse.cards.Dragon.Buff.*;
import shadowverse.cards.Dragon.Default.*;
import shadowverse.cards.Dragon.Discard1.*;
import shadowverse.cards.Dragon.Discard2.*;
import shadowverse.cards.Dragon.Natura.*;
import shadowverse.cards.Dragon.Ramp.*;
import shadowverse.cards.Dragon.Tempo.*;
import shadowverse.characters.Dragon;
import shadowverse.characters.Elf;

import java.util.ArrayList;

public class DragonPool extends AbstractBanPool {
    static String ID;
    public static CardColor color;
    static ArrayList<BanGroup> pool;
    static int groupCount;
    static int activeCount;

    public DragonPool(int code) {
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
        return new DragonPool(this.code);
    }

    public AbstractBanPool copy(int code) {
        return new DragonPool(code);
    }

    static {
        ID = "shadowverse:DragonPool";
        color = Dragon.Enums.COLOR_BROWN;
        pool = new ArrayList<>();
        BanGroup Default = new BanGroup("RowenCard");
        BanGroup Armed = new BanGroup("LavateinnDragon");
        BanGroup Discard1 = new BanGroup("ImperialDragoon");
        BanGroup Discard2 = new BanGroup("Lumiore");
        BanGroup Natura = new BanGroup("Valdain");
        BanGroup Ramp = new BanGroup("ResplendentPhoenix");
        BanGroup Tempo = new BanGroup("DragonsongFlute");
        BanGroup Buff = new BanGroup("Dracu");
        Default.addCard(new Antemaria());
        Default.addCard(new BlazingBreath());
        Default.addCard(new DraconicFervor());
        Default.addCard(new DragonEmpressOtohime());
        Default.addCard(new DragonicCall());
        Default.addCard(new Drazael());
        Default.addCard(new Ethica());
        Default.addCard(new Garyu());
        Default.addCard(new Georgius());
        Default.addCard(new HotheadedMarauder());
        Default.addCard(new Rahab());
        Default.addCard(new RazorClawedThief());
        Default.addCard(new RowenCard());
        Default.addCard(new ScorchedEarthTyrant());
        Default.addCard(new SeabrandDragon());
        Default.addCard(new SiLong());
        Default.addCard(new WardenOfTheAdamantClaw());
        Discard1.addCard(new DracomancerRites());
        Discard1.addCard(new DraconicCounsel());
        Discard1.addCard(new DragoonMedic());
        Discard1.addCard(new Heliodragon());
        Discard1.addCard(new ImperialDragoon());
        Discard1.addCard(new Kit());
        Discard1.addCard(new RockbackAnkylosaurus());
        Discard1.addCard(new ScaleboundPlight());
        Discard1.addCard(new ShipsbanePlesiosaurus());
        Discard1.addCard(new SoaringDragonewt());
        Discard1.addCard(new SpringwellDragonKeeper());
        Discard1.addCard(new SteelcapPachycephalosaurus());
        Discard1.addCard(new TurncoatDragonSummoner());
        Discard1.addCard(new WildfangDragonewt());
        Discard2.addCard(new Argente());
        Discard2.addCard(new AugiteWyrm());
        Discard2.addCard(new BlazingDragonewt());
        Discard2.addCard(new DragonCounsel());
        Discard2.addCard(new DragonewtMight());
        Discard2.addCard(new GoldenDragonDen());
        Discard2.addCard(new Lilium());
        Discard2.addCard(new Lumiore());
        Discard2.addCard(new MermaidSinger());
        Discard2.addCard(new NoirNBlanc());
        Discard2.addCard(new OrbedCancer());
        Discard2.addCard(new WiseDragonewtScholar());
        Natura.addCard(new CursedFuror());
        Natura.addCard(new Djeana());
        Natura.addCard(new DragonplateWarrior());
        Natura.addCard(new LightningVelociraptor());
        Natura.addCard(new Lindworm());
        Natura.addCard(new ManagarmrScout());
        Natura.addCard(new NewfoundAllies());
        Natura.addCard(new TouchingThoughts());
        Natura.addCard(new Valdain());
        Natura.addCard(new VerdantRebirth());
        Natura.addCard(new WhirlwindPteranodon());
        Natura.addCard(new WildfireTyrannosaur());
        Natura.addCard(new WildMana());
        Ramp.addCard(new Dagon());
        Ramp.addCard(new DragonDevouringDread());
        Ramp.addCard(new EncounterFromTheDeep());
        Ramp.addCard(new Ghandagoza());
        Ramp.addCard(new PolyphonicRoar());
        Ramp.addCard(new Poseidon());
        Ramp.addCard(new PumpkinDragon());
        Ramp.addCard(new Python());
        Ramp.addCard(new ResplendentPhoenix());
        Ramp.addCard(new TheJustice());
        Ramp.addCard(new WaterwyrmBlessing());
        Ramp.addCard(new Zoe());
        Tempo.addCard(new AdherentOfArdor());
        Tempo.addCard(new ArdentTorch());
        Tempo.addCard(new DiscipleOfDisdain());
        Tempo.addCard(new DisdainfulRending());
        Tempo.addCard(new DragonHoard());
        Tempo.addCard(new HypersonicDragonewt());
        Tempo.addCard(new IvoryDragon());
        Tempo.addCard(new PrimeDragonKeeper());
        Tempo.addCard(new ServantOfDisdain());
        Tempo.addCard(new SneerOfDisdain());
        Armed.addCard(new ArmorBurst());
        Armed.addCard(new Byron());
        Armed.addCard(new DraconicArmor());
        Armed.addCard(new Draconir());
        Armed.addCard(new HammerDragonewt());
        Armed.addCard(new IronscaleSerpentDrake());
        Armed.addCard(new LanceLizard());
        Armed.addCard(new LavateinnDragon());
        Armed.addCard(new Reggie());
        Armed.addCard(new Rola());
        Armed.addCard(new SwiftbladeDragonewt());
        Armed.addCard(new WyrmfireEngineer());
        Buff.addCard(new CelestialDragoon());
        Buff.addCard(new CoralShark());
        Buff.addCard(new Dracu());
        Buff.addCard(new DragonAwakening());
        Buff.addCard(new GrandSlamTamer());
        Buff.addCard(new Gunbein());
        Buff.addCard(new Joe());
        Buff.addCard(new PrimeConflagration());
        Buff.addCard(new PropheticDragon());
        Buff.addCard(new Romelia());
        Buff.addCard(new Sandstorm());
        Buff.addCard(new WhimsicalMermaid());
        Buff.addCard(new WindsweptDragonewt());
        pool.add(Default);
        pool.add(Armed);
        pool.add(Discard1);
        pool.add(Discard2);
        pool.add(Natura);
        pool.add(Ramp);
        pool.add(Tempo);
        pool.add(Buff);
        groupCount = pool.size();
        activeCount = 5;
    }
}
