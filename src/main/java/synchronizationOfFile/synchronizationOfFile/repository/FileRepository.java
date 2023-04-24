package synchronizationOfFile.synchronizationOfFile.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import synchronizationOfFile.synchronizationOfFile.domain.FileInfo;

public interface FileRepository extends JpaRepository<FileInfo, Long> {
}
