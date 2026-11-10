package school.profile;

import school.common.Permission;
import school.common.UserType;

import java.util.EnumSet;
import java.util.Set;

/** Concrete Product: student. Pattern: Factory Method. Owner: Developer 1. */
public class StudentProfile extends AbstractUserProfile {

    public StudentProfile(String id, String fullName) {
        super(id, fullName);
    }

    @Override
    public UserType getType() {
        return UserType.STUDENT;
    }

    @Override
    protected Set<Permission> basePermissions() {
        return EnumSet.of(Permission.VIEW_OWN_GRADES);
    }
}
