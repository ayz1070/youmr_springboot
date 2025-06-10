package youmr.youmr_springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import youmr.youmr_springboot.entity.Attendance;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {

    // 특정 회원이 특정 날짜, 특정 타입에 이미 출석했는지 조회
    Optional<Attendance> findByMemberIdAndAttendanceDateAndAttendanceType(Long memberId, LocalDate attendanceDate, String attendanceType);

    List<Attendance> findAllByOrderByAttendanceDateDesc();

    int deleteAllByAttendanceDate(LocalDate date);
}