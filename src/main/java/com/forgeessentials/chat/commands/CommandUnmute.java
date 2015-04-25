package com.forgeessentials.chat.commands;

import com.forgeessentials.api.UserIdent;
import com.forgeessentials.core.commands.ForgeEssentialsCommandBase;
import com.forgeessentials.core.misc.TranslatedCommandException;
import com.forgeessentials.core.misc.Translator;
import com.forgeessentials.util.OutputHandler;

import cpw.mods.fml.common.FMLCommonHandler;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.permissions.PermissionsManager.RegisteredPermValue;

import java.util.List;

public class CommandUnmute extends ForgeEssentialsCommandBase {

    @Override
    public String getCommandName()
    {
        return "unmute";
    }

    @Override
    public List<String> addTabCompletionOptions(ICommandSender sender, String[] args)
    {
        if (args.length == 1)
        {
            return getListOfStringsMatchingLastWord(args, FMLCommonHandler.instance().getMinecraftServerInstance().getAllUsernames());
        }
        else
        {
            return null;
        }
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args)
    {
        if (args.length == 1)
        {
            EntityPlayer receiver = UserIdent.getPlayerByMatchOrUsername(sender, args[0]);
            if (receiver == null)
                throw new TranslatedCommandException("Player %s does not exist, or is not online.", args[0]);
            NBTTagCompound tag = receiver.getEntityData().getCompoundTag(EntityPlayer.PERSISTED_NBT_TAG);
            tag.setBoolean("mute", false);
            receiver.getEntityData().setTag(EntityPlayer.PERSISTED_NBT_TAG, tag);
            OutputHandler.chatError(sender, Translator.format("You unmuted %s.", args[0]));
            OutputHandler.chatError(receiver, Translator.format("You were unmuted by %s.", sender.getCommandSenderName()));
        }
    }

    @Override
    public boolean canConsoleUseCommand()
    {
        return false;
    }

    @Override
    public String getPermissionNode()
    {
        return "fe.chat." + getCommandName();
    }

    @Override
    public String getCommandUsage(ICommandSender sender)
    {

        return "/unmute <player> Unmutes a player.";
    }

    @Override
    public RegisteredPermValue getDefaultPermission()
    {

        return RegisteredPermValue.OP;
    }
}
