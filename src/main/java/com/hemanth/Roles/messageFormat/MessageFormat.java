package com.hemanth.Roles.messageFormat;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class MessageFormat
{

    private int statusCode;
    private String message;

    private Long timeStamp;

}
