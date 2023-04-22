package synchronizationOfFile.synchronizationOfFile.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // db가 생성해주는걸 identity 라고 한다.
    private Long id; // 시스템이 저장하는 id (기본키 역할의 필드이다.)
    private String name; // 이름
    private String pwd; // 비밀번호


}

