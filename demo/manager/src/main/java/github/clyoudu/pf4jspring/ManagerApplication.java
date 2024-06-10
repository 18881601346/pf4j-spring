package github.clyoudu.pf4jspring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Map;

/**
 * @author leichen
 */
@SpringBootApplication
public class ManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ManagerApplication.class, args);
    }


    @Component
    public class BeanPrinter implements ApplicationRunner {

        @Autowired
        private ApplicationContext context;

        @Override
        public void run(ApplicationArguments args) {
            // 打印Bean定义信息
            String[] beanNames = context.getBeanDefinitionNames();
            Arrays.sort(beanNames);
            for (String beanName : beanNames) {
                System.out.println(beanName);
            }

            // 获取所有Bean实例
            // 获取 singletonObjects Map，其中保存了所有单例 Bean 的实例
            Map<String, Object> singletonObjects = context.getBeansOfType(Object.class);
            // 打印所有 registeredSingletons 的信息
            singletonObjects.forEach((beanName, beanInstance) -> {
                System.out.println("Bean Name: " + beanName + ", Bean Instance: " + beanInstance);
            });
        }
    }
}
