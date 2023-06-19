// File: dropdown.js

// Đợi tài liệu HTML tải xong
$(document).ready(function() {
    // Ẩn ban đầu `.user-login-dropdown`
    $('.user-login-dropdown').css('display', 'none');

    // Bắt sự kiện khi di chuột vào `.user-login-info`
    $('.user-login-info').mouseenter(function() {
        // Hiển thị `.user-login-dropdown` khi di chuột vào `.user-login-info`
        $(this).find('.user-login-dropdown').css('display', 'block');
    });

    // Bắt sự kiện khi di chuột ra khỏi `.user-login-info`
    $('.user-login-info').mouseleave(function() {
        // Ẩn `.user-login-dropdown` khi di chuột ra khỏi `.user-login-info`
        $(this).find('.user-login-dropdown').css('display', 'none');
    });
});
<!--Start of Tawk.to Script-->
var Tawk_API=Tawk_API||{}, Tawk_LoadStart=new Date();
(function(){
var s1=document.createElement("script"),s0=document.getElementsByTagName("script")[0];
s1.async=true;
s1.src='https://embed.tawk.to/648ade1ccc26a871b022ae62/1h2v778mc';
s1.charset='UTF-8';
s1.setAttribute('crossorigin','*');
s0.parentNode.insertBefore(s1,s0);
})();
<!--End of Tawk.to Script-->