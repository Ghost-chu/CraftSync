package com.mcsunnyside.craftsync.sync;

import org.apache.commons.lang.ArrayUtils;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.logging.Logger;

public class SyncManager {
    private final Set<SyncModuleRegister> registers = new HashSet<>();
    private final Logger logger = Logger.getLogger("CraftSync");

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

    public void callLoad(@NotNull String field, @NotNull Player player, @NotNull String data, @NotNull SyncTime syncTime) {
        registers.forEach(reg -> {
            if (!reg.getSync().field().equals(field)) {
                return;
            }
            if (!ArrayUtils.contains(reg.getSync().sync(), syncTime)) {
                return;
            }
            if (reg.getSync().type() != SyncType.LOAD) {
                return;
            }
            try {
                reg.getMethod().invoke(reg.getModuleObject(), SyncDataContainer.builder().player(player).data(data).build());
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        });
    }

    public void callSave(@NotNull Player player, @NotNull SyncTime syncTime) {
        this.callSave(player, syncTime, null);
    }
    @NotNull
    public Map<String, SyncDataContainer> callSave(@NotNull Player player, @NotNull SyncTime syncTime, @Nullable String field) {
        Map<String, SyncDataContainer> table = new HashMap<>();
        registers.forEach(reg -> {
            if (field != null && !reg.getSync().field().equals(field)) {
                return;
            }
            if (!ArrayUtils.contains(reg.getSync().sync(), syncTime)) {
                return;
            }
            if (reg.getSync().type() != SyncType.SAVE) {
                return;
            }
            try {
                SyncDataContainer container = (SyncDataContainer) reg.getMethod().invoke(reg.getModuleObject(), player);
                table.put(reg.getSync().field(), container);
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        });
        return table;
    }
}
