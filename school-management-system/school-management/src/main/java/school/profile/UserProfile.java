package school.profile;

import school.common.Permission;
import school.common.UserType;

import java.util.List;
import java.util.Set;

/**
 * Common abstraction of any user profile.
 *
 * Pattern: Decorator (Component interface) + Factory Method (Product interface)
 * Owner:   Developer 1
 */
public interface UserProfile {

    String getId();

    String getFullName();

    UserType getType();

    Set<Permission> getPermissions();

    List<String> getRoleNames();

    default boolean hasPermission(Permission permission) {
        return getPermissions().contains(permission);
    }

    default String describe() {
        return String.format("%s (%s) | roles=%s | permissions=%s",
                getFullName(), getId(), getRoleNames(), getPermissions());
    }
}
