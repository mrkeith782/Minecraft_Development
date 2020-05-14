package io.github.mrkeith;

import org.bukkit.command.CommandExecutor;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class tetrisCommandExe implements CommandExecutor {
    private final tetris tetris;

    public tetrisCommandExe(tetris tetris) {
        this.tetris = tetris;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().toLowerCase().equals("tetris")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage("The command can only be executed by a player.");
            }

            int temp = args.length;

            if (temp > 0) {
                sender.sendMessage("This command requires no arguments.");
            } else {
                sender.sendMessage("Howdy " + sender.getName());
            }

            Player player = ((Player) sender).getPlayer();

            Location loc = player.getLocation();

            sender.sendMessage("Location of " + player.getDisplayName() + ": " + loc);

            Block block = loc.getBlock();
            block.setType(Material.STONE);


            return true;
        }

        if (cmd.getName().toLowerCase().equals("tetris1")) {
            if (!(sender instanceof Player)) { //Check if the user is a player.
                sender.sendMessage("The command can only be executed by a player.");
                return true;
            }

            int temp = args.length;

            if (temp > 0) { //Check arg count.
                sender.sendMessage("This command requires no arguments.");
                return true;
            }

            //Get user inventory to add shotgun to
            Player player = ((Player) sender).getPlayer(); 
            PlayerInventory inventory = player.getInventory();

            inventory.addItem(new ItemStack(Material.WOOD_HOE,10));

            sender.sendMessage("You got a shotgun!");
        }
        return false;
    }

}