package com.timedmarch.zerismod.Commands;

import com.mojang.brigadier.context.CommandContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;

public class Test {
    public static int execute(CommandContext<CommandSourceStack> context) {
        context.getSource().sendSuccess(() -> Component.nullToEmpty("Test command executed from Test.java!"), false);
        return 1;
    }
}