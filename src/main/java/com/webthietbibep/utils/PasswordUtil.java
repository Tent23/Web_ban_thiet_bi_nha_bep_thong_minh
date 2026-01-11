package com.webthietbibep.utils;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordUtil {

    // Hash mật khẩu
    public static String hash(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt(10));
    }

    // So sánh mật khẩu người nhập với hash trong DB
    public static boolean check(String password, String hash) {
        return BCrypt.checkpw(password, hash);
    }
}
