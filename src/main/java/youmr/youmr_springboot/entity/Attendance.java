package youmr.youmr_springboot.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "attendance",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"member_id", "attendance_date", "attendance_type"})})
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @Column(name = "attendance_date", nullable = false)
    private LocalDate attendanceDate;

    @Column(name = "attendance_type", nullable = false, length = 30)
    private String attendanceType = "DEFAULT";

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }
}