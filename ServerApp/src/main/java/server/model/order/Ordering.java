package server.model.order;

import server.model.denomination.Denomination;
import server.model.fund.Fund;
import server.model.user.User;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table
public class Ordering implements Serializable, Comparable {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.TABLE)
    private int id;

    @Column(nullable = false)
    private LocalDateTime dateOrderCreated;

    @Column(nullable = true)
    private LocalDateTime dateClientsCome;

    @Column(nullable = true)
    private int amountOfPeople;

    @Column(nullable = true)
    private String description;

    @Column(nullable = true)
    private double advancePayment;

    @ManyToOne()
    @JoinColumn(name = "user_taken_id", referencedColumnName = "login")
    private User whoTakenOrder;


    @ManyToOne()
    @JoinColumn(name = "user_serving_id", referencedColumnName = "login")
    private User whoServesOrder;


    @OneToOne(targetEntity = Fund.class, mappedBy = "order", cascade = {CascadeType.REMOVE},fetch = FetchType.EAGER)
    private Fund fund;


    @Column
    @Enumerated
    private OrderType type;


    @OneToMany(mappedBy = "order", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<Denomination> denominations;

    public Ordering() {
    }

    public double getAdvancePayment() {
        return advancePayment;
    }

    public void setAdvancePayment(double advancePayment) {
        this.advancePayment = advancePayment;
    }

    public int getAmountOfPeople() {
        return amountOfPeople;
    }

    public void setAmountOfPeople(int amountOfPeople) {
        this.amountOfPeople = amountOfPeople;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDateOrderCreated() {
        return dateOrderCreated;
    }

    public void setDateOrderCreated(LocalDateTime dateOrderCreated) {
        this.dateOrderCreated = dateOrderCreated;
    }

    public LocalDateTime getDateClientsCome() {
        return dateClientsCome;
    }

    public void setDateClientsCome(LocalDateTime dateClientsCome) {
        this.dateClientsCome = dateClientsCome;
    }

    public User getWhoTakenOrder() {
        return whoTakenOrder;
    }

    public void setWhoTakenOrder(User whoTakenOrder) {
        this.whoTakenOrder = whoTakenOrder;
    }

    public User getWhoServesOrder() {
        return whoServesOrder;
    }

    public void setWhoServesOrder(User whoServesOrder) {
        this.whoServesOrder = whoServesOrder;
    }

    public Fund getFund() {
        return fund;
    }

    public void setFund(Fund fund) {
        this.fund = fund;
    }

    public OrderType getType() {
        return type;
    }

    public void setType(OrderType type) {
        this.type = type;
    }

    public List<Denomination> getDenominations() {
        return denominations;
    }

    public void setDenominations(List<Denomination> denominations) {
        this.denominations = denominations;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Ordering ordering = (Ordering) o;

        if (id != ordering.id) return false;
        if (amountOfPeople != ordering.amountOfPeople) return false;
        if (Double.compare(ordering.advancePayment, advancePayment) != 0) return false;
        if (dateOrderCreated != null ? !dateOrderCreated.equals(ordering.dateOrderCreated) : ordering.dateOrderCreated != null)
            return false;
        if (dateClientsCome != null ? !dateClientsCome.equals(ordering.dateClientsCome) : ordering.dateClientsCome != null)
            return false;
        if (description != null ? !description.equals(ordering.description) : ordering.description != null)
            return false;
        if (whoTakenOrder != null ? !whoTakenOrder.equals(ordering.whoTakenOrder) : ordering.whoTakenOrder != null)
            return false;
        if (whoServesOrder != null ? !whoServesOrder.equals(ordering.whoServesOrder) : ordering.whoServesOrder != null)
            return false;
        if (fund != null ? !fund.equals(ordering.fund) : ordering.fund != null) return false;
        if (type != ordering.type) return false;
        return !(denominations != null ? !denominations.equals(ordering.denominations) : ordering.denominations != null);

    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return "OrderingPanel{" +
                "id=" + id +
                ", dateOrderCreated=" + dateOrderCreated +
                ", dateClientsCome=" + dateClientsCome +
                ", amountOfPeople=" + amountOfPeople +
                ", description='" + description + '\'' +
                ", whoTakenOrder=" + whoTakenOrder.getName() +
                ", whoServesOrder=" + whoServesOrder +
                ", type=" + type +
                '}';
    }

    @Override
    public int compareTo(Object o) {
        Ordering ordering = (Ordering)o;
        return this.getDateOrderCreated().compareTo(ordering.getDateOrderCreated());
    }
}
