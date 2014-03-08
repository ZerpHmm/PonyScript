package rafoudiablol.ponyscript.command;

import net.minecraft.command.ICommandSender;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.World;

public class CommandSenderEntity implements ICommandSender
{
	public final Entity target;
	
	public CommandSenderEntity(Entity entity) {
		target = entity;
	}
	
	public Entity getEntity() {
		return target;
	}
	
	@Override
	public String getCommandSenderName() {
		return target.getCommandSenderName();
	}

	@Override
	public IChatComponent func_145748_c_() {
		return new ChatComponentText(this.getCommandSenderName());
	}

	@Override
	public void addChatMessage(IChatComponent message)
	{
		if(target instanceof ICommandSender)
		{
			((ICommandSender)target).addChatMessage(message);
		}
	}
	
	@Override
	public boolean canCommandSenderUseCommand(int i, String cmd) {
		return i <= 2;
	}

	@Override
	public ChunkCoordinates getPlayerCoordinates() {
		return new ChunkCoordinates((int)(target.posX), (int)(target.boundingBox.minY), (int)(target.posZ));
	}

	@Override
	public World getEntityWorld() {
		return target.worldObj;
	}
}
