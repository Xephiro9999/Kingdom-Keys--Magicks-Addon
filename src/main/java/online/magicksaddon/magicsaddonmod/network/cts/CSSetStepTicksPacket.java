package online.magicksaddon.magicsaddonmod.network.cts;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.network.NetworkEvent;
import online.magicksaddon.magicsaddonmod.capabilities.IGlobalCapabilitiesX;
import online.magicksaddon.magicsaddonmod.capabilities.ModCapabilitiesX;
import online.magicksaddon.magicsaddonmod.network.PacketHandlerX;

import java.util.function.Supplier;

public class CSSetStepTicksPacket {

    private int ticks;
    private byte type;


    public CSSetStepTicksPacket(){}

    public CSSetStepTicksPacket(int ticks, byte type){
        this.ticks = ticks;
        this.type = type;
    }

    public void encode(FriendlyByteBuf buffer) {
        buffer.writeInt(this.ticks);
        buffer.writeByte(this.type);
    }

    public static CSSetStepTicksPacket decode(FriendlyByteBuf buffer) {
        CSSetStepTicksPacket msg = new CSSetStepTicksPacket();
        msg.ticks = buffer.readInt();
        msg.type = buffer.readByte();
        return msg;
    }

    public static void handle(final CSSetStepTicksPacket message, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            Player player = ctx.get().getSender();
            IGlobalCapabilitiesX globalData = ModCapabilitiesX.getGlobal(player);
            globalData.setStepTicks(message.ticks,message.type);

            PacketHandlerX.syncGlobalToAllAround(player, globalData);
        });
        ctx.get().setPacketHandled(true);
    }
}
