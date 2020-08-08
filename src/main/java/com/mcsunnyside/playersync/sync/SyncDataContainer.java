package com.mcsunnyside.playersync.sync;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.bukkit.entity.Player;

@Data
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class SyncDataContainer {
    private Player player;
    private String data;
}
