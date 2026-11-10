package school.profile.factory;

import school.common.UserType;
import school.profile.UserProfile;

import java.util.EnumMap;
import java.util.Map;

/**
 * Registry that picks the right Creator by UserType.
 * OCP: a new user type = a new factory + one register(...) call; no existing code changes.
 *
 * Pattern: Factory Method (factory lookup)
 * Owner:   Developer 1
 */
public class UserProfileFactoryProvider {

    private final Map<UserType, UserProfileFactory> factories = new EnumMap<>(UserType.class);

    public UserProfileFactoryProvider() {
        register(new StudentProfileFactory());
        register(new TeacherProfileFactory());
        register(new AdminProfileFactory());
    }

    public final void register(UserProfileFactory factory) {
        factories.put(factory.supportedType(), factory);
    }

    public UserProfile create(UserType type, String id, String fullName) {
        UserProfileFactory factory = factories.get(type);
        if (factory == null) {
            throw new IllegalArgumentException("No factory registered for " + type);
        }
        return factory.createProfile(id, fullName);
    }
}
