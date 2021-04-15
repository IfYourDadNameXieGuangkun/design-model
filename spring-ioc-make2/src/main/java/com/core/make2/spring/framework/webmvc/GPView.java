package com.core.make2.spring.framework.webmvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.RandomAccessFile;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 这里的GPView就是前面所说的自定义模板解析引擎，其核心方法
 * 是render（）。在render（）方法中完成对模板的渲染，最终返回浏览器
 * 能识别的字符串，通过Response输出
 */
public class GPView {
    public static final String DEFAULT_CONTEXT_TYPE = "text/html;charset=utf-8";

    private File viewFile;

    public GPView(File viewFile) {
        this.viewFile = viewFile;
    }

    public String getContextType() {
        return DEFAULT_CONTEXT_TYPE;
    }

    public void render(Map<String, ?> model, HttpServletRequest req, HttpServletResponse resp) throws Exception {
        StringBuffer sb = new StringBuffer();
        RandomAccessFile ra = new RandomAccessFile(this.viewFile, "r");
        try {
            String line = null;
            while (null != (line = ra.readLine())) {
                line = new String(line.getBytes("ISO-8859-1"), "UTF-8");
                Pattern pattern = Pattern.compile("￥\\{[^\\}]+\\}", Pattern.CASE_INSENSITIVE);
                Matcher matcher = pattern.matcher(line);
                while (matcher.find()) {
                    String paramName = matcher.group();
                    paramName = paramName.replaceAll("￥\\{|\\}", "");
                    Object paramValue = model.get(paramName);
                    if (null == paramValue) {
                        continue;
                    }
                    //要把￥{}中间的这个字符串取出来
                    line = matcher.replaceFirst(makeStringForRegExp(paramValue.toString()));
                    matcher = pattern.matcher(line);
                }
                sb.append(line);
            }
        } catch (Exception e) {

        } finally {
            ra.close();
        }
        resp.setCharacterEncoding("utf-8");
        resp.getWriter().write(sb.toString());
    }

    public static String makeStringForRegExp(String str) {
        return str.replace("\\","\\\\")
                .replace("*","\\*")
                .replace("+","\\+")
                .replace("|","\\|")
                .replace("}","\\}")
                .replace(")","\\)")
                .replace("$","\\$")
                .replace("]","\\]")
                .replace(",","\\,")
                .replace("&","\\&")
                .replace(".","\\.")
                .replace("?","\\?")
                .replace("[","\\[")
                .replace("^","\\^")
                .replace("(","\\(")
                .replace("{","\\{");
    }
}
