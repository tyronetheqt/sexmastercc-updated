package me.chachoox.lithium.impl.modules.player.cornerclip;

import me.chachoox.lithium.impl.event.events.movement.MotionUpdateEvent;
import me.chachoox.lithium.impl.event.listener.ModuleListener;

public class ListenerMotion extends ModuleListener<CornerClip, MotionUpdateEvent> {
    public ListenerMotion(CornerClip module) {
        super(module, MotionUpdateEvent.class);
    }

    @Override
    public void call(MotionUpdateEvent event) {

    }
}
