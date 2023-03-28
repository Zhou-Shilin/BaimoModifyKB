# BaimoModifyKB
BaimoModifyKB is a plugin for the Minecraft server software Nukkit. It allows server administrators to modify the knockback distance of players in different worlds.

## Installation
To install BaimoModifyKB, simply download the plugin jar file and place it in the plugins folder of your Nukkit server.

## Usage
To set the knockback distance for a world, use the /setkb command followed by a float value. For example, to set the knockback distance to 2.0 for the current world, use the command /setkb 2.0.

## Configuration
BaimoModifyKB uses a configuration file to store the knockback distances for each world. The default configuration file is generated when the plugin is first enabled and can be found at plugins/BaimoModifyKB/config.yml.

To modify the knockback distance for a world, simply edit the value for that world in the configuration file. The value should be a float representing the desired knockback distance.

## Code Overview
The main class for BaimoModifyKB is BaimoModifyKB.java. This class extends the PluginBase class provided by Nukkit and implements the Listener interface to handle events.

The onEnable method is called when the plugin is enabled and is responsible for registering event listeners and loading the configuration file.

The onDisable method is called when the plugin is disabled and is responsible for saving the current knockback distances to the configuration file.

The onEntityDamageByEntity method is an event listener that is called whenever an entity is damaged by another entity. It checks if the damager is a player and if so, sets the knockback distance for the event to the value specified for the world the player is in.

The onCommand method is called whenever a player enters a command. It handles the /setkb command by setting the knockback distance for the world the player is in to the value specified in the command.

## Conclusion
BaimoModifyKB is a simple but useful plugin for Nukkit servers that allows server administrators to modify the knockback distance of players in different worlds. It is easy to install and configure, and provides a convenient command interface for setting knockback distances on the fly.
