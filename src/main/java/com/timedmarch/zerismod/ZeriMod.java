package com.timedmarch.zerismod;

import com.mojang.logging.LogUtils;
import com.timedmarch.zerismod.Commands.CommandHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(ZeriMod.MOD_ID)
public class ZeriMod {
    // Define mod id in a common place for everything to reference
    public static final String MOD_ID = "zerimod";
    // Directly reference a slf4j logger
    public static final Logger LOGGER = LogUtils.getLogger();

    public ZeriMod() {
        // Register the setup methods
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::commonSetup);

        // Register commands
        MinecraftForge.EVENT_BUS.addListener(this::onRegisterCommands);

        LOGGER.info("ZeriMod has been initialized!");
    }

    private void commonSetup(FMLCommonSetupEvent event) {
        LOGGER.info("Common setup completed for ZeriMod.");
    }

    private void onRegisterCommands(RegisterCommandsEvent event) {
        CommandHandler.register(event.getDispatcher());
    }
}
