package com.mcsunnyside.craftsync.sync;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

@Data
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class SyncDataContainer {
    @NotNull
    private Player player;
    @NotNull
    private String data;
}
