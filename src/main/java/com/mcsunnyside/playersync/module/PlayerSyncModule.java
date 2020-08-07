package com.mcsunnyside.playersync.module;

import com.mcsunnyside.playersync.sync.Sync;
import com.mcsunnyside.playersync.sync.SyncDataContainer;
import com.mcsunnyside.playersync.sync.SyncModule;
import com.mcsunnyside.playersync.sync.SyncType;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class PlayerSyncModule implements SyncModule {
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

    @Sync(field = "exp", type = SyncType.LOAD)
    public void loadPlayerExp(@NotNull SyncDataContainer data) {
        float exp = Float.parseFloat(data.getData());
        data.getPlayer().setExp(exp);
    }

    @NotNull
    @Sync(field = "exp", type = SyncType.SAVE)
    public SyncDataContainer savePlayerExp(@NotNull Player player) {
        return SyncDataContainer.builder()
                .player(player)
                .data(String.valueOf(player.getExp()))
                .build();
    }
}
