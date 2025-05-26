package youmr.youmr_springboot.dto.member;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SignUpResponse {
    private MemberResponse member;
    private String firebaseToken;
}