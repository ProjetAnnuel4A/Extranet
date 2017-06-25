package com.esgi.extranet.planning.entities;

import lombok.*;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.joda.time.DateTime;

import javax.persistence.*;
import java.util.Date;

/**
 * @author timotheearnauld
 */
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="attribution")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ScheduleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String start;

    @Column
    private String end;

    @Column
    private String title;
}
