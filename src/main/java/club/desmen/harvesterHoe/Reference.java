package club.desmen.harvesterHoe;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;


public class Reference {

    private Material material = Material.DIAMOND_HOE;
    private String name = "§c§lHarvester Hoe";
    private List<String> lore = new ArrayList<>();
    private int price = 20;

    private String permission = "harvesterhoe.use";

    public Material getMaterial()
    {
        return material;
    }

    public void setMaterial(Material material)
    {
        this.material = material;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public List<String> getLore()
    {
        return lore;
    }

    public void setLore(List<String> lore) {
        ArrayList<String> loreList = new ArrayList<String>();
        {
            for (String lorelist : lore) {
                loreList.add(lorelist.replaceAll("(&([a-fk-o0-9]))", "\u00A7$2"));
            }
        }
        this.lore = loreList;
    }

    public String getPermission()
    {
        return permission;
    }

    public void setPermission(String permission)
    {
        this.permission = permission;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public ItemStack getItemStack()
    {
        ItemStack itemStack = new ItemStack(Material.DIAMOND_HOE);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(name);
        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
}
