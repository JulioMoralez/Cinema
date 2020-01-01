package pack.config;

import pack.model.Role;

import java.util.ArrayList;
import java.util.List;

public class Consts {

    public static final Role ROLE_USER = new Role(1,"ROLE_USER");
    public static final Role ROLE_ADMIN = new Role(2,"ROLE_ADMIN");
    public static final Role ROLE_MODERATOR = new Role(3,"ROLE_MODERATOR");
    public static final List<Role> ROLES;

    static {
        ROLES = new ArrayList<>();
        ROLES.add(ROLE_USER);
        ROLES.add(ROLE_ADMIN);
        ROLES.add(ROLE_MODERATOR);
    }

}
