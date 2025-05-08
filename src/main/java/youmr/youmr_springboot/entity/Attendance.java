package youmr.youmr_springboot.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "attendance", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"member_id", "attendance_date", "attendance_type"}),
        @UniqueConstraint(columnNames = {"member_id", "event_id"})
})
public class Attendance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 필수
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    // 일일 출석일 경우 사용
    private LocalDate attendanceDate;

    // "DEFAULT", "EVENT" 같은 구분자
    private String attendanceType;

    // 이벤트 출석일 경우 사용
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id")
    private Event event;

    private LocalDateTime createdAt;
}