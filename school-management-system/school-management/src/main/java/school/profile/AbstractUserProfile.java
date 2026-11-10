package school.profile;

import school.common.Permission;

import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * Shared state and behaviour of the concrete profiles.
 *
 * Pattern: Factory Method (base of Concrete Products)
 * Owner:   Developer 1
 */
public abstract class AbstractUserProfile implements UserProfile {

    private final String id;
    private final String fullName;

    protected AbstractUserProfile(String id, String fullName) {
        this.id = Objects.requireNonNull(id, "id");
        this.fullName = Objects.requireNonNull(fullName, "fullName");
    }

    /** Permissions every user of this concrete type has by default. */
    protected abstract Set<Permission> basePermissions();

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getFullName() {
        return fullName;
    }

    @Override
    public Set<Permission> getPermissions() {
        return Collections.unmodifiableSet(EnumSet.copyOf(basePermissions()));
    }

    @Override
    public List<String> getRoleNames() {
        return List.of(getType().name());
    }
}
