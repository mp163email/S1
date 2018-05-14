package inject;

import com.google.inject.Guice;
import com.google.inject.Injector;

public class InjectUtilS {

    private static Injector injector = null;

    public static void createInjector () {
        injector = Guice.createInjector(new CoreModel());
    }

    public static <T> T getInjectObj (Class<T> var) {
        return injector.getInstance(var);
    }
}
