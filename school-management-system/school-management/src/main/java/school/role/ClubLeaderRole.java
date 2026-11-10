package school.role;

import school.common.Permission;
import school.profile.UserProfile;

import java.util.EnumSet;
import java.util.Set;

/** Concrete Decorator: club leader. Pattern: Decorator. Owner: Developer 1. */
public class ClubLeaderRole extends RoleDecorator {

    public ClubLeaderRole(UserProfile wrapped) {
        super(wrapped);
    }

    @Override
    protected String roleName() {
        return "CLUB_LEADER";
    }

    @Override
    protected Set<Permission> grantedPermissions() {
        return EnumSet.of(Permission.MANAGE_CLUB);
    }
}
