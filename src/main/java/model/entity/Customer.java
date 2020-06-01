package model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Customer implements Serializable {

    private int id;
    private String name;
    private String login;

    @XmlTransient
    private String password;

    @XmlElement(name = "chequeBook")
    @XmlElementWrapper(name = "chequeBooks")
    private List<ChequeBook> chequeBooks;

    public Customer(int id, String name, String login, String password) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.password = password;
        this.chequeBooks = new ArrayList<>();
    }

    public void add(ChequeBook chequeBook) {
        chequeBooks.add(chequeBook);
    }

    public ChequeBook get(int index) {
        return chequeBooks.get(index);
    }

    public void remove(int index) {
        chequeBooks.remove(index);
    }

    public int getSize() {
        return chequeBooks.size();
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", chequeBooks=" + chequeBooks +
                '}';
    }

}
