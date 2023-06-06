package synchronizationOfFile.synchronizationOfFile.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Data
public class Member {
    @Id
    @GeneratedValue
    private Long id; // 시스템이 저장하는 id (기본키 역할의 필드이다.)
    private String name; // 이름
    private String password; // 비밀번호
    @DateTimeFormat(pattern = "yyyy-MM-dd / HH:mm:ss")
    private LocalDateTime createdAt;



}
