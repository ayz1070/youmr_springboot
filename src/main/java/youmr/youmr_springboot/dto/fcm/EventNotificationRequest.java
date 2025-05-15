package youmr.youmr_springboot.dto.fcm;


import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class EventNotificationRequest {

    @NotBlank
    private String title;

    @NotBlank
    private String body;
}