import com.easy.RunDemoApplication;
import com.easy.entity.po.Demo;
import com.easy.entity.query.DemoQuery;
import com.easy.entity.query.UserInfoQuery;
import com.easy.mapper.DemoMapper;
import com.easy.service.DemoService;
import com.easy.service.TestService;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.minidev.json.JSONArray;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;


@SpringBootTest(classes = RunDemoApplication.class) // 明确指定启动类
public class SpringTestV1 {
    SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    ObjectMapper mapper = new ObjectMapper();
    @Autowired
    private TestService testService;
    @Autowired
    private DemoMapper demoMapper;
    @Autowired
    private DemoService demoService;


    @Test
    public void demo(){


    }

    @Test
    public void select() {
        demoService.insertBatch(null);



        DemoQuery demoQuery = new DemoQuery();
        demoQuery.setIdList(Arrays.asList(1, 2, 3));


        /*demoQuery.setPageOffset(0);
        demoQuery.setPageSize(2);
        List<Demo> test = testService.selectList(null);
        test.forEach(System.out::println);*/
        Integer count = testService.selectCount(null);
        System.out.println("数量:" + count);
    }

    @Test
    public void insert() throws ParseException {
        Demo demo = new Demo();
        demo.setName("河池-v9");
        demo.setAge(13);
        Demo demo2 = new Demo();
        demo2.setName("河池-v10");
        demo2.setAge(15);

        testService.insertOrUpdate(null);

    }

    @Test
    public void update() {
        Demo demo = new Demo();
        DemoQuery demoQuery = new DemoQuery();

        demo.setAge(20);
        demo.setName("河池");

        demoQuery.setNameList(Lists.list("河池"));
        //testService.updateByCondition(demo, demoQuery);

        testService.updateById(demo, 9);


    }

    @Test
    public void delete() {



    }

    public static void main(String[] args) {
        test("1",null);

    }

    public static void test(String... str) {
        System.out.println(str);
        if (str != null) {
            System.out.println(Arrays.toString(str));
        }
        // 1. 转为可变 List
        List<String> list = new ArrayList<>(Arrays.asList(str));
        list.removeIf(Objects::isNull);
        String[] newArray = list.toArray(new String[0]);
        test2(newArray);
    }
    public static void test2(String... str) {
        // 使用str
        System.out.println(Arrays.toString(str));
    }

}
