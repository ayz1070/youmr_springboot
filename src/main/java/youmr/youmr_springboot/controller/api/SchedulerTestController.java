package youmr.youmr_springboot.controller.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import youmr.youmr_springboot.common.scheduler.AttendanceScheduler;

@RestController
@RequestMapping("/api/test/scheduler")
@RequiredArgsConstructor
public class SchedulerTestController {

    private final AttendanceScheduler attendanceScheduler;

    @PostMapping("/daily-attendance")
    public ResponseEntity<Void> triggerDailyAttendance() {
        attendanceScheduler.sendDailyAttendanceReminder();
        return ResponseEntity.ok().build();
    }

    @PostMapping("/monthly-payment")
    public ResponseEntity<Void> triggerMonthlyPayment() {
        attendanceScheduler.sendMonthlyPaymentReminder();
        return ResponseEntity.ok().build();
    }
}