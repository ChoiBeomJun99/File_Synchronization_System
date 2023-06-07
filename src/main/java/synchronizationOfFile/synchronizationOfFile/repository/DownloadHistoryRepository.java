package synchronizationOfFile.synchronizationOfFile.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import synchronizationOfFile.synchronizationOfFile.domain.DownloadHistory;
import synchronizationOfFile.synchronizationOfFile.domain.FileInfo;

public interface DownloadHistoryRepository extends JpaRepository<DownloadHistory, Long> {
    DownloadHistory findByFileName(String fileName);
}
