package io.github.mrkeith;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;


public class tetris extends JavaPlugin {
    @Override
    public void onEnable() {

        HashMap<UUID, Location> homes = new HashMap<UUID, Location>();

        //Test command and command that gives a stack of shotguns.
        this.getCommand("tetris").setExecutor(new tetrisCommandExe(this));
        this.getCommand("tetris1").setExecutor(new tetrisCommandExe(this));

        //Commands the broadcast to everyone on the server, with fancy colors!
        this.getCommand("broadcast").setExecutor(new tetrisBroadcast(this));
        this.getCommand("broadcastname").setExecutor(new tetrisBroadcast(this));
        this.getCommand("bn").setExecutor(new tetrisBroadcast(this));
        this.getCommand("sethome").setExecutor(new tetrishome(this, homes));
        this.getCommand("home").setExecutor(new tetrishome(this, homes));

        //Working on map logic
        this.getCommand("testmap").setExecutor(new tetrismaps(this));

        //Handles the logic behind the shotgun and rail gun.
        this.getServer().getPluginManager().registerEvents(new tetrisShotgun(this), this);
        this.getServer().getPluginManager().registerEvents(new tetrisLeaderboard(this), this);
        getLogger().info("Tetris Battles v0.1 started.");
    }

    @Override
    public void onDisable() {
        getLogger().info("Tetris Battles v0.1 ended.");
    }
}




//
//
//
//                            