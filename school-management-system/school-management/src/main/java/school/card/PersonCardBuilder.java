package school.card;

import school.grade.Grade;
import school.profile.UserProfile;

import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * Builder: assembles a PersonCard step by step (contacts, grades, documents, benefits)
 * and validates the mandatory parts in build().
 *
 * Pattern: Builder (Builder)
 * Owner:   Developer 1
 */
public class PersonCardBuilder {

    private final UserProfile profile;
    private ContactInfo contact;
    private final List<Grade> grades = new ArrayList<>();
    private final List<Document> documents = new ArrayList<>();
    private final Set<Benefit> benefits = EnumSet.noneOf(Benefit.class);

    public PersonCardBuilder(UserProfile profile) {
        this.profile = Objects.requireNonNull(profile, "profile");
    }

    public PersonCardBuilder withContact(ContactInfo contact) {
        this.contact = contact;
        return this;
    }

    public PersonCardBuilder withGrades(Collection<Grade> newGrades) {
        grades.addAll(newGrades);
        return this;
    }

    public PersonCardBuilder addDocument(Document document) {
        documents.add(document);
        return this;
    }

    public PersonCardBuilder addBenefit(Benefit benefit) {
        benefits.add(benefit);
        return this;
    }

    public PersonCard build() {
        if (contact == null) {
            throw new IllegalStateException("Contact information is mandatory for a card");
        }
        return new PersonCard(profile, contact, List.copyOf(grades), List.copyOf(documents), Set.copyOf(benefits));
    }
}
