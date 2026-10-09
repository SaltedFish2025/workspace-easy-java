package com.easyjava.utils;

/**
 * 字符串工具
 */
public class MyStringUtils {
    public static void main(String[] args) {
        System.out.println(humpNaming("name"));
    }


    /**
     * 字段缩进
     *
     * @param level 缩进级别
     * @param str   原始字段
     * @return 添加/t后的字段
     */
    public static String strRetract(int level, String str) {
        if (str == null || level <= 0) {
            return str;
        }
        // 直接使用 + 拼接，简洁明了
        for (int i = 0; i < level; i++) {
            str = "\t" + str;
        }
        return str;
    }

    /**
     * 大驼峰命名
     *
     * @param name 原始名[user_name = UserName]
     * @return 驼峰名
     */
    public static String humpNaming(String name) {
        return humpNaming(name, true);
    }

    /**
     * 驼峰命名
     *
     * @param name 原始名[user_name = userName]
     * @param type [T:大驼峰,F:小驼峰]
     * @return 驼峰名
     */
    public static String humpNaming(String name, Boolean type) {
        if (name == null || name.isEmpty()) {
            return name;
        }
        boolean capitalizeNext = false; // 标记下一个有效字符是否需要大写
        StringBuilder sb = new StringBuilder();
        for (char c : name.toCharArray()) {
            if (c == '_') {
                capitalizeNext = true;
            } else {
                // 遇到非下划线字符
                if (capitalizeNext) {
                    sb.append(Character.toUpperCase(c));
                    capitalizeNext = false; // 重置标记
                } else {
                    sb.append(c);
                }
            }
        }
        if (sb.length() > 0) {
            char charAt = Character.toLowerCase(sb.charAt(0));
            if (type) {
                charAt = Character.toUpperCase(sb.charAt(0));
            }
            sb.setCharAt(0, charAt);//稳定大驼峰还是小驼峰
            return sb.toString();
        } else {
            return name;
        }
    }


}
