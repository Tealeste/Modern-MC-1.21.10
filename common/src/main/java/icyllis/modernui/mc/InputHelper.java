package icyllis.modernui.mc;

import com.mojang.blaze3d.platform.InputConstants;
import com.mojang.blaze3d.platform.Window;
import net.minecraft.client.Minecraft;

/**
 * Replacement helpers for the legacy {@code Screen.has*Down()} queries that were
 * removed in Minecraft 1.21.10.
 */
public final class InputHelper {

    private InputHelper() {
    }

    public static boolean isControlDown() {
        return isKeyDown(InputConstants.KEY_LCONTROL) || isKeyDown(InputConstants.KEY_RCONTROL);
    }

    public static boolean isShiftDown() {
        return isKeyDown(InputConstants.KEY_LSHIFT) || isKeyDown(InputConstants.KEY_RSHIFT);
    }

    public static boolean isAltDown() {
        return isKeyDown(InputConstants.KEY_LALT) || isKeyDown(InputConstants.KEY_RALT);
    }

    private static boolean isKeyDown(int key) {
        Minecraft minecraft = Minecraft.getInstance();
        if (minecraft == null) {
            return false;
        }
        Window window = minecraft.getWindow();
        return window != null && InputConstants.isKeyDown(window, key);
    }
}
