package com.olhahn.agreementApp.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.lang.NonNull;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

/**
 * Project: agreement.
 * @author Olha Hnatiuk on 5/3/18
 *
 * AgreementEntity - class, which represents agreement.
 **/
@Entity
@Table(name = "agreement")
@NamedQueries({
        @NamedQuery(
                name = "findActiveAgreements",
                query = "from AgreementEntity where active=true"
        ),
        @NamedQuery(
                name = "findNonactiveAgreements",
                query = "from AgreementEntity where active=false"
        )
})
@SequenceGenerator(name = "agreement_id_seq", sequenceName = "agreement_id_seq")
public class AgreementEntity implements Serializable {

    /**
     * Id of the agreement.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    /**
     * Order number of the agreement.
     */
    @NonNull
    @Column(name = "order_number", unique = true)
    private int number;

    /**
     * Date from which agreements starts.
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM/dd/yyyy")
    @NonNull
    @Column(name = "date_from", unique = true)
    private Date dateFrom = Calendar.getInstance().getTime();

    /**
     * Date of the agreement's end.
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM/dd/yyyy")
    @NonNull
    @Column(name = "to_date")
    private Date dateTo = Calendar.getInstance().getTime();

    /**
     * Value for which agreement was signed.
     */
    @NonNull
    @Column(name = "amount")
    private float amount;

    /**
     * Amount type (netto, brutto).
     */
    @NonNull
    @Column(name = "amount_type")
    private String amountType = "NET";

    /**
     * Period (month, year..).
     */
    @NonNull
    @Column(name = "amount_period")
    private String period = "MONTH";

    /**
     * Authorization percent.
     */
    @Column(name = "authorization_percent")
    private float percent;

    /**
     * Boolean that shows if agreement is active.
     */
    @NonNull
    @Column(name = "active", columnDefinition = "tinyint")
    private boolean active = true;

    /**
     * Number of the request.
     */
    @NonNull
    @Column(name = "request", unique = true)
    private int request;

    /**
     * Agreement's system.
     */
    @NonNull
    @ManyToOne
    private SystemEntity system;

    /**
     * Constructor without arguments, do nothing.
     */
    public AgreementEntity() { }


    // Getters and setters

    /**
     * Getter for field id.
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * Getter for field active.
     * @return active
     */
    public boolean getActive() {
        return active;
    }

    /**
     * Getter for field dateFrom.
     * @return gate when agreement starts
     */
    public Date getDateFrom() {
        return new Date(dateFrom.getTime());
    }

    /**
     * Getter for field dateTo.
     * @return gate when agreement ends
     */
    public Date getDateTo() {
        return new Date(dateTo.getTime());
    }

    /**
     * Getter for field amount.
     * @return amount field
     */
    public float getAmount() {
        return amount;
    }

    /**
     * Getter for field percent.
     * @return percent field
     */
    public float getPercent() {
        return percent;
    }

    /**
     * Getter for field number.
     * @return number field
     */
    public int getNumber() {
        return number;
    }

    /**
     * Getter for field request.
     * @return request field
     */
    public int getRequest() {
        return request;
    }

    /**
     * Getter for field amountType.
     * @return amountType field
     */
    public String getAmountType() {
        return amountType;
    }

    /**
     * Getter for field period.
     * @return period field
     */
    public String getPeriod() {
        return period;
    }

    /**
     * Getter for field system.
     * @return system field
     */
    public SystemEntity getSystem() {
        return system;
    }

    /**
     * Setter for field active.
     * @param active new value for the field
     */
    public void setActive(boolean active) {
        this.active = active;
    }

    /**
     * Setter for field amount.
     * @param amount new value for the field
     */
    public void setAmount(float amount) {
        this.amount = amount;
    }

    /**
     * Setter for field amountType.
     * @param amountType new value for the field
     */
    public void setAmountType(String amountType) {
        this.amountType = amountType;
    }

    /**
     * Setter for field dateFrom.
     * @param dateFrom new value for the field
     */
    public void setDateFrom(Date dateFrom) {
        this.dateFrom = new Date(dateFrom.getTime());
    }

    /**
     * Setter for field dateTo.
     * @param dateTo new value for the field
     */
    public void setDateTo(Date dateTo) {
        this.dateTo = new Date(dateTo.getTime());
    }

    /**
     * Setter for field number.
     * @param number new value for the field
     */
    public void setNumber(int number) {
        this.number = number;
    }

    /**
     * Setter for field percent.
     * @param percent new value for the field
     */
    public void setPercent(float percent) {
        this.percent = percent;
    }

    /**
     * Setter for field period.
     * @param period new value for the field
     */
    public void setPeriod(String period) {
        this.period = period;
    }

    /**
     * Setter for field request.
     * @param request new value for the field
     */
    public void setRequest(int request) {
        this.request = request;
    }

    /**
     * Setter for field system.
     * @param system new value for the field
     */
    public void setSystem(SystemEntity system) {
        this.system = system;
    }

    /**
     * Setter for field id.
     * @param id new value for the field
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * toString() function for class Agreement entity.
     * @return string consisting from order number
     */
    @Override
    public String toString() {
        return "Order number: " + number;
    }
}
