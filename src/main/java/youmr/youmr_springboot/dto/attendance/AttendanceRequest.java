package youmr.youmr_springboot.dto.attendance;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import youmr.youmr_springboot.entity.Attendance;
import youmr.youmr_springboot.entity.Member;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class AttendanceRequest {

    private Long memberId;               // 출석하는 회원 ID
    private LocalDate attendanceDate;    // 출석 날짜
    private String attendanceType = "DEFAULT"; // 출석 타입

    @Builder
    public AttendanceRequest(Long memberId, LocalDate attendanceDate, String attendanceType) {
        this.memberId = memberId;
        this.attendanceDate = attendanceDate;
        this.attendanceType = attendanceType;
    }

    // Request -> Entity 변환
    public Attendance toEntity(Member member) {
        return Attendance.builder()
                .member(member)
                .attendanceDate(this.attendanceDate)
                .attendanceType(this.attendanceType)
                .build();
    }
}