package edu.utn.utnphones.controller.web;


import edu.utn.utnphones.controller.CallController;
import edu.utn.utnphones.domain.User;
import edu.utn.utnphones.projections.CallsTotalByMonth;
import edu.utn.utnphones.session.SessionManager;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.xml.bind.ValidationException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CallsControllerWebTest {

    SessionManager sessionManager;
    CallsTotalByMonth callsTotalByMonth;
    CallsControllerWeb callControllerWeb;
    CallController callController;
    User user;

    @Before
    public void setUp() {
        callController = mock(CallController.class);
        sessionManager = mock(SessionManager.class);
        user = mock(User.class);
        callsTotalByMonth = mock(CallsTotalByMonth.class);
    }


    @Test
    public void testGetCallsTotalByMonthOk()  {
        List<CallsTotalByMonth> list = new ArrayList<>();
        list.add(callsTotalByMonth);
        when(callController.getCallsTotalByMonth(5)).thenReturn(list);
        when(sessionManager.getCurrentUser("token")).thenReturn(user);
        ResponseEntity<List<CallsTotalByMonth>> responseEntity = callControllerWeb.getCallsTotalByMonth("token",5);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(list, responseEntity.getBody());

    }

    @Test
    public void testGetCallsTotalByMonthEmpty() {
        List<CallsTotalByMonth> list = new ArrayList<>();
        list.clear();
        when(callController.getCallsTotalByMonth(5)).thenReturn(list);
        when(sessionManager.getCurrentUser("token")).thenReturn(user);
        ResponseEntity<List<CallsTotalByMonth>> responseEntity = callControllerWeb.getCallsTotalByMonth("token",5);

        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
    }

}
