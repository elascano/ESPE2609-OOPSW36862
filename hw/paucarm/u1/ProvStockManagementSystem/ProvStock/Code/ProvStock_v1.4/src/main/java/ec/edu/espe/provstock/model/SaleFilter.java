package ec.edu.espe.provstock.model;

import java.time.LocalDate;

/**
 * Argument object with the filters of the sales history (FR-07.2): date range,
 * salesperson, customer and status. A filter that is not set is not used.
 *
 * @author Pachacama Andres, Parra Carlos, Paucar Matias, ByteWorks, @ESPE
 */
public class SaleFilter {

    private LocalDate startDate;
    private LocalDate endDate;
    private User salesperson;
    private Customer customer;
    private String status;

    public SaleFilter from(LocalDate startDate) {
        this.startDate = startDate;
        return this;
    }

    public SaleFilter to(LocalDate endDate) {
        this.endDate = endDate;
        return this;
    }

    public SaleFilter bySalesperson(User salesperson) {
        this.salesperson = salesperson;
        return this;
    }

    public SaleFilter byCustomer(Customer customer) {
        this.customer = customer;
        return this;
    }

    public SaleFilter withStatus(String status) {
        this.status = status;
        return this;
    }

    /**
     * @return true if the sale passes every filter that was set
     */
    public boolean matches(Sale sale) {
        LocalDate date = sale.getDateTime().toLocalDate();
        boolean inPeriod = (startDate == null || !date.isBefore(startDate))
                && (endDate == null || !date.isAfter(endDate));
        boolean sameSalesperson = salesperson == null || sale.getRegisteredBy() == salesperson;
        boolean sameCustomer = customer == null || sale.getCustomer() == customer;
        boolean sameStatus = status == null || status.isBlank() || sale.getStatus().equals(status);
        return inPeriod && sameSalesperson && sameCustomer && sameStatus;
    }
}
