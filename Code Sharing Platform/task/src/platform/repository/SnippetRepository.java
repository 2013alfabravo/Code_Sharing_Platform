package platform.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import platform.data.Code;
import java.util.List;
import java.util.Optional;

public interface SnippetRepository extends JpaRepository<Code, String> {
    @Query(value = "SELECT * FROM code WHERE " +
            "(uuid = :uuid AND view_limit = FALSE AND time_limit = FALSE) OR " +
            "(uuid = :uuid AND view_limit = TRUE AND time_limit = FALSE AND views > 0) OR " +
            "(uuid = :uuid AND time_limit = TRUE AND view_limit = FALSE AND expiration_date > CURRENT_TIMESTAMP) OR " +
            "(uuid = :uuid AND time_limit = TRUE AND view_limit = TRUE AND views > 0 AND expiration_date > CURRENT_TIMESTAMP) LIMIT 1", nativeQuery = true)
    Optional<Code> findByUuid(@Param(value = "uuid") String uuid);

    @Query(value = "SELECT * FROM code WHERE " +
            "(view_limit = FALSE AND time_limit = FALSE)" +
            "ORDER BY timestamp DESC LIMIT 10", nativeQuery = true)
    List<Code> findTenRecentSnippets();

    @Modifying
    @Query(value = "UPDATE code SET views = :views WHERE uuid = :uuid", nativeQuery = true)
    void updateViewsByUuid(@Param(value = "views") int views, @Param(value = "uuid") String uuid);

}
