package youmr.youmr_springboot.dto.fcm;

import lombok.Getter;
import lombok.NoArgsConstructor;
import youmr.youmr_springboot.entity.type.DeviceType;

@Getter
@NoArgsConstructor
public class RefreshFcmTokenRequest {

    private String memberId;
    private String token;
    private DeviceType deviceType;

    public RefreshFcmTokenRequest(String memberId, String token, DeviceType deviceType) {
        this.memberId = memberId;
        this.token = token;
        this.deviceType = deviceType;
    }
}