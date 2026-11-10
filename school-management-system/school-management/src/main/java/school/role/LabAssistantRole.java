package school.role;

import school.common.Permission;
import school.profile.UserProfile;

import java.util.EnumSet;
import java.util.Set;

/** Concrete Decorator: lab assistant. Pattern: Decorator. Owner: Developer 1. */
public class LabAssistantRole extends RoleDecorator {

    public LabAssistantRole(UserProfile wrapped) {
        super(wrapped);
    }

    @Override
    protected String roleName() {
        return "LAB_ASSISTANT";
    }

    @Override
    protected Set<Permission> grantedPermissions() {
        return EnumSet.of(Permission.USE_LAB_EQUIPMENT);
    }
}
