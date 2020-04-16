package fr.moselleacademy.mvc.model.entity;

import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.spi.CDI;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

/**
 * Un client.
 *
 * @author MOSELLE Maxime
 */
@Dependent
@Entity
@Table(name = "customer")
public class Customer extends AbstractModel {

    /**
     * Numéro de série.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Clef primaire.
     */
    @Id
    @Column(name = "id", nullable = false, columnDefinition = "VARCHAR(36)")
    private UUID id;

    /**
     * Version.
     */
    @Version
    @Column(name = "version", nullable = false)
    private Long version;

    /**
     * Adresse de courriel. Clef fonctionnelle.
     */
    @Email
    @NotBlank
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    /**
     * Prénom.
     */
    @Size(max = 255)
    @Column(name = "given_name")
    private String givenName;

    /**
     * Nom de famille.
     */
    @Size(max = 255)
    @Column(name = "family_name")
    private String familyName;

    /**
     * Date de naissance.
     */
    @Past
    @Column(name = "birth_date")
    private LocalDate birthDate;

    /**
     * Constructeur par défaut. Requis pour le fonctionnement des technologies
     * de Java EE.
     */
    public Customer() {
        this.id = UUID.randomUUID();
        this.version = 0L;
        // Ne pas appeler explicitement
    }

    /**
     * Construire une nouvelle instance de l'entité.
     *
     * @return Nouvelle instance
     */
    public static Customer of() {
        return CDI
                .current()
                .select(Customer.class)
                .get();
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(final Object obj) {
        boolean eq;
        if (this == obj) {
            eq = true;
        } else if (!(obj instanceof Customer)) {
            eq = false;
        } else {
            var other = (Customer) obj;
            eq = Objects.equals(id, other.id);
        }
        return eq;
    }

    @Override
    public String toString() {
        return String.format(
                "%s[%s]{version=%s}",
                getClass().getName(),
                id,
                version
        );
    }

    // ------------------------
    // Accesseurs & Muttateurs
    // ------------------------
    public UUID getId() {
        return id;
    }

    public void setId(final UUID id) {
        this.id = id;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(final Long version) {
        this.version = version;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(final String givenName) {
        this.givenName = givenName;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(final String familyName) {
        this.familyName = familyName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(final LocalDate birthDate) {
        this.birthDate = birthDate;
    }

}
