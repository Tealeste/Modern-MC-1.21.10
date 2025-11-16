package icyllis.modernui.mc.text;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.component.ResolvableProfile;

import javax.annotation.Nullable;
import java.util.Objects;

/**
 * Stores the data required to render a Minecraft inline object (atlas sprite or player head).
 */
public final class InlineObject {

    public enum Type {
        ATLAS,
        PLAYER
    }

    public static final int WIDTH = 8;
    public static final int HEIGHT = 8;
    public static final float TOP_OFFSET = -1.0f;

    private final Type type;
    @Nullable
    private final ResourceLocation atlasId;
    @Nullable
    private final ResourceLocation spriteId;
    @Nullable
    private final ResolvableProfile profile;
    private final boolean showHat;

    private InlineObject(Type type,
                         @Nullable ResourceLocation atlasId,
                         @Nullable ResourceLocation spriteId,
                         @Nullable ResolvableProfile profile,
                         boolean showHat) {
        this.type = type;
        this.atlasId = atlasId;
        this.spriteId = spriteId;
        this.profile = profile;
        this.showHat = showHat;
    }

    public static InlineObject atlas(ResourceLocation atlasId, ResourceLocation spriteId) {
        Objects.requireNonNull(atlasId, "Atlas id");
        Objects.requireNonNull(spriteId, "Sprite id");
        return new InlineObject(Type.ATLAS, atlasId, spriteId, null, false);
    }

    public static InlineObject player(ResolvableProfile profile, boolean showHat) {
        Objects.requireNonNull(profile, "Profile");
        return new InlineObject(Type.PLAYER, null, null, profile, showHat);
    }

    public Type type() {
        return type;
    }

    @Nullable
    public ResourceLocation atlasId() {
        return atlasId;
    }

    @Nullable
    public ResourceLocation spriteId() {
        return spriteId;
    }

    @Nullable
    public ResolvableProfile profile() {
        return profile;
    }

    public boolean showHat() {
        return showHat;
    }
}
