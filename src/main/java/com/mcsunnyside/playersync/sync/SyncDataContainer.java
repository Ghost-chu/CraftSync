package com.mcsunnyside.playersync.sync;

import org.bukkit.entity.Player;

public class SyncDataContainer {
    private Player player;
    private String data;

    public SyncDataContainer(Player player, String data) {
        this.player = player;
        this.data = data;
    }

    public static SyncDataContainerBuilder builder() {
        return new SyncDataContainerBuilder();
    }

    public Player getPlayer() {
        return this.player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public String getData() {
        return this.data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String toString() {
        return "SyncDataContainer(player=" + this.getPlayer() + ", data=" + this.getData() + ")";
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof SyncDataContainer)) return false;
        final SyncDataContainer other = (SyncDataContainer) o;
        if (!other.canEqual(this)) return false;
        final Object this$player = this.getPlayer();
        final Object other$player = other.getPlayer();
        if (this$player == null ? other$player != null : !this$player.equals(other$player)) return false;
        final Object this$data = this.getData();
        final Object other$data = other.getData();
        return this$data == null ? other$data == null : this$data.equals(other$data);
    }

    protected boolean canEqual(final Object other) {
        return other instanceof SyncDataContainer;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $player = this.getPlayer();
        result = result * PRIME + ($player == null ? 43 : $player.hashCode());
        final Object $data = this.getData();
        result = result * PRIME + ($data == null ? 43 : $data.hashCode());
        return result;
    }

    public static class SyncDataContainerBuilder {
        private Player player;
        private String data;

        SyncDataContainerBuilder() {
        }

        public SyncDataContainer.SyncDataContainerBuilder player(Player player) {
            this.player = player;
            return this;
        }

        public SyncDataContainer.SyncDataContainerBuilder data(String data) {
            this.data = data;
            return this;
        }

        public SyncDataContainer build() {
            return new SyncDataContainer(player, data);
        }

        public String toString() {
            return "SyncDataContainer.SyncDataContainerBuilder(player=" + this.player + ", data=" + this.data + ")";
        }
    }
}
