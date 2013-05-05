/**
 * FireworkManager - Package: net.syamn.fireworkmanager.command
 * Created: 2012/12/21 23:57:02
 */
package net.syamn.fireworkmanager.command;


import net.syamn.fireworkmanager.Perms;
import net.syamn.fireworkmanager.exception.CommandException;
import net.syamn.fireworkmanager.firework.FireworkMetaContainer;
import net.syamn.fireworkmanager.util.Actions;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;

/**
 * ClickCommand (ClickCommand.java)
 * @author syam(syamn)
 */
public class ClickCommand extends BaseCommand {
    public ClickCommand() {
        bePlayer = true;
        name = "click";
        argLength = 0;
        usage = "[action] <- set current firework";
    }

    @Override
    public void execute() throws CommandException {
        if (args.size() <= 0) sendClickCommandUsage();
        
        ClickParam param = null;
        for (ClickParam p : ClickParam.values()){
            if (p.name().equalsIgnoreCase(args.get(0))){
                param = p;
                break;
            }
        }
        
        if (param == null) sendClickCommandUsage();
        
        ItemStack item = player.getItemInHand();
        if (param.isNeedsInHand()){
            if (!Material.FIREWORK.equals(item.getType())){
                throw new CommandException("&c花火を手に持っていません！");
            }
        }
        FireworkMeta meta = (FireworkMeta)item.getItemMeta();
        
        switch (param){
            case SET:
                FireworkMetaContainer.removeSetMap(player);
                FireworkMetaContainer.putSetMap(player, meta);
                Actions.message(sender, "&a手に持っている花火を選択しました！");
                break;
            case ADD:
                FireworkMetaContainer.putSetMap(player, meta);
                Actions.message(sender, "&a手に持っている花火を追加選択しました！");
                break;
            case CLEAR:
                FireworkMetaContainer.removeSetMap(player);
                Actions.message(sender, "&a選択済み花火リストをクリアしました！");
                break;
            default:
                throw new CommandException("&cNot defined "+ param.name() + ", Please contact plugin developer.");
        }
    }
    
    private void sendClickCommandUsage() throws CommandException{
        throw new CommandException("&c引数が不正です: set, add, clear");
    }

    @Override
    public boolean permission() {
        return Perms.CLICK.has(sender);
    }
    
    enum ClickParam{
        SET (true),
        ADD (true),
        CLEAR (false),
        ;
        
        boolean inHand = false;
        ClickParam(boolean inHand){
            this.inHand = inHand;
        }
        
        boolean isNeedsInHand(){
            return this.inHand;
        }
    }
}