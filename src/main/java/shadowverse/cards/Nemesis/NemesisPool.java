package shadowverse.cards.Nemesis;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import jdk.nashorn.internal.parser.Token;
import shadowverse.cards.AbstractBanPool;
import shadowverse.cards.BanGroup;
import shadowverse.cards.Nemesis.Artifact.*;
import shadowverse.cards.Nemesis.Condemned.*;
import shadowverse.cards.Nemesis.Default.*;
import shadowverse.cards.Nemesis.Mech.*;
import shadowverse.cards.Nemesis.Pile.*;
import shadowverse.cards.Nemesis.Puppet.*;
import shadowverse.cards.Nemesis.Resonance.*;
import shadowverse.cards.Nemesis.Tokens.*;
import shadowverse.characters.Nemesis;

import java.util.ArrayList;

public class NemesisPool extends AbstractBanPool {
    static String ID;
    public static CardColor color;
    static ArrayList<BanGroup> pool;
    static int groupCount;
    static int activeCount;

    public NemesisPool(int code) {
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
        return new NemesisPool(this.code);
    }

    public AbstractBanPool copy(int code) {
        return new NemesisPool(code);
    }

    static {
        ID = "shadowverse:NemesisPool";
        color = Nemesis.Enums.COLOR_SKY;
        pool = new ArrayList<>();
        BanGroup Default = new BanGroup("Tolerance");
        BanGroup Artifact = new BanGroup("GenesisArtifact");
        BanGroup Condemned = new BanGroup("Cutthroat");
        BanGroup Mech = new BanGroup("BelphometCard");
        BanGroup Pile = new BanGroup("DeusExMachina");
        BanGroup Puppet = new BanGroup("Orchid");
        BanGroup Resonance = new BanGroup("Cassim");
        BanGroup Tokens = new BanGroup("OmenOfDestruction2");
        Default.addCard(new ApostleOfDestruction());
        Default.addCard(new Concentrate());
        Default.addCard(new Karula());
        Default.addCard(new MindDivider());
        Default.addCard(new NilpotentEntity());
        Default.addCard(new OmniscientKaiser());
        Default.addCard(new Paracelsus());
        Default.addCard(new RebelAgainstFate());
        Default.addCard(new ReturningMelody());
        Default.addCard(new RevisedExperiment());
        Default.addCard(new Scavenge());
        Default.addCard(new ServantOfDarkness());
        Default.addCard(new Synchronization());
        Default.addCard(new TheWheelOfFortune());
        Default.addCard(new Tolerance());
        Default.addCard(new Vyrmedea());
        Default.addCard(new Robopup());
        Default.addCard(new MagnaSaber());
        Default.addCard(new PuppeteerStrings());
        Default.addCard(new Metaproduction());
        Default.addCard(new DiscipleOfDestruction());
        Default.addCard(new Automation());
        Default.addCard(new Miriam());
        Default.addCard(new DisseminatorOfWisdom());
        Default.addCard(new SyntheticEden());
        Artifact.addCard(new Ralmia());
        Artifact.addCard(new ArtifactImpulse());
        Artifact.addCard(new ArtifactScan());
        Artifact.addCard(new CelestialArtifact());
        Artifact.addCard(new GenesisArtifact());
        Artifact.addCard(new Modest());
        Artifact.addCard(new MultiarmedArtifact());
        Artifact.addCard(new RoboticUser());
        Artifact.addCard(new Zerk());
        Artifact.addCard(new SisterlyBonds());
        Artifact.addCard(new AugmentationBestowal());
        Artifact.addCard(new DeviceTuner());
        Condemned.addCard(new ANewDiscovery());
        Condemned.addCard(new BlackrustUnderling());
        Condemned.addCard(new Cutthroat());
        Condemned.addCard(new DimensionCrack());
        Condemned.addCard(new IronforgedRightHand());
        Condemned.addCard(new Judith());
        Condemned.addCard(new Kyrzael());
        Condemned.addCard(new SmeltworkBodyguard());
        Condemned.addCard(new WardenOfTrigger());
        Condemned.addCard(new StringMaster());
        Mech.addCard(new AerialCraft());
        Mech.addCard(new Armadillo());
        Mech.addCard(new Bearminator());
        Mech.addCard(new BelphometCard());
        Mech.addCard(new Enforcer());
        Mech.addCard(new Gioffre());
        Mech.addCard(new LasershellTortoise());
        Mech.addCard(new MegaEnforcer());
        Mech.addCard(new SumoMechanic());
        Mech.addCard(new MagnaGiant());
        Mech.addCard(new Invasion());
        Pile.addCard(new ArtifactCall());
        Pile.addCard(new Biofabrication());
        Pile.addCard(new DeusExMachina());
        Pile.addCard(new Icarus());
        Pile.addCard(new IronStaffMechanic());
        Pile.addCard(new KnowerOfHistory());
        Pile.addCard(new NewOrder());
        Pile.addCard(new Prototype());
        Pile.addCard(new Spine());
        Pile.addCard(new Lazuli());
        Pile.addCard(new Evamia());
        Pile.addCard(new Acceleratium());
        Pile.addCard(new PurelightPrototype());
        Puppet.addCard(new DollsOwner());
        Puppet.addCard(new InfinityPuppeteer());
        Puppet.addCard(new Licht());
        Puppet.addCard(new Lyelth());
        Puppet.addCard(new Noa());
        Puppet.addCard(new Orchid());
        Puppet.addCard(new PuppetRoom());
        Puppet.addCard(new TriggerPuppeteer());
        Puppet.addCard(new Zwei());
        Puppet.addCard(new Chastity());
        Resonance.addCard(new Cassim());
        Resonance.addCard(new DominateFortress());
        Resonance.addCard(new GadgetUser());
        Resonance.addCard(new Ines());
        Resonance.addCard(new MagnaZero());
        Resonance.addCard(new Norn());
        Resonance.addCard(new Rosa());
        Resonance.addCard(new Shin());
        Resonance.addCard(new ChaosAura());
        Resonance.addCard(new TechnologyMana());
        Resonance.addCard(new CannonHermitCrab());
        Resonance.addCard(new Yuwan());
        Resonance.addCard(new FieryBarrage());
        Tokens.addCard(new AdherentOfMelody());
        Tokens.addCard(new DestructionRefrain());
        Tokens.addCard(new UnnamedDetermination());
        Tokens.addCard(new HeartlessBattle());
        Tokens.addCard(new ICCard());
        Tokens.addCard(new Illganeau());
        Tokens.addCard(new Maisha());
        Tokens.addCard(new OmenOfDestruction());
        Tokens.addCard(new Windup());
        Tokens.addCard(new RoboticsReporter());
        pool.add(Default);
        pool.add(Artifact);
        pool.add(Condemned);
        pool.add(Mech);
        pool.add(Pile);
        pool.add(Puppet);
        pool.add(Resonance);
        pool.add(Tokens);
        groupCount = pool.size();
        activeCount = 5;
    }
}
