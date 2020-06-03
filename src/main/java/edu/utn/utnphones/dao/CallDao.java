package edu.utn.utnphones.dao;

import edu.utn.utnphones.domain.Call;
import edu.utn.utnphones.projections.CallsTotalByMonth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface CallDao extends JpaRepository<Call,Integer> {

    @Procedure(value = "sp_insertcall")
    void addCall(String lineFrom, String lineTo, int seg, Date date);


    //Calls By total duration by month
    @Query(value = "select u.user_name Name, u.user_lastname Lastname, count(c.call_id) TotalCall\n" +
            "from calls c inner join phone_lines p on c.call_line_id_from = p.line_id \n" +
            "inner join users u on u.user_id = p.line_user_id\n" +
            "where u.user_id = p.line_user_id and month(c.call_date) = ?1\n" +
            "group by u.user_name\n", nativeQuery = true)
    List<CallsTotalByMonth> getCallsTotalByMonth(int month);


}
