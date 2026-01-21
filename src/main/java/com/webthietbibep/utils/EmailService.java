package com.webthietbibep.utils;

public class EmailService {

    public static void sendVerifyEmail(String toEmail, String token) {
        String verifyLink = "http://localhost:8080/verify?token=" + token;
        String content = """
            <h3>Xác nhận tài khoản</h3>
            <p>Vui lòng click link bên dưới để xác nhận email:</p>
            <a href="%s">Xác nhận tài khoản</a>
        """.formatted(verifyLink);

        // dùng JavaMail (bạn đã từng hỏi App Password → OK)
        MailUtil.sendHtmlMail(toEmail, "Xác nhận tài khoản", content);
    }
}
