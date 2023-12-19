package me.chachoox.lithium.impl.modules.misc.suicide;

import me.chachoox.lithium.api.module.Category;
import me.chachoox.lithium.api.module.Module;

public class Suicide extends Module {



    public Suicide() {
        super("Suicide", new String[]{"Suicide", "suicide", "die"}, "emo mode", Category.MISC);
        this.offerProperties();
    }

    @Override
    public void onEnable() {
        mc.player.sendChatMessage("/kill");
        disable();
    }

}
