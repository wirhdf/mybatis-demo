package com.wish.mybatisdemo;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.ShearCaptcha;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.TimeInterval;
import cn.hutool.core.img.ImgUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.io.file.FileAppender;
import cn.hutool.core.io.resource.ClassPathResource;
import cn.hutool.core.io.resource.ResourceUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.*;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import cn.hutool.poi.word.Word07Writer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wish.mybatisdemo.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.util.StringUtils;

import java.awt.*;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.List;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SimpleTest {


    @Test
    public void test01() {
        System.out.println(StringUtils.capitalize("abc"));
    }


    @Test
    public void test02() {
        User user = new User();
        User user1 = Optional.ofNullable(user).orElseThrow(() -> new RuntimeException("user不能为空！"));
        List<Object> objects = Collections.emptyList();
        System.out.println(user1);
    }

    @Test
    public void testHutool() {
        TimeInterval timer = DateUtil.timer();
        Random random = new Random();
        try {
            Thread.sleep(random.nextInt(10) * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long interval = timer.interval();
        System.out.println("花费毫秒数:" + timer.interval());
        System.out.println("花费分钟数:" + timer.intervalMinute());

    }

    @Test
    public void testHutoolIOCopy() {
        BufferedInputStream inputStream = FileUtil.getInputStream("D:\\temp\\codes\\test.txt");
        BufferedOutputStream outputStream = FileUtil.getOutputStream("D:\\temp\\codes\\test_copy.txt");
        IoUtil.copy(inputStream, outputStream);
    }

    @Test
    public void testHutoolIO() {
        FileAppender appender = new FileAppender(FileUtil.file("D:\\\\temp\\\\codes\\\\test_copy.txt"), 16, true);
        appender.append("\r\n");
        appender.append("123");
        appender.append("abc");
        appender.append("xyz");
        appender.flush();
        System.out.println(appender.toString());
    }


    @Test
    public void testHutoolResource() {
        String str = ResourceUtil.readUtf8Str("test.txt");
        System.out.println(str);
    }

    @Test
    public void testHutool02() {
        String abc = StrUtil.upperFirst("abc");
        System.out.println(abc);
        String s = StrUtil.lowerFirst(abc);
        System.out.println(s);

        boolean equal = ObjectUtil.equal(abc, s);
        Method[] methods = ReflectUtil.getMethods(User.class);

    }

    @Test
    public void testResource() {
        try {
            ClassPathResource resource = new ClassPathResource("test.txt");
            InputStream inputStream = resource.getStream();
            String read = IoUtil.read(inputStream, "UTF-8");
            System.out.println(read);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testBigDecimal() {
        String s = NumberUtil.decimalFormat("00.00", 123.000);
        System.out.println(s);
        int[] ints = NumberUtil.generateRandomNumber(0, 10, 2);
        System.out.println(Arrays.toString(ints));
        int[] range = NumberUtil.range(0, 100);
        long factorial = NumberUtil.factorial(2);
        System.out.println(factorial);
        String join = StrUtil.join("", ints);
        System.out.println(join);
    }

    @Test
    public void testArrayUtil() {
        int[] arr = {};
        Integer[] arr2 = {};
        boolean empty = ArrayUtil.isEmpty(arr);
        Integer[] wrap = ArrayUtil.wrap(arr);
        this.getClass().isArray();
    }

    @Test
    public void testRandomUtil() {
        for (int i = 0; i < 10; i++) {
            int randomInt = RandomUtil.randomInt(5);
            System.out.println(randomInt);
        }
        String s = RandomUtil.randomString(5);
        System.out.println(s);
        System.out.println(RandomUtil.randomUUID());
    }

    @Test
    public void testIdUtil() {
        Snowflake snowflake = IdUtil.createSnowflake(1, 1);
        long id = snowflake.nextId();
        System.out.println(id);
    }

    @Test
    public void testZipUtil() {
        ZipUtil.zip("D:\\temp\\codes", "D:\\temp\\alibaba.zip", true);
        FileUtil.file("d:/test1/file1.txt");
    }

    @Test
    public void testReUtil() {
        String content = "ZZZaaabbbccc中文1234";
        String resultDelFirst = ReUtil.delFirst("(\\w)aa(\\w)", content);
        System.out.println(resultDelFirst);
    }

    @Test
    public void testValidator() {
    }

    @Test
    public void testImgUtil() {
        ImgUtil.pressText(//
                FileUtil.file("D:\\temp\\codes\\xxx.jpg"), //
                FileUtil.file("D:\\temp\\codes\\xxx_result2.jpg"), //
                "版权所有", Color.WHITE, //文字
                new Font("黑体", Font.BOLD, 100), //字体
                200, //x坐标修正值。 默认在中间，偏移量相对于中间偏移
                -100, //y坐标修正值。 默认在中间，偏移量相对于中间偏移
                0.9f//透明度：alpha 必须是范围 [0.0, 1.0] 之内（包含边界值）的一个浮点数字
        );
    }

    @Test
    public void testBeanUtil() {
        User user = new User();
        user.setId(1L);
        user.setUsername("zs");
        user.setPassword("123");

//        Map<String, Object> stringObjectMap = BeanUtil.beanToMap(user);
        Map<String, Object> stringObjectMap1 = BeanUtil.beanToMap(user, false, true);
        stringObjectMap1.forEach((s, o) -> System.out.println("key:" + s + "--> value:" + o));


    }

    @Test
    public void testCollUtil() {
    }


    @Test
    public void testExcelUtil() {
        User user1 = new User();
        User user2 = new User();
        User user3 = new User();
        ArrayList<User> users = CollUtil.newArrayList(user1, user2, user3);
        ExcelWriter writer = ExcelUtil.getWriter("d:/writeTest3.xlsx");
        writer.write(users);
        writer.close();

    }

    @Test
    public void testWordUtil() {
        Word07Writer writer = new Word07Writer();
        writer.addText(new Font("宋体", Font.PLAIN, 22), "我是第一部分");
        writer.addText(new Font("宋体", Font.PLAIN, 22), "我是第二部分");
        writer.addText(new Font("宋体", Font.PLAIN, 22), "我是第三部分");
        writer.addText(new Font("宋体", Font.PLAIN, 22), "我是第四部分");
        writer.addText(new Font("宋体", Font.PLAIN, 22), "我是第五部分");

        // 写出到文件
        writer.flush(FileUtil.file("d:/wordWrite.docx"));
        // 关闭
        writer.close();

    }


    @Test
    public void testxxx() {
        //定义图形验证码的长、宽、验证码字符数、干扰线宽度
        ShearCaptcha captcha = CaptchaUtil.createShearCaptcha(200, 100, 4, 4);
        //ShearCaptcha captcha = new ShearCaptcha(200, 100, 4, 4);
        //图形验证码写出，可以写出到文件，也可以写出到流
        captcha.write("d:/shear.png");
    }

    @Test
    public void testAssert() {
        boolean s = false;
        Assert.isTrue(s, "不能为空");
    }


    @Test
    public void testFunctionInterface() {
//        String[] array = new String[]{"Apple", "Orange", "Banana", "Lemon", "Daxx", "Cioio", "apple"};
//
//        Arrays.sort(array, (s1, s2) -> s1.compareToIgnoreCase(s2));
//        System.out.println(Arrays.toString(array));

        User user1 = new User(1L, "zs");
        User user2 = new User(2L, "ls");
        User user3 = new User(3L, "ww");
        User user4 = new User(4L, "sb");
        User user5 = new User(4L, "sa");

        ArrayList<User> userList = new ArrayList<>();
        userList.add(user1);
        userList.add(user2);
        userList.add(user3);
        userList.add(user4);
        userList.add(user5);


        Map<Long, String> map = userList.stream().collect(Collectors.toMap(User::getId, User::getUsername, (s, s2) -> s, LinkedHashMap::new));

        System.out.println(map);


//        Map<Long, String> collect = userList.stream().collect(
//                Collectors.toMap(
//                        user -> user.getId(),
//                        user -> user.getUsername()
//                ));
//
//        collect.forEach((k, v) -> System.out.println(k + "-->" + v));
//        List<Long> idList = userList.stream().map(User::getId).sorted().distinct().collect(Collectors.toList());
//        long count = userList.stream().map(User::getId).sorted().distinct().limit(1).count();

//        System.out.println(count);
//        System.out.println(idList);
//        System.out.println(idList instanceof ArrayList);
//        idList.forEach(System.out::println);


//
////        System.out.println(userList.toString());
////
////        System.out.println();
////
//////        userList.sort((u1,u2) -> u1.getId().compareTo(u2.getId()));
//////        userList.sort(Comparator.comparing(user -> user.getId()));
//        userList.sort(Comparator.comparing(User::getId).reversed().thenComparing(User::getUsername));
////
////        System.out.println(userList.toString());
////
////        Supplier<Long> id = user1::getId;
//        Function<String, Integer> getHello = user1::getNameLength;
//        Integer length = getHello.apply("aaa");
//        System.out.println(length);

    }

    @Test
    public void testSplit() throws RuntimeException {
//        List<String> list = Arrays.asList("Apple", "Orange", "Banana", "Lemon", "Daxx", "Cioio", "apple","Org","Bat","Longdan","Dad");
//
//        Map<String, List<String>> collectMap = list.stream().collect(Collectors.groupingBy(s -> s.substring(0, 1)));
//        collectMap.forEach((k, v) -> System.out.println(k+"-->"+v));

        List<Integer> numList = Arrays.asList(5, 2, 4, 1, 3, 9, 7, 22, 18);
        Optional<Integer> maxNum = numList.stream().collect(Collectors.maxBy(Comparator.comparing(Integer::intValue)));
        System.out.println(maxNum.get());
//        String s1 = list.stream().filter(s -> s.length() > 4).findAny().get();

//        Integer reduce = numList.stream().reduce(Integer::min).get();

//        Integer integer = numList.stream().max(Integer::compareTo).get();

//        int sum = numList.stream().mapToInt(Integer::intValue).sum();
//        int max = numList.stream().mapToInt(Integer::intValue).max().getAsInt();
//        int min = numList.stream().mapToInt(Integer::intValue).min().getAsInt();
//        double asDouble = numList.stream().mapToInt(Integer::intValue).average().getAsDouble();
//        System.out.println(sum);
//        System.out.println(max);
//        System.out.println(min);
//        System.out.println(asDouble);

//        IntStream.rangeClosed(1,10).forEach(System.out::println);


    }

    @Test
    public void testNIO() {
        try {
            Stream<String> lines = Files.lines(Paths.get("D:\\temp\\codes\\test.txt"), Charset.defaultCharset());
            List<String> strList = lines.flatMap(line -> Arrays.stream(line.split(" "))).collect(Collectors.toList());
            String join = String.join(" ", strList);
            System.out.println(join);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testSumming() {
        List<Integer> numList = Arrays.asList(5, 2, 4, 1);
        Integer sum = numList.stream().collect(Collectors.summingInt(Integer::intValue));
        double avg = numList.stream().collect(Collectors.averagingInt(Integer::intValue));
        IntSummaryStatistics summaryStatistics = numList.stream().collect(Collectors.summarizingInt(Integer::intValue));
        System.out.println(sum);
        System.out.println(avg);
        System.out.println(summaryStatistics.getSum());
        System.out.println(summaryStatistics.getAverage());
        System.out.println(summaryStatistics.getCount());
        System.out.println(summaryStatistics.getMax());
        System.out.println(summaryStatistics.getMin());
    }

    @Test
    public void testJoin() {
        List<String> list = Arrays.asList("Apple", "Orange", "Banana", "Lemon", "Daxx", "Cioio", "apple", "Org", "Bat", "Longdan", "Dad");
//        String joinStr = list.stream().collect(Collectors.joining(" "));
//        System.out.println(joinStr);
//        String joinStr2 = list.stream().collect(Collectors.joining());
//        System.out.println(joinStr2);
//        Map<String, Long> summMap = list.stream().collect(Collectors.groupingBy(s -> s.substring(0, 1).toLowerCase(), Collectors.counting()));
//        Map<String, List<String>> summMap = list.stream().collect(Collectors.groupingBy(s -> s.substring(0, 1).toLowerCase()));

//        Map<String, Optional<String>> summMap = list.stream().collect(
//                Collectors.groupingBy(s -> s.substring(0, 1).toLowerCase(),
//                        Collectors.maxBy(Comparator.comparing(String::length))));

//        Map<String, String> summMap = list.stream().collect(Collectors.groupingBy(
//                s -> s.substring(0, 1).toLowerCase(),
//                Collectors.collectingAndThen(Collectors.maxBy(Comparator.comparing(String::length)), Optional::get)));


        Map<String, String> summMap = list.stream().collect(
                Collectors.groupingBy(
                        s -> s.substring(0, 1).toLowerCase(),
                        Collectors.collectingAndThen(Collectors.minBy(Comparator.comparing(String::length)), Optional::get)));

        printJsonString(summMap);
    }

    @Test
    public void testGrouping() {
        User user1 = new User(1L, "zs", 1, 22,1L);
        User user2 = new User(2L, "ls", 1, 23,1L);
        User user3 = new User(3L, "wxj", 0, 35,2L);
        User user4 = new User(4L, "ww", 1, 66,1L);
        User user5 = new User(5L, "xmm", 0, 40,2L);
        User user6 = new User(6L, "sg", 1, 36,1L);
        ArrayList<User> userList = new ArrayList<>();
        userList.add(user1);
        userList.add(user2);
        userList.add(user3);
        userList.add(user4);
        userList.add(user5);
        userList.add(user6);

        userList.stream()
                .filter(user -> user.getSex().equals(1))
                .collect(Collectors.groupingBy(User::getAge))
                .entrySet().stream().sorted(Map.Entry.comparingByKey());

//        System.out.println(users);

    }

    @Test
    public void testOptional() {
//        System.out.println(opUser);
//        System.out.println(opUser.orElse(new User()));
//        System.out.println(opUser.orElseGet(User::new));
        try {
            User user = null;
            Optional<User> opUser = Optional.ofNullable(user);
            System.out.println(opUser.orElseThrow(() -> {
                throw new RuntimeException("user 对象不能为空");
            }));
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    @Test
    public void testLocalDate() {
        String s1 = LocalDateTime.now().toString();
        String s = LocalDate.now().toString();
        System.out.println(s);
        System.out.println(s1);
    }

    @Test
    public void testMapStream() {
//        Map<String, Integer> map = new HashMap<>();
//        map.put("United States", 1);
//        map.put("Germany", 49);
//        map.put("France", 33);
//        map.put("China", 86);
//        map.put("Pakistan", 92);
//        map.put("Pakistan", 93);

        Map<Integer, String> map = new LinkedHashMap<>();
        map.put(5,"a");
        map.put(2,"b");
        map.put(3,"c");
//        map.put(1,"d");
//        map.put(4,"e");

        System.out.println(map);
//
//        LinkedHashMap<String, Integer> linkedHashMap = map.entrySet().stream()
//                .sorted(Map.Entry.comparingByValue())
//                .collect(Collectors.toMap(
//                        Map.Entry::getKey,
//                        Map.Entry::getValue,
//                        (oldVal, newVal) -> newVal,
//                        LinkedHashMap::new)
//                );
//
//        System.out.println(linkedHashMap);
    }

    private void printJsonString(Object object) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String jsonStr = objectMapper.writeValueAsString(object);
            System.out.println(jsonStr);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testLinkedHashMap(){
//        Map<Integer, String> map = new LinkedHashMap<>();
//        map.put(5,"a");
//        map.put(2,"b");
//        map.put(3,"c");
//        System.out.println(map);
        ConcurrentSkipListMap map = new ConcurrentSkipListMap();
        map.put(5,"a");
        map.put(2,"b");
        map.put(3,"c");
        System.out.println(map);
    }


}
