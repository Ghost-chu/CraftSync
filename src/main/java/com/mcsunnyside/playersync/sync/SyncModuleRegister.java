package com.mcsunnyside.playersync.sync;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.lang.reflect.Method;

@AllArgsConstructor
@Builder
@Data
@EqualsAndHashCode
public class SyncModuleRegister {
    private Method method;
    private SyncModule moduleObject;
    private Sync sync;
}
