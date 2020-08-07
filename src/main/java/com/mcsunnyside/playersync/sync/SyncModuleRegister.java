package com.mcsunnyside.playersync.sync;

import java.lang.reflect.Method;

public class SyncModuleRegister {
    private Method method;
    private SyncModule moduleObject;
    private Sync sync;

    public SyncModuleRegister(Method method, SyncModule moduleObject, Sync sync) {
        this.method = method;
        this.moduleObject = moduleObject;
        this.sync = sync;
    }

    public static SyncModuleRegisterBuilder builder() {
        return new SyncModuleRegisterBuilder();
    }

    public Method getMethod() {
        return this.method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public SyncModule getModuleObject() {
        return this.moduleObject;
    }

    public void setModuleObject(SyncModule moduleObject) {
        this.moduleObject = moduleObject;
    }

    public Sync getSync() {
        return this.sync;
    }

    public void setSync(Sync sync) {
        this.sync = sync;
    }

    public String toString() {
        return "SyncModuleRegister(method=" + this.getMethod() + ", moduleObject=" + this.getModuleObject() + ", sync=" + this.getSync() + ")";
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof SyncModuleRegister)) return false;
        final SyncModuleRegister other = (SyncModuleRegister) o;
        if (!other.canEqual(this)) return false;
        final Object this$method = this.getMethod();
        final Object other$method = other.getMethod();
        if (this$method == null ? other$method != null : !this$method.equals(other$method)) return false;
        final Object this$moduleObject = this.getModuleObject();
        final Object other$moduleObject = other.getModuleObject();
        if (this$moduleObject == null ? other$moduleObject != null : !this$moduleObject.equals(other$moduleObject))
            return false;
        final Object this$sync = this.getSync();
        final Object other$sync = other.getSync();
        return this$sync == null ? other$sync == null : this$sync.equals(other$sync);
    }

    protected boolean canEqual(final Object other) {
        return other instanceof SyncModuleRegister;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $method = this.getMethod();
        result = result * PRIME + ($method == null ? 43 : $method.hashCode());
        final Object $moduleObject = this.getModuleObject();
        result = result * PRIME + ($moduleObject == null ? 43 : $moduleObject.hashCode());
        final Object $sync = this.getSync();
        result = result * PRIME + ($sync == null ? 43 : $sync.hashCode());
        return result;
    }

    public static class SyncModuleRegisterBuilder {
        private Method method;
        private SyncModule moduleObject;
        private Sync sync;

        SyncModuleRegisterBuilder() {
        }

        public SyncModuleRegister.SyncModuleRegisterBuilder method(Method method) {
            this.method = method;
            return this;
        }

        public SyncModuleRegister.SyncModuleRegisterBuilder moduleObject(SyncModule moduleObject) {
            this.moduleObject = moduleObject;
            return this;
        }

        public SyncModuleRegister.SyncModuleRegisterBuilder sync(Sync sync) {
            this.sync = sync;
            return this;
        }

        public SyncModuleRegister build() {
            return new SyncModuleRegister(method, moduleObject, sync);
        }

        public String toString() {
            return "SyncModuleRegister.SyncModuleRegisterBuilder(method=" + this.method + ", moduleObject=" + this.moduleObject + ", sync=" + this.sync + ")";
        }
    }
}
