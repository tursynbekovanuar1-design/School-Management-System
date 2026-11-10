package school.profile;

import school.common.Permission;
import school.common.UserType;

import java.util.EnumSet;
import java.util.Set;

/** Concrete Product: administrator. Pattern: Factory Method. Owner: Developer 1. */
public class AdminProfile extends AbstractUserProfile {

    public AdminProfile(String id, String fullName) {
        super(id, fullName);
    }

    @Override
    public UserType getType() {
        return UserType.ADMIN;
    }

    @Override
    protected Set<Permission> basePermissions() {
        return EnumSet.of(Permission.MANAGE_USERS, Permission.VIEW_ALL_GRADES, Permission.PUBLISH_REPORTS);
    }
}
