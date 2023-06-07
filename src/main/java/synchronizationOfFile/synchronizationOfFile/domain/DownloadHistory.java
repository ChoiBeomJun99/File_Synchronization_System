package synchronizationOfFile.synchronizationOfFile.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Entity @Data
public class DownloadHistory {
    @Id
    @GeneratedValue
    Long id; // 데이터 고유 Id
    String fileName; // 파일의 이름
    Long memberId; // 파일을 다운 받은 멤버의 Id
    @DateTimeFormat(pattern = "yyyy-MM-dd / HH:mm:ss")
    private LocalDateTime createdAt; // 파일을 다운받은 시각
}
