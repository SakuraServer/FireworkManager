/**
 * FireworkManager - Package: net.syamn.fireworkmanager
 * Created: 2012/12/21 18:12:47
 */
package net.syamn.fireworkmanager;

import org.bukkit.permissions.Permissible;

/**
 * Perms (Perms.java)
 * @author syam(syamn)
 */
public enum Perms {
    /* 権限ノード */
    // User Commands
    SET ("user.set"),
    CLICK ("user.click"),

    // Free Permissions

    // Spec Permissions
    CLICK_LAUNCH ("spec.clicklaunch"),

    // Admin Commands
    RELOAD("admin.reload"),

    ;

    // ノードヘッダー
    final String HEADER = "fireworkmanager.";
    private String node;

    /**
     * コンストラクタ
     *
     * @param node
     *            権限ノード
     */
    Perms(final String node) {
        this.node = HEADER + node;
    }

    /**
     * 指定したプレイヤーが権限を持っているか
     *
     * @param player
     *            Permissible. Player, CommandSender etc
     * @return boolean
     */
    public boolean has(final Permissible perm) {
        if (perm == null)
            return false;
        return perm.hasPermission(node); // only support SuperPerms
    }
}