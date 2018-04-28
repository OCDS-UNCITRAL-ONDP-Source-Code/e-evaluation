package com.procurement.evaluation.model.dto.ocds;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.util.HashSet;
import java.util.Set;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "name",
        "identifier",
        "address",
        "additionalIdentifiers",
        "contactPoint"
})
public class OrganizationReference {

    @JsonProperty("id")
    @NotNull
    private String id;

    @Size(min = 1)
    @NotNull
    @JsonProperty("name")
    private final String name;

    @Valid
    @JsonProperty("identifier")
    private final Identifier identifier;

    @Valid
    @JsonProperty("additionalIdentifiers")
    private final Set<Identifier> additionalIdentifiers;

    @Valid
    @JsonProperty("address")
    private final Address address;

    @Valid
    @JsonProperty("contactPoint")
    private final ContactPoint contactPoint;

    @JsonCreator
    public OrganizationReference(@JsonProperty("id") final String id,
                                 @JsonProperty("name") final String name,
                                 @JsonProperty("identifier") final Identifier identifier,
                                 @JsonProperty("address") final Address address,
                                 @JsonProperty("additionalIdentifiers") final HashSet<Identifier> additionalIdentifiers,
                                 @JsonProperty("contactPoint") final ContactPoint contactPoint) {
        this.id = id;
        this.name = name;
        this.identifier = identifier;
        this.address = address;
        this.additionalIdentifiers = additionalIdentifiers;
        this.contactPoint = contactPoint;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(id)
                .append(name)
                .append(identifier)
                .append(address)
                .append(additionalIdentifiers)
                .append(contactPoint)
                .toHashCode();
    }

    @Override
    public boolean equals(final Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof OrganizationReference)) {
            return false;
        }
        final OrganizationReference rhs = (OrganizationReference) other;
        return new EqualsBuilder().append(id, rhs.id)
                .append(name, rhs.name)
                .append(identifier, rhs.identifier)
                .append(address, rhs.address)
                .append(additionalIdentifiers, rhs.additionalIdentifiers)
                .append(contactPoint, rhs.contactPoint)
                .isEquals();
    }
}