package coder.jdev.utils;

import coder.jdev.exceptions.HostelManagementException;
import coder.jdev.exceptions.PageableException;
import coder.jdev.exceptions.users.StudentException;
import lombok.experimental.UtilityClass;
import org.apache.tomcat.util.http.parser.Host;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;

@UtilityClass
public class Utils {

    public static void checkPageSize(final int pageSize, final int maxPageSize) {
        if (pageSize > maxPageSize) {
            throwException("page size %d is exceeded max page size %d".formatted(pageSize, maxPageSize), PageableException.class);
        }
    }

    public static void throwException(final String message, final Class<? extends HostelManagementException> throwableClazz) {
        try {
            throw throwableClazz.getConstructor(String.class).newInstance(message);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                 NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    public static Supplier<RuntimeException> userIsNotExistWithIdSupplier(final long id) {
        return runtimeExceptionSupplier("user is not exist with id %d".formatted(id), StudentException.class);
    }

    public static Supplier<RuntimeException> runtimeExceptionSupplier(final String message, final Class<? extends HostelManagementException> throwableClazz) {
        return () -> {
            try {
                return throwableClazz.getConstructor(String.class).newInstance(message);
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
        };
    }

    public static List<String> getErrorMessageList(Throwable throwable) {
        final List<String> list = new ArrayList<>();
        while (throwable != null) {
            if (Objects.nonNull(throwable.getMessage()) || not(throwable.getMessage().isBlank())) {
                list.add(throwable.getMessage());
            }
            throwable = throwable.getCause();
        }
        return list;
    }

    public static boolean not(final boolean condition) {
        return !condition;
    }
}
