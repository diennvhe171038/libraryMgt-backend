package swp391.learning.domain.enums;

import lombok.Getter;

@Getter
public enum ResponseCode { // thang cong: 200, that bai: 400, khong tim thay: 404, Khong ton tai:401
    SUCCESS(200, "Success"),
    FAIL(400, "Fail"),
    USER_NOT_FOUND(404, "User not found"),
    PASSWORD_INCORRECT(400, "Password incorrect"),
    OTP_INCORRECT(400, "OTP incorrect"),
    Expired_OTP(400, "Expired OTP"),
    USER_EXIST(400, "User exist"),
    OLD_PASSWORD_INCORRECT(400, "Old password incorrect"),
//    category
    CATEGORY_EXIST(400,"Category exist"),
    CATEGORY_NOT_EXIST(401,"Category not exist"),
    CATEGORY_LIST_IS_EMPTY(400,"Category list is empty"),;


    private final int code;
    private final String message;

    ResponseCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}

