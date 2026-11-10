package school.profile.factory;

import school.common.UserType;
import school.profile.TeacherProfile;
import school.profile.UserProfile;

/** Concrete Creator: creates TeacherProfile. Pattern: Factory Method. Owner: Developer 1. */
public class TeacherProfileFactory extends UserProfileFactory {

    @Override
    protected UserProfile createUserProfile(String id, String fullName) {
        return new TeacherProfile(id, fullName);
    }

    @Override
    public UserType supportedType() {
        return UserType.TEACHER;
    }
}
