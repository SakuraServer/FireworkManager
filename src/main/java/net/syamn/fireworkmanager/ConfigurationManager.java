/**
 * FireworkManager - Package: net.syamn.fireworkmanager
 * Created: 2012/12/21 17:54:10
 */
package net.syamn.fireworkmanager;

import java.io.File;
import java.util.logging.Logger;
import net.syamn.fireworkmanager.util.FileStructure;
import org.bukkit.configuration.file.FileConfiguration;

/**
 * ConfigurationManager (ConfigurationManager.java)
 * @author syam(syamn)
 */
public class ConfigurationManager {
    /* Current config.yml File Version! */
    private final int latestVersion = 1;

    // Logger
    private static final Logger log = FireworkManager.log;
    private static final String logPrefix = FireworkManager.logPrefix;
    private static final String msgPrefix = FireworkManager.msgPrefix;
    private final FireworkManager plugin;

    // private YamlConfiguration conf;
    private FileConfiguration conf;
    private File pluginDir;

    // hookup plugin
    private boolean useVault;

    /**
     * Constructor
     */
    public ConfigurationManager(final FireworkManager plugin) {
        this.plugin = plugin;

        this.pluginDir = this.plugin.getDataFolder();
    }

    /**
     * Load config.yml
     */
    public void loadConfig(final boolean initialLoad) throws Exception {
        // create directories
        FileStructure.createDir(pluginDir);

        // get config.yml path
        File file = new File(pluginDir, "config.yml");
        if (!file.exists()) {
            FileStructure.extractResource("/config.yml", pluginDir, false,
                    false);
            log.info("config.yml is not found! Created default config.yml!");
        }

        plugin.reloadConfig();
        conf = plugin.getConfig();

        checkver(conf.getInt("ConfigVersion", 1));

        // check vault
        useVault = conf.getBoolean("UseVault", false);
        if (!initialLoad && useVault
                && (plugin.getVault() == null || plugin.getEconomy() == null)) {
            plugin.setupVault();
        }
    }

    /**
     * Check configuration file version
     */
    private void checkver(final int ver) {
        // compare configuration file version
        if (ver < latestVersion) {
            // first, rename old configuration
            final String destName = "oldconfig-v" + ver + ".yml";
            String srcPath = new File(pluginDir, "config.yml").getPath();
            String destPath = new File(pluginDir, destName).getPath();
            try {
                FileStructure.copyTransfer(srcPath, destPath);
                log.info("Copied old config.yml to " + destName + "!");
            } catch (Exception ex) {
                log.warning("Failed to copy old config.yml!");
            }

            // force copy config.yml and languages
            FileStructure
                    .extractResource("/config.yml", pluginDir, true, false);
            // Language.extractLanguageFile(true);

            plugin.reloadConfig();
            conf = plugin.getConfig();

            log.info("Deleted existing configuration file and generate a new one!");
        }
    }

    /* ***** Begin Configuration Getters *********************** */
    // General
    
    // Launch by Click
    public int getLaunchTool(){
        return conf.getInt("LaunchToolID", 293);
    }
    public int getCheckDistance(){
        return conf.getInt("CheckDistance", 300);
    }

    // Vault
    public boolean getUseVault() {
        return useVault;
    }
    public void setUseVault(final boolean bool) {
        this.useVault = bool;
    }

    // Debug
    public boolean isDebug() {
        return conf.getBoolean("Debug", false);
    }
}