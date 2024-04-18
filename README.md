# SafeLogout

## About

This plugin adds the /logout command, which makes sure that the player is not in combat, before disconnecting them from the proxy.

Could be used for servers that want to:

- Give players the confidence that they are logging out without combat logging by accident.
- Allow players to log out via a command.

This plugin requires [CombatLogX](https://www.spigotmc.org/resources/combatlogx.31689/) and a Proxy (Velocity/BungeeCord). If you use Velocity, please make sure `bungee-plugin-message-channel` is enabled in the config.


## Commands

`/logout` - Sends a bungee plugin message to disconnect the player with the message "Safely Logged Out!".
