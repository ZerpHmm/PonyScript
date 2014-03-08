package rafoudiablol.ponyscript.command;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.script.ScriptException;

import org.luaj.vm2.LuaError;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import rafoudiablol.api.util.Stringize;
import rafoudiablol.ponyscript.ModCore;

public class CommandScript extends CommandBase
{
	@Override
	public String getCommandName()
	{
		return "exe";
	}

	/**
	 *	les commandes administrateurs ne sont pas permises dans un script (ban, op, etc)
	**/
	@Override
    public int getRequiredPermissionLevel()
    {
        return 2;
    }

	@Override
	public String getCommandUsage(ICommandSender var1) {
		return "ponyscript.command.usage";
	}

	@Override
	public void processCommand(ICommandSender target, String[] args)
		throws CommandException
	{
		if(args.length <= 0)
			throw new WrongUsageException("ponyscript.command.usage");
		
		final String script = Stringize.joinString(args);
		
		try
		{
			ModCore.lua.scriptRun(new StringReader(script), target);
		}
		catch(ScriptException e)
		{
			throw new CommandException(e.getMessage());
		}
		catch(LuaError e)
		{
			throw new CommandException(e.getMessage());
		}
	}
}
