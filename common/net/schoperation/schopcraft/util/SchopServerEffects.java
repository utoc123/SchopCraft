package net.schoperation.schopcraft.util;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.fml.common.FMLCommonHandler;

/*
 * Responsible for effecting players with status effects, if the methods trying to do them are client-side.
 * This does them server-side.
 */
public class SchopServerEffects {

	
	public static void effectPlayer(String uuid, String effect, int duration, int amplifier, boolean isAmbient, boolean showParticles) {
		
		// basic variables
		MinecraftServer server = FMLCommonHandler.instance().getMinecraftServerInstance();
		int playerCount = server.getCurrentPlayerCount();
		String[] playerList = server.getOnlinePlayerNames();
		
		// iterate through each player
		for (int num = 0; num < playerCount; num++) {
			
			// instance of player
			EntityPlayerMP player = server.getPlayerList().getPlayerByUsername(playerList[num]);
			
			// is this the right player? check uuids
			if (player.getCachedUniqueIdString().equals(uuid)) {
				
				// decipher potion effect string and effect the player accordingly
				// instant damage
				if (effect.equals("poison")) {
					
					player.addPotionEffect(new PotionEffect(MobEffects.POISON, duration, amplifier, isAmbient, showParticles));
				}
			}
		}		
	}
}