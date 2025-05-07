package youmr.youmr_springboot.controller.api.v1;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import youmr.youmr_springboot.dto.attendance.AttendanceRequest;
import youmr.youmr_springboot.dto.attendance.AttendanceResponse;
import youmr.youmr_springboot.service.AttendanceService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/attendances")
public class AttendanceV1Controller {

    private final AttendanceService attendanceService;


    // 전체 출석 조회
    @GetMapping
    public ResponseEntity<List<AttendanceResponse>> getAllAttendances() {
        List<AttendanceResponse> responses = attendanceService.getAllAttendances();
        return ResponseEntity.ok(responses);
    }

    // 출석
    @PostMapping
    public ResponseEntity<AttendanceResponse> attend(@RequestBody AttendanceRequest request) {
        AttendanceResponse response = attendanceService.attend(request);
        return ResponseEntity.ok(response);
    }

    // 출석 취소하기
    @DeleteMapping
    public ResponseEntity<Void> cancelAttendance(
            @RequestParam Long memberId,
            @RequestParam LocalDate attendanceDate,
            @RequestParam String attendanceType
    ) {
        attendanceService.cancelAttendance(memberId, attendanceDate, attendanceType);
        return ResponseEntity.noContent().build();
    }
}