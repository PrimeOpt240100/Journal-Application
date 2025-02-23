package net.engineeringdigest.journalApp.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JournalApplicationApiResponse {

    private int code;
    private String msg;
    private Object data;

}
