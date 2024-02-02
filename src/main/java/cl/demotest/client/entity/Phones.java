package cl.demotest.client.entity;


import lombok.*;

import javax.persistence.*;


@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Phones")
@Table(name = Phones.TABLE_NAME )
public class Phones  {

    public static final String TABLE_NAME = "phones_join_users";

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "id", unique = true,  updatable = false, nullable = false)
   private long id;

    private String number;

    private String citycode;

    private String countrycode;
}


