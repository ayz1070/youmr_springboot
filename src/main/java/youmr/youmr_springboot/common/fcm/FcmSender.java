package youmr.youmr_springboot.common.fcm;

import com.google.firebase.messaging.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FcmSender {

    public void sendToMultipleTokens(List<String> tokens, String title, String body) {
        if (tokens.isEmpty()) return;

        MulticastMessage message = MulticastMessage.builder()
                .addAllTokens(tokens)
                .setNotification(Notification.builder().setTitle(title).setBody(body).build())
                .build();

        try {
            BatchResponse response = FirebaseMessaging.getInstance().sendMulticast(message);
            System.out.println("푸시 발송 성공: " + response.getSuccessCount() + "개");
        } catch (FirebaseMessagingException e) {
            e.printStackTrace();  // 로깅 또는 DB 저장 가능
        }
    }
}