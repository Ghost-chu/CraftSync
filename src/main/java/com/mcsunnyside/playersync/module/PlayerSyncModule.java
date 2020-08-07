package com.mcsunnyside.playersync.module;

import com.mcsunnyside.playersync.sync.Sync;
import com.mcsunnyside.playersync.sync.SyncDataContainer;
import com.mcsunnyside.playersync.sync.SyncModule;
import com.mcsunnyside.playersync.sync.SyncType;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

public class PlayerSyncModule implements SyncModule {
    private final Logger logger = Logger.getLogger("PlayerSync");

    @Sync(field = "hp", type = SyncType.LOAD)
    public void loadPlayerHealth(@NotNull SyncDataContainer data) {
        double hp = Double.parseDouble(data.getData());
        data.getPlayer().setHealth(hp);
    }

    @NotNull
    @Sync(field = "hp", type = SyncType.SAVE)
    public SyncDataContainer savePlayerHealth(@NotNull Player player) {
        return SyncDataContainer.builder()
                .player(player)
                .data(String.valueOf(player.getHealth()))
                .build();
    }

    @Sync(field = "food", type = SyncType.LOAD)
    public void loadPlayerFood(@NotNull SyncDataContainer data) {
        int food = Integer.parseInt(data.getData());
        data.getPlayer().setFoodLevel(food);
    }

    @NotNull
    @Sync(field = "food", type = SyncType.SAVE)
    public SyncDataContainer savePlayerFood(@NotNull Player player) {
        return SyncDataContainer.builder()
                .player(player)
                .data(String.valueOf(player.getFoodLevel()))
                .build();
    }

    @Sync(field = "exp", type = SyncType.LOAD)
    public void loadPlayerExp(@NotNull SyncDataContainer data) {
        int exp = Integer.parseInt(data.getData());
        data.getPlayer().setTotalExperience(exp);
    }

    @NotNull
    @Sync(field = "exp", type = SyncType.SAVE)
    public SyncDataContainer savePlayerExp(@NotNull Player player) {
        return SyncDataContainer.builder()
                .player(player)
                .data(String.valueOf(player.getTotalExperience()))
                .build();
    }

    @Sync(field = "air", type = SyncType.LOAD)
    public void loadPlayerAir(@NotNull SyncDataContainer data) {
        int air = Integer.parseInt(data.getData());
        data.getPlayer().setRemainingAir(air);
    }

    @NotNull
    @Sync(field = "air", type = SyncType.SAVE)
    public SyncDataContainer savePlayerAir(@NotNull Player player) {
        return SyncDataContainer.builder()
                .player(player)
                .data(String.valueOf(player.getRemainingAir()))
                .build();
    }

    @Sync(field = "maxair", type = SyncType.LOAD)
    public void loadPlayerMaxAir(@NotNull SyncDataContainer data) {
        int air = Integer.parseInt(data.getData());
        data.getPlayer().setMaximumAir(air);
    }

    @NotNull
    @Sync(field = "maxair", type = SyncType.SAVE)
    public SyncDataContainer savePlayerMaxAir(@NotNull Player player) {
        return SyncDataContainer.builder()
                .player(player)
                .data(String.valueOf(player.getMaximumAir()))
                .build();
    }

    @Sync(field = "effects", type = SyncType.LOAD)
    public void loadPlayerPotionEffects(@NotNull SyncDataContainer data) {
        YamlConfiguration configuration = new YamlConfiguration();
        try {
            configuration.load(data.getData());
        } catch (IOException | InvalidConfigurationException ioException) {
            ioException.printStackTrace();
            logger.warning("Failed to sync player " + data.getPlayer().getName() + "'s potion effects!");
            return;
        }
        //noinspection unchecked
        Collection<PotionEffect> effects = (Collection<PotionEffect>) configuration.getList("effects");
        if (effects == null) {
            return;
        }
        for (PotionEffectType value : PotionEffectType.values()) {
            data.getPlayer().removePotionEffect(value);
        }
        data.getPlayer().addPotionEffects(effects);
    }

    @NotNull
    @Sync(field = "effects", type = SyncType.SAVE)
    public SyncDataContainer savePlayerPotionEffects(@NotNull Player player) {
        YamlConfiguration configuration = new YamlConfiguration();
        configuration.set("effects", player.getActivePotionEffects());
        return SyncDataContainer.builder().player(player).data(configuration.saveToString()).build();
    }

    @Sync(field = "inventory", type = SyncType.LOAD)
    public void loadPlayerInventory(@NotNull SyncDataContainer data) {
        YamlConfiguration configuration = new YamlConfiguration();
        try {
            configuration.load(data.getData());
        } catch (IOException | InvalidConfigurationException ioException) {
            ioException.printStackTrace();
            logger.warning("Failed to sync player " + data.getPlayer().getName() + "'s inventory!");
        }
        //noinspection unchecked
        List<ItemStack> contents = (List<ItemStack>) configuration.getList("inventory.contents");
        //noinspection unchecked
        List<ItemStack> extras = (List<ItemStack>) configuration.getList("inventory.extras");
        data.getPlayer().getInventory().clear();
        if (contents != null) {
            data.getPlayer().getInventory().setContents(contents.toArray(new ItemStack[0]));
        }
        if (extras != null) {
            data.getPlayer().getInventory().setExtraContents(extras.toArray(new ItemStack[0]));
        }
    }

