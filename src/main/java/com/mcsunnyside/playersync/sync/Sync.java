package com.mcsunnyside.playersync.sync;


import org.jetbrains.annotations.NotNull;

public @interface Sync {
   @NotNull String field() default "unknown";

   @NotNull SyncTime[] sync() default {SyncTime.JOIN, SyncTime.QUIT, SyncTime.TIMER};

   @NotNull SyncType type() default SyncType.LOAD;
}
