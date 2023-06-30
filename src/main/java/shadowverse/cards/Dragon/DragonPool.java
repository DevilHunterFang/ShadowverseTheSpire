package shadowverse.cards.Dragon;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import shadowverse.cards.AbstractBanPool;
import shadowverse.cards.BanGroup;
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
        ID = "shadowverse:ElfPool";
        color = Elf.Enums.COLOR_GREEN;
        pool = new ArrayList<>();
        BanGroup Default = new BanGroup("TheHanged");
        BanGroup Armed = new BanGroup("TheHanged");
        BanGroup Discard1 = new BanGroup("TheHanged");
        BanGroup Discard2 = new BanGroup("TheHanged");
        BanGroup Natura = new BanGroup("TheHanged");
        BanGroup Ramp = new BanGroup("TheHanged");
        BanGroup Tempo = new BanGroup("TheHanged");
        BanGroup Buff = new BanGroup("TheHanged");
        activeCount = 5;
    }
}
