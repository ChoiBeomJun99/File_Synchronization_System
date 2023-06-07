package synchronizationOfFile.synchronizationOfFile.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Entity @Data
public class SharedFileList {
    @Id
    @GeneratedValue
    Long id; // 데이터 고유 Id
    String fileName; // 파일의 이름
    Long shareMemberId; // 공유한 멤버의 Id
    Long sharedMemberId; // 공유받은 멤버의 Id
    @DateTimeFormat(pattern = "yyyy-MM-dd / HH:mm:ss")
    private LocalDateTime createdAt; // 공유 시간
}
