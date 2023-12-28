package me.chachoox.lithium.impl.modules.misc.armorwarning;

import me.chachoox.lithium.api.module.Category;
import me.chachoox.lithium.api.module.Module;

public class ArmorWarning extends Module {

    public ArmorWarning() {
        super("ArmorWarning", new String[]{"ArmorWarning", "armorwarning"}, "Warns you when ur armor is low.", Category.MISC);
    }
}
