package shadowverse.cards.Witch;

import shadowverse.cards.Neutral.Neutral.*;
import shadowverse.cards.Witch.Academic.*;
import shadowverse.cards.Witch.Basic.FieryEmbrace;
import shadowverse.cards.Witch.Basic.Insight;
import shadowverse.cards.Witch.Chess.MagicalRook;
import shadowverse.cards.Witch.Chess.MagicalStrategy;
import shadowverse.cards.Witch.Chess.MysticKing;
import shadowverse.cards.Witch.Default.*;
import shadowverse.cards.Witch.Earth1.CommenceExperiment;
import shadowverse.cards.Witch.Earth1.DwarfAlchemist;
import shadowverse.cards.Witch.Earth1.WitchSnap;
import shadowverse.cards.Witch.Earth2.Clarke;
import shadowverse.cards.Witch.Earth2.Gruinne;
import shadowverse.cards.Witch.Earth2.Magisa;
import shadowverse.cards.Witch.Mech.GrimoireSorcerer;
import shadowverse.cards.Witch.Spellboost1.Kuon;
import shadowverse.cards.Witch.Spellboost1.MagicMissile;
import shadowverse.cards.Witch.Spellboost1.Ogler;
import shadowverse.cards.Witch.Spellboost2.GigantChimera;
import shadowverse.cards.Witch.Spellboost2.JubilanceWitch;
import shadowverse.cards.Witch.Spellboost2.Lou;
import shadowverse.cards.Witch.Spellboost2.MagicOwl;
import shadowverse.helper.StartPack;

public class WitchStarterPack {
    public static StartPack academic;
    public static StartPack chess;
    public static StartPack earth1;
    public static StartPack earth2;
    public static StartPack spellboost1;
    public static StartPack spellboost2;

    static {
        academic = new StartPack(new AcademyWyrmist(), new Anne());
        chess = new StartPack(new MagicalStrategy(), new MysticKing());
        earth1 = new StartPack(new Clarke(), new ForbiddenDarkMage());
        earth2 = new StartPack(new Clarke(), new Magisa());
        spellboost1 = new StartPack(new MagicMissile(), new Kuon());
        spellboost2 = new StartPack(new MagicOwl(), new GigantChimera());

        academic.addCard(new AngelicSnipe());
        academic.addCard(new CloudChorus(),2);
        academic.addCard(new Goliath(), 3);
        academic.addCard(new Insight());
        academic.addCard(new Hanna());
        academic.addCard(new Owen(),2);
        academic.addCard(new MysterianKnowledge());

        chess.addCard(new AngelicSnipe(), 2);
        chess.addCard(new Goliath(), 3);
        chess.addCard(new CloudChorus(), 3);
        chess.addCard(new MagicalRook());
        chess.addCard(new FieryEmbrace());

        earth1.addCard(new Goblin());
        earth1.addCard(new Goliath(), 2);
        earth1.addCard(new AngelicSwordMaiden());
        earth1.addCard(new Insight());
        earth1.addCard(new WitchSnap(),2);
        earth1.addCard(new DwarfAlchemist(), 3);
        earth1.addCard(new ConjureTwosome());


        earth2.addCard(new Goblin());
        earth2.addCard(new Goliath(), 3);
        earth2.addCard(new CloudChorus(),2);
        earth2.addCard(new AngelicSwordMaiden());
        earth2.addCard(new Insight());
        earth2.addCard(new Gruinne());
        earth2.addCard(new JubilanceWitch());
        earth2.addCard(new CommenceExperiment());

        spellboost1.addCard(new AngelicSnipe(),1);
        spellboost1.addCard(new CloudChorus(),3);
        spellboost1.addCard(new Goliath(), 3);
        spellboost1.addCard(new Insight());
        spellboost1.addCard(new FieryEmbrace());
        spellboost1.addCard(new Lou(),2);

        spellboost2.addCard(new AngelicSnipe(),1);
        spellboost2.addCard(new CloudChorus(),3);
        spellboost2.addCard(new Goliath(), 3);
        spellboost2.addCard(new Insight());
        spellboost2.addCard(new FieryEmbrace());
        spellboost2.addCard(new Lou(),2);
    }
}
