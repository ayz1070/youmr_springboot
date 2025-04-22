package youmr.youmr_springboot.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 요청한 리소스를 찾을 수 없을 때 발생하는 예외.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)  // 404 상태 코드 반환
public class NotFoundException extends RuntimeException {

    /** 기본 생성자 */
    public NotFoundException() {
        super("리소스를 찾을 수 없습니다.");
    }

    /** 커스텀 메시지를 포함하는 생성자 */
    public NotFoundException(String message) {
        super(message);
    }
}