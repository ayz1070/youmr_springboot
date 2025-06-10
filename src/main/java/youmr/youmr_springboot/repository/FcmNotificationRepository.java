package youmr.youmr_springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import youmr.youmr_springboot.entity.FcmNotification;

import java.util.List;

public interface FcmNotificationRepository extends JpaRepository<FcmNotification, Long> {

    List<FcmNotification> findAllByIsSentFalse();


}