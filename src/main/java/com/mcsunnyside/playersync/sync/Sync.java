package com.mcsunnyside.playersync.sync;


import org.jetbrains.annotations.NotNull;

public @interface Sync {
   /**
    * Unique ID for Sync Item, used as column name
    *
    * @return The Unique ID
    */
   @NotNull String field() default "unknown";

   /**
    * Controls the sync start at
    *
    * @return Default is JOIN, QUIT and TIMER
    */
   @NotNull SyncTime[] sync() default {SyncTime.JOIN, SyncTime.QUIT, SyncTime.TIMER};

   /**
    * Mark as this method use for load or save between database
    *
    * @return The SyncType
    */
   @NotNull SyncType type() default SyncType.LOAD;
}
