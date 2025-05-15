package youmr.youmr_springboot.entity;

import jakarta.persistence.*;
import lombok.*;
import youmr.youmr_springboot.entity.type.DeviceType;

import java.time.LocalDateTime;

@Entity
@Table(name = "fcm_token", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"member_id", "token"})
})
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class FcmToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "member_id", nullable = false)
    private String memberId;

    @Column(name = "token", nullable = false, length = 512)
    private String token;

    @Column(name = "is_active", nullable = false)
    private boolean isActive = true;

    @Column(name = "device_type")
    @Enumerated(EnumType.STRING)
    private DeviceType deviceType;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;


    @PrePersist
    protected void onCreate() {
        LocalDateTime now = LocalDateTime.now();
        createdAt = now;
        updatedAt = now;
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    public void deactivate() {
        this.isActive = false;
    }

    public void refreshToken(String newToken, DeviceType deviceType) {
        this.token = newToken;
        this.deviceType = deviceType;
        this.isActive = true;
        this.updatedAt = LocalDateTime.now();
    }
}