package com.lilo.liloproject.repository;

import com.lilo.liloproject.model.TimeLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.ZonedDateTime;
import java.util.List;

@Repository
public interface TimeLogRepository extends JpaRepository<TimeLog, Long> {

    List<TimeLog> findAllByEmployeeIDAndTimeInBetween (String employeeID, ZonedDateTime dateStart, ZonedDateTime dateEnd);

    TimeLog findFirstByEmployeeIDOrderByTimeInDesc(String employeeID);



}
