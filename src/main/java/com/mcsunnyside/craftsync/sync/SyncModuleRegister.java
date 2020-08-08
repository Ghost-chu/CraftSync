package com.mcsunnyside.craftsync.sync;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Method;

@AllArgsConstructor
@Builder
@Data
@EqualsAndHashCode

public class SyncModuleRegister {
    @NotNull
    private Method method;
    @NotNull
    private SyncModule moduleObject;
    @NotNull
    private Sync sync;
}
