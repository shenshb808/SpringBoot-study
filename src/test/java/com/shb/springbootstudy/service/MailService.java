package com.shb.springbootstudy.service;

import com.shb.springbootstudy.SpringbootStudyApplication;
import com.shb.springbootstudy.domain.entity.User;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Date;

@SpringBootTest
public class MailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    TemplateEngine templateEngine;

    /**
     * 发送简单邮件
     */
    @Test
    void send(){
        //发送简单邮件
        SimpleMailMessage message = new SimpleMailMessage();
        //设置邮件主题
        message.setSubject("这是一封测试邮件");
        //设置邮件发送者
        message.setFrom("782846415@qq.com");
        //设置邮件接收者，可以有多个接收者
        message.setTo("shenshb808@126.com");
        //设置邮件抄送人，可以有多个抄送人
        //message.setCc("37xxxxx37@qq.com");
        //设置隐秘抄送人，可以有多个
        //message.setBcc("14xxxxx098@qq.com");
        //设置邮件发送日期
        message.setSentDate(new Date());
        //设置邮件的正文
        message.setText("这是测试邮件的正文");
        javaMailSender.send(message);
    }

    /**
     * 图片附件邮件发送
     * @throws MessagingException
     */
    @Test
    void send2() throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,true);
        helper.setSubject("这是一封测试邮件");
        helper.setFrom("782846415@qq.com");
        helper.setTo("shenshb808@126.com");
        //helper.setCc("37xxxxx37@qq.com");
        //helper.setBcc("14xxxxx098@qq.com");
        helper.setSentDate(new Date());
        helper.setText("这是测试邮件的正文");
        helper.addAttachment("javaboy.jpg",new File("F:\\头像\\1612510689.jpg"));
        javaMailSender.send(mimeMessage);
    }

    @Test
    void test3() throws MessagingException, IOException, TemplateException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setSubject("这是一封测试邮件");
        helper.setFrom("782846415@qq.com");
        helper.setTo("shenshb808@126.com");
        //helper.setCc("37xxxxx37@qq.com");
        //helper.setBcc("14xxxxx098@qq.com");
        helper.setSentDate(new Date());
        //构建 Freemarker 的基本配置
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_0);
        // 配置模板位置
        ClassLoader loader = SpringbootStudyApplication.class.getClassLoader();
        configuration.setClassLoaderForTemplateLoading(loader, "templates");
        //加载模板
        Template template = configuration.getTemplate("mail.ftl");
        User user = new User();
        user.setUsername("javaboy");
        user.setNum(1);
        user.setSalary((double) 99999);
        StringWriter out = new StringWriter();
        //模板渲染，渲染的结果将被保存到 out 中 ，将out 中的 html 字符串发送即可
        template.process(user, out);
        helper.setText(out.toString(),true);
        javaMailSender.send(mimeMessage);
    }

    @Test
    void test4() throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setSubject("这是一封测试邮件");
        helper.setFrom("782846415@qq.com");
        helper.setTo("shenshb808@126.com");
        //helper.setCc("37xxxxx37@qq.com");
        //helper.setBcc("14xxxxx098@qq.com");
        helper.setSentDate(new Date());
        Context context = new Context();
        context.setVariable("username", "javaboy");
        context.setVariable("num","000001");
        context.setVariable("salary", "99999");
        String process = templateEngine.process("mail.html", context);
        helper.setText(process,true);
        javaMailSender.send(mimeMessage);
    }

}
