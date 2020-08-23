package net.minestom.server.extras.selfmodification.mixins;

import net.minestom.server.extras.selfmodification.MinestomOverwriteClassLoader;
import org.spongepowered.asm.service.IClassProvider;

import java.net.URL;

public class MinestomClassProvider implements IClassProvider {
    private final MinestomOverwriteClassLoader classLoader;

    public MinestomClassProvider(MinestomOverwriteClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    @Override
    public URL[] getClassPath() {
        return classLoader.getURLs();
    }

    @Override
    public Class<?> findClass(String name) throws ClassNotFoundException {
        return classLoader.findClass(name);
    }

    @Override
    public Class<?> findClass(String name, boolean initialize) throws ClassNotFoundException {
        return Class.forName(name, initialize, Thread.currentThread().getContextClassLoader());
    }

    @Override
    public Class<?> findAgentClass(String name, boolean initialize) throws ClassNotFoundException {
        return Class.forName(name, initialize, classLoader);
    }
}
