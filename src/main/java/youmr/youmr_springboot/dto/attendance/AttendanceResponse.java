package youmr.youmr_springboot.dto.attendance;

import lombok.Builder;
import lombok.Getter;
import youmr.youmr_springboot.entity.Attendance;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Builder
public class AttendanceResponse {

    private Long id;
    private Long memberId;
    private String memberName;
    private String memberProfileImageUrl;
    private LocalDate attendanceDate;
    private String attendanceType;
    private LocalDateTime createdAt;

    // Entity -> Response 변환
    public static AttendanceResponse fromEntity(Attendance attendance) {
        return AttendanceResponse.builder()
                .id(attendance.getId())
                .memberId(attendance.getMember().getId())
                .memberName(attendance.getMember().getName())
                .memberProfileImageUrl(attendance.getMember().getProfileImageUrl())
                .attendanceDate(attendance.getAttendanceDate())
                .attendanceType(attendance.getAttendanceType())
                .createdAt(attendance.getCreatedAt())
                .build();
    }
}