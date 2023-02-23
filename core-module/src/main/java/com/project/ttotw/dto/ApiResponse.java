package com.project.ttotw.dto;

import com.project.ttotw.enums.ApiResponseStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class ApiResponse {

    private <T, E> ResponseEntity<?> get(ApiResponseStatus status, @Nullable String message, @Nullable T data, @Nullable E errors,
                                         @Nullable Long page, @Nullable Long size, @Nullable Long total) {

        if (status.equals(ApiResponseStatus.SUCCESS)) {
            //pagedBody
            if (page != null && size != null && total != null) {
                return new ResponseEntity<>(PagedBody.builder()
                        .status(status.getValue())
                        .message(message != null ? message : "")
                        .data(data != null ? data : Collections.emptyList())
                        .page(page)
                        .size(size)
                        .total(total),
                        HttpStatus.OK);
            }
            //succeededBody
            else {
                return new ResponseEntity<>(SucceededBody.builder()
                        .status(status.getValue())
                        .message(message != null ? message : "")
                        .data(data != null ? data : null),
                        HttpStatus.OK);
            }
        }

        else if (status.equals(status.equals(ApiResponseStatus.FAIL))) {
            return new ResponseEntity<>(FailedBody.builder()
                    .status(status.getValue())
                    .message(message != null ? message : "")
                    .errors(errors != null ? errors : null),
                    HttpStatus.OK);
        }

        else if (status.equals(ApiResponseStatus.ERROR)) {
            return new ResponseEntity<>(ErroredBody.builder()
                    .status(status.getValue())
                    .message(message != null ? message : ""),
                    HttpStatus.OK);
        }

        else {
            throw new RuntimeException("");
        }
    }

    /**
     * <p>성공 응답을 반환합니다. 첫 번째 인자는 message, 두 번째 인자는 data 에 표시됩니다.</p>
     * <pre>
     *  {
     *      "status" : "success",
     *      "message" : "success message",
     *      "data" : "배열 또는 단일 데이터"
     *  }
     * </pre>
     *
     * @param message   응답 바디 message 필드에 포함될 정보
     * @param data      응답 바디 data 필드에 포함될 정보
     * @return          응답 객체
     */
    public <T> ResponseEntity<?> success(String message, T data) {
        return get(ApiResponseStatus.SUCCESS, message, data, null, null, null, null);
    }

    /**
     * <p>성공 응답을 반환합니다. 전달된 인자는 data 에 표시됩니다.</p>
     * <pre>
     *  {
     *      "status" : "success",
     *      "message" : null,
     *      "data" : "배열 또는 단일 데이터"
     *  }
     * </pre>
     *
     * @param data      응답 바디 data 필드에 포함될 정보
     * @return          응답 객체
     */
    public <T> ResponseEntity<?> success(T data) {
        return get(ApiResponseStatus.SUCCESS, null, data, null, null, null, null);
    }

    /**
     * <p>성공 응답을 반환합니다.</p>
     * <pre>
     *  {
     *      "status" : "success",
     *      "message" : null,
     *      "data" : null
     *  }
     * </pre>
     *
     * @return          응답 객체
     */
    public <T> ResponseEntity<?> success() {
        return get(ApiResponseStatus.SUCCESS, null, null, null, null, null, null);
    }

    //TODO:: 페이지네이션 정보를 포함한 성공 응답을 반환한다.
    //TODO:: 실패 응답을 반환한다.

    /**
     * <p>예외 발생 시 에러 응답을 반환합니다.</p>
     * <pre>
     *     {
     *         "status" : "error",
     *         "message" : "custom error message"
     *     }
     * </pre>
     *
     * @param message   응답 바디 message 필드에 포함될 정보
     * @return          응답 객체
     */
    public <T> ResponseEntity<?> error(String message) {
        return get(ApiResponseStatus.ERROR, message, null, null, null, null, null);
    }

    /**
     * <p>성공 응답 객체의 바디</p>
     */
    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class SucceededBody<T> {

        private String status;
        private String message;
        private T data;
    }

    /**
     * <p>페이지네이션 정보가 포함된 응답 객체의 바디</p>
     */
    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class PagedBody<T> {

        private String status;
        private String message;
        private T data;
        private Long page;
        private Long size;
        private Long total;
    }

    /**
     * <p>실패 응답 객체의 바디</p>
     */
    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class FailedBody<E> {

        private String status;
        private String message;
        private E errors;
    }

    /**
     * <p>오류 응답 객체의 바디</p>
     */
    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ErroredBody {

        private String status;
        private String message;
    }

}
