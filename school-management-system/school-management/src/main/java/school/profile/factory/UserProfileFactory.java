package school.profile.factory;

import school.common.UserType;
import school.profile.UserProfile;

/**
 * Creator of the Factory Method pattern.
 * The public template method validates input; subclasses decide WHICH profile to instantiate.
 *
 * Pattern: Factory Method (Creator)
 * Owner:   Developer 1
 */
public abstract class UserProfileFactory {

    /** Template method: common validation + delegation to the factory method. */
    public final UserProfile createProfile(String id, String fullName) {
        requireNotBlank(id, "id");
        requireNotBlank(fullName, "fullName");
        return createUserProfile(id, fullName);
    }

    /** The Factory Method itself. */
    protected abstract UserProfile createUserProfile(String id, String fullName);

    /** Which user type this factory is responsible for (used for registration). */
    public abstract UserType supportedType();

    private static void requireNotBlank(String value, String fieldName) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(fieldName + " must not be blank");
        }
    }
}
