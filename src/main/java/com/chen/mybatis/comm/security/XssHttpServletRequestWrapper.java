package com.chen.mybatis.comm.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * http封装
 *
 * @author 陈智颖
 * @title <一句话说明功能>
 * @date 2019-01-14 上午11:19
 **/
public class XssHttpServletRequestWrapper extends HttpServletRequestWrapper {

    HttpServletRequest originRequest = null;

    public XssHttpServletRequestWrapper(HttpServletRequest request) {
        super(request);
        originRequest = request;
    }

    /**
     * 覆盖getHeader方法，将参数名和参数值都做xss过滤。<br/>
     * 如果需要获得原始的值，则通过super.getHeaders(name)来获取<br/>
     * getHeaderNames 也可能需要覆盖
     *
     * @param name
     * @return
     */
    @Override
    public String getHeader(String name) {
        String value = super.getHeader(xssEncode(name));
        if (null != value && value.length() > 0) {
            value = xssEncode(value);
        }

        return value;
    }

    /**
     * 覆盖getParameter方法，将参数名和参数值都做xss过滤。<br/>
     * 如果需要获得原始的值，则通过super.getParameterValues(name)来获取<br/>
     * getParameterNames,getParameterValues和getParameterMap也可能需要覆盖
     *
     * @param name
     * @return
     */
    @Override
    public String getParameter(String name) {
        String value = super.getParameter(xssEncode(name));
        if (null != value && value.length() > 0) {
            value = xssEncode(value);
        }

        return value;
    }

    @Override
    public String[] getParameterValues(String name) {
        String[] values = super.getParameterValues(xssEncode(name));
        if (null == values) {
            return null;
        }

        int length = values.length;
        String[] encodeValues = new String[length];
        for (int i = 0; i < length; i++) {
            encodeValues[i] = xssEncode(values[i]);
        }

        return encodeValues;
    }

    @Override
    public String getQueryString() {
        String value = super.getQueryString();
        if (null != value && value.length() > 0) {
            value = xssEncode(value);
        }

        return value;
    }

    @Override
    public Map<String, String[]> getParameterMap() {
        Map<String, String[]> originMap = super.getParameterMap();
        Map<String, String[]> filterMap = new HashMap<>();
        for (String key : originMap.keySet()) {
            String valName = key;
            String[] originValue = originMap.get(valName);
            if (null == originValue) {
                filterMap.put(valName, originValue);
            } else {
                String[] newValue = new String[originValue.length];
                for (int i = 0; i < originValue.length; i++) {
                    newValue[i] = xssEncode(originValue[i]);
                }
                filterMap.put(valName, newValue);
            }
        }

        return filterMap;
    }

    /**
     * 将容易引起xss漏洞的半角字符直接替换成全角字符
     *
     * @param str
     * @return
     */
    public String xssEncode(String str) {
        if (null == str || str.length() == 0) {
            return str;
        }

        String result = stripXss(str);
        if (null != result) {
//            result = escape(result);
        }

        return result;
    }

    private String stripXss(String value) {
        if (null != value && value.length() > 0) {
            // NOTE: It's highly recommended to use the ESAPI library and uncomment the following line to
            // avoid encoded attacks.
            // value = ESAPI.encoder().canonicalize(value);
            // Avoid null characters
            value = value.replaceAll("", "");
            // Avoid anything between script tags
            Pattern scriptPattern = Pattern.compile("<script>(.*?)</script>", Pattern.CASE_INSENSITIVE);
            value = scriptPattern.matcher(value).replaceAll("");
            // Avoid anything in a src='...' type of expression
            scriptPattern = Pattern.compile("src[\r\n]*=[\r\n]*\\\'(.*?)\\\'", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            value = scriptPattern.matcher(value).replaceAll("");
            scriptPattern = Pattern.compile("src[\r\n]*=[\r\n]*\\\"(.*?)\\\"", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            value = scriptPattern.matcher(value).replaceAll("");
            // Remove any lonesome </script> tag
            scriptPattern = Pattern.compile("</script>", Pattern.CASE_INSENSITIVE);
            value = scriptPattern.matcher(value).replaceAll("");
            // Remove any lonesome <script ...> tag
            scriptPattern = Pattern.compile("<script(.*?)>", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            value = scriptPattern.matcher(value).replaceAll("");
            // Avoid eval(...) expressions
            scriptPattern = Pattern.compile("eval\\((.*?)\\)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            value = scriptPattern.matcher(value).replaceAll("");
            // Avoid expression(...) expressions
            scriptPattern = Pattern.compile("expression\\((.*?)\\)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            value = scriptPattern.matcher(value).replaceAll("");
            // Avoid javascript:... expressions
            scriptPattern = Pattern.compile("javascript:", Pattern.CASE_INSENSITIVE);
            value = scriptPattern.matcher(value).replaceAll("");
            // Avoid vbscript:... expressions
            scriptPattern = Pattern.compile("vbscript:", Pattern.CASE_INSENSITIVE);
            value = scriptPattern.matcher(value).replaceAll("");
            // Avoid onload= expressions
            scriptPattern = Pattern.compile("onload(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            value = scriptPattern.matcher(value).replaceAll("");

            scriptPattern = Pattern.compile("<iframe>(.*?)</iframe>", Pattern.CASE_INSENSITIVE);
            value = scriptPattern.matcher(value).replaceAll("");

            scriptPattern = Pattern.compile("</iframe>", Pattern.CASE_INSENSITIVE);
            value = scriptPattern.matcher(value).replaceAll("");
            // Remove any lonesome <script ...> tag
            scriptPattern = Pattern.compile("<iframe(.*?)>", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            value = scriptPattern.matcher(value).replaceAll("");
        }

        return value;
    }

    public String escape(String str) {
        StringBuilder sb = new StringBuilder(str.length() + 16);
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            switch (c) {
                case '>':
                    sb.append('＞');  // 全角大于号
                    break;
                case '<':
                    sb.append('＜');  // 全角小于号
                    break;
                case '\'':
                    sb.append('＇');  // 全角单引号
                    break;
                case '"':
                    sb.append('＂');  // 全角双引号
                    break;
                case '\\':
                    sb.append('＼');  // 全角斜线
                    break;
                case '%':
                    sb.append('％');  // 全角百分号
                    break;
                case ':':
                    sb.append('：');  // 全角冒号
                    break;
                default:
                    sb.append(c);
                    break;
            }
        }

        return sb.toString();
    }

    /**
     * 获取最原始的request
     *
     * @return
     */
    public HttpServletRequest getOrgRequest() {
        return originRequest;
    }

    /**
     * 获取最原始的request的静态方法
     *
     * @param req
     * @return
     */
    public static HttpServletRequest getOrgRequest(HttpServletRequest req) {
        if (req instanceof XssHttpServletRequestWrapper) {
            return ((XssHttpServletRequestWrapper) req).getOrgRequest();
        }

        return req;
    }
}
