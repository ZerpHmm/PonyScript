package rafoudiablol.ponyscript;

import net.minecraft.block.Block;
import net.minecraft.command.ServerCommandManager;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;

import org.apache.logging.log4j.Logger;

import rafoudiablol.api.BaseForgeMod;
import rafoudiablol.api.BBApi;
import rafoudiablol.api.network.IPacketManager;
import rafoudiablol.ponyscript.command.CommandScript;
import rafoudiablol.ponyscript.lua.LuaInstance;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(
	modid = ModCore.MODID,
	name = ModCore.NAME,
	version = ModCore.VERSION,
	dependencies = BBApi.DEPENDENCY
)

public class ModCore extends BaseForgeMod
{
	public static final LuaInstance lua = new LuaInstance();
	public static final String VERSION = "1.0";
	public static final String NAME = "PonyScript";
	public static final String MODID = "ponyscript";
	public static final String CHANNEL = MODID;
	public static final String ASSETS = MODID;
	
	@Instance(value = MODID)
	public static ModCore instance;
	
	public static Logger log() {
		return instance.getLogger();
	}
	
	public static IPacketManager network() {
		return instance.getPacketManager();
	}

	/**
	 *	enregistre la commande sur le serveur
	 *	@param event
	**/
	@EventHandler
	public void onEvent(FMLServerStartingEvent event)
	{		
		lua.onNewServerStarted(event);
	}
	
	@EventHandler @Override
	public void pre(FMLPreInitializationEvent event)
	{
		super.pre(event);
		
		Object target = new ModEventHandler();
		MinecraftForge.EVENT_BUS.register(target);
		FMLCommonHandler.instance().bus().register(target);
		
		lua.init();
	}
	
	@EventHandler @Override
	public void post(FMLPostInitializationEvent event)
	{
		super.post(event);
	}
	
	@EventHandler @Override
	public void init(FMLInitializationEvent event) {
		super.init(event);
	}
}