package shadowverse.cards.Witch;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import shadowverse.cards.AbstractBanPool;
import shadowverse.cards.BanGroup;
import shadowverse.cards.Witch.Academic.*;
import shadowverse.cards.Witch.Chess.*;
import shadowverse.cards.Witch.Default.*;
import shadowverse.cards.Witch.Earth1.*;
import shadowverse.cards.Witch.Earth2.*;
import shadowverse.cards.Witch.Mech.*;
import shadowverse.cards.Witch.Natura.*;
import shadowverse.cards.Witch.Spellboost1.*;
import shadowverse.cards.Witch.Spellboost2.*;
import shadowverse.characters.Witchcraft;

import java.util.ArrayList;

public class WitchPool extends AbstractBanPool {
    static String ID;
    public static CardColor color;
    static ArrayList<BanGroup> pool;
    static int groupCount;
    static int activeCount;

    public WitchPool(int code) {
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
        return new WitchPool(this.code);
    }

    public AbstractBanPool copy(int code) {
        return new WitchPool(code);
    }

    static {
        ID = "shadowverse:WitchPool";
        color = Witchcraft.Enums.COLOR_BLUE;
        pool = new ArrayList<>();
        BanGroup Default = new BanGroup("Sephie");
        BanGroup Academic = new BanGroup("AnneAndGrea");
        BanGroup Chess = new BanGroup("MysticKing");
        BanGroup Earth1 = new BanGroup("OrichalcumGolem");
        BanGroup Earth2 = new BanGroup("Magisa");
        BanGroup Mech = new BanGroup("Tetra");
        BanGroup Natura = new BanGroup("Riley");
        BanGroup Spellboost1 = new BanGroup("DimensionShift");
        BanGroup Spellboost2 = new BanGroup("Runie");
        Default.addCard(new AdherentOfElimination());
        Default.addCard(new Cagliostro());
        Default.addCard(new ChainLightning());
        Default.addCard(new Chaos());
        Default.addCard(new ConjureTwosome());
        Default.addCard(new DevotedResearcher());
        Default.addCard(new ForbiddenDarkMage());
        Default.addCard(new GuildAssembly());
        Default.addCard(new ImaginationRealized());
        Default.addCard(new MadcapConjuration());
        Default.addCard(new MysterianKnowledge());
        Default.addCard(new MysticSeeker());
        Default.addCard(new ObsessiveScholar());
        Default.addCard(new Oz());
        Default.addCard(new Petrification());
        Default.addCard(new Sephie());
        Default.addCard(new Simael());
        Default.addCard(new StaffOfWhirlwinds());
        Default.addCard(new Vincent());
        Default.addCard(new VolunteerTestSubject());
        Default.addCard(new WardenOfTheArcane());
        Default.addCard(new Yukishima());
        Academic.addCard(new Amaryllis());
        Academic.addCard(new Anne());
        Academic.addCard(new AnneAndGrea());
        Academic.addCard(new BellWitch());
        Academic.addCard(new Craig());
        Academic.addCard(new Grea());
        Academic.addCard(new Hanna());
        Academic.addCard(new Miranda());
        Academic.addCard(new MysterianParty());
        Academic.addCard(new Owen());
        Chess.addCard(new Blitz());
        Chess.addCard(new Check());
        Chess.addCard(new MagicalBishop());
        Chess.addCard(new MagicalKnight());
        Chess.addCard(new MagicalRook());
        Chess.addCard(new MagicalStrategy());
        Chess.addCard(new MysticKing());
        Chess.addCard(new MysticQueen());
        Chess.addCard(new TheFool());
        Chess.addCard(new UnleashTruth());
        Earth1.addCard(new AcidGolem());
        Earth1.addCard(new ArcticChimera());
        Earth1.addCard(new AstrologicalSorcerer());
        Earth1.addCard(new CommenceExperiment());
        Earth1.addCard(new DwarfAlchemist());
        Earth1.addCard(new GolemAssault());
        Earth1.addCard(new MasterMageLevi());
        Earth1.addCard(new OrichalcumGolem());
        Earth1.addCard(new PiousInstructor());
        Earth1.addCard(new WitchSnap());
        Earth2.addCard(new Clarke());
        Earth2.addCard(new Erasmus());
        Earth2.addCard(new Faust());
        Earth2.addCard(new GrandSummoning());
        Earth2.addCard(new Magisa());
        Earth2.addCard(new Telescope());
        Earth2.addCard(new WitchsCauldron());
        Earth2.addCard(new BindingRitual());
        Earth2.addCard(new ColossalSummoning());
        Mech.addCard(new Awakened());
        Mech.addCard(new GrimoireSorcerer());
        Mech.addCard(new Isabelle());
        Mech.addCard(new JetBroomWitch());
        Mech.addCard(new MagitechGolem());
        Mech.addCard(new MechabookSorcerer());
        Mech.addCard(new MechanizedLifeform());
        Mech.addCard(new MechastaffSorcerer());
        Mech.addCard(new SorceryInSolidarity());
        Mech.addCard(new Tetra());
        Natura.addCard(new Aeroelementalist());
        Natura.addCard(new ApexElemental());
        Natura.addCard(new Elementalmana());
        Natura.addCard(new Geoelementist());
        Natura.addCard(new Maiser());
        Natura.addCard(new MysterianWisdom());
        Natura.addCard(new Pyromancer());
        Natura.addCard(new Riley());
        Natura.addCard(new Shop());
        Natura.addCard(new Stormelementalist());
        Spellboost1.addCard(new BladeRain());
        Spellboost1.addCard(new DimensionalWitch());
        Spellboost1.addCard(new DimensionShift());
        Spellboost1.addCard(new FatesHand());
        Spellboost1.addCard(new Kuon());
        Spellboost1.addCard(new Kyouka(0));
        Spellboost1.addCard(new MagicMissile());
        Spellboost1.addCard(new Ogler());
        Spellboost1.addCard(new OmenOfTruth());
        Spellboost1.addCard(new ZealotOfTruth());
        Spellboost2.addCard(new AbsoluteZeroBlade());
        Spellboost2.addCard(new Concentration());
        Spellboost2.addCard(new EdictOfTruth());
        Spellboost2.addCard(new FireChain());
        Spellboost2.addCard(new Ghios());
        Spellboost2.addCard(new GigantChimera());
        Spellboost2.addCard(new Gruinne());
        Spellboost2.addCard(new JubilanceWitch());
        Spellboost2.addCard(new Lifetime());
        Spellboost2.addCard(new Lou());
        Spellboost2.addCard(new MagicOwl());
        Spellboost2.addCard(new Runie());
        Spellboost2.addCard(new TruthsAdjudication());
        pool.add(Default);
        pool.add(Academic);
        pool.add(Chess);
        pool.add(Earth1);
        pool.add(Earth2);
        pool.add(Mech);
        pool.add(Natura);
        pool.add(Spellboost1);
        pool.add(Spellboost2);
        groupCount = pool.size();
        activeCount = 5;
    }
}