    @NotNull
    @Sync(field = "inventory", type = SyncType.SAVE)
    public SyncDataContainer savePlayerInventory(@NotNull Player player) {
        List<ItemStack> contents = new ArrayList<>();
        for (ItemStack stack : player.getInventory().getContents()) {
            //noinspection ConstantConditions
            if (stack == null) { //Filter NULL
                stack = new ItemStack(Material.AIR);
            }
            contents.add(stack);
        }
        List<ItemStack> extras = new ArrayList<>();
        for (ItemStack stack : player.getInventory().getExtraContents()) {
            //noinspection ConstantConditions
            if (stack == null) { //Filter NULL
                stack = new ItemStack(Material.AIR);
            }
            extras.add(stack);
        }

        YamlConfiguration configuration = new YamlConfiguration();
        configuration.set("inventory.contents", contents);
        configuration.set("inventory.extras", extras);

        return SyncDataContainer.builder()
                .player(player)
                .data(configuration.saveToString())
                .build();
    }

    @Sync(field = "enderchest", type = SyncType.LOAD)
    public void loadPlayerEnderChest(@NotNull SyncDataContainer data) {
        YamlConfiguration configuration = new YamlConfiguration();
        try {
            configuration.load(data.getData());
        } catch (IOException | InvalidConfigurationException ioException) {
            ioException.printStackTrace();
            logger.warning("Failed to sync player " + data.getPlayer().getName() + "'s enderchest!");
        }
        //noinspection unchecked
        List<ItemStack> contents = (List<ItemStack>) configuration.getList("enderchest");
        data.getPlayer().getInventory().clear();
        if (contents != null) {
            data.getPlayer().getEnderChest().setContents(contents.toArray(new ItemStack[0]));
        }
    }

    @NotNull
    @Sync(field = "enderchest", type = SyncType.SAVE)
    public SyncDataContainer savePlayerEnderChest(@NotNull Player player) {
        List<ItemStack> contents = new ArrayList<>();
        for (ItemStack stack : player.getEnderChest().getContents()) {
            //noinspection ConstantConditions
            if (stack == null) { //Filter NULL
                stack = new ItemStack(Material.AIR);
            }
            contents.add(stack);
        }
        YamlConfiguration configuration = new YamlConfiguration();
        configuration.set("enderchest", contents);
        return SyncDataContainer.builder()
                .player(player)
                .data(configuration.saveToString())
                .build();
    }


    @Sync(field = "attributes", type = SyncType.LOAD)
    public void loadPlayerAttribute(@NotNull SyncDataContainer data) {
        YamlConfiguration configuration = new YamlConfiguration();
        try {
            configuration.load(data.getData());
        } catch (IOException | InvalidConfigurationException ioException) {
            ioException.printStackTrace();
            logger.warning("Failed to sync player " + data.getPlayer().getName() + "'s attribute!");
        }
        for (Attribute value : Attribute.values()) {

            //noinspection ConstantConditions
            data.getPlayer().getAttribute(value).setBaseValue(configuration.getDouble("attribute." + value.getKey().getNamespace() + "." + value.getKey().getKey() + ".base", 1.0d));
//            YamlConfiguration modifierSaver = new YamlConfiguration();
//            try {
//                modifierSaver.load(configuration.getString("attribute."+value.getKey().getNamespace()+"."+value.getKey().getKey()+".modifiers"));
//            } catch (IOException | InvalidConfigurationException ioException) {
//                ioException.printStackTrace();
//                logger.warning("Failed to sync player " + data.getPlayer().getName() + "'s attribute's modifiers!");
//            }
//            data.getPlayer().getAttribute(value).addModifier();
//            modifierSaver.set("modifiers",player.getAttribute(value).getModifiers());
//            configuration.set("attribute."+value.getKey().getNamespace()+"."+value.getKey().getKey()+".modifiers",modifierSaver.saveToString());
        }
    }

    @NotNull
    @Sync(field = "attributes", type = SyncType.SAVE)
    public SyncDataContainer savePlayerAttribute(@NotNull Player player) {
        YamlConfiguration configuration = new YamlConfiguration();
        for (Attribute value : Attribute.values()) {
            configuration.set("attribute." + value.getKey().getNamespace() + "." + value.getKey().getKey() + ".base", player.getAttribute(value).getBaseValue());
            YamlConfiguration modifierSaver = new YamlConfiguration();
            modifierSaver.set("modifiers", player.getAttribute(value).getModifiers());
            configuration.set("attribute." + value.getKey().getNamespace() + "." + value.getKey().getKey() + ".modifiers", modifierSaver.saveToString());
        }
        return SyncDataContainer.builder()
                .player(player)
                .data(configuration.saveToString()).build();
    }

}
