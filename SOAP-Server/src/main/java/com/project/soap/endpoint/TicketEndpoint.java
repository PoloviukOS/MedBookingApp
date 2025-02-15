package com.project.soap.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import com.project.soap.services.TicketService;
import generated.soap.project.com.CreateScheduleRequest;
import generated.soap.project.com.CreateScheduleResponse;
import generated.soap.project.com.TicketStatus;

import javax.xml.datatype.XMLGregorianCalendar;
import java.time.LocalDate;
import java.time.LocalTime;

@Endpoint
public class TicketEndpoint {
    private static final String NAMESPACE_URI = "http://com.project.soap";

    @Autowired
    private TicketService ticketService;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "CreateScheduleRequest")
    @ResponsePayload
    public CreateScheduleResponse createSchedule(@RequestPayload CreateScheduleRequest request){
        CreateScheduleResponse response = new CreateScheduleResponse();
        XMLGregorianCalendar date = request.getDate();
        LocalDate localDate = LocalDate.of(date.getYear(), date.getMonth(), date.getDay());

        XMLGregorianCalendar startTime = request.getStartTime();
        LocalTime localTime = LocalTime.of(startTime.getHour(), startTime.getMinute(), startTime.getSecond());

        ticketService.createScheduleRequest(
                request.getDoctorId(),
                localDate,
                localTime,
                request.getDuration(),
                request.getQuantity()
        );
        TicketStatus ticketStatus = new TicketStatus();
        ticketStatus.setStatus("SUCCESS");
        ticketStatus.setMessage("Content Created Successfully");
        response.setTicketStatus(ticketStatus);
        return response;
    }
}
