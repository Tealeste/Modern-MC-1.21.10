package icyllis.modernui.mc.text;

import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.FontDescription;
import net.minecraft.network.chat.FontDescription.Resource;
import net.minecraft.resources.ResourceLocation;

import javax.annotation.Nullable;

/**
 * Helper for handling the new {@link FontDescription} based font selectors in 1.21.10.
 */
public final class FontDescriptions {

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
        }
        return Minecraft.DEFAULT_FONT;
    }

    /**
     * Checks whether the description refers to the given font identifier.
     */
    public static boolean matches(@Nullable FontDescription description, ResourceLocation fontId) {
        return description instanceof Resource resource && resource.id().equals(fontId);
    }
}
