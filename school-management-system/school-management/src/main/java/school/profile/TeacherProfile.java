package school.profile;

import school.common.Permission;
import school.common.UserType;

import java.util.EnumSet;
import java.util.Set;

/** Concrete Product: teacher. Pattern: Factory Method. Owner: Developer 1. */
public class TeacherProfile extends AbstractUserProfile {

    public TeacherProfile(String id, String fullName) {
        super(id, fullName);
    }

    @Override
    public UserType getType() {
        return UserType.TEACHER;
    }

    @Override
    protected Set<Permission> basePermissions() {
        return EnumSet.of(Permission.VIEW_ALL_GRADES, Permission.EDIT_GRADES, Permission.PUBLISH_REPORTS);
    }
}
