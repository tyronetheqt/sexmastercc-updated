package me.chachoox.lithium.impl.modules.misc.middleclick;

import me.chachoox.lithium.api.module.Category;
import me.chachoox.lithium.api.module.Module;
import me.chachoox.lithium.api.property.NumberProperty;
import me.chachoox.lithium.api.property.Property;
import me.chachoox.lithium.impl.managers.Managers;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.RayTraceResult;

public class MiddleClick extends Module {

    protected final Property<Boolean> friend =
            new Property<>(
                    true,
                    new String[]{"Friend", "MiddleClickFriend", "mcf", "f"},
                    "Adds the player you middle click as a friend."
            );

    protected final Property<Boolean> pearl =
            new Property<>(
                    false,
                    new String[]{"Pearl", "pearlington", "pear"},
                    "Throws a pearl whenever we middle click."
            );

    protected final Property<Boolean> xp =
            new Property<>(
                    false,
                    new String[]{"Xp", "xp", "xp"},
                    "Throws xp whenever we middle click."
            );

    public final NumberProperty<Integer> delay =
            new NumberProperty<>(
                    1, 0, 10,
                    new String[]{"Delay", "ThrowDelay", "ExpDelay"},
                    "Delay between throwing exp bottles."
            );

    protected final Property<Boolean> wasteStop =
            new Property<>(
                    false,
                    new String[]{"WasteStop", "Waste", "wst"},
                    "Dont waste xp."
            );

    protected final Property<Boolean> lookdown =
            new Property<>(
                    false,
                    new String[]{"Down", "down", "down"},
                    "Throws xp down when we middle click."
            );

    protected final NumberProperty<Float> pitch =
            new NumberProperty<>(
                    90.0f, -90.0f, 90.0f, 1.0f,
                    new String[]{"Pitch", "Pitch", "Pitch"},
                    "What pitch to look at when xp'ing."
            );

    protected boolean clicked = false;

    public MiddleClick() {
        super("MiddleClick", new String[]{"MiddleClick", "mcf", "mcp"}, "Allows you to preform actions using the scroll wheel.", Category.MISC);
        this.offerProperties(friend, pearl, xp, delay, wasteStop, lookdown, pitch);
        this.offerListeners(new ListenerUpdate(this));
    }

    protected boolean onEntity() {
        RayTraceResult result;
        return (result = mc.objectMouseOver) != null && result.typeOfHit == RayTraceResult.Type.ENTITY && result.entityHit instanceof EntityPlayer;
    }

    protected void onClick() {
        Entity entity;
        RayTraceResult result = mc.objectMouseOver;
        if (result != null && result.typeOfHit == RayTraceResult.Type.ENTITY && (entity = result.entityHit) instanceof EntityPlayer) {
            if (Managers.FRIEND.isFriend(entity.getName())) {
                Managers.FRIEND.removeFriend(entity.getName());
            } else {
                Managers.FRIEND.addFriend(entity.getName());
            }
        }
        clicked = true;
    }

}