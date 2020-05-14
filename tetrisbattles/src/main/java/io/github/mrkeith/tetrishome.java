package io.github.mrkeith;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class tetrishome implements CommandExecutor {
    private final tetris tetris;
    HashMap<UUID, Location> homes = new HashMap<UUID, Location>();

    public tetrishome(tetris tetris, HashMap<UUID, Location> homes) {
        this.tetris = tetris;
        this.homes = homes;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player player = (Player) sender;
        if (cmd.getName().toLowerCase().equals("sethome")) {
            String msg = ChatColor.BOLD + "" + ChatColor.RED + "Set home to <" + (int)player.getLocation().getX() + "," + (int)player.getLocation().getY() + "," + (int)player.getLocation().getZ() + ">";

            if (homes.get(player.getUniqueId()) != null) { //Special case that adds to msg if the player has set a home previously.
                Location oldHome = homes.get(player.getUniqueId());
                msg += " from <" + (int)oldHome.getX() + "," + (int)oldHome.getY() + "," + (int)oldHome.getZ() + ">";   
            }

            homes.put(player.getUniqueId(), player.getLocation());
            sender.sendMessage(msg); 
            return true;
        }
        if (cmd.getName().toLowerCase().equals("home")) {
            Location playerHome = homes.get(player.getUniqueId());
            player.teleport(playerHome);
            return true;
        }
        return false;
    }
}