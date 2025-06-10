package youmr.youmr_springboot.service;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import org.springframework.stereotype.Service;

import com.google.firebase.auth.UserRecord.CreateRequest;

import java.util.HashMap;
import java.util.Map;

@Service
public class FirebaseTokenService {


    public String createToken(Long memberId, String role) {
        try {
            String uid = "youmr_" + memberId;

            // ✅ 사용자 없으면 새로 생성
            try {
                FirebaseAuth.getInstance().getUser(uid);
            } catch (FirebaseAuthException e) {
                if (e.getAuthErrorCode().name().equals("USER_NOT_FOUND")) {
                    CreateRequest createRequest = new CreateRequest().setUid(uid);
                    FirebaseAuth.getInstance().createUser(createRequest);
                } else {
                    throw e; // 다른 오류는 다시 던지기
                }
            }

            Map<String, Object> claims = new HashMap<>();
            claims.put("role", role);

            return FirebaseAuth.getInstance().createCustomToken(uid, claims);
        } catch (FirebaseAuthException e) {
            throw new RuntimeException("Firebase token 생성 실패", e);
        }
    }

}