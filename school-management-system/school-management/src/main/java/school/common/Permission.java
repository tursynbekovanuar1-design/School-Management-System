package school.common;

/** Fine-grained permissions. Roles (Decorator) and base profiles grant subsets of these. */
public enum Permission {
    VIEW_OWN_GRADES,
    VIEW_ALL_GRADES,
    EDIT_GRADES,
    PUBLISH_REPORTS,
    MANAGE_USERS,
    MANAGE_CLUB,
    USE_LAB_EQUIPMENT
}
