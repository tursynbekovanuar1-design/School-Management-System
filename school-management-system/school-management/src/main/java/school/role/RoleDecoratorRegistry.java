package school.role;

import school.profile.UserProfile;

import java.util.EnumMap;
import java.util.Map;
import java.util.function.UnaryOperator;

/**
 * Maps a RoleType to the decorator constructor, so callers never use "new XxxRole(...)" directly.
 * OCP: a new role = a new decorator class + one register(...) call.
 *
 * Pattern: Decorator (assembly helper)
 * Owner:   Developer 1
 */
public class RoleDecoratorRegistry {

    private final Map<RoleType, UnaryOperator<UserProfile>> decorators = new EnumMap<>(RoleType.class);

    public RoleDecoratorRegistry() {
        register(RoleType.HEAD_TEACHER, HeadTeacherRole::new);
        register(RoleType.CLUB_LEADER, ClubLeaderRole::new);
        register(RoleType.LAB_ASSISTANT, LabAssistantRole::new);
    }

    public final void register(RoleType type, UnaryOperator<UserProfile> decorator) {
        decorators.put(type, decorator);
    }

    public UserProfile decorate(UserProfile profile, RoleType type) {
        UnaryOperator<UserProfile> decorator = decorators.get(type);
        if (decorator == null) {
            throw new IllegalArgumentException("No decorator registered for " + type);
        }
        return decorator.apply(profile);
    }
}
