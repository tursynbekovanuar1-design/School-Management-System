package school.profile.factory;

import school.common.UserType;
import school.profile.StudentProfile;
import school.profile.UserProfile;

/** Concrete Creator: creates StudentProfile. Pattern: Factory Method. Owner: Developer 1. */
public class StudentProfileFactory extends UserProfileFactory {

    @Override
    protected UserProfile createUserProfile(String id, String fullName) {
        return new StudentProfile(id, fullName);
    }

    @Override
    public UserType supportedType() {
        return UserType.STUDENT;
    }
}
