package test.testtwo.common.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseDTO {
    private boolean successful;
    private Object data;
    private String message;
    private String title;
    private Integer code;
}
