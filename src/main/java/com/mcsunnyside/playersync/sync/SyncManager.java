package com.mcsunnyside.playersync.sync;

import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

public class SyncManager {
    private final Set<SyncModuleRegister> registers = new HashSet<>();
    private final Logger logger = Logger.getLogger("PlayerSync");

    public void register(@NotNull SyncModule module) {
        for (Method declaredMethod : module.getClass().getDeclaredMethods()) {
            if (declaredMethod.getAnnotation(Sync.class) == null) {
                continue;
            }
            Sync sync = declaredMethod.getAnnotation(Sync.class);
            registers.add(SyncModuleRegister.builder().method(declaredMethod).moduleObject(module).sync(sync).build());
            logger.info("Successfully registered: " + sync.field() + " for Type=" + sync.type() + " SyncTimes="
                    + Arrays.toString(sync.sync()));
        }
    }
}
