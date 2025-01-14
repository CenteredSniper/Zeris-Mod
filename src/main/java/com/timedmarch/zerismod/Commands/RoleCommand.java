package com.timedmarch.zerismod.Commands;

package com.example.zerimod.Commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.network.chat.Component;
import com.example.zerimod.PlayerRoleManager;

public class RoleCommand {
    // Register the role commands
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        // /role check <player>
        dispatcher.register(
                Commands.literal("role")
                        .then(Commands.literal("check")
                                .then(Commands.argument("player", StringArgumentType.string())
                                        .executes(RoleCommand::executeCheckRoleCommand))
                        )
        );

        // /role add <player> <role>
        dispatcher.register(
                Commands.literal("role")
                        .then(Commands.literal("add")
                                .then(Commands.argument("player", StringArgumentType.string())
                                        .then(Commands.argument("role", StringArgumentType.string())
                                                .executes(RoleCommand::executeAddRoleCommand))
                                )
                        )
        );
    }

    // Command: /role check <player>
    private static int executeCheckRoleCommand(CommandContext<CommandSourceStack> context) {
        String playerName = StringArgumentType.getString(context, "player");
        CommandSourceStack source = context.getSource();
        ServerPlayer player = source.getServer().getPlayerList().getPlayerByName(playerName);
        if (player != null) {
            String role = PlayerRoleManager.getRole(player);
            source.sendSuccess(() -> Component.nullToEmpty("Player " + playerName + " has role: " + role), false);
        } else {
            source.sendFailure(Component.literal("Player not found"));
        }
        return 1;
    }

    // Command: /role add <player> <role>
    private static int executeAddRoleCommand(CommandContext<CommandSourceStack> context) {
        String playerName = StringArgumentType.getString(context, "player");
        String role = StringArgumentType.getString(context, "role");
        CommandSourceStack source = context.getSource();
        ServerPlayer player = source.getServer().getPlayerList().getPlayerByName(playerName);
        if (player != null) {
            PlayerRoleManager.addRole(player, role);
            source.sendSuccess(() -> Component.nullToEmpty("Added role " + role + " to player " + playerName), false);
        } else {
            source.sendFailure(Component.literal("Player not found"));
        }
        return 1;
    }
}
