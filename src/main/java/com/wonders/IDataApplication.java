package com.wonders;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.core.convert.converter.Converter;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

@SpringBootApplication
@EnableScheduling
@Slf4j
@EnableAsync
@EnableCaching
@MapperScan("com.wonders.**.dao")
public class IDataApplication {

    public static void main(String[] args) {
        SpringApplication.run(IDataApplication.class, args);
    }

    @Bean("myTaskExecutor")
    public Executor myTaskExecutor() {
        ThreadPoolTaskScheduler executor = new ThreadPoolTaskScheduler();
        executor.setPoolSize(10);//核心线程数量，线程池创建时候初始化的线程数
        executor.setThreadNamePrefix("myTask-");//设置好了之后可以方便我们定位处理任务所在的线程池
        executor.setWaitForTasksToCompleteOnShutdown(true);//用来设置线程池关闭的时候等待所有任务都完成再继续销毁其他的Bean
        //线程池对拒绝任务的处理策略：这里采用了CallerRunsPolicy策略，当线程池没有处理能力的时候，该策略会直接在 execute 方法的调用线程中运行被拒绝的任务；如果执行程序已关闭，则会丢弃该任务
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());

        executor.setRemoveOnCancelPolicy(true);//与future.cancel()方法结合，队列中的任务被删除。
        return executor;
    }

    /**
     * 注册日期绑定
     * @return
     */
    @Bean
    public Converter<String, Date> addNewConvert() {
        return new Converter<String, Date>() {
            @Override
            public Date convert(String source) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date = null;
                try {
                    date = sdf.parse(source);
                } catch (ParseException e) {
                    sdf = new SimpleDateFormat("yyyy-MM-dd");
                    try {
                        date = sdf.parse(source);
                    } catch (ParseException e1) {
                    }

                }
                return date;
            }
        };
    }


}
