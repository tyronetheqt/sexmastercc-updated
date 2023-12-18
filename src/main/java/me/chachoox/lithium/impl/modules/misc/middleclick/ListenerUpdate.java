package me.chachoox.lithium.impl.modules.misc.middleclick;

import me.chachoox.lithium.api.util.inventory.ItemUtil;
import me.chachoox.lithium.api.util.math.StopWatch;
import me.chachoox.lithium.api.util.network.PacketUtil;
import me.chachoox.lithium.impl.event.events.update.UpdateEvent;
import me.chachoox.lithium.impl.event.listener.ModuleListener;
import me.chachoox.lithium.impl.managers.Managers;
import me.chachoox.lithium.impl.managers.minecraft.RotationManager;
import net.minecraft.init.Items;
import net.minecraft.network.play.client.CPacketPlayerTryUseItem;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.MathHelper;
import org.lwjgl.input.Mouse;

public class ListenerUpdate extends ModuleListener<MiddleClick, UpdateEvent> {
    public ListenerUpdate(MiddleClick module) {
        super(module, UpdateEvent.class);
    }

    private final StopWatch timer = new StopWatch();

    @Override
    public void call(UpdateEvent event) {
        if (Mouse.isButtonDown(2)) {
            if (!module.clicked && mc.currentScreen == null) {
                if (module.friend.getValue()) {
                    module.onClick();
                }
                if (module.pearl.getValue()) {
                    if (module.onEntity()) {
                        return;
                    }
                    int pearlSlot = ItemUtil.getItemFromHotbar(Items.ENDER_PEARL);
                    if (pearlSlot != -1 || mc.player.getHeldItemOffhand().getItem() == Items.ENDER_PEARL) {
                        int oldSlot = mc.player.inventory.currentItem;
                        if (!(mc.player.getHeldItemOffhand().getItem() == Items.ENDER_PEARL)) {
                            ItemUtil.switchTo(pearlSlot);
                        }
                        mc.playerController.processRightClick(mc.player, mc.world, mc.player.getHeldItemOffhand().getItem() == Items.ENDER_PEARL ? EnumHand.OFF_HAND : EnumHand.MAIN_HAND);
                        if (!(mc.player.getHeldItemOffhand().getItem() == Items.ENDER_PEARL)) {
                            ItemUtil.switchTo(oldSlot);
                        }
                    }
                }
            }

            if (module.xp.getValue()) {
                int expSlot = ItemUtil.getItemFromHotbar(Items.EXPERIENCE_BOTTLE);
                if (expSlot != -1 || mc.player.getHeldItemOffhand().getItem() == Items.ENDER_PEARL && timer.passed(module.delay.getValue() * 10)) {
                    if (module.lookdown.getValue()){
                        float yaw = MathHelper.wrapDegrees(Managers.ROTATION.getYaw());
                        float pitch = module.pitch.getValue();
                        Managers.ROTATION.setRotations(yaw, pitch);
                    }
                    int oldSlot = mc.player.inventory.currentItem;
                    ItemUtil.switchTo(expSlot);
                    PacketUtil.send(new CPacketPlayerTryUseItem(EnumHand.MAIN_HAND));
                    ItemUtil.switchTo(oldSlot);
                    timer.reset();
                }
            }

            module.clicked = true;
        } else {
            module.clicked = false;
        }
    }
}
