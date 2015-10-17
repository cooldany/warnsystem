package de.cooldany.warnsystem;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Warnsystem extends JavaPlugin implements Listener {
	
	public static String prefix = "§7[§cWarn§7] ";
	public static Warnsystem plugin;
	
	@Override
	public void onEnable() {		
		System.out.println("[Warn] Plugin Aktiviert!");
	}
	@Override
	public void onDisable() {		
		System.out.println("[Warn] Plugin Deaktiviert!");
		
		PluginManager pm = Bukkit.getPluginManager();
		
		getCommands();
		getEvents(pm);
		
		plugin = this;
	}
	
	public void getEvents(PluginManager pm) {
		pm.registerEvents(new WarnCommand(this), this);
		getConfig().options().copyDefaults(true);
		saveConfig();
		loadConfig();
	}
	
	public void getCommands() {
		WarnCommand cmd = new WarnCommand(this);
		this.getCommand("warn").setExecutor(cmd);
		this.getCommand("warns").setExecutor(cmd);
		this.getCommand("delwarn").setExecutor(cmd);
	}
	
	
	private void loadConfig() {
		
	}

}
