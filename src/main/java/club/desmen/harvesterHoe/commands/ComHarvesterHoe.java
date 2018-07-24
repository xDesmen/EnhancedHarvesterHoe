package club.desmen.harvesterHoe.commands;

import club.desmen.harvesterHoe.Core;
import club.desmen.harvesterHoe.Reference;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;



public class ComHarvesterHoe implements CommandExecutor
{

    private Reference reference;

    public ComHarvesterHoe(Core core)
    {
        this.reference = core.getReference();
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings)
    {
        if(command.getName().equalsIgnoreCase("HarvesterHoe"))
        {
            if(commandSender.hasPermission(reference.getPermission()))
            {
                if(strings.length == 0)
                {
                    commandSender.sendMessage(Core.chatColor("&c/harvesterhoe <player>"));
                    return true;
                }
                Player t = Bukkit.getPlayer(strings[0]);
                if(t == null)
                {
                    commandSender.sendMessage(Core.chatColor("&cPlayer isn't online"));
                    return true;
                }
                t.getInventory().addItem(reference.getItemStack());
                commandSender.sendMessage(Core.chatColor("&aGave " + t.getName() + " a harvester hoe"));
            }
        }
        return false;
    }
}
