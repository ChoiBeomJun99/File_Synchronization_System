package synchronizationOfFile.synchronizationOfFile.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Entity @Data
public class FileInfo {
    @Id @GeneratedValue
    Long id; // 데이터 고유 Id
    String name; // 파일의 이름
    Long memberId; // 파일 올린 멤버의 id
    String type; // 타입
}
