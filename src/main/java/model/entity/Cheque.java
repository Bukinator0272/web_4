package model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.*;
import java.util.Scanner;

@Getter
@Setter
@AllArgsConstructor
public class Cheque implements Serializable, Comparable<Cheque> {

    private String serviceType;
    private int price;
    private int count;
    private int cost;

    @Override
    public String toString() {
        return "Cheque{" +
                "serviceType='" + serviceType + '\'' +
                ", price=" + price +
                ", count=" + count +
                ", cost=" + cost +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null) return false;
        if (!(obj instanceof Cheque)) return false;
        Cheque cheque = (Cheque) obj;
        return serviceType.equals(cheque.getServiceType()) &&
                price == cheque.getPrice() &&
                count == cheque.getCount() &&
                cost == cheque.getCost();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = serviceType.hashCode();
        result = prime * result + price;
        result = prime * result + count;
        result = prime * result + cost;
        return result;
    }

    @Override
    public int compareTo(Cheque cheque) {
        if (this.getServiceType() == null || cheque.getServiceType() == null)
            return 0;
        return this.getServiceType().compareTo(cheque.getServiceType());
    }

    public static void write(Cheque cheque) throws IOException {
        FileWriter out = new FileWriter(new File("Cheque.txt"));
        out.write(cheque.toString());
        out.flush();
        out.close();
    }

    public static Cheque read() throws FileNotFoundException {
        Scanner scan = new Scanner(new FileReader("Cheque.txt"));
        if (scan.hasNext()) {
            return new Cheque(
                    scan.next(),
                    scan.nextInt(),
                    scan.nextInt(),
                    scan.nextInt()
            );
        }
        return null;
    }

}
