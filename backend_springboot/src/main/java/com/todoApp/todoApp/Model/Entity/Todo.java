package com.todoApp.todoApp.Model.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.Instant;
import java.util.Date;

@Data
@Entity

@Table(name = "todo")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true,nullable = false)
    private String todoName;

    private String todoDescription;

    private String todoColor;

    @DateTimeFormat(pattern = "dd/mm/yyyy hh:mm:ss")
    @Column(nullable = false)
    private String date;

    @Column(nullable=false, columnDefinition = "VARCHAR(255) DEFAULT '0'")
    private boolean completed;

    @DateTimeFormat(pattern = "dd/mm/yyyy hh:mm:ss")
    @CreationTimestamp()
    private Instant createdAt ;

    @ManyToOne()
    @JoinColumn(referencedColumnName = "id",name="user_id")
    private User user;

    public boolean getCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
