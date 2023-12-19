package me.chachoox.lithium.impl.modules.player.cornerclip;

import me.chachoox.lithium.api.module.Category;
import me.chachoox.lithium.api.module.Module;
import me.chachoox.lithium.api.property.NumberProperty;
import me.chachoox.lithium.api.property.Property;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraft.util.math.MathHelper;

public class CornerClip extends Module {

    int disabletime = 0;

    protected final NumberProperty<Float> delay =
            new NumberProperty<>(5.0f, 1.0f, 100.0f, 0.1f,
                    new String[]{"Delay", "delay", "d"},
                    "Delay duh"
            );


    protected final Property<Boolean> disable =
            new Property<>(true,
                    new String[]{"Disable", "disable", "dis"},
                    "Disables"
            );

    protected final NumberProperty<Float> updates =
            new NumberProperty<>(10.0f, 1.0f, 30.0f, 0.1f,
                    new String[]{"Updates", "update", "up"},
                    "idk lol"
            );

    protected final Property<Boolean> extend =
            new Property<>(true,
                    new String[]{"Extend", "extend", "ex"},
                    "Amazing technology"
            );

    public CornerClip() {
        super("CornerClip", new String[]{"CornerClip", "clip", "clip"}, "clip bruhhhh", Category.PLAYER);
        this.offerProperties(delay, disable, updates, extend);
        this.offerListeners(new ListenerMotion(this));
    }

    @Override
    public void onEnable() {
        if (!mc.player.onGround) {
            disable();
        }
            if (mc.world.getCollisionBoxes(mc.player, mc.player.getEntityBoundingBox().grow(0.02, 0, 0.01)).size() < 2) {
                mc.player.setPosition(roundToClosest(mc.player.posX, Math.floor(mc.player.posX) + 0.301, Math.floor(mc.player.posX) + 0.699), mc.player.posY, roundToClosest(mc.player.posZ, Math.floor(mc.player.posZ) + 0.301, Math.floor(mc.player.posZ) + 0.699));
            } else if (mc.player.ticksExisted % delay.getValue() == 0) {
                mc.player.setPosition(mc.player.posX + MathHelper.clamp(roundToClosest(mc.player.posX, Math.floor(mc.player.posX) + 0.241, Math.floor(mc.player.posX) + 0.759) - mc.player.posX, -0.03, 0.03), mc.player.posY, mc.player.posZ + MathHelper.clamp(roundToClosest(mc.player.posZ, Math.floor(mc.player.posZ) + 0.241, Math.floor(mc.player.posZ) + 0.759) - mc.player.posZ, -0.03, 0.03));
                mc.player.connection.sendPacket(new CPacketPlayer.Position(mc.player.posX, mc.player.posY, mc.player.posZ, true));
                mc.player.connection.sendPacket(new CPacketPlayer.Position(roundToClosest(mc.player.posX, Math.floor(mc.player.posX) + 0.23, Math.floor(mc.player.posX) + 0.77), mc.player.posY, roundToClosest(mc.player.posZ, Math.floor(mc.player.posZ) + 0.23, Math.floor(mc.player.posZ) + 0.77), true));
                mc.player.setPosition(mc.player.posX + MathHelper.clamp(roundToClosest(mc.player.posX, Math.floor(mc.player.posX) + 0.241, Math.floor(mc.player.posX) + 0.759) - mc.player.posX, -0.03, 0.03), mc.player.posY, mc.player.posZ + MathHelper.clamp(roundToClosest(mc.player.posZ, Math.floor(mc.player.posZ) + 0.241, Math.floor(mc.player.posZ) + 0.759) - mc.player.posZ, -0.03, 0.03));
            }
            if (disable.getValue()) {
                if (disabletime >= updates.getValue()) {
                    disable();
                }
                disabletime++;
            }
        }
    public static double roundToClosest(double num, double low, double high) {
        double d2 = high - num;
        double d1 = num - low;
        if (d2 > d1) {
            return low;
        }
        return high;
    }

    @Override
    public void onDisable()
    {
        disabletime = 0;
    }

}
