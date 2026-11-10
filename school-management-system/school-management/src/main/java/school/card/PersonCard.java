package school.card;

import school.grade.Grade;
import school.profile.UserProfile;

import java.util.List;
import java.util.Set;

/**
 * Product of the Builder: an immutable, complex student/teacher card.
 *
 * Pattern: Builder (Product)
 * Owner:   Developer 1
 */
public record PersonCard(UserProfile profile,
                         ContactInfo contact,
                         List<Grade> grades,
                         List<Document> documents,
                         Set<Benefit> benefits) {

    public String summary() {
        StringBuilder text = new StringBuilder();
        text.append("   Profile  : ").append(profile.describe()).append(System.lineSeparator());
        text.append("   Contacts : ").append(contact.email()).append(", ")
                .append(contact.phone()).append(", ").append(contact.address()).append(System.lineSeparator());
        text.append("   Grades   : ");
        grades.forEach(g -> text.append(g.subject()).append('=').append(g.score()).append(' '));
        text.append(System.lineSeparator());
        text.append("   Documents: ");
        documents.forEach(d -> text.append(d.title()).append(" (").append(d.category()).append(") "));
        text.append(System.lineSeparator());
        text.append("   Benefits : ").append(benefits);
        return text.toString();
    }
}
