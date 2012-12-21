/**
 * FireworkManager - Package: net.syamn.fireworkmanager.firework
 * Created: 2012/12/21 22:38:44
 */
package net.syamn.fireworkmanager.firework;


import net.minecraft.server.v1_4_6.EntityFireworks;

import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_4_6.inventory.CraftItemStack;

/**
 * FireworkUtil (FireworkUtil.java)
 * @author syam(syamn)
 */
public class FireworkUtil {
    public static void launch(final Firework firework, final Location loc){
        if (firework == null || firework.getItem() == null || loc == null)
            return;
        
        net.minecraft.server.v1_4_6.World nmsworld = ((org.bukkit.craftbukkit.v1_4_6.CraftWorld)loc.getWorld()).getHandle();
        EntityFireworks nmsfw = new EntityFireworks(nmsworld, loc.getX(), loc.getY(), loc.getZ(), CraftItemStack.asNMSCopy(firework.getItem()));
        nmsworld.addEntity(nmsfw);
    }
}
