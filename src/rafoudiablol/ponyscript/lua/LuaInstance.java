package rafoudiablol.ponyscript.lua;

import java.io.InputStreamReader;
import java.io.Reader;

import javax.script.ScriptException;

import net.minecraft.command.ICommandSender;
import net.minecraft.command.ServerCommandManager;

import org.luaj.vm2.Globals;
import org.luaj.vm2.LuaError;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.jse.CoerceJavaToLua;
import org.luaj.vm2.lib.jse.JsePlatform;

import rafoudiablol.ponyscript.command.CommandScript;
import cpw.mods.fml.common.event.FMLServerStartingEvent;

public class LuaInstance
{
	private static final String API_LUA_FILE = "/assets/ponyscript/ponyscript.lua";
	public static final String PONY_TARGET = "__TARGET__";
	public static final String PONY_SERVER = "__SERVER__";
	public static final String PONY_WORLD = "__WORLD__";
	
	private Globals luaGlobals;
	
	public LuaInstance()
	{
		luaGlobals = JsePlatform.standardGlobals();
	}
	
	public void init()
	{
		final LuaValue ponyscript = luaGlobals.load(
				new InputStreamReader(LuaInstance.class.getResourceAsStream(API_LUA_FILE)), "ponyscript.lua");
		
		System.out.println(ponyscript.call());
	}
	
	public void onNewServerStarted(FMLServerStartingEvent event)
	{
		final ServerCommandManager commandManager = (ServerCommandManager)
				event.getServer().getCommandManager();
		
		commandManager.registerCommand(new CommandScript());
		luaGlobals.set(PONY_SERVER, CoerceJavaToLua.coerce(new WrapperServer(event.getServer())));
	}
	
	public void scriptRun(Reader source, ICommandSender target)
		throws ScriptException, LuaError
	{
		final LuaValue script = luaGlobals.load(source, target.getCommandSenderName());
		luaGlobals.set(PONY_TARGET, CoerceJavaToLua.coerce(new WrapperTarget(target)));
		luaGlobals.set(PONY_WORLD, CoerceJavaToLua.coerce(new WrapperWorld(target)));
		
		if(target.getPlayerCoordinates() != null)
		{
			luaGlobals.set("x", target.getPlayerCoordinates().posX);
			luaGlobals.set("y", target.getPlayerCoordinates().posY);
			luaGlobals.set("z", target.getPlayerCoordinates().posZ);
		}
		script.call();
		
		/*
		final Bindings bindings = new SimpleBindings();
		bindings.put("blockat", new FuncBlockAt(target));
		
		if(target.getPlayerCoordinates() != null)
		{
			bindings.put("x", target.getPlayerCoordinates().posX);
			bindings.put("y", target.getPlayerCoordinates().posY);
			bindings.put("z", target.getPlayerCoordinates().posZ);
		}
		
		engineClient.setBindings(bindings, ScriptContext.ENGINE_SCOPE);
		engineClient.eval(script, bindings);*/
	}
}
