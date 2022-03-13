package com.github.zelmothedragon.whiteapp.model.entity;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.spi.CDI;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.persistence.metamodel.SingularAttribute;
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
public class Customer extends Model {

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
    @NotBlank
    @Size(max = 255)
    @Column(name = "given_name")
    private String givenName;

    /**
     * Nom de famille.
     */
    @NotBlank
    @Size(max = 255)
    @Column(name = "family_name")
    private String familyName;

    /**
     * Date de naissance.
     */
    @Past
    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "picture")
    private String picture;

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

    /**
     * Construire une instance factice vide. Ne doit jamais être persistée.
     *
     * @return Une instance factice vide.
     */
    private static Customer ofEmpty() {
        var entity = Customer.of();
        entity.setId(UUID.fromString("00000000-0000-0000-0000-000000000000"));
        entity.setVersion(0L);
        entity.setGivenName("");
        entity.setFamilyName("");
        entity.setEmail("");
        entity.setBirthDate(LocalDate.parse("0000-01-01"));
        return entity;
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
    // Lecture
    // ------------------------
    /**
     * Obtenir le nombre d'occurence enregistré.
     *
     * @return Le nombre d'occurence
     */
    public static long size() {
        var empty = Customer.ofEmpty();
        return empty.count();
    }

    /**
     * Indiquer si aucune occurence existe.
     *
     * @return La valeur <code>true</code> si aucune occurence existe, sinon la
     * valeur <code>false</code> est retournée
     */
    public static boolean isEmpty() {
        var empty = Customer.ofEmpty();
        return empty.count() == 0;
    }

    /**
     * Chercher toutes les entités enregistrés.
     *
     * @return Une liste des entités persistées
     */
    public static List<Customer> find() {
        var empty = Customer.ofEmpty();
        return empty.select();
    }

    /**
     * Rechercher une entité en fonction de la clef primaire.
     *
     * @param id Clef primaire
     * @return Une option contenant ou non l'entité correspondante à la clef
     * primaire
     */
    public static Optional<Customer> find(final UUID id) {
        var empty = Customer.ofEmpty();
        return empty.selectWhere(id);
    }

    /**
     * Vérifier l'existance de l'adresse de courriel dans la persistance.
     *
     * @param email Adresse de courriel
     * @return La valeur <code>true</code> si cette adresse est déjà utilisée,
     * sinon la valeur <code>false</code> est retournée
     */
    public static boolean emailExists(final String email) {
        var empty = Customer.ofEmpty();
        return empty.countWhere(Customer_.email, email) == 1L;
    }

    /**
     * Rechercher des entités.
     *
     * @param column Nom de la colonne de recherche
     * @param value Valeur
     * @return La liste des entités liée à la recherche
     */
    public static List<Customer> find(final SingularAttribute<Customer, ?> column, final Object value) {
        var empty = Customer.ofEmpty();
        return empty.selectWhere(column, value);
    }

    // ------------------------
    // Accesseurs & Mutateurs
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

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

}
