package github.clyoudu.pf4jspring;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Map;

@RestController
public class TempController implements ApplicationContextAware {

    private ApplicationContext context;

    @GetMapping("/get")
    public void test() {
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

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }
}
