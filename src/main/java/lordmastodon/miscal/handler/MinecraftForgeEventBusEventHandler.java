package lordmastodon.miscal.handler;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.io.IOUtils;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class MinecraftForgeEventBusEventHandler {
	
	@SubscribeEvent
	public void onPlayerJoinWorldEvent(EntityJoinWorldEvent event) throws MalformedURLException, IOException {
		if(event.entity instanceof EntityPlayer) {
			String s;

			InputStream in = new URL("http://jakarta.apache.org").openStream();

			try {
				s = IOUtils.toString(in);
			} finally {
				IOUtils.closeQuietly(in);
			}
		}
	}

}
