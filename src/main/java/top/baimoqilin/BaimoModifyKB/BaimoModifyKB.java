package top.baimoqilin.BaimoModifyKB;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.entity.EntityDamageByEntityEvent;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.utils.Config;

import java.util.HashMap;
import java.util.Map;

public class BaimoModifyKB extends PluginBase implements Listener {

    private Map<String, Float> kbMap = new HashMap<>();

    @Override
    public void onEnable() {
        this.getServer().getPluginManager().registerEvents(this, this);
        this.saveDefaultConfig();
        Config config = this.getConfig();
        for (String worldName : config.getKeys(false)) {
            kbMap.put(worldName, (float) config.getDouble(worldName));
        }
    }

    @Override
    public void onDisable() {
        Config config = this.getConfig();
        for (String worldName : kbMap.keySet()) {
            config.set(worldName, kbMap.get(worldName));
        }
        config.save();
    }

    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Player) {
            Player player = (Player) event.getDamager();
            String worldName = player.getLevel().getName();
            if (kbMap.containsKey(worldName)) {
                float kb = kbMap.get(worldName);
                event.setKnockBack(kb);
            }
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("setkb")) {
            if (args.length == 1) {
                if (sender instanceof Player) {
                    Player player = (Player) sender;
                    String worldName = player.getLevel().getName();
                    try {
                        float kb = Float.parseFloat(args[0]);
                        kbMap.put(worldName, kb);
                        sender.sendMessage("Set knockback distance to " + kb + " for world " + worldName);
                        return true;
                    } catch (NumberFormatException e) {
                        sender.sendMessage("Invalid knockback distance value: " + args[0]);
                        return false;
                    }
                } else {
                    sender.sendMessage("This command can only be used by a player.");
                    return false;
                }
            } else {
                sender.sendMessage("Usage: /setkb <value/float>");
                return false;
            }
        }
        return false;
    }
}
