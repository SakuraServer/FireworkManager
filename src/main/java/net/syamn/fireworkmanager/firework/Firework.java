/**
 * FireworkManager - Package: net.syamn.fireworkmanager.firework
 * Created: 2012/12/21 18:55:16
 */
package net.syamn.fireworkmanager.firework;


import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;

/**
 * Firework (Firework.java)
 * @author syam(syamn)
 */
public class Firework{
    private ItemStack item;
    private FireworkMeta meta; // TODO waiting for add FireworkMeta
    
    /**
     * Constructor
     * @param item ItemStack: FIREWORK
     */
    public Firework(final ItemStack item){
        setItem(item);
    }
    
    /**
     * Set firework itemstack
     * @param item ItemStack: FIREWORK
     */
    public void setItem(final ItemStack item){
        if (item.getType() != Material.FIREWORK){
            throw new IllegalArgumentException("Parameter item must be " + Material.FIREWORK.name() +".");
        }
        if (!(item.getItemMeta() instanceof FireworkMeta)){
            throw new IllegalArgumentException("Could not cast to FireworkMeta!");
        }
        
        this.item = item;
        this.meta = (FireworkMeta) item.getItemMeta();
    }
    
    /**
     * Get firework itemstack
     * @return ItemStack
     */
    public ItemStack getItem(){
        this.item.setItemMeta(meta);
        return this.item;
    }
    
    public void setFlightDuration(final byte flight){
        //TODO
    }
}
