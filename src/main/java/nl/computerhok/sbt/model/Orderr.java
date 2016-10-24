package nl.computerhok.sbt.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Orderr {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String article;
    private int amount;
    private Date orderdate;

    protected Orderr() {
    }

    public Orderr(String article, int amount, Date orderdate) {
        this.article = article;
        this.amount = amount;
        this.orderdate = orderdate;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Orderr{");
        sb.append("id=").append(id);
        sb.append(", article='").append(article).append('\'');
        sb.append(", amount=").append(amount);
        sb.append(", orderdate=").append(orderdate);
        sb.append('}');
        return sb.toString();
    }

    public Long getId() {
        return id;
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
}
