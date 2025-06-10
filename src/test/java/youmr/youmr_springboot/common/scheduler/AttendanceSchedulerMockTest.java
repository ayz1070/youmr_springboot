package youmr.youmr_springboot.common.scheduler;


import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import youmr.youmr_springboot.repository.AttendanceRepository;
import youmr.youmr_springboot.service.FcmNotificationSendService;

import java.time.LocalDate;

import static org.mockito.Mockito.*;

@SpringJUnitConfig
class AttendanceSchedulerMockTest {

    @TestConfiguration
    static class TestConfig {
        @Bean
        public AttendanceScheduler attendanceScheduler(
                FcmNotificationSendService fcmNotificationSendService,
                AttendanceRepository attendanceRepository) {
            return new AttendanceScheduler(fcmNotificationSendService, attendanceRepository);
        }
    }

    @Mock
    private FcmNotificationSendService fcmNotificationSendService;

    @Mock
    private AttendanceRepository attendanceRepository;

    @Autowired
    private AttendanceScheduler attendanceScheduler;

    @Test
    void testClearAttendanceRecords() {
        // given
        when(attendanceRepository.deleteAllByAttendanceDate(any(LocalDate.class))).thenReturn(5);

        // when
        attendanceScheduler.clearAttendanceRecords();

        // then
        verify(attendanceRepository, times(1)).deleteAllByAttendanceDate(any(LocalDate.class));
    }

    @Test
    void testSendDailyAttendanceReminder() {
        // when
        attendanceScheduler.sendDailyAttendanceReminder();

        // then
        verify(fcmNotificationSendService, times(1))
                .sendToWeekTypeMembers(any(), eq("출석 알림"), anyString());
    }
}