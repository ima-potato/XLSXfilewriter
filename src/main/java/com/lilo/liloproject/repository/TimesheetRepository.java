package com.lilo.liloproject.repository;

import com.lilo.liloproject.model.Timesheet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TimesheetRepository extends JpaRepository<Timesheet, Long> {

    List<Timesheet> findAllByEmployeeID(String employeeID);
    List<Timesheet> findAllByEmployeeIDAndStartDateTime(String employeeID, LocalDateTime startDate);
    List<Timesheet> findAllByEmployeeIDOrderByStartDateTimeDesc(String employeeID);
    Timesheet findFirstByEmployeeIDOrderByStartDateTimeDesc(String employeeID);
    Timesheet findByEmployeeIDAndStartDateTime(String employeeID, LocalDateTime startDate);
    Timesheet findByEmployeeIDAndId(String employeeID, Long startDate);
    List<Timesheet> findAllByEmployeeIDAndStatus(String employeeID, String status);
    List<Timesheet> findFirst48ByEmployeeIDOrderByStartDateTimeDesc(String employeeID);

}
