package by.RIP.repository;

import by.RIP.entity.Fish;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FishDao extends JpaRepository<Fish,Long> {
}
