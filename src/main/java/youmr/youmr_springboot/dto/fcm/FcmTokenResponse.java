package youmr.youmr_springboot.dto.fcm;

import lombok.Builder;
import lombok.Getter;
import youmr.youmr_springboot.entity.FcmToken;
import youmr.youmr_springboot.entity.type.DeviceType;

import java.time.LocalDateTime;

@Getter
@Builder
public class FcmTokenResponse {

    private Long id;
    private String memberId;
    private String token;
    private boolean isActive;
    private DeviceType deviceType;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static FcmTokenResponse fromEntity(FcmToken entity) {
        return FcmTokenResponse.builder()
                .id(entity.getId())
                .memberId(entity.getMemberId())
                .token(entity.getToken())
                .isActive(entity.isActive())
                .deviceType(entity.getDeviceType())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }
}