package me.chachoox.lithium.api.event.bus.api;

public interface ICancellable {

    void setCancelled(boolean cancelled);

    boolean isCancelled();

}
