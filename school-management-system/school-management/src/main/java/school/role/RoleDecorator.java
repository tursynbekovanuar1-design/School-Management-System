package school.role;

import school.common.Permission;
import school.common.UserType;
import school.profile.UserProfile;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * Base Decorator: wraps a UserProfile and adds one role with extra permissions.
 * Decorators can be stacked: new LabAssistantRole(new ClubLeaderRole(student)).
 *
 * Pattern: Decorator (Base Decorator)
 * Owner:   Developer 1
 */
public abstract class RoleDecorator implements UserProfile {

    private final UserProfile wrapped;

    protected RoleDecorator(UserProfile wrapped) {
        this.wrapped = Objects.requireNonNull(wrapped, "wrapped");
    }

    protected abstract String roleName();

    protected abstract Set<Permission> grantedPermissions();

    @Override
    public String getId() {
        return wrapped.getId();
    }

    @Override
    public String getFullName() {
        return wrapped.getFullName();
    }

    @Override
    public UserType getType() {
        return wrapped.getType();
    }

    @Override
    public Set<Permission> getPermissions() {
        Set<Permission> result = EnumSet.noneOf(Permission.class);
        result.addAll(wrapped.getPermissions());
        result.addAll(grantedPermissions());
        return Collections.unmodifiableSet(result);
    }

    @Override
    public List<String> getRoleNames() {
        List<String> roles = new ArrayList<>(wrapped.getRoleNames());
        roles.add(roleName());
        return Collections.unmodifiableList(roles);
    }
}
