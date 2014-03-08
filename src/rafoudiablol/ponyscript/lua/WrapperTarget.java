package rafoudiablol.ponyscript.lua;

import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;

public class WrapperTarget
{
	protected final ICommandSender target;
	public final int x;
	public final int y;
	public final int z;
	
	public WrapperTarget(ICommandSender sender)
	{
		target = sender;
		
		if(target.getPlayerCoordinates() != null)
		{
			x = target.getPlayerCoordinates().posX;
			y = target.getPlayerCoordinates().posY;
			z = target.getPlayerCoordinates().posZ;
		}
		else
		{
			x = 0;
			y = 0;
			z = 0;
		}
	}

	public String getName()
	{
		return target.getCommandSenderName();
	}
	
	public void chat(String msg)
	{
		target.addChatMessage(new ChatComponentText(msg));
	}
}
