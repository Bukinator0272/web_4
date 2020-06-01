package model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.io.*;
import java.util.*;

@Getter
@Setter
@AllArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
public class ChequeBook implements Serializable {

    private int accountNumber;

    @XmlElement(name = "cheque")
    @XmlElementWrapper(name = "cheques")
    private List<Cheque> cheques;

    public ChequeBook(int accountNumber) {
        this.accountNumber = accountNumber;
        this.cheques = new ArrayList<>();
    }

    public void add(Cheque cheque) {
        cheques.add(cheque);
    }

    public Cheque get(int index) {
        return cheques.get(index);
    }

    public void remove(int index) {
        cheques.remove(index);
    }

    public int getSize() {
        return cheques.size();
    }

    @Override
    public String toString() {
        return "ChequeBook{" +
                "accountNumber=" + accountNumber +
                ", cheques=" + cheques +
                '}';
    }

    public static void write(ChequeBook chequeBook, String name) throws IOException {
        FileWriter out = new FileWriter(new File(name));
        out.write(chequeBook.getAccountNumber() + "\r\n");
        for (Cheque cheque : chequeBook.cheques)
            out.write(cheque.toString() + "\r\n");
        out.flush();
        out.close();
    }

    public static ChequeBook read(String name) throws IOException {
        Scanner scan = new Scanner(new FileReader(name));
        ChequeBook chequeBook = new ChequeBook(scan.nextInt());
        while (scan.hasNext()) {
            chequeBook.add(new Cheque(
                    scan.next(),
                    scan.nextInt(),
                    scan.nextInt(),
                    scan.nextInt()
            ));
        }
        return chequeBook;
    }

    public void sort() {
        Collections.sort(cheques);
    }

    public void removeDuplicates() {
        Set<Cheque> set = new LinkedHashSet<>(cheques);
        cheques.clear();
        cheques.addAll(set);
    }

}
