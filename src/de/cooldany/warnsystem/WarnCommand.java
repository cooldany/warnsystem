package de.cooldany.warnsystem;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class WarnCommand implements CommandExecutor, Listener {
	
	public Warnsystem plugin;
	
	public WarnCommand(Warnsystem instance) {
		plugin = instance;
	}
	
	public Integer getWarns(Player p) {
		if (!this.plugin.getConfig().contains("Spieler." + p.getName() + ".Warns")){
			this.plugin.getConfig().createSection("Spieler." + p.getName() + ".Warns");
			this.plugin.getConfig().set("Spieler." + p.getName() + ".Warns", 0);
			this.plugin.saveConfig();
			return 0;
		}
		
		int i = this.plugin.getConfig().getInt("Spieler." + p.getName() + ".Warns");
		return i;
	}

	public void removeWarns(Player p, int wieviele) {
		int i = this.plugin.getConfig().getInt("Spieler." + p.getName() + ".Warns");
		i-=wieviele;
		this.plugin.getConfig().set("Spieler." + p.getName() + ".warns", i);
		this.plugin.saveConfig();
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		sender.sendMessage("On Command startet");
		sender.sendMessage("Command:" + label);
		if (sender instanceof Player) {
			sender.sendMessage("Command wurde von Player getriggered");
		if(label.equalsIgnoreCase("warns")) {
			sender.sendMessage("Command warns startet");
			Player p = (Player) sender;
			if(args.length == 0) {
				sender.sendMessage("Es wurde kein Parameter übergeben");
				if(getWarns(p) == 0) {
					p.sendMessage("§2Du hast keine Warns!");
				} else if(getWarns(p) == 1) {
					p.sendMessage("§eDu hast schon §41 §eWarn!");
				} else if(getWarns(p) == 2) {
					p.sendMessage("§eDu hast schon §42 §eWarns!, noch ein Weiterer und ein Zeitlicher Ban Folgt!");
				}
				sender.sendMessage("On Command Endet erfolgreich!");
				return true;
			} else if(args.length == 1) {
				sender.sendMessage("Es wurde 1 Parameter übergeben!");
				Player p2 = p.getServer().getPlayer(args[0]);
				p.sendMessage("§7Warns von §e" + p2.getName() + "§7.");
				p.sendMessage("");
				
				if(getWarns(p2) == 0) {
					p.sendMessage("§7Warns: §aNoch Keine!");
					
				} else if(getWarns(p2) <=1) {
					p.sendMessage("§7Warns: §e" + getWarns(p));
				}
				
				if(p2.isBanned() == true);
				p.sendMessage("§7Gebannt: §eJa!");
				
			    }else {
			    	p.sendMessage("§7Gebannt: §eNein!");
			    }
			return true;
		}
		sender.sendMessage("On Command endet aus unbekanntem Grund");
		return false;
		}
		sender.sendMessage("On Command endet weil nicht von Spieler ausgeführt!");
		sender.sendMessage("Command kann nur als Spieler verwendet werden!");
		return true;
	}


}
