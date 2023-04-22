package synchronizationOfFile.synchronizationOfFile.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import synchronizationOfFile.synchronizationOfFile.domain.ConnectList;

public interface ConnectListRepository extends JpaRepository<ConnectList, Long> {
    ConnectList findById(String Id);
}
