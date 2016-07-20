package cn.huntdog.first;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;

/**
 * Created by JonDai {jondai@aliyun.com} on 2016/3/10.
 */

//@EnableScheduling
@SpringBootApplication
public class Application implements EmbeddedServletContainerCustomizer {
    public static void main(String[] args){
        SpringApplication.run(Application.class, args);
    }


    /**
     * 通过实现EmbeddedServletContainerCustomizer接口来自定义启动项
     * @param configurableEmbeddedServletContainer
     */
    @Override
    public void customize(ConfigurableEmbeddedServletContainer configurableEmbeddedServletContainer) {
        configurableEmbeddedServletContainer.setPort(8081);
    }
}