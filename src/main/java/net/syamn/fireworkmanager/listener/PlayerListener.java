/**
 * FireworkManager - Package: net.syamn.fireworkmanager.listener
 * Created: 2012/12/21 22:45:07
 */
package net.syamn.fireworkmanager.listener;

import java.util.logging.Logger;
import net.syamn.fireworkmanager.ConfigurationManager;
import net.syamn.fireworkmanager.FireworkManager;
import net.syamn.fireworkmanager.Perms;
import net.syamn.fireworkmanager.firework.FireworkMetaContainer;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.event.Event.Result;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;

/**
 * PlayerListener (PlayerListener.java)
 * @author syam(syamn)
 */
public class PlayerListener implements Listener{
    private static final Logger log = FireworkManager.log;
    private static final String logPrefix = FireworkManager.logPrefix;
    private static final String msgPrefix = FireworkManager.msgPrefix;

    private final FireworkManager plugin;
    private final ConfigurationManager config;

    public PlayerListener(final FireworkManager plugin){
        this.plugin = plugin;
        this.config = plugin.getConfigs();
    }
    
    @EventHandler(priority = EventPriority.NORMAL)
    public void onPlayerInteract(final PlayerInteractEvent event){
        // instead of ignoreCancelled = true
        if (event.useItemInHand() == Result.DENY){
            return;
        }
        
        final Player player = event.getPlayer();
        // return if not right click
        if (!(Action.RIGHT_CLICK_AIR.equals(event.getAction()) || Action.RIGHT_CLICK_BLOCK.equals(event.getAction()))) {
            return;
        }
        
        // check inHand items and permission
        if (player.getItemInHand() == null || player.getItemInHand().getTypeId() != config.getLaunchTool() || !Perms.CLICK_LAUNCH.has(player)){
            return;
        }
        
        final FireworkMeta meta = FireworkMetaContainer.getRandomFireworkMeta(player);
        if (meta != null){
            Block target = player.getTargetBlock(null, config.getCheckDistance());
            if (target != null && target.getType() != Material.AIR){
                Firework fw = (Firework)target.getWorld().spawnEntity(target.getLocation(), EntityType.FIREWORK);
                fw.setFireworkMeta(meta);
            }
        }
    }
}
