package springboot.shinkwang.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springboot.shinkwang.dto.TodoDTO;
import springboot.shinkwang.model.TodoEntity;
import springboot.shinkwang.persistence.TodoRepository;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository repository;

    public String testService() {

        //TodoEntity 생성
        TodoEntity entity = TodoEntity.builder().title("My First todo item").build();
        //TodoEntity 저장
        repository.save(entity);
        //TodoEntity 검색
        TodoEntity savedEntity = repository.findById(entity.getId()).get();

        return savedEntity.getTitle();
    }

    public List<TodoEntity> create(final TodoEntity entity) {
        //Validations
        validate(entity);

        repository.save(entity);

        log.info("Entity Id : {} is saved", entity.getId());

        return repository.findByUserId(entity.getUserId());
    }

    private void validate(final TodoEntity entity) {
        if (entity == null) {
            log.warn("Entity cannot be null");
            throw new RuntimeException("Entity cannot be null");
        }

        if (entity.getUserId() == null) {
            log.warn("Unknown user.");
            throw new RuntimeException("Unkown user.");
        }
    }
}
