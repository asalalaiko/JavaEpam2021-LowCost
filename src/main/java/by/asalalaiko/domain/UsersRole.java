package by.asalalaiko.domain;

import java.util.Arrays;

public enum UsersRole {
    ADMIN, USER;

    public static UsersRole getByOrdinal(int ordinal) {
        return Arrays.asList(UsersRole.values()).stream().filter(pr -> ordinal == pr.ordinal()).findFirst().get();
    }

}
