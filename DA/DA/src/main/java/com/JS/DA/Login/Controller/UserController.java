package com.JS.DA.Login.Controller;

import com.JS.DA.Login.Model.User;
import com.JS.DA.Login.Service.UserService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private JavaMailSender javaMailSender;
    @GetMapping("/login")
    public String login() {
        return "login/login";
    }
    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("user", new User());
        return "login/register";
    }
    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("user") User user,
                           BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors) {
                model.addAttribute(error.getField() + "_error",
                        error.getDefaultMessage());
            }
            return "login/register";
        }
        sendThankYouEmail(user.getEmail(), user.getName(), user.getAddress(), user.getPhoneNumber(), user.getUsername()); // gửi mail cảm ơn
        user.setPhoneNumber(user.getPhoneNumber());
        user.setAddress(user.getAddress());
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword())); // mã hóa BCryptPasswordEncoder
        userService.save(user);
        return "redirect:/login";
    }
    private void sendThankYouEmail(String recipientEmail, String recipientName, String address, String phoneNumber, String username) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setTo(recipientEmail);
            helper.setSubject("Linh Kiện 24/7 - Cảm Ơn Bạn Đã Đăng Ký Tài Khoản");

            String content = "<html><head>";
            content += "<style>";
            content += "body { font-family: Arial, sans-serif; font-size: 14px; line-height: 1.5; color: #333; }";
            content += ".container { max-width: 600px; margin: 0 auto; padding: 20px; background-color: #f7f7f7; }";
            content += ".header h1 { color: #333; font-size: 24px; text-align: center; margin-bottom: 20px; }";
            content += ".content { background-color: #fff; padding: 20px; border-radius: 5px; }";
            content += ".section { margin-bottom: 20px; }";
            content += ".section-title { color: #333; font-size: 18px; font-weight: bold; margin-bottom: 10px; }";
            content += ".section-content { color: #555; }";
            content += "hr { border: none; border-top: 1px solid #ddd; margin: 20px 0; }";
            content += "</style></head><body>";
            content += "<div class=\"container\">";
            content += "<div class=\"header\"><h1>Linh Kiện 24/7</h1></div>";
            content += "<div class=\"content\">";
            content += "<div class=\"section\">";
            content += "<div class=\"section-title\">Xin chào " + recipientName + ",</div>";
            content += "<div class=\"section-content\">";
            content += "<p>Lời đầu tiên, chúng tôi xin gửi lời cảm ơn sâu sắc nhất tới bạn đã tin tưởng và đăng ký tài khoản tại Linh Kiện 24/7.</p>";
            content += "<p>Dưới đây là thông tin chi tiết về đăng ký của bạn:</p>";
            content += "<p><strong>Tài khoản:</strong> " + username + "</p>";
            content += "<p><strong>Email:</strong> " + recipientEmail + "</p>";
            content += "<p><strong>Họ tên:</strong> " + recipientName + "</p>";
            content += "<p><strong>Địa chỉ:</strong> " + address + "</p>";
            content += "<p><strong>Điện thoại:</strong> " + phoneNumber + "</p>";
            content += "<hr>";
            content += "<p>Nếu có bất kỳ thắc mắc hoặc yêu cầu hỗ trợ, vui lòng liên hệ với chúng tôi:</p>";
            content += "<div style=\"display: flex; align-items: center;\">";
            content += "<p>Tổng Đài: 0123456789</p>";
            content += "<p style=\"margin-left: 20px;\">Email: linhkien247@gmail.com</p>";
            content += "</div>";
            content += "<p>Chúng tôi luôn sẵn sàng phục vụ bạn 24/7.</p>";
            content += "</div></div>";
            content += "<div style=\"text-align: right;\">";
            content += "<p>Trân trọng,</p>";
            content += "<p>Đội ngũ quản trị</p>";
            content += "</div></div></div></body></html>";

            helper.setText(content, true);

            javaMailSender.send(message);

            System.out.println("Cảm ơn bạn đã đăng ký! Email cảm ơn đã được gửi thành công.");
        } catch (MessagingException e) {
            System.out.println("Có lỗi xảy ra trong quá trình gửi email: " + e.getMessage());
        }
    }
    @GetMapping("/account")
    public String showAccountInfo(Model model, Principal principal) {
        // Lấy tên tài khoản người dùng từ Principal
        String username = principal.getName();

        // Gọi userService để lấy thông tin người dùng từ tên tài khoản
        User user = userService.findByUsername(username);

        // Chuyển thông tin người dùng vào model để hiển thị trong view
        model.addAttribute("user", user);

        // Trả về tên view hiển thị thông tin tài khoản (ví dụ: "account-info")
        return "user/account/account-info";
    }
    @PostMapping("/account/update")
    public String updateAccountInfo(@ModelAttribute("user") User updatedUser, Principal principal) {
        // Lấy tên tài khoản người dùng từ Principal
        String username = principal.getName();

        // Lấy thông tin người dùng hiện tại từ tên tài khoản
        User currentUser = userService.findByUsername(username);

        // Cập nhật thông tin người dùng
        currentUser.setUsername(updatedUser.getUsername());
        currentUser.setAddress(updatedUser.getAddress());
        currentUser.setPhoneNumber(updatedUser.getPhoneNumber());

        // Lưu thông tin người dùng đã cập nhật vào cơ sở dữ liệu
        userService.save(currentUser);

        // Chuyển hướng về trang thông tin tài khoản sau khi cập nhật thành công
        return "redirect:/account";
    }


}
