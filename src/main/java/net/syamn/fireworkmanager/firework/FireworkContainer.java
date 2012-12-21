/**
 * FireworkManager - Package: net.syamn.fireworkmanager.firework
 * Created: 2012/12/21 22:54:18
 */
package net.syamn.fireworkmanager.firework;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import org.bukkit.entity.Player;

/**
 * FireworkContainer (FireworkContainer.java)
 * @author syam(syamn)
 */
public class FireworkContainer {
    private static HashMap<String, ArrayList<Firework>> setMap = new HashMap<String, ArrayList<Firework>>();
    
    public static void putSetMap(final Player player, final Firework firework){
        final String name = player.getName();
        ArrayList<Firework> list = (setMap.get(name) != null) ? setMap.get(name) : new ArrayList<Firework>();
        list.add(firework);
        setMap.put(name, list);
    }
    
    public static ArrayList<Firework> getSetMap(final Player player){
        return setMap.get(player.getName());
    }
    
    public static Firework getRandomFirework(final Player player){
        ArrayList<Firework> list = setMap.get(player.getName());
        if (list == null || list.size() <= 0) return null;
        if (list.size() == 1) return list.get(0);
        
        Random rnd = new Random();
        return list.get(rnd.nextInt(list.size()));
    }
    
    public static void removeSetMap(final Player player){
        setMap.remove(player.getName());
    }
}
