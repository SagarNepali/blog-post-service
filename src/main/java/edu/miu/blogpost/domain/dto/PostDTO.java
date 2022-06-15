package edu.miu.blogpost.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import edu.miu.blogpost.dto.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Builder
@AllArgsConstructor
@Data
@NoArgsConstructor
public class PostDTO {
    private Long id;
    private String content;
    private User user;
    private Date postDate;
}
