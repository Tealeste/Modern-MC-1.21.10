package icyllis.modernui.mc.text;

import icyllis.modernui.mc.ModernUIMod;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.FontDescription;
import net.minecraft.network.chat.FontDescription.Resource;
import net.minecraft.resources.ResourceLocation;

import javax.annotation.Nullable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Helper for handling the new {@link FontDescription} based font selectors in 1.21.10.
 */
public final class FontDescriptions {

    private static final Map<FontDescription, ResourceLocation> SPECIAL_FONT_IDS = new ConcurrentHashMap<>();
    private static final Map<ResourceLocation, FontDescription> SPECIAL_FONTS = new ConcurrentHashMap<>();
    private static final AtomicInteger SPECIAL_COUNTER = new AtomicInteger();

    private FontDescriptions() {
    }

    /**
     * Extracts the backing {@link ResourceLocation} from a description if possible.
     * Falls back to Minecraft's default font when the description refers to sprite/player
     * based glyph sources that are unsupported by Modern UI's layout engine.
     */
    public static ResourceLocation toResourceLocation(@Nullable FontDescription description) {
        if (description instanceof Resource resource) {
            return resource.id();
        } else if (description instanceof FontDescription.AtlasSprite || description instanceof FontDescription.PlayerSprite) {
            return SPECIAL_FONT_IDS.computeIfAbsent(description,
                    desc -> registerSpecialFont(desc, desc instanceof FontDescription.AtlasSprite ? "atlas" : "player"));
        }
        return Minecraft.DEFAULT_FONT;
    }

    private static ResourceLocation registerSpecialFont(FontDescription description, String type) {
        ResourceLocation id = ModernUIMod.location("inline/" + type + "/" + SPECIAL_COUNTER.incrementAndGet());
        SPECIAL_FONTS.put(id, description);
        return id;
    }

    /**
     * Checks whether the description refers to the given font identifier.
     */
    public static boolean matches(@Nullable FontDescription description, ResourceLocation fontId) {
        return description instanceof Resource resource && resource.id().equals(fontId);
    }

    public static boolean isSpecial(ResourceLocation fontId) {
        return SPECIAL_FONTS.containsKey(fontId);
    }

    @Nullable
    public static FontDescription getSpecialFont(ResourceLocation fontId) {
        return SPECIAL_FONTS.get(fontId);
    }
}
