package generated.soap.project.com;

import javax.xml.datatype.XMLGregorianCalendar;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Ticket", propOrder = {
    "id",
    "doctor",
    "patient",
    "date",
    "startTime",
    "endTime"
})
public class Ticket {
    protected long id;
    protected long doctor;
    protected long patient;
    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar date;
    @XmlElement(required = true)
    @XmlSchemaType(name = "time")
    protected XMLGregorianCalendar startTime;
    @XmlElement(required = true)
    @XmlSchemaType(name = "time")
    protected XMLGregorianCalendar endTime;

    public long getId() {
        return id;
    }

    public void setId(long value) {
        this.id = value;
    }

    public long getDoctor() {
        return doctor;
    }

    public void setDoctor(long value) {
        this.doctor = value;
    }

    public long getPatient() {
        return patient;
    }

    public void setPatient(long value) {
        this.patient = value;
    }

    public XMLGregorianCalendar getDate() {
        return date;
    }

    public void setDate(XMLGregorianCalendar value) {
        this.date = value;
    }

    public XMLGregorianCalendar getStartTime() {
        return startTime;
    }

    public void setStartTime(XMLGregorianCalendar value) {
        this.startTime = value;
    }

    public XMLGregorianCalendar getEndTime() {
        return endTime;
    }

    public void setEndTime(XMLGregorianCalendar value) {
        this.endTime = value;
    }
}
