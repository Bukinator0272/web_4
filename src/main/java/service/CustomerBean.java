package service;

import lombok.Getter;
import lombok.Setter;
import model.entity.Customer;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.SQLException;

@Getter
@Setter
@SessionScoped
@ManagedBean(name = "customerBean")
public class CustomerBean {

    @EJB
    private CustomerEJB customerEJB;
    private Customer customer = new Customer();

    public boolean validateUserLogin() throws SQLException {
        customer = customerEJB.validateUserLogin(customer.getLogin(), customer.getPassword());
        return customer.getLogin() != null;
    }

    public void parseXml() throws JAXBException, IOException {
        FacesContext ctx = FacesContext.getCurrentInstance();
        final HttpServletResponse resp = (HttpServletResponse)ctx.getExternalContext().getResponse();
        StringWriter writer = new StringWriter();
        JAXBContext jaxbContext = JAXBContext.newInstance(Customer.class);
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        resp.setContentType("text/xml");
        resp.setCharacterEncoding("UTF-8");
        marshaller.marshal(customer, writer);
        PrintWriter out = resp.getWriter();
        out.print(writer.toString());
        ctx.responseComplete();
    }



}
