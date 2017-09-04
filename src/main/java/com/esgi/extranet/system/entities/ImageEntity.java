package com.esgi.extranet.system.entities ;

import lombok.* ;
import org.codehaus.jackson.annotate.JsonIgnoreProperties ;

import javax.persistence.* ;
import javax.validation.constraints.NotNull ;

/**
 * Created by Samuel Bijou on 28/06/2017.
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name="image")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ImageEntity
{

    @NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id ;

    @NotNull
    @Column(nullable = false)
    private String name ;

    @NotNull
    @Column(nullable = false)
    private byte[] datas ;

}
