package rafoudiablol.ponyscript;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.world.WorldEvent;
import rafoudiablol.api.BBClient;
import rafoudiablol.api.BBServer;
import rafoudiablol.ponyscript.command.CommandSenderEntity;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.Phase;
import cpw.mods.fml.common.gameevent.TickEvent.PlayerTickEvent;
import cpw.mods.fml.relauncher.Side;

public class ModEventHandler
{
	@SubscribeEvent
	public void onEvent(LivingDeathEvent event)
	{
		if(event.entityLiving.worldObj.isRemote)
		{
			return;
		}
		
		final NBTTagCompound tag = event.entityLiving.getEntityData();
		
		if(tag.hasKey("OnKilled"))
		{
			String str = tag.getString("OnKilled");
			
			if(!str.isEmpty())
			{
				BBServer.getCommandManager().executeCommand(new CommandSenderEntity(event.entityLiving), str);
			}
		}
	}
	
	/* test
	@SubscribeEvent
	public void onEvent(PlayerTickEvent event)
	{
		if(event.side == Side.SERVER && event.phase == Phase.END && event.player instanceof EntityPlayerMP) // last is implicit
		{
			EntityPlayerMP playermp = (EntityPlayerMP)event.player;
			
			if(playermp.posY < 100) {
				playermp.setPositionAndUpdate(playermp.posX, 100, playermp.posZ);
				playermp.motionY = 0;
			}
		}
		else if(event.side == Side.CLIENT && event.phase == Phase.START)
		{
			EntityPlayer player = BBClient.getMinecraft().thePlayer;

			if(player.boundingBox.minY <= 100) {
				player.setPosition(event.player.posX, 100, event.player.posZ);
				player.motionY = 0;
			}
		}
	}
	*/
}
