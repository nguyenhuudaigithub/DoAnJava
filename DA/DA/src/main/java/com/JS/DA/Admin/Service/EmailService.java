package com.JS.DA.Admin.Service;

import com.JS.DA.Admin.Model.EmailForm;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender mailSender;

    public void sendHtmlEmail(EmailForm emailForm) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            String recipient = "nguyenhuudaihs@gmail.com";
            String subject = emailForm.getTieuDe();

            StringBuilder content = new StringBuilder();
            content.append("<html><head>");
            content.append("<style>");
            content.append("body { font-family: Arial, sans-serif; }");
            content.append(".container { max-width: 1200px; margin: 0 auto; padding: 20px; background-color: #f7f7f7; }");
            content.append(".header { text-align: center; margin-bottom: 20px; }");
            content.append(".header h1 { color: #333; }");
            content.append(".content { background-color: #fff; padding: 20px; border-radius: 5px; }");
            content.append(".section { margin-bottom: 20px; }");
            content.append(".section-title { color: #333; font-size: 18px; font-weight: bold; margin-bottom: 10px; }");
            content.append(".section-content { color: #777; }");
            content.append("hr { border: none; border-top: 1px solid #ddd; margin: 20px 0; }");
            content.append("</style></head><body>");
            content.append("<div class=\"container\">");
            content.append("<div class=\"header\"><h1>Linh Kiện 24/7</h1></div>");
            content.append("<div class=\"content\">");
            content.append("<div class=\"section\">");
            content.append("<div class=\"section-title\">Mail</div>");
            content.append("<div class=\"section-content\">");
            content.append("<p><strong>Họ tên:</strong> ").append(emailForm.getHoTen()).append("</p>");
            content.append("<p><strong>Email:</strong> ").append(emailForm.getEmail()).append("</p>");
            content.append("<p><strong>Điện thoại:</strong> ").append(emailForm.getDienThoai()).append("</p>");
            content.append("<p><strong>Báo cáo/ Hỗ trợ:</strong> ").append(emailForm.getDeTaiName()).append("</p>");
            content.append("</div></div>");

            // Lấy nội dung HTML đã được tạo bởi CKEditor từ trường noiDungGopY
            String ckEditorContent = emailForm.getNoiDungGopY();
            // Kiểm tra nếu có nội dung từ CKEditor
            if (ckEditorContent != null && !ckEditorContent.isEmpty()) {
                content.append("<div class=\"section\">");
                content.append("<div class=\"section-title\">Nội dung</div>");
                content.append("<div class=\"section-content\">");
                content.append(ckEditorContent);
                content.append("</div></div>");
            }

            content.append("</div></div></body></html>");

            helper.setTo(recipient);
            helper.setSubject(subject);
            helper.setText(content.toString(), true);

            mailSender.send(message);
        } catch (MailException | MessagingException e) {
            e.printStackTrace();
        }
    }

}
