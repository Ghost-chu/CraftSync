package com.mcsunnyside.playersync.database;

import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.UUID;

/**
 * DEV NOTICE:
 * We will add a field `serial` and `syncstatus` when writing database.
 * And this field value will change when writing EVERYTIME.
 * If `serial` value different between two reads, that mean player data has been modified.
 * Plugin should re-sync the player data until `syncstatus` is `idle` and `serial` is same.
 */
public abstract class DatabaseSource {
    @NotNull
    abstract PlayerFields fetchPlayerFields(@NotNull UUID player);

    abstract boolean savePlayerFields(@NotNull UUID player, @NotNull Map<String, String> fields);

    boolean isReSyncRequired(@NotNull UUID player, @NotNull UUID serial) {
        return fetchPlayerFields(player).getSerial().equals(serial);
    }

    @NotNull
    abstract DatabaseSyncStatus getSyncStatus(@NotNull UUID player); //SYNCING = Syncing, IDLE = No syncing
}
