package lordmastodon.miscal.handler;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import org.apache.commons.io.IOUtils;

import lordmastodon.miscal.Miscal;
import lordmastodon.miscal.constants.ModConstants;
import lordmastodon.miscal.util.Version;
import lordmastodon.miscal.util.VersionNumber.EnumVersionPriority;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class MFEBEHandler {
	
	@SubscribeEvent
	public void onPlayerJoinWorldEvent(EntityJoinWorldEvent event) {
		if (!event.world.isRemote) {
			try {
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
					
					Version newVersion = new Version();
					newVersion.setVersionNumbers(new int[] { Integer.valueOf(newVersionNumbersS[0]), Integer.valueOf(newVersionNumbersS[1]), Integer.valueOf(newVersionNumbersS[2]) });
					
					Version internalVersion = new Version();
					internalVersion.setVersionNumbers(new int[] { Integer.valueOf(internalVersionNumbersS[0]), Integer.valueOf(internalVersionNumbersS[0]), Integer.valueOf(internalVersionNumbersS[0]) } );
					
					if(event.world.isRemote) {
						newVersion.getVersionNumber(2).setAvailable(newVersion.getVersionNumber(2).versionNumber() > internalVersion.getVersionNumber(2).versionNumber());
						newVersion.getVersionNumber(1).setAvailable(newVersion.getVersionNumber(1).versionNumber() > internalVersion.getVersionNumber(1).versionNumber());
						newVersion.getVersionNumber(0).setAvailable(newVersion.getVersionNumber(0).versionNumber() > internalVersion.getVersionNumber(0).versionNumber());
						
						if(newVersion.getVersionNumber(0).available() || newVersion.versionNumbers().get(1).available() || newVersion.versionNumbers().get(2).available()) {
							event.entity.addChatMessage(new ChatComponentText("There is a new update available for Miscal, version " + EnumChatFormatting.BOLD + s + EnumChatFormatting.RESET + "."));
							
							if(newVersion.hasPriority(EnumVersionPriority.HIGH)) {
								event.entity.addChatMessage(new ChatComponentText("This is an overhaul update and it is highly recommended you update as the new version is incompatible with old ones."));
							} else if(newVersion.hasPriority(EnumVersionPriority.MEDIUM)) {
								event.entity.addChatMessage(new ChatComponentText("Please note that this is an update that adds new content, and it is recommended you update."));
							} else {
								event.entity.addChatMessage(new ChatComponentText("This is an overhaul update and it is highly recommended you update as the new version is incompatible with old ones."));
							}
							
							event.entity.addChatMessage(new ChatComponentText("Please visit: https://drone.io/github.com/LordMastodon/Miscal/files to get the new update."));
						}
					}
				}
			} catch (IOException e) {
				Miscal.modLogger.warn("No version file was found, this may not be the most up-to-date version.");
			}
		}
	}
}
