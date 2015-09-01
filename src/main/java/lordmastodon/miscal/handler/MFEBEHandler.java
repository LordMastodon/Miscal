package lordmastodon.miscal.handler;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.io.IOUtils;

import lordmastodon.miscal.constants.ModConstants;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class MFEBEHandler {
	
	@SubscribeEvent
	public void onPlayerJoinWorldEvent(EntityJoinWorldEvent event) throws MalformedURLException, IOException {
		if(event.entity instanceof EntityPlayer) {
			String s;

			InputStream in = new URL("https://drone.io/github.com/LordMastodon/Miscal/files/build/libs/version.txt").openStream();

			try {
				s = IOUtils.toString(in);
			} finally {
				IOUtils.closeQuietly(in);
			}
			
			String[] newVersionNumbersS = s.split(".");
			String[] internalVersionNumbersS = ModConstants.MOD_VERSION.split(".");
			
			int[] newVersionNumbers = new int[] {};
			int[] internalVersionNumbers = new int[] {};
			
			for(int i = 0; i < newVersionNumbersS.length; i++) {
				newVersionNumbers[i] = Integer.parseInt(newVersionNumbersS[i]);
			}
			
			for(int i = 0; i < internalVersionNumbersS.length; i++) {
				internalVersionNumbers[i] = Integer.parseInt(internalVersionNumbersS[i]);
			}
			
			if(event.world.isRemote) {
				boolean bugUpdateAvailable = newVersionNumbers[2] > internalVersionNumbers[2] ? true : false;
				boolean majorUpdateAvailable = newVersionNumbers[1] > internalVersionNumbers[1] ? true : false;
				boolean overhaulUpdateAvailable = newVersionNumbers[0] > internalVersionNumbers[0] ? true : false;
				
				if(bugUpdateAvailable || majorUpdateAvailable || overhaulUpdateAvailable) {
					event.entity.addChatMessage(new ChatComponentText("There is a new update available for Miscal, version " + EnumChatFormatting.BOLD + s + EnumChatFormatting.RESET + "."));
					
					if(bugUpdateAvailable && !majorUpdateAvailable && !overhaulUpdateAvailable) {
						event.entity.addChatMessage(new ChatComponentText("Please note that this is only a bug-fixing update and is not completely necessary."));
					} else if(!bugUpdateAvailable && majorUpdateAvailable && !overhaulUpdateAvailable) {
						event.entity.addChatMessage(new ChatComponentText("Please note that this is an update that adds new content, and it is recommended you update."));
					} else if(!bugUpdateAvailable && !majorUpdateAvailable && overhaulUpdateAvailable) {
						event.entity.addChatMessage(new ChatComponentText("This is an overhaul update and it is highly recommended you update as the new version is incompatible with old ones."));
					}
					
					event.entity.addChatMessage(new ChatComponentText("Please visit: https://drone.io/github.com/LordMastodon/Miscal/files to get the new update."));
				}
			}
		}
	}

}
