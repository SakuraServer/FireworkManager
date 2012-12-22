/**
 * FireworkManager - Package: net.syamn.fireworkmanager.firework
 * Created: 2012/12/21 18:55:16
 */
package net.syamn.fireworkmanager.firework;


import java.util.List;

import org.bukkit.FireworkEffect;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;

/**
 * Firework (Firework.java)
 * @author syam(syamn)
 */
public class Firework{
    private ItemStack item;
    private FireworkMeta meta;
    
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
    
    // Effects
    public void addEffect(final FireworkEffect effect){
        if (effect == null) throw new IllegalArgumentException("Null effect!");
        this.meta.addEffect(effect);
    }
    public void addEffects(final Iterable<FireworkEffect> effects){
        if (effects == null) throw new IllegalArgumentException("Null effects!");
        this.meta.addEffects(effects);
    }
    public void setEffect(final FireworkEffect effect){
        clearEffects();
        addEffect(effect);
    }
    public void setEffects(final Iterable<FireworkEffect> effects){
        clearEffects();
        addEffects(effects);
    }
    public List<FireworkEffect> getEffects(){
        return this.meta.getEffects();
    }
    
    public void clearEffects(){
        this.meta.clearEffects();
    }
    public boolean hasEffects(){
        return this.meta.hasEffects();
    }
    
    
    // Power
    public void setPower(final int power){
        if (power <= 0 || power >= 128){
            throw new IllegalArgumentException("Invalid FireworkMeta power!");
        }
        this.meta.setPower(power);
    }    
    public int getPower(){
        return this.meta.getPower();
    }
}
