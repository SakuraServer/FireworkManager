/**
 * FireworkManager - Package: net.syamn.fireworkmanager.command
 * Created: 2012/12/21 18:09:38
 */
package net.syamn.fireworkmanager.command;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import net.syamn.fireworkmanager.ConfigurationManager;
import net.syamn.fireworkmanager.FireworkManager;
import net.syamn.fireworkmanager.exception.CommandException;
import net.syamn.fireworkmanager.util.Actions;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * BaseCommand (BaseCommand.java)
 * @author syam(syamn)
 */
public abstract class BaseCommand {
    // Logger
    protected static final Logger log = FireworkManager.log;
    protected static final String logPrefix = FireworkManager.logPrefix;
    protected static final String msgPrefix = FireworkManager.msgPrefix;
    /* コマンド関係 */
    protected CommandSender sender;
    protected List<String> args = new ArrayList<String>();
    protected String name;
    protected int argLength = 0;
    protected String usage;
    protected boolean bePlayer = true;
    protected Player player;
    protected String command;
    protected FireworkManager plugin;
    protected ConfigurationManager config;

    public boolean run(final FireworkManager plugin, final CommandSender sender, final String[] preArgs, final String cmd) {
        this.plugin = plugin;
        this.config = plugin.getConfigs();

        this.sender = sender;
        this.command = cmd;

        // 引数をソート
        args.clear();
        for (String arg : preArgs)
            args.add(arg);

        // 引数からコマンドの部分を取り除く
        // (コマンド名に含まれる半角スペースをカウント、リストの先頭から順にループで取り除く)
        for (int i = 0; i < name.split(" ").length && i < args.size(); i++)
            args.remove(0);

        // 引数の長さチェック
        if (argLength > args.size()) {
            sendUsage();
            return true;
        }

        // 実行にプレイヤーであることが必要かチェックする
        if (bePlayer && !(sender instanceof Player)) {
            Actions.message(sender, "&cThis command cannot run from Console!");
            return true;
        }
        if (sender instanceof Player) {
            player = (Player) sender;
        }

        // 権限チェック
        if (!permission()) {
            Actions.message(sender, "&cYou don't have permission to use this!");
            return true;
        }

        // 実行
        try {
            execute();
        } catch (CommandException ex) {
            Throwable error = ex;
            while (error instanceof CommandException) {
                Actions.message(sender, error.getMessage());
                error = error.getCause();
            }
        }

        return true;
    }

    /**
     * コマンドを実際に実行する
     *
     * @return 成功すればtrue それ以外はfalse
     * @throws CommandException
     *             CommandException
     */
    public abstract void execute() throws CommandException;

    /**
     * コマンド実行に必要な権限を持っているか検証する
     *
     * @return trueなら権限あり、falseなら権限なし
     */
    public abstract boolean permission();

    /**
     * コマンドの使い方を送信する
     */
    public void sendUsage() {
        Actions.message(sender, "&c/" + this.command + " " + name + " " + usage);
    }

    public String getName() {
        return this.name;
    }
}