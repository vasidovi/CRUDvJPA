/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.json.bind.annotation.JsonbTransient;
import javax.json.bind.annotation.JsonbTypeAdapter;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import util.JSONDateAdapter;

/**
 *
 * @author Dovile
 */
@Entity
@Table(name = "persons")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Persons.findAll", query = "SELECT p FROM Persons p")
    , @NamedQuery(name = "Persons.findById", query = "SELECT p FROM Persons p WHERE p.id = :id")
    , @NamedQuery(name = "Persons.findByFirstName", query = "SELECT p FROM Persons p WHERE p.firstName = :firstName")
    , @NamedQuery(name = "Persons.findByLastName", query = "SELECT p FROM Persons p WHERE p.lastName = :lastName")
    , @NamedQuery(name = "Persons.findByBirthDate", query = "SELECT p FROM Persons p WHERE p.birthDate = :birthDate")
    , @NamedQuery(name = "Persons.findBySalary", query = "SELECT p FROM Persons p WHERE p.salary = :salary")})
public class Persons implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private String firstName;
    private String lastName;
    private Date birthDate;
//  @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    private BigDecimal salary;
    private List<Addresses> addressesList;
    private List<Contacts> contactsList;

    public Persons() {
    }

    public Persons(Integer id) {
        this.id = id;
    }

    public Persons(Integer id, String firstName, String lastName, Date birthDate, BigDecimal salary, Object birthdate) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.salary = salary;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
//    @JsonbProperty(nillable = true)
    @Column(name = "id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "first_name")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "last_name")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

//    @JsonbDateFormat(value = "YYYY-MM-DD", locale = "Locale.ENGLISH")
//    @JsonbTypeSerializer(JSONDateSerializer.class)
//    @JsonbTypeDeserializer(JSONDateDeserializer.class)
    @Column(name = "birth_date")
    @Temporal(TemporalType.DATE)
    @JsonbTypeAdapter(JSONDateAdapter.class)
    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        if (birthDate == null) {
            this.birthDate = null;
        } else {

            this.birthDate = new Date(birthDate.getTime());
        }

    }

    @Column(name = "salary")
//  @JsonbNumberFormat("#0.00")
//  @JsonbTypeDeserializer(JSONBDDeserializer.class)
    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        System.out.println("setting salary to " + salary);
        this.salary = salary;
    }

    @XmlTransient
    @JsonbTransient
    @OneToMany(mappedBy = "personId")
    public List<Addresses> getAddressesList() {
        return addressesList;
    }

    public void setAddressesList(List<Addresses> addressesList) {
        this.addressesList = addressesList;
    }

    @XmlTransient
    @JsonbTransient
    @OneToMany(mappedBy = "personId")
    public List<Contacts> getContactsList() {
        return contactsList;
    }

    public void setContactsList(List<Contacts> contactsList) {
        this.contactsList = contactsList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Persons)) {
            return false;
        }
        Persons other = (Persons) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Persons{" + "id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", birthDate=" + birthDate + ", salary=" + salary + ", addressesList=" + addressesList + ", contactsList=" + contactsList;
    }

}
