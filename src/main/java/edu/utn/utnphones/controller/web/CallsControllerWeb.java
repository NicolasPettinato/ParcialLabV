package edu.utn.utnphones.controller.web;

import edu.utn.utnphones.controller.CallController;
import edu.utn.utnphones.controller.UserController;
import edu.utn.utnphones.projections.CallsTotalByMonth;
import edu.utn.utnphones.session.SessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.ValidationException;
import java.util.List;

@RestController
@RequestMapping("/calls")
public class CallsControllerWeb {

    private CallController callController;
    private SessionManager sessionManager;



    @Autowired
    public CallsControllerWeb(CallController callController, SessionManager sessionManager) {
        this.callController = callController;
        this.sessionManager = sessionManager;
    }

    @GetMapping("/{month}")
    public ResponseEntity<List<CallsTotalByMonth>> getCallsTotalByMonth(@PathVariable int month) {
        ResponseEntity<List<CallsTotalByMonth>> responseEntity;
        List<CallsTotalByMonth> list = callController.getCallsTotalByMonth(month);
        if(!list.isEmpty()){
            responseEntity = ResponseEntity.ok().body(list);
        }else{
            responseEntity = ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return responseEntity;
    }

}
