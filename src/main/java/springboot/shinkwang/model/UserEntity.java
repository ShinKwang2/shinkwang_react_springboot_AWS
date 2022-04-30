package springboot.shinkwang.model;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = "email")})
public class UserEntity {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;  // 사용자에게 고유하게 부여되는 id

    @Column(nullable = false)
    private String username;    // 사용자 이름

    @Column(nullable = false)
    private String email;   // 사용자의 email, 아이디와 같은 기능을 한다.

    @Column(nullable = false)
    private String password;    // 패스워드
}
