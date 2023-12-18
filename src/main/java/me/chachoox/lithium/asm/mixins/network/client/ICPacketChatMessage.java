package me.chachoox.lithium.asm.mixins.network.client;

import net.minecraft.network.play.client.CPacketChatMessage;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(CPacketChatMessage.class)
public interface ICPacketChatMessage {
    @Accessor("message")
    void setMessage(String msg);
}
