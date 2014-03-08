package rafoudiablol.ponyscript.lua;

import net.minecraft.block.Block;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

import org.luaj.vm2.LuaError;

import cpw.mods.fml.common.registry.GameData;
import cpw.mods.fml.common.registry.GameRegistry;

public class WrapperBlock
{
	protected final ICommandSender target;
	protected final World worldObj;
	public final int x;
	public final int y;
	public final int z;
	
	public WrapperBlock(ICommandSender sender, int x, int y, int z)
	{
		target = sender;
		worldObj = target.getEntityWorld();
		this.x = x;
		this.y = y;
		this.z = z;
		
		if(worldObj == null)
			throw new LuaError("the world can't be null");
	}
	
	public void set_id(String newid)
	{
		try
		{
			final Block newblock = CommandBase.getBlockByText(target, newid);
			worldObj.setBlock(this.x, this.y, this.z, newblock, 0, 3);
		}
		catch(Exception e)
		{
			// getBlockByText() throws NumberFormatException
			target.addChatMessage(new ChatComponentTranslation("ponyscript.exception.block_not_found", newid)
				.setChatStyle(new ChatStyle().setColor(EnumChatFormatting.RED)));
		}
	}
	
	public String get_id()
	{
		final Block block = worldObj.getBlock(this.x, this.y, this.z);
		return block.getUnlocalizedName();
	}
}
