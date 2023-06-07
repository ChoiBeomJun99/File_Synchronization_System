package synchronizationOfFile.synchronizationOfFile.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import synchronizationOfFile.synchronizationOfFile.domain.SharedFileList;

public interface SharedFileListRepository extends JpaRepository<SharedFileList, Long>  {
    SharedFileList findBySharedMemberId(Long id);
    SharedFileList findByFileName(String fileName);

}
