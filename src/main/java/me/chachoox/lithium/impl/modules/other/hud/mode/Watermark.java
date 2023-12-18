package me.chachoox.lithium.impl.modules.other.hud.mode;

import me.chachoox.lithium.Lithium;
import me.chachoox.lithium.api.util.text.TextColor;

public enum Watermark {
    NONE(""),
    SQUIDWARDHACK("SquidwardHack " + TextColor.GRAY + Lithium.VERSION),
    GROGHACK("GrogHack " + TextColor.GRAY + "v1" + Lithium.VERSION.replace("v0", "")), //wtf is this
    CUSTOM("");

    private final String watermark;

    Watermark(String watermark) {
        this.watermark = watermark;
    }

    public String getWatermark() {
        return watermark;
    }
}
