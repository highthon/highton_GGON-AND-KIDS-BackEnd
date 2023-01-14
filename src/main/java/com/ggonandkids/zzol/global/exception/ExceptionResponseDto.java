package com.ggonandkids.zzol.global.exception;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@Builder
public class ExceptionResponseDto {

    @JsonProperty(value = "timestamp", index = 1)
    private final LocalDateTime localDateTime = LocalDateTime.now();
    private final int status;
    private final HttpStatus httpStatus;
    private final String message;
}
