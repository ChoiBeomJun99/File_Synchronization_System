package synchronizationOfFile.synchronizationOfFile.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Entity
@Data
public class ConnectList {
    @Id @GeneratedValue
    private Long id;
    private String name;

    @DateTimeFormat(pattern = "yyyy-MM-dd / HH:mm:ss")
    private LocalDateTime connectedAt;
}
