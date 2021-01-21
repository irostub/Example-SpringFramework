package hello.hellospring.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

//Jpa와 연동
@Entity
public class Member {

    //Id 어노테이션 : PK 세팅, GeneratedValue 어노테이션 : IDENTITY&AutoIncrement와 같음
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //DB에 name이란 이름으로 컬럼명을 설정하지 않았다면 @Coloum(name="") 어노테이션으로 매핑가능
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
