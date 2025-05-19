package youmr.youmr_springboot.controller.api.v1;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import youmr.youmr_springboot.dto.fcm.FcmTokenResponse;
import youmr.youmr_springboot.dto.fcm.RefreshFcmTokenRequest;
import youmr.youmr_springboot.service.FcmTokenService;

@Tag(name = "FCM API", description = "FCM 토큰 등록 및 관리")
@RestController
@RequestMapping("/api/v1/fcm/token")
@RequiredArgsConstructor
public class FcmTokenV1Controller {

    private final FcmTokenService fcmTokenService;

    /// create or update
    @PostMapping
    public ResponseEntity<FcmTokenResponse> saveOrUpdateToken(@RequestBody RefreshFcmTokenRequest request) {
        FcmTokenResponse response = fcmTokenService.saveOrUpdateToken(request);
        return ResponseEntity.ok(response);
    }

    // 토큰 비활성화 (로그아웃, 탈퇴 등)
    @PutMapping("/deactivate/{memberId}")
    public ResponseEntity<Void> deactivateToken(@PathVariable Long memberId) {
        fcmTokenService.deactivateTokenByMemberId(memberId);
        return ResponseEntity.noContent().build();
    }

    // memberId로 토큰 조회
    @GetMapping("/{memberId}")
    public ResponseEntity<FcmTokenResponse> getTokenByMemberId(@PathVariable Long memberId) {
        return fcmTokenService.findTokenByMemberId(memberId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}