package youmr.youmr_springboot.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import youmr.youmr_springboot.dto.attendance.AttendanceRequest;
import youmr.youmr_springboot.dto.attendance.AttendanceResponse;
import youmr.youmr_springboot.entity.Attendance;
import youmr.youmr_springboot.entity.Member;
import youmr.youmr_springboot.repository.AttendanceRepository;
import youmr.youmr_springboot.repository.MemberRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class AttendanceService {

    private final AttendanceRepository attendanceRepository;
    private final MemberRepository memberRepository;


    // 전체 출석 조회
    public List<AttendanceResponse> getAllAttendances() {
        return attendanceRepository.findAllByOrderByAttendanceDateDesc()
                .stream()
                .map(AttendanceResponse::fromEntity)
                .collect(Collectors.toList());
    }

    // 출석
    public AttendanceResponse attend(AttendanceRequest request) {
        // 1. memberId로 Member 조회
        Member member = memberRepository.findById(request.getMemberId())
                .orElseThrow(() -> new IllegalArgumentException("회원이 존재하지 않습니다."));

        // 2. 이미 출석했는지 체크
        attendanceRepository.findByMemberIdAndAttendanceDateAndAttendanceType(
                request.getMemberId(),
                request.getAttendanceDate(),
                request.getAttendanceType()
        ).ifPresent(a -> {
            throw new IllegalStateException("이미 출석한 기록이 존재합니다.");
        });

        // 3. 출석 저장
        Attendance attendance = request.toEntity(member);
        Attendance savedAttendance = attendanceRepository.save(attendance);

        // 4. Response로 변환해서 리턴
        return AttendanceResponse.fromEntity(savedAttendance);
    }

    // 출석 취소
    public void cancelAttendance(Long memberId, LocalDate attendanceDate, String attendanceType) {
        Attendance attendance = attendanceRepository.findByMemberIdAndAttendanceDateAndAttendanceType(memberId, attendanceDate, attendanceType)
                .orElseThrow(() -> new IllegalArgumentException("출석 기록이 존재하지 않습니다."));

        attendanceRepository.delete(attendance);
    }
}