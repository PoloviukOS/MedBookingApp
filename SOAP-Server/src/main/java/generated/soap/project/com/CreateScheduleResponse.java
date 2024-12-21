package generated.soap.project.com;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "ticketStatus",
    "ticket"
})
@XmlRootElement(name = "CreateScheduleResponse")
public class CreateScheduleResponse {
    @XmlElement(required = true)
    protected TicketStatus ticketStatus;
    @XmlElement(required = true)
    protected Ticket ticket;

    public TicketStatus getTicketStatus() {
        return ticketStatus;
    }

    public void setTicketStatus(TicketStatus value) {
        this.ticketStatus = value;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket value) {
        this.ticket = value;
    }
}
