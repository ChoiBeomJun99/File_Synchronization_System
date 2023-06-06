package synchronizationOfFile.synchronizationOfFile.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Member {
    @Id
    @GeneratedValue
    private Long id; // 시스템이 저장하는 id (기본키 역할의 필드이다.)
    private String name; // 이름
    private String password; // 비밀번호

}
