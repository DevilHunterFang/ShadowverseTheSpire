package shadowverse.skin;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.esotericsoftware.spine.AnimationState;
import com.esotericsoftware.spine.AnimationStateData;
import com.esotericsoftware.spine.Skeleton;
import com.esotericsoftware.spine.SkeletonData;
import com.esotericsoftware.spine.SkeletonJson;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.screens.CharSelectInfo;
import shadowverse.Shadowverse;

public abstract class AbstractSkin {
    public Texture portraitStatic_IMG;

    public Texture portraitAnimation_IMG;

    public TextureAtlas portraitAtlas = null;

    public Skeleton portraitSkeleton;

    public AnimationState portraitState;

    public AnimationStateData portraitStateData;

    public SkeletonData portraitData;

    public String portraitAtlasPath = null;

    public String NAME;

    public int portraitAnimationType = 0;

    public String SHOULDER1;

    public String SHOULDER2;

    public String CORPSE;

    public String atlasURL;

    public String jsonURL;

    public String DESCRIPTION = null;

    public String scmlURL;

    public String hurt;

    public String hurt2;

    public String hurt3;

    public String hurt4;

    public String select;

    public void loadPortraitAnimation() {
        if (hasAnimation().booleanValue()) {
            loadAnimation();
            setAnimation();
            InitializeStaticPortraitVar();
        } else {
            updateBgImg();
        }
    }

    public Boolean hasAnimation() {
        return Boolean.valueOf((this.portraitAtlasPath != null));
    }

    public void loadAnimation() {
        this.portraitAtlas = new TextureAtlas(Gdx.files.internal(this.portraitAtlasPath + ".atlas"));
        SkeletonJson json = new SkeletonJson(this.portraitAtlas);
        json.setScale(Settings.scale / 1.0F);
        this.portraitData = json.readSkeletonData(Gdx.files.internal(this.portraitAtlasPath + ".json"));
        this.portraitSkeleton = new Skeleton(this.portraitData);
        this.portraitSkeleton.setColor(Color.WHITE);
        this.portraitStateData = new AnimationStateData(this.portraitData);
        this.portraitState = new AnimationState(this.portraitStateData);
        this.portraitStateData.setDefaultMix(0.2F);
        this.portraitState.setTimeScale(1.0F);
    }

    public void setAnimation() {
        this.portraitState.setAnimation(1, "fade_in", false);
        this.portraitState.addAnimation(0, "idle", true, 0.0F);
        InitializeStaticPortraitVar();
    }

    public void InitializeStaticPortraitVar() {
    }

    public Texture updateBgImg() {
        Shadowverse.saveSettings();
        if (!hasAnimation().booleanValue())
            return this.portraitStatic_IMG;
        return this.portraitAnimation_IMG;
    }

    public CharSelectInfo updateCharInfo(CharSelectInfo info) {
        return info;
    }

    public void render(SpriteBatch sb) {
        if (hasAnimation().booleanValue()) {
            this.portraitState.update(Gdx.graphics.getDeltaTime());
            this.portraitState.apply(this.portraitSkeleton);
            this.portraitSkeleton.updateWorldTransform();
            setPos();
            skeletonRenderUpdate(sb);
            this.portraitSkeleton.setColor(Color.WHITE.cpy());
            this.portraitSkeleton.setFlip(false, false);
            sb.end();
            CardCrawlGame.psb.begin();
            skeletonRender(sb);
        }
    }

    public void setPos() {
    }

    public void skeletonRenderUpdate(SpriteBatch sb) {
    }

    public void skeletonRender(SpriteBatch sb) {
        if (hasAnimation().booleanValue()) {
            AbstractCreature.sr.draw(CardCrawlGame.psb, this.portraitSkeleton);
            CardCrawlGame.psb.end();
            sb.begin();
        }
    }

    public void update() {
    }

    public void clearWhenClick() {
    }

    public void extraHitboxRender(SpriteBatch sb) {
    }

    public Boolean extraHitboxClickCheck() {
        return Boolean.valueOf(false);
    }

    public String getNewCharDescription() {
        if (this.DESCRIPTION != null)
            return this.DESCRIPTION;
        return "";
    }

    public String getAtlasURL() {
        return this.atlasURL;
    }

    public String getJsonURL() {
        return this.jsonURL;
    }

    public String getSHOULDER1() {
        return this.SHOULDER1;
    }

    public String getSHOULDER2() {
        return this.SHOULDER2;
    }

    public String getCORPSE() {
        return this.CORPSE;
    }

    public String getScmlURL() {
        return this.scmlURL;
    }

    public String getHurt() {
        return hurt;
    }

    public String getHurt2() {
        return hurt2;
    }


    public String getHurt3() {
        return hurt3;
    }


    public String getHurt4() {
        return hurt4;
    }

    public String select(){
        return select;
    }

    public void playHurtSound(int lastDamageTaken){
        if (lastDamageTaken >= 20){
            CardCrawlGame.sound.playA(hurt4, 0.0F);
        }else if (lastDamageTaken > 0 ){
            String sound = null;
            int roll = AbstractDungeon.cardRandomRng.random(99);
            if (roll < 50) {
                sound = hurt;
            } else if (roll < 80) {
                sound = hurt2;
            } else {
                sound = hurt3;
            }
            CardCrawlGame.sound.playA(sound, 0.0F);
        }
    }

    public void playSelect(){
        CardCrawlGame.sound.playA(select, 0.0F);
    }

    public void dispose() {
        if (this.portraitAtlas != null)
            this.portraitAtlas.dispose();
    }
}

