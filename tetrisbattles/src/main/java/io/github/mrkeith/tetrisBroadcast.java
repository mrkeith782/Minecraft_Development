package io.github.mrkeith;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;

public class tetrisBroadcast implements CommandExecutor{
    private final tetris Tetris;

    public tetrisBroadcast(tetris tetris) {
        this.Tetris = tetris;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().toLowerCase().equals("broadcast")) {

            sender.sendMessage(ChatColor.BOLD + "If you would like to include your username in this broadcast, please use /broadcastname <msg>.");

            if (args.length == 0) {
                return false;
            }

            String msg = ChatColor.BOLD + "" + ChatColor.RED + "[BROADCAST] " + ChatColor.GOLD;

            for(int i = 0; i < args.length; i++) {
                msg += args[i];
                msg += " ";
            }

            Bukkit.broadcastMessage(msg);
            return true;
        }

        if (cmd.getName().toLowerCase().equals("broadcastname") || cmd.getName().toLowerCase().equals("bn")) {
            if (args.length == 0) {
                return false;
            }

            String msg = ChatColor.RED + "" + ChatColor.BOLD + "[";

            if (sender instanceof Player) {
                msg += sender.getName() + "] " + ChatColor.GOLD;
            }
            else {
                msg += "CONSOLE] " + ChatColor.GOLD;
            }

            for(int i = 0; i < args.length; i++) {
                msg += args[i];
                msg += " ";
            }

            Bukkit.broadcastMessage(msg);
            return true;
        }
        return false;
    }
}