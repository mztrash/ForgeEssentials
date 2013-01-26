package com.ForgeEssentials.core.commands;

import com.ForgeEssentials.core.moduleLauncher.ModuleLauncher;
import com.ForgeEssentials.util.FEChatFormatCodes;

import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;

public class CommandFEReload extends ForgeEssentialsCommandBase
{

	@Override
	public String getCommandName()
	{
		return "fereload";
	}

	@Override
	public void processCommandPlayer(EntityPlayer sender, String[] args)
	{
		sender.sendChatToPlayer("Reloading ForgeEssentials configs. May not work for all settings!");
		sender.sendChatToPlayer(FEChatFormatCodes.RED + "This is experimental!");
		ModuleLauncher.instance.reloadConfigs(sender);
		sender.sendChatToPlayer("Done!");
	}

	@Override
	public void processCommandConsole(ICommandSender sender, String[] args)
	{
		sender.sendChatToPlayer("Reloading ForgeEssentials configs. May not work for all settings!");
		sender.sendChatToPlayer(FEChatFormatCodes.RED + "This is experimental!");
		ModuleLauncher.instance.reloadConfigs(sender);
		sender.sendChatToPlayer("Done!");
	}

	@Override
	public boolean canConsoleUseCommand()
	{
		return true;
	}

	@Override
	public String getCommandPerm()
	{
		return "ForgeEssentials.CoreCommands." + getCommandName();
	}

}
