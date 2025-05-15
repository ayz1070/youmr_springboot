package youmr.youmr_springboot.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import youmr.youmr_springboot.entity.type.FcmNotificationTargetType;
import youmr.youmr_springboot.entity.type.FcmNotificationType;

import java.time.LocalDateTime;

@Entity
@Table(name = "notifications")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FcmNotification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 알림 제목
    @Column(nullable = false)
    private String title;

    // 알림 내용
    @Column(nullable = false)
    private String body;

    // 알림 유형 (ATTENDANCE, EVENT, VOTE, CUSTOM 등)
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private FcmNotificationType type;

    // 대상 (ALL, WEEK_TYPE, MEMBER 등)
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private FcmNotificationTargetType targetType;

    // 예: 특정 요일, 특정 사용자 ID 등
    @Column(name = "target_value")
    private String targetValue;

    // 발송 여부
    @Column(nullable = false)
    private boolean isSent;

    // 발송 시간
    private LocalDateTime sentAt;

    @CreationTimestamp
    private LocalDateTime createdAt;
}