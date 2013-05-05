/**
 * FireworkMetaManager - Package: net.syamn.fireworkmanager.firework
 * Created: 2012/12/21 22:54:18
 */
package net.syamn.fireworkmanager.firework;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.FireworkMeta;

/**
 * FireworkMetaContainer (FireworkMetaContainer.java)
 * @author syam(syamn)
 */
public class FireworkMetaContainer {
    private static HashMap<String, ArrayList<FireworkMeta>> setMap = new HashMap<String, ArrayList<FireworkMeta>>();
    
    public static void putSetMap(final Player player, final FireworkMeta fireworkMeta){
        final String name = player.getName();
        ArrayList<FireworkMeta> list = (setMap.get(name) != null) ? setMap.get(name) : new ArrayList<FireworkMeta>();
        list.add(fireworkMeta);
        setMap.put(name, list);
    }
    
    public static ArrayList<FireworkMeta> getSetMap(final Player player){
        return setMap.get(player.getName());
    }
    
    public static FireworkMeta getRandomFireworkMeta(final Player player){
        ArrayList<FireworkMeta> list = setMap.get(player.getName());
        if (list == null || list.size() <= 0) return null;
        if (list.size() == 1) return list.get(0);
        
        Random rnd = new Random();
        return list.get(rnd.nextInt(list.size()));
    }
    
    public static void removeSetMap(final Player player){
        setMap.remove(player.getName());
    }
}
