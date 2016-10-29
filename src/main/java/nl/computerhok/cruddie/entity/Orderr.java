package nl.computerhok.cruddie.entity;

import org.apache.commons.lang.builder.ToStringBuilder;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity
public class Orderr {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long orderrid;
    private String article;
    private int amount;
    private Date orderdate;

    @ManyToOne(optional = false)
    @JoinColumn(name = "customerid")
    private Customer customer;


    protected Orderr() {
    }

    public Orderr(String article, int amount, Date orderdate, Customer customer) {
        this.article = article;
        this.amount = amount;
        this.orderdate = orderdate;
        this.customer = customer;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    public Long getOrderrid() {
        return orderrid;
    }

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Date getOrderdate() {
        return orderdate;
    }

    public void setOrderdate(Date orderdate) {
        this.orderdate = orderdate;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

}
