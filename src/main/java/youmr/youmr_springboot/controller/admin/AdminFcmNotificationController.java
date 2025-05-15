package youmr.youmr_springboot.controller.admin;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import youmr.youmr_springboot.dto.fcm.EventNotificationRequest;
import youmr.youmr_springboot.entity.FcmNotification;
import youmr.youmr_springboot.entity.type.FcmNotificationTargetType;
import youmr.youmr_springboot.entity.type.FcmNotificationType;
import youmr.youmr_springboot.service.FcmNotificationSendService;
import youmr.youmr_springboot.service.FcmNotificationService;

@RestController
@RequestMapping("/api/admin/fcm/notify")
@RequiredArgsConstructor
public class AdminFcmNotificationController {

    private final FcmNotificationSendService fcmNotificationSendService;
    private final FcmNotificationService fcmNotificationService;

    // ✅ 이벤트 알림 전체 발송 + 저장
    @PostMapping("/event")
    public ResponseEntity<Void> sendEventNotificationToAll(
            @RequestBody @Valid EventNotificationRequest request
    ) {
        // 1. FCM 전송
        fcmNotificationSendService.sendToAllActiveMembers(
                request.getTitle(),
                request.getBody()
        );

        // 2. 알림 내역 저장
        FcmNotification saved = FcmNotification.builder()
                .title(request.getTitle())
                .body(request.getBody())
                .type(FcmNotificationType.EVENT)
                .targetType(FcmNotificationTargetType.ALL)
                .isSent(true)
                .sentAt(java.time.LocalDateTime.now())
                .build();

        fcmNotificationService.saveNotification(saved);

        return ResponseEntity.ok().build();
    }
}