package youmr.youmr_springboot.common.scheduler;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import youmr.youmr_springboot.entity.type.WeekType;
import youmr.youmr_springboot.service.FcmNotificationSendService;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.EnumMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class AttendanceNotificationScheduler {

    private final FcmNotificationSendService fcmNotificationSendService;

    // 요일별 알림 메시지 매핑
    private static final Map<WeekType, String> dayMessages = new EnumMap<>(WeekType.class);
    static {
        dayMessages.put(WeekType.MONDAY, "월요일입니다! 출석체크를 잊지 마세요 📝");
        dayMessages.put(WeekType.TUESDAY, "화요일 출석 알림이 도착했습니다 🚀");
        dayMessages.put(WeekType.WEDNESDAY, "수요일은 중간 점검! 출석 부탁드려요 ⏰");
        dayMessages.put(WeekType.THURSDAY, "목요일입니다. 힘내세요! 출석도요 💪");
        dayMessages.put(WeekType.FRIDAY, "금요일 출석 알림입니다 ✨");
        dayMessages.put(WeekType.SATURDAY, "토요일! 주말에도 잊지 말고 출석 🙌");
        dayMessages.put(WeekType.SUNDAY, "일요일입니다! 출석하고 한 주 마무리 💡");
    }

    // 매일 정오에 실행
    @Scheduled(cron = "0 0 12 * * *", zone = "Asia/Seoul")
    public void sendDailyAttendanceReminder() {
        DayOfWeek today = LocalDate.now().getDayOfWeek(); // ex: MONDAY
        WeekType weekType = WeekType.valueOf(today.name()); // enum 이름 일치할 경우

        String message = dayMessages.getOrDefault(weekType, "오늘도 출석 부탁드립니다.");

        fcmNotificationSendService.sendToWeekTypeMembers(
                weekType,
                "출석 알림",
                message
        );
    }
}