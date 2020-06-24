package edu.utn.utnphones.controller.web;

import edu.utn.utnphones.controller.CallController;
import edu.utn.utnphones.domain.Call;
import edu.utn.utnphones.projections.CallsTotalByMonth;
import edu.utn.utnphones.session.SessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/calls")
public class WebController {

    private CallController callController;

    @Autowired
    public WebController(CallController callController) {
        this.callController = callController;
    }

    @GetMapping("/month/{month}")
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


    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Call>> getCallsByDate(@RequestParam(value = "from") String from, @RequestParam(value = "to") String to, @PathVariable int userId) throws ParseException {
        ResponseEntity<List<Call>> responseEntity;
        List<Call> callList;
        if ((from != null) && (to != null)){
            Date dateFrom = new SimpleDateFormat("yyyy-MM-dd").parse(from);
            Date dateTo = new SimpleDateFormat("yyyy-MM-dd").parse(to);
            callList = callController.getCallsByDate(dateFrom,dateTo,userId);
            if (!callList.isEmpty()){
                responseEntity = ResponseEntity.ok().body(callList);
            }else{
                responseEntity = ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }
        }else{
            responseEntity = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return responseEntity;
    }

}
