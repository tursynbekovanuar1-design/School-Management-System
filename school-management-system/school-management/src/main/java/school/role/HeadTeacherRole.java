package school.role;

import school.common.Permission;
import school.profile.UserProfile;

import java.util.EnumSet;
import java.util.Set;

/** Concrete Decorator: head teacher. Pattern: Decorator. Owner: Developer 1. */
public class HeadTeacherRole extends RoleDecorator {

    public HeadTeacherRole(UserProfile wrapped) {
        super(wrapped);
    }

    @Override
    protected String roleName() {
        return "HEAD_TEACHER";
    }

    @Override
    protected Set<Permission> grantedPermissions() {
        return EnumSet.of(Permission.MANAGE_USERS, Permission.VIEW_ALL_GRADES, Permission.PUBLISH_REPORTS);
    }
}
