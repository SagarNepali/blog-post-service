package edu.miu.blogpost.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "t_posts")
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({"id","content","user_id"})
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String content;
    @NotNull
    @JsonProperty("user_id")
    private Long userId;
    @Column(name="post_date")
    @UpdateTimestamp
    @JsonProperty("post_date")
    private Date postDate;

}
