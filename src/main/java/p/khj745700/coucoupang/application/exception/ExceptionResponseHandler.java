package p.khj745700.coucoupang.application.exception;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@AllArgsConstructor
public class ExceptionResponseHandler {

    @Value("${logging.level.root}")
    private String value;

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> exceptionHandler(Exception e) {
        if(!value.equals("DEBUG")) {
            return ResponseEntity.internalServerError().body("확인 불가능한 에러입니다. 다시 요청해 주세요. 재발 시 운영팀 문의 바랍니다.");
        }
        return ResponseEntity.badRequest().body(e.getStackTrace());
    }

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<?> customExceptionHandler(CustomException ce) {
        return toErrorResponse(ce);
    }

    private ResponseEntity<?> toErrorResponse(CustomException ce) {
        return ResponseEntity.status(ce.getExceptionConstants().getStatus()).body(ce.getExceptionConstants().getMessage());
    }
}
