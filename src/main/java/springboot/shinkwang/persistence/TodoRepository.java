package springboot.shinkwang.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import springboot.shinkwang.model.TodoEntity;

import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<TodoEntity, String> {

    List<TodoEntity> findByUserId(String userId);

    // ?1은 메서드의 매개변수의 순서 위치다.
    // :userId 로 쓰는 것이 더 명시적, 숫자는 언제든지 뒤바뀔 수 있기에
//    @Query("select t from TodoEntity t where t.userId = :userId")
//    List<TodoEntity> findByUserIdByQuery(String userId);
}
