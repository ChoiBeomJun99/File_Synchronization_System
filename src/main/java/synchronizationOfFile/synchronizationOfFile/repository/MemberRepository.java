package synchronizationOfFile.synchronizationOfFile.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import synchronizationOfFile.synchronizationOfFile.domain.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Member findByName(String Name);
}
