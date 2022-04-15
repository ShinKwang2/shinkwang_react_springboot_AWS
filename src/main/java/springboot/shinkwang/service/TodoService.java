package springboot.shinkwang.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import springboot.shinkwang.model.TodoEntity;
import springboot.shinkwang.persistence.TodoRepository;

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
}
