package net.engineeringdigest.journalApp.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BaseResponse<T> {

    private String code;
    private String msg;
    private T data;

}
