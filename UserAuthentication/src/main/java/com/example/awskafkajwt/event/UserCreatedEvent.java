package com.example.awskafkajwt.event;

import com.example.awskafkajwt.entity.User;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class UserCreatedEvent extends Event<User>{

}
