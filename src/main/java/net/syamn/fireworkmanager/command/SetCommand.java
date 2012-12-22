/**
 * FireworkManager - Package: net.syamn.fireworkmanager.command
 * Created: 2012/12/22 14:19:58
 */
package net.syamn.fireworkmanager.command;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import net.syamn.fireworkmanager.Perms;
import net.syamn.fireworkmanager.command.ClickCommand.ClickParam;
import net.syamn.fireworkmanager.exception.CommandException;
import net.syamn.fireworkmanager.firework.Firework;
import net.syamn.fireworkmanager.firework.FireworkContainer;
import net.syamn.fireworkmanager.util.Actions;
import net.syamn.fireworkmanager.util.Util;

import org.bukkit.FireworkEffect;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

/**
 * SetCommand (SetCommand.java)
 * @author syam(syamn)
 */
public class SetCommand extends BaseCommand {
    public SetCommand() {
        bePlayer = true;
        name = "set";
        argLength = 0;
        usage = "[action] <- modify in hand firework";
    }

    @Override
    public void execute() throws CommandException {
        if (args.size() < 1) sendSetCommandUsage();
        
        SetParam param = Util.isMatches(SetParam.values(), args.remove(0));
        if (param == null || args.size() < param.getArgs()) sendSetCommandUsage();
        
        ItemStack item = player.getItemInHand();
        if (!Material.FIREWORK.equals(item.getType())){
            throw new CommandException("&c花火を手に持っていません！");
        }
        
        Firework firework = new Firework(item);
        
        switch (param){
            case TYPE:
                // TODO write stuff...
                //Type t = Util.isMatches(FireworkEffect.Type.values(), args.get(0));
               
            case CLEAR:
                firework.clearEffects();
                Actions.message(sender, "&aこの花火のエフェクトを削除しました！");
                break;
                
            case DISTANCE:
                if (!Util.isInteger(args.get(0))){
                    throw new CommandException("&c指定した飛距離が数値ではありません！");
                }
                int power = Integer.parseInt(args.get(0));
                if (power <= 0 || power >= 128){
                    throw new CommandException("&c飛距離は1～127を指定する必要があります！");
                }
                
                firework.setPower(power);
                Actions.message(sender, "&aこの花火の飛距離を " + power + " に変更しました！");
                break;
                
            default:
                throw new CommandException("&cNot defined "+ param.name() + ", Please contact plugin developer.");
        }
        
        item = firework.getItem();
        player.setItemInHand(item);
    }
    
    private void sendSetCommandUsage() throws CommandException{
        List<String> col = new ArrayList<String>();
        for (SetParam value : SetParam.values()){
            col.add(value.name());
        }
        throw new CommandException("&c引数が不正です: " + (Util.join(col, ", ").toLowerCase(Locale.ENGLISH)));
    }

    @Override
    public boolean permission() {
        return Perms.SET.has(sender);
    }
    
    enum SetParam{
        TYPE (1),
        CLEAR (0),
        DISTANCE (1),
        ;
        
        int args;
        SetParam(int args){
            this.args = args;
        }
        int getArgs(){
            return this.args;
        }
    }
}