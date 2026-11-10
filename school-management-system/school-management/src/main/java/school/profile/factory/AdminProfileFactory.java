package school.profile.factory;

import school.common.UserType;
import school.profile.AdminProfile;
import school.profile.UserProfile;

/** Concrete Creator: creates AdminProfile. Pattern: Factory Method. Owner: Developer 1. */
public class AdminProfileFactory extends UserProfileFactory {

    @Override
    protected UserProfile createUserProfile(String id, String fullName) {
        return new AdminProfile(id, fullName);
    }

    @Override
    public UserType supportedType() {
        return UserType.ADMIN;
    }
}
