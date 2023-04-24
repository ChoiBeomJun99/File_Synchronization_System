package synchronizationOfFile.synchronizationOfFile.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Data
public class FileTransferObject {
    String fileName; // 이름과 매핑할 것
    String type; // 타입

    // 생성자
    public FileTransferObject(){}

    // 생성자 오버라이딩 #1
    public FileTransferObject(String fileName, String type){
        this.fileName = fileName;
        this.type = type;
    }
}
