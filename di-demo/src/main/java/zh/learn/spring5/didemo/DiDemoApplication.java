package zh.learn.spring5.didemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import zh.learn.spring5.didemo.controllers.ConstructorInjectedController;
import zh.learn.spring5.didemo.controllers.GetterInjectedController;
import zh.learn.spring5.didemo.controllers.MyController;
import zh.learn.spring5.didemo.controllers.PropertyInjectedController;
import zh.learn.spring5.didemo.examplebeans.FakeDataSource;
import zh.learn.spring5.didemo.examplebeans.FakeJmsBroker;

@SpringBootApplication
public class DiDemoApplication {

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(DiDemoApplication.class, args);

        MyController controller = (MyController) ctx.getBean("myController");

        System.out.println(controller.hello());
        System.out.println(ctx.getBean(PropertyInjectedController.class).sayHello());
        System.out.println(ctx.getBean(GetterInjectedController.class).sayHello());
        System.out.println(ctx.getBean(ConstructorInjectedController.class).sayHello());

        System.out.println("\n----- Fake data source");
        FakeDataSource fakeDataSource = ctx.getBean(FakeDataSource.class);
        System.out.println(fakeDataSource.getUser());
        System.out.println(fakeDataSource.getPassword());
        System.out.println(fakeDataSource.getUrl());

        System.out.println("\n----- Fake JMS broker");
        FakeJmsBroker fakeJmsBroker = ctx.getBean(FakeJmsBroker.class);
        System.out.println(fakeJmsBroker.getUsername());
        System.out.println(fakeJmsBroker.getPassword());
        System.out.println(fakeJmsBroker.getUrl());
    }
}