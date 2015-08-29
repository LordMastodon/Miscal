package lordmastodon.miscal.handler;

import lordmastodon.miscal.constants.ModConstants;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class MinecraftForgeEventBusEventHandler {
	
	@SubscribeEvent
	public void onPlayerJoinWorldEvent(EntityJoinWorldEvent event) {
		if(event.entity instanceof EntityPlayer) {
			//event.entity.addChatMessage(new ChatTextComponent);
			System.out.println("Player joined world");
		}
	}

}
