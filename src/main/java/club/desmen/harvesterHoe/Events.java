package club.desmen.harvesterHoe;

import com.massivecraft.factions.FPlayer;
import com.massivecraft.factions.FPlayers;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;



public class Events implements Listener
{

    private Reference reference;
    private static Core plugin;

    public Events(Core core)
    {
        plugin = plugin;
        this.reference = core.getReference();
    }


    @EventHandler
    public void onInteract(BlockBreakEvent e)
    {
        Block block = e.getBlock();
        if (block.getType() == Material.SUGAR_CANE_BLOCK) {
            Player p = e.getPlayer();
            if (p.getItemInHand() != null && p.getItemInHand().getType() != Material.AIR) {
                ItemStack itemStack = p.getItemInHand();
                if (itemStack.getType() == reference.getMaterial()) {
                    if (itemStack.getItemMeta().getDisplayName() != null) {
                        if (itemStack.getItemMeta().getDisplayName().equals(reference.getName())) {
                            Plugin factions = Bukkit.getServer().getPluginManager().getPlugin("Factions");
                            if (factions != null && factions.isEnabled()) {
                                FPlayer fPlayer = FPlayers.getInstance().getByPlayer(e.getPlayer());
                                if (!fPlayer.isInOwnTerritory()) return;
                            }
                                e.setCancelled(true);
                                List<Block> blocks = getBlocksOfSC(block);
                                for (ListIterator iterator = blocks.listIterator(blocks.size()); iterator.hasPrevious(); ) {
                                    final Block listElement = (Block) iterator.previous();
                                    listElement.setType(Material.AIR);
                                }
                                int price = reference.getPrice() * blocks.size();
                                Core.getEconomy().depositPlayer(p, price);
                            }
                        }
                    }
                }
            }
        }


    private List<Block> getBlocksOfSC(Block block)
    {
        List<Block> blocks = new ArrayList<>();
        blocks.add(block);
        Location checkLoc = block.getLocation().clone().add(0, 1, 0);
        while (checkLoc.getBlock().getType() == Material.SUGAR_CANE_BLOCK)
        {
            blocks.add(checkLoc.getBlock());
            checkLoc.add(0, 1, 0);
        }
        return blocks;
    }
}
