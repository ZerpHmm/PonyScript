package rafoudiablol.ponyscript.lua;

import net.minecraft.server.MinecraftServer;

import org.luaj.vm2.LuaTable;
import org.luaj.vm2.LuaUserdata;
import org.luaj.vm2.LuaValue;

public class WrapperServer
{
	protected final MinecraftServer serverObj;
	
	public WrapperServer(MinecraftServer server)
	{
		serverObj = server;
	}
}
