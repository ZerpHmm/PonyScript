package rafoudiablol.ponyscript.lua;

import org.luaj.vm2.LuaError;

import net.minecraft.command.ICommandSender;
import net.minecraft.world.World;

public class WrapperWorld
{
	protected final ICommandSender target;
	protected final World worldObj;
	
	public WrapperWorld(ICommandSender sender)
	{
		target = sender;
		worldObj = sender.getEntityWorld();
		
		if(worldObj == null)
			throw new LuaError("world can't be null");
	}
	
	public WrapperBlock getBlockAt(int x, int y, int z)
	{
		return new WrapperBlock(target, x, y, z);
	}
}
