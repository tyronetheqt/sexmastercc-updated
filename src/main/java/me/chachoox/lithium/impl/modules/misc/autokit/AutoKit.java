package me.chachoox.lithium.impl.modules.misc.autokit;

import me.chachoox.lithium.api.module.Category;
import me.chachoox.lithium.api.module.Module;
import me.chachoox.lithium.api.property.StringProperty;

public class AutoKit extends Module {

    protected final StringProperty kit =
            new StringProperty(
                    "pvp",
                    new String[]{"Kit", "kit"}
            );

    public AutoKit() {
        super("AutoKit", new String[]{"AutoKit", "kit", "kit"}, "Automatically rekit", Category.MISC);
        this.offerProperties(kit);
    }

    @Override
    public void onEnable() {
        mc.player.sendChatMessage("/kit " + kit.getValue());
        disable();
    }

}
