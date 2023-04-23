package synchronizationOfFile.synchronizationOfFile.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import synchronizationOfFile.synchronizationOfFile.domain.ConnectList;

import java.util.Optional;

public interface ConnectListRepository extends JpaRepository<ConnectList, Long> {
    ConnectList findByName(String Name);
}
