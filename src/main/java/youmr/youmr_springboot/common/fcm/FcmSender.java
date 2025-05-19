package youmr.youmr_springboot.common.fcm;

import com.google.firebase.messaging.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FcmSender {

    public void sendToMultipleTokens(List<String> tokens, String title, String body) {
        if (tokens.isEmpty()) return;

        for (String token : tokens) {
            sendToToken(token, title, body);
        }
    }

    private void sendToToken(String token, String title, String body) {
        Message message = Message.builder()
                .setToken(token)
                .setNotification(Notification.builder()
                        .setTitle(title)
                        .setBody(body)
                        .build())
                .build();

        try {
            String response = FirebaseMessaging.getInstance().send(message);
            System.out.println("✅ FCM 전송 성공: " + response);
        } catch (FirebaseMessagingException e) {
            System.err.println("❌ FCM 전송 실패: " + e.getMessage());
        }
    }
}