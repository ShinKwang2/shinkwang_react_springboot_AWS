package springboot.shinkwang.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springboot.shinkwang.dto.ResponseDTO;
import springboot.shinkwang.dto.UserDTO;
import springboot.shinkwang.model.UserEntity;
import springboot.shinkwang.service.UserService;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody UserDTO userDTO) {

        try {
            // 요청을 이용해 저장할 사용자 만들기
            UserEntity user = UserEntity.builder()
                    .email(userDTO.getEmail())
                    .username(userDTO.getUsername())
                    .password(userDTO.getPassword())
                    .build();

            // 서비스를 이용해 리포지터리에 사용자 저장
            UserEntity registeredUser = userService.create(user);
            UserDTO responseUserDTO = UserDTO.builder()
                    .email(registeredUser.getEmail())
                    .id(registeredUser.getId())
                    .username(registeredUser.getUsername())
                    .build();

            // 유저 정보는 항상 하나이므로, 리스트를 만들어야 하는 ResponseDTO를 사용하지 않고 그냥 UserDTO 리턴.
            return ResponseEntity.ok(responseUserDTO);
        } catch (Exception e) {
            // 예외가 나는 경우 bad 리스폰스 리턴
            ResponseDTO responseDTO = ResponseDTO.builder().error(e.getMessage()).build();
            return ResponseEntity
                    .badRequest()
                    .body(responseDTO);
        }
    }

    @PostMapping("/signin")
    public ResponseEntity<?> authenticate(@RequestBody UserDTO userDTO) {
        UserEntity user = userService.getByCredentials(
                userDTO.getEmail(), userDTO.getPassword());

        if (user != null) {
            // 토큰 생성
            final UserDTO responseUserDTO = UserDTO.builder()
                    .email(user.getEmail())
                    .id(user.getId())
                    .build();

            return ResponseEntity.ok().body(responseUserDTO);
        } else {
            ResponseDTO responseDTO = ResponseDTO.builder()
                    .error("Login failed").build();

            return ResponseEntity.badRequest().body(responseDTO);
        }
    }
}
