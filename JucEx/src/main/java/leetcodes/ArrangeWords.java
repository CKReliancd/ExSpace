package leetcodes;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Administrator
 * @Description
 * @Date 2021/6/28 17:02
 */
public class ArrangeWords {

    public static void main(String[] args) {

        String text = "Leetcode is cool";
        String text1 = "To be or not to be";
        String result = arrangeWords1(text1);
        System.out.println(result);
    }


    private static String arrangeWords2(String text) {

        String[] split = StringUtils.split(text, " ");

        List<String> collect = Arrays.stream(split)
                .sorted(Comparator.comparingInt(s -> s.length()))
                .collect(Collectors.toList());

        String result = StringUtils.join(collect, " ");

        return result;
    }

    private static String arrangeWords1(String text) {
        String[] split = StringUtils.split(text, " ");
        List<String> collect = Arrays.stream(split)
                .sorted(Comparator.comparingInt(s -> s.length()))
                .collect(Collectors.toList());
        String result = StringUtils.join(collect, " ");
        return result;
    }


}
