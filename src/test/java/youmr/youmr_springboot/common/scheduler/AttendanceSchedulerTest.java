package youmr.youmr_springboot.common.scheduler;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AttendanceSchedulerTest {

    @Autowired
    private AttendanceScheduler attendanceScheduler;

    @Test
    void testSendDailyAttendanceReminder() {
        attendanceScheduler.sendDailyAttendanceReminder();
        // 로그 또는 실제 전송 결과를 콘솔로 확인
    }

    @Test
    void testClearAttendanceRecords() {
        attendanceScheduler.clearAttendanceRecords();
        // 로그 또는 DB 확인
    }
}