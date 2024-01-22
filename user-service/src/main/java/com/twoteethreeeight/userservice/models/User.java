package com.twoteethreeeight.userservice.models;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@Document("user")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
    @Id
    private ObjectId _id;

    @NotBlank(message = "Name can not be blank.")
    private String fullName;

    @Indexed(unique = true)
    @Email(message = "Email is invalid.")
    private String email;

    @Pattern(regexp = "^[0-9]{10}$", message = "Phone number must contains ten digits.")
    private String phone;

    private String role;

}
