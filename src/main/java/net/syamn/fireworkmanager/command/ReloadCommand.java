/**
 * FireworkManager - Package: net.syamn.fireworkmanager.command
 * Created: 2012/12/21 18:12:17
 */
package net.syamn.fireworkmanager.command;

import net.syamn.fireworkmanager.Perms;
import net.syamn.fireworkmanager.util.Actions;

/**
 * ReloadCommand (ReloadCommand.java)
 * @author syam(syamn)
 */
public class ReloadCommand extends BaseCommand {
    public ReloadCommand() {
        bePlayer = false;
        name = "reload";
        argLength = 0;
        usage = "<- reload config.yml";
    }

    @Override
    public void execute() {
        try {
            plugin.getConfigs().loadConfig(false);
        } catch (Exception ex) {
            log.warning(logPrefix
                    + "an error occured while trying to load the config file.");
            ex.printStackTrace();
            return;
        }
        Actions.message(sender, "&aConfiguration reloaded!");
    }

    @Override
    public boolean permission() {
        return Perms.RELOAD.has(sender);
    }
}