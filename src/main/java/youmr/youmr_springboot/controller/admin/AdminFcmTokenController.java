package youmr.youmr_springboot.controller.admin;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import youmr.youmr_springboot.dto.fcm.FcmTokenResponse;
import youmr.youmr_springboot.service.FcmTokenService;

import java.util.List;

@RestController
@RequestMapping("/api/admin/fcm/tokens")
@RequiredArgsConstructor
public class AdminFcmTokenController {

    private final FcmTokenService fcmTokenService;

    // 모든 유효한 토큰 조회
    @GetMapping
    public ResponseEntity<List<FcmTokenResponse>> getAllActiveTokens() {
        List<FcmTokenResponse> tokens = fcmTokenService.findAllActiveTokens();
        return ResponseEntity.ok(tokens);
    }
}