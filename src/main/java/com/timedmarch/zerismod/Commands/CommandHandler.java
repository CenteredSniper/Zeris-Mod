package com.timedmarch.zerismod.Commands;

import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;

public class CommandHandler {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(
                Commands.literal("test").executes(Test::execute)
        );

        RoleCommand.register(dispatcher);
    }
}

