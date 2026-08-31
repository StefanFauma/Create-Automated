package create_automated.create_automated;

import com.mojang.logging.LogUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.slf4j.Logger;

@Mod(Create_automated.MODID)
public class Create_automated
{

    public static final String MODID = "create_automated";
    //private static final Logger LOGGER = LogUtils.getLogger();
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(MODID);
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MODID);
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);
    //
    // ITEMS
    //
    public static final DeferredItem<Item> DIAMOND_NUGGET_ITEM = ITEMS.registerSimpleItem("diamond_nugget");
    public static final DeferredItem<Item> COATED_BRASS_INGOT_ITEM = ITEMS.registerSimpleItem("coated_brass_ingot");
    public static final DeferredItem<Item> HARDENED_BRASS_INGOT_ITEM = ITEMS.registerSimpleItem("hardened_brass_ingot");
    public static final DeferredItem<Item> COMPACT_COAL_ITEM = ITEMS.registerSimpleItem("compact_coal");
    public static final DeferredItem<Item> SMALL_NETHERITE_SCRAP_ITEM = ITEMS.registerSimpleItem("small_netherite_scrap");

    public static final DeferredItem<Item> ICON_ITEM = ITEMS.registerSimpleItem("icon");

    //public static final DeferredBlock<Block> EXAMPLE_BLOCK = BLOCKS.registerSimpleBlock("example_block", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    //public static final DeferredItem<BlockItem> EXAMPLE_BLOCK_ITEM = ITEMS.registerSimpleBlockItem("example_block", EXAMPLE_BLOCK);

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> CREATE_AUTOMATED_TAB = CREATIVE_MODE_TABS.register("create_automated", () -> CreativeModeTab.builder().title(Component.translatable("itemGroup.create_automated")).withTabsBefore(CreativeModeTabs.SPAWN_EGGS).icon(() -> ICON_ITEM.get().getDefaultInstance()).displayItems((parameters, output) ->
    {
        output.accept(DIAMOND_NUGGET_ITEM.get());
        output.accept(COATED_BRASS_INGOT_ITEM.get());
        output.accept(HARDENED_BRASS_INGOT_ITEM.get());
        output.accept(COMPACT_COAL_ITEM.get());
        output.accept(SMALL_NETHERITE_SCRAP_ITEM.get());
    }
    ).build());

    public Create_automated(IEventBus modEventBus, ModContainer modContainer)
    {
        modEventBus.addListener(this::commonSetup);

        BLOCKS.register(modEventBus);
        ITEMS.register(modEventBus);
        CREATIVE_MODE_TABS.register(modEventBus);

        NeoForge.EVENT_BUS.register(this);

        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
    }

    // Add the example block item to the building blocks tab
    //private void addCreative(BuildCreativeModeTabContentsEvent event) {
    //    if (event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS) event.accept(EXAMPLE_BLOCK_ITEM);
    //}

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {
    }

    @EventBusSubscriber(modid = MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
        }
    }
}
