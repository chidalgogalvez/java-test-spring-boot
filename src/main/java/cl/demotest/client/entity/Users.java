package cl.demotest.client.entity;

import cl.demotest.client.util.Constants;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.UUID;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class Users {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "isActive")
    private String isActive;


    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "createAt")
    @JsonFormat(pattern = "yyy-MM-dd HH:mm:ss", timezone= Constants.TIME_ZONE)
    private Date createAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updateAt")
    @JsonFormat(pattern = "yyy-MM-dd HH:mm:ss", timezone= Constants.TIME_ZONE)
    private Date updateAt;


    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "phones_users_id")
    private List<Phones> phones;



}
