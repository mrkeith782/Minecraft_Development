package io.github.mrkeith;

import java.lang.Math;

import org.bukkit.Effect;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public class tetrisShotgun implements Listener {
    private final tetris Tetris;

    public tetrisShotgun(tetris tetris) {
        this.Tetris = tetris;
    }

    @EventHandler
    public void onPlayerInteractBlock(PlayerInteractEvent event) {
        Player player = event.getPlayer();

        if (player.getItemInHand().getType() == Material.WOOD_HOE) {
            for(int i = 0; i < 100; i++) {
                double x = ((Math.random()-0.5)*(0.2));
                double y = ((Math.random()-0.5)*(0.2));
                double z = ((Math.random()-0.5)*(0.2));
                
                Vector variance = new Vector(x, y, z);

                Snowball sb = player.getWorld().spawn(player.getEyeLocation(), Snowball.class);
                sb.setShooter(player);
                sb.setVelocity(player.getLocation().getDirection().add(variance));
                sb.setTicksLived(1100);


                if (!(player.getGameMode() == GameMode.CREATIVE)) { 
                    player.getInventory().removeItem(new ItemStack(Material.WOOD_HOE, 1));
                }
            }
        }

        if (player.getItemInHand().getType() == Material.WOOD_AXE) {
            Vector playerVector = player.getLocation().getDirection();
            Vector vectorChange = playerVector.multiply(0.1);

            Location loc = player.getEyeLocation();
            BukkitRunnable playFlameEffect = new BukkitRunnable(){
            
                @Override
                public void run() {

                    int count = 0;
                    while(count < 5) {
                        if (loc.getBlock().getType() != Material.AIR) {
                            player.getWorld().spigot().playEffect(loc, Effect.FLAME, 1, 1, 0, 0, 0, (float)0.1, 15, 10000);
                            count++;
                            return;
                        }
                        player.getWorld().spigot().playEffect(loc, Effect.FLAME, 1, 1, 0, 0, 0, 0, 1, 10000);
                        loc.add(vectorChange);
                    }
                }
            };

            playFlameEffect.runTask(Tetris); //wait x, run every y

            // while (loc.getBlock().getType() == Material.AIR) {
            //     Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
            //         public void run() {
            //             player.getWorld().playEffect(loc, Effect.FLAME, 0);
            //         }
            //     }, (0.1 * 20));
            // }
        }
    }
}