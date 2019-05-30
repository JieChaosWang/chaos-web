/**
 *
 */
package com.my.test.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author wcj
 * @since 2019年5月29日15:42:37 工具类
 *
 */
public class BeeUtils {

    private static AtomicLong atomicLong = new AtomicLong(System.currentTimeMillis());

    /**
     * @author wcj 2019年5月29日15:42:37 判断属性是否为空或null 不能判断集合是否为空！
     * @param fieldName
     * @param fieldValue
     * @throws
     */
    public static void isEmpty(String fieldName, Object fieldValue) throws Exception {
        if (null == fieldValue || !StringUtils.hasText(fieldValue.toString())) {
            throw new Exception(fieldName + "--属性不能为空");
        }
    }

    /**
     * 检查email
     *
     * @param email
     * @return
     */
    public static boolean checkEmail(String email) {
        if (!email.matches("[\\w\\.\\-]+@([\\w\\-]+\\.)+[\\w\\-]+")) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 检测卡号的位数
     *
     * @param num
     * @return
     */
    public static boolean checkCardNum(String num) {

        boolean flag = false;
        try {
            Pattern regex = Pattern.compile("^[-\\d]{1,26}$");
            Matcher matcher = regex.matcher(num);
            flag = matcher.matches();
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }

    /**
     * 验证手机号码 验证规则为1开头的11位数字
     *
     * @return
     */
    public static boolean checkMobileNumber(String mobileNumber) {
        boolean flag = false;
        try {
            Pattern regex = Pattern.compile("^1\\d{10}$");
            Matcher matcher = regex.matcher(mobileNumber);
            flag = matcher.matches();
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }

    /**
     * 判断手机号码是否合法（不能为空且满足校验规则） 校验规则：首位为1开始，且为11位
     *
     * @param fieldName
     * @param mobile
     * @throws Exception
     */
    public static void mobileIsLegal(String fieldName, String mobile) throws Exception {
        if (StringUtils.isEmpty(mobile)) {
            throw new Exception(fieldName + "--手机号码为空");
        }
        if (!StringUtils.isEmpty(mobile) && !BeeUtils.checkMobileNumber(mobile)) {
            throw new Exception(fieldName + "--手机号码格式不正确");
        }
    }

    /**
     *
     ********************************************************* .<br>
     * [方法] matchPassword <br>
     * [描述] 验证密码是否是符合规范的要求 <br>
     * 验证密码输入格式：需由小写字母、数字同时组成，且长度为：6-20位 <br>
     * [返回] boolean <br>
     * [作者] wcj <br>
     * [时间] 2014-9-19 下午4:58:19 <br>
     ********************************************************* .<br>
     */
    public static boolean matchPassword(String password) {
        return password.matches("^(?=.*[a-z])(?=.*[0-9])[a-zA-Z0-9]{6,20}$");
    }

    /**
     *
     ********************************************************* .<br>
     * [方法] hideMobileNumber <br>
     * [描述] 隐藏手机号码关键信息(首3位+****+后4位)：136**1186 <br>
     * [返回] String <br>
     * [作者] wcj <br>
     * [时间] 2015-1-16 上午11:47:07 <br>
     ********************************************************* .<br>
     */
    public static String hideMobileNumber(String mobileNumber) throws Exception {
        if (!checkMobileNumber(mobileNumber)) {
            throw new Exception("手机号码格式不正确");
        }
        return mobileNumber.substring(0, 3) + "****" + mobileNumber.substring(7, 11);
    }

    /**
     *
     ********************************************************* .<br>
     * [方法] hideEmail <br>
     * [描述] 隐藏邮箱关键信息：隐藏格式：1.开始2位+****+此处一位+@及其后缀 如：17****4@qq.com<br>
     * [返回] String <br>
     * [作者] wcj <br>
     * [时间] 2015-1-16 上午11:55:06 <br>
     ********************************************************* .<br>
     */
    public static String hideEmail(String email) throws Exception {
        if (!checkEmail(email)) {
            throw new Exception("邮箱格式不正确");
        }
        if (email.indexOf("@") > 3) { // 说明@前多余三位：17***3@qq.com 格式展示
            return email.substring(0, 2) + "****" + email.substring(email.indexOf("@") - 1, email.length());
        } else {// 说明@前<=三位 ：全部展示
            return email;
        }
    }

    /**
     *
     ********************************************************* .<br>
     * [方法] checkMoney <br>
     * [描述] 验证金额格式是否正确：[正数+小数部位可有可无] <br>
     * [返回] boolean <br>
     * [作者] wcj <br>
     * [时间] 2015-1-16 下午5:37:22 <br>
     ********************************************************* .<br>
     */
    public static boolean checkMoney(Object value) {
        Pattern pattern = Pattern
                .compile("^(([1-9]{0}\\d{0,12})(\\.(\\d){1,4})?)|(0\\.0[1-9]{1,3})|(0\\.[1-9]{1,3}(\\d{1,3})?)$"); // 判断小数点后2位的数字的正则表达式
        Matcher match = pattern.matcher(value.toString());
        return match.matches();
    }

    /**
     *
     ********************************************************* .<br>
     * [方法] checkPostCode <br>
     * [描述] 验证邮政编码：6位数字 <br>
     * [返回] boolean <br>
     * [作者] wcj <br>
     * [时间] 2015-1-19 下午12:02:12 <br>
     ********************************************************* .<br>
     */
    public static boolean checkPostCode(Object value) {
        Pattern pattern = Pattern.compile("^\\d{6}$"); // 判断邮编:6位数字
        Matcher match = pattern.matcher(value.toString());
        return match.matches();
    }

    // 判断是否为正数
    public static boolean isNumber(Object value) {
        Pattern pattern = Pattern.compile("^[1-9]\\d*$");
        Matcher match = pattern.matcher(value.toString());
        return match.matches();
    }

    // 64编码 //网络文件
    public static String encodeBase64File(String path) {
        InputStream inputFile = null;
        try {
            URL url = new URL(path); // 创建URL
            URLConnection urlconn = url.openConnection(); // 试图连接并取得返回状态码
            urlconn.setReadTimeout(50000);
            urlconn.connect();
            HttpURLConnection httpconn = (HttpURLConnection) urlconn;
            int httpResult = httpconn.getResponseCode();
            if (httpResult != HttpURLConnection.HTTP_OK) // 不等于HTTP_OK说明连接不成功
                System.out.print("无法连接到");
            else {
                inputFile = urlconn.getInputStream();
                ByteArrayOutputStream outStream = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];

                int len = 0;
                while ((len = inputFile.read(buffer)) != -1) {
                    outStream.write(buffer, 0, len);
                }
                inputFile.close();
                byte[] fileData = outStream.toByteArray();
                outStream.flush();
                outStream.close();

                return Base64.getEncoder().encodeToString(fileData);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;

        } finally {
            if (inputFile != null)
                try {
                    inputFile.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    return null;

                }

        }
        return null;
    }

    // 64编码 //网络文件 返回字节数组

    /**
     * 为了从七牛上读取企业营业执照byte流和contentType
     *
     * @author cws 2017年11月8日
     * @param path
     * @return
     */
    public static Map<String, Object> readNetFile(String path) {
        InputStream inputFile = null;
        try {
            URL url = new URL(path); // 创建URL
            URLConnection urlconn = url.openConnection(); // 试图连接并取得返回状态码
            urlconn.setReadTimeout(50000);
            urlconn.connect();
            HttpURLConnection httpconn = (HttpURLConnection) urlconn;
            int httpResult = httpconn.getResponseCode();
            if (httpResult != HttpURLConnection.HTTP_OK) // 不等于HTTP_OK说明连接不成功
                System.out.print("无法连接到");
            else {
                inputFile = urlconn.getInputStream();
                ByteArrayOutputStream outStream = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];

                int len = 0;
                while ((len = inputFile.read(buffer)) != -1) {
                    outStream.write(buffer, 0, len);
                }
                inputFile.close();
                byte[] fileData = outStream.toByteArray();
                outStream.flush();
                outStream.close();

                Map<String, Object> map = new HashMap<String, Object>();
                map.put("contentType", httpconn.getContentType());
                map.put("byteFile", fileData);

                return map;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;

        } finally {
            if (inputFile != null)
                try {
                    inputFile.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    return null;

                }

        }
        return null;
    }

    public static String decoderBase64File(String base64Code) throws Exception {
        String base64Str = new String();
        try {
            byte[] buffer = Base64.getDecoder().decode(base64Code.getBytes());
            FileOutputStream out = new FileOutputStream(base64Str);
            out.write(buffer);
            out.close();
            base64Str = new String(buffer, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            throw new Exception("不支持的解码类型", e);

        } catch (IOException e) {
            e.printStackTrace();
            throw new Exception("解码出错", e);
        }
        return base64Str;
    }

    public static String encodeBase64LocalFile(String path) {
        FileInputStream inputFile = null;
        try {

            File file = new File(path);
            inputFile = new FileInputStream(file);
            byte[] buffer = new byte[(int) file.length()];
            inputFile.read(buffer);
            inputFile.close();
            return Base64.getEncoder().encodeToString(buffer);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (inputFile != null)
                try {
                    inputFile.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    return null;
                }
        }
    }

    public static String createEcardId() {
        Long formatDate = Long.valueOf(new SimpleDateFormat("yyyyMMddhhmmss00000").format(new Date()));
        formatDate = atomicLong.getAndIncrement() + formatDate;
        return "E" + formatDate.toString();
    }

    // 验证费率：支持小数点后5位数
    public static boolean checkRate(Object value) {
        Pattern pattern = Pattern.compile("^0\\.\\d{2}(\\d{0,3})?$");
        Matcher match = pattern.matcher(value.toString());
        return match.matches();
    }

    // 验证费率：支持小数点后5位数,可以只有一位小数
    public static boolean checkRateNew(Object value) {
        Pattern pattern = Pattern.compile("^0\\.\\d{1,5}$");
        Matcher match = pattern.matcher(value.toString());
        return match.matches();
    }

    /**
     * 将长度大于8的的替换为****
     *
     * @param str
     * @return
     */
    public static String repStr(String str) {
        str = str.substring(0, 4) + "****" + str.substring(8, str.length());
        return str;
    }

    /**
     * 生成8位随机字符串，含字母和数字
     *
     * @author zhangli
     * @date 2016年11月25日
     * @return
     */
    public static String create8RandomChar() {
        String[] CHARS = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R",
                "S", "D", "U", "V", "W", "X", "Y", "Z", "1", "2", "3", "4", "5", "6", "7", "8", "9", "0"};
        Random ran = new Random();
        StringBuilder bud = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            int k = ran.nextInt(CHARS.length);
            bud.append(CHARS[k]);
        }
        return bud.toString();
    }

    public static String getId() {
        try {
            URL url = new URL("http://192.168.1.225:9049/?type=1"); // 创建URL
            URLConnection urlconn = url.openConnection(); // 试图连接并取得返回状态码
            HttpURLConnection httpconn = (HttpURLConnection) urlconn;
            httpconn.setConnectTimeout(5000);
            httpconn.setReadTimeout(5000);
            InputStream input = urlconn.getInputStream();
            byte[] buffer = new byte[input.available()];
            input.read(buffer);
            input.close();
            httpconn.disconnect();
            String str = new String(buffer);
            return String.valueOf(JSON.parseObject(str).get("result"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String sendRequest(String urlString, String params) {
        HttpURLConnection httpConn = null;
        OutputStream out = null;
        String result = null;
        try {
            URL url = new URL("http://192.168.1.225:9049/?type=1");
            httpConn = (HttpURLConnection) url.openConnection();
            httpConn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            httpConn.setRequestMethod("GET");
            httpConn.setConnectTimeout(3000);
            httpConn.setReadTimeout(3000);
            httpConn.setDoOutput(true);
            httpConn.setDoInput(true);
            byte[] datas = readInputStream(httpConn.getInputStream());
            result = new String(datas, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null)
                    out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return String.valueOf(JSON.parseObject(result).get("result"));
    }

    public static byte[] readInputStream(InputStream inStream) throws Exception {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while ((len = inStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, len);
        }
        byte[] data = outStream.toByteArray();// 网页的二进制数据
        outStream.close();
        inStream.close();
        return data;
    }

    /**
     * 去掉字符串中某些特殊字符，变为' '（为solr查询条件用）
     *
     * @author zhangli
     * @date 2017年3月3日
     * @param str
     * @return
     */
    public static String removceSpecialChar(String str) {
        if (!StringUtils.isEmpty(str)) {
            String[] CHARS = {"\\", "+", "-", "!", "(", ")", ":", "^", "[", "]", "{", "}", "~", "?", "|", "&", ";",
                    "/"};
            for (String string : CHARS) {
                if (str.contains(string)) {
                    str = str.replace(string, " ");
                }
            }
        }
        return str;
    }

    /**
     * 将request中的parameterMap 转化为可读写的 map
     *
     * @author feng 2017年9月21日
     * @param properties
     * @return
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    public static Map getParameterMap(Map properties) {
        // 返回值Map
        Map returnMap = new HashMap();
        Iterator entries = properties.entrySet().iterator();
        Map.Entry entry;
        String name = "";
        String value = "";
        while (entries.hasNext()) {
            entry = (Map.Entry) entries.next();
            name = (String) entry.getKey();
            Object valueObj = entry.getValue();
            if (null == valueObj) {
                value = "";
            } else if (valueObj instanceof String[]) {
                String[] values = (String[]) valueObj;
                for (int i = 0; i < values.length; i++) {
                    if (values[i].equals("null")) {
                        value = ",";
                    } else {
                        value = values[i] + ",";
                    }
                }
                value = value.substring(0, value.length() - 1);
            } else {
                value = valueObj.toString();
                if (value.equals("null")) {
                    value = "";
                }
            }
            returnMap.put(name, value);
        }
        return returnMap;
    }

    /**
     *
     * 判断数组中是否有重复值
     *
     * @author renchengpeng 2017年12月21日
     * @param array
     * @return
     */
    public static boolean checkRepeat(String[] array) {
        Set<String> set = new HashSet<String>();
        for (String str : array) {
            set.add(str);
        }
        if (set.size() != array.length) {
            return false;// 有重复
        } else {
            return true;// 不重复
        }
    }

//    /**
//     *
//     * 功能描述: 获取域名后面部分（如www.qiyeos.com, 返回qiyeos.com）
//     *
//     * @param: domainUrl
//     *             域名
//     * @param: isChildModule
//     *             是否是子域名
//     * @return:
//     * @auther: renchengpeng
//     * @date: 2018/6/15 17:43
//     */
//    public static String getDomainUrl(String domainUrl, Boolean isChildModule) {
//        String defaultDomainUrl = "qiyeos.com";
//        if (org.apache.commons.lang.StringUtils.isNotEmpty(domainUrl)) {
//            // 分隔出主域名
//            String[] splitUrl = domainUrl.split("\\.");
//            if (splitUrl.length > 1) {
//                if (isChildModule || splitUrl[0].equals("www") || splitUrl[0].equals("dev")
//                        || splitUrl[0].equals("ci")) {
//                    defaultDomainUrl = domainUrl.substring(splitUrl[0].length() + 1);// 包含.
//                } else {
//                    defaultDomainUrl = domainUrl;
//                }
//            }
//        }
//        return defaultDomainUrl;
//    }

    /**
     *
     * 功能描述: 用特定的协议替换url中的http或者https协议
     *
     * @param:
     * @return:
     * @auther: renchengpeng
     * @date: 2018/6/19 10:37
     */
//    public static String replaceProtocol(String url, String protocol) throws Exception {
//        String resUrl = "";
//        // 替换协议
//        if (!StringUtils.isEmpty(protocol) && (protocol.equals(Constants.STR_HTTP) || protocol.equals(Constants.STR_HTTPS))) {
//            // http协议
//            resUrl = url.replaceFirst("^http(s)?", protocol);
//        } else {
//            // 用默认的协议
//            resUrl = url;
//        }
//        return resUrl;
//    }

    /**
     * 功能描述: 隐藏银行卡号,营业执照号等, 保留前后4位 其他都是*
     *
     * @param account
     * @auther: xuxitan
     * @date: 2018/7/3 10:37
     * @return
     */
    public static String hideAccount(String account) {
        int m = account.length();
        String resAccount = account;
        StringBuilder s = new StringBuilder();
        if (m > 8) {
            int n = m - 8;
            for (int i = 0; i < n; i++) {
                s.append("*");
            }
            resAccount = account.substring(0, 4) + s + account.substring(m - 4);
        }
        return resAccount;
    }

    /**
     * 功能描述: 执行宇哥的Shell脚本
     * @param request 为了获取授权路径而保留的参数。
     * @param shellPath 脚本在本项目路径。 											  		例 ： request.getServletContext().getRealPath("/") + "WEB-INF";
     * @param allParms 带参数路径shell脚本的参数，每个之间要用一个" "空格隔开。  		 			例 ： String allParms = shellPath + "/add-domain.sh qiyeos.com：xyz.com " ;
     * @return
     */
    public static String gotoShell(HttpServletRequest request, String shellPath, String allParms) {

        String inOrderCommand = request.getServletContext().getRealPath("/") + "WEB-INF";

        JSONObject resultJSON = new JSONObject();

        ProcessBuilder builderCommand = new ProcessBuilder("/usr/bin/chmod", "+x", inOrderCommand);
        builderCommand.directory(new File(shellPath));

        int runningStatus = 0;
        String s = null;
        StringBuffer sb = new StringBuffer();

        try {

            Process process = builderCommand.start();

            BufferedReader stdInput = new BufferedReader(new InputStreamReader(process.getInputStream()));
            BufferedReader stdError = new BufferedReader(new InputStreamReader(process.getErrorStream()));

            while ((s = stdInput.readLine()) != null) {
                sb.append(s);
            }

            while ((s = stdError.readLine()) != null) {
                sb.append(s);
            }

            runningStatus = process.waitFor();


            String[] cmdString = {"/bin/sh", "-c", allParms};
            Process pRuntime = Runtime.getRuntime().exec(cmdString);

            BufferedReader stdInputRuntime = new BufferedReader(new InputStreamReader(pRuntime.getInputStream()));
            BufferedReader stdErrorRuntime = new BufferedReader(new InputStreamReader(pRuntime.getErrorStream()));

            while ((s = stdInputRuntime.readLine()) != null) {
                sb.append(s);
            }

            while ((s = stdErrorRuntime.readLine()) != null) {
                sb.append(s);
            }

            runningStatus = pRuntime.waitFor();

            closeStream(stdInput);
            closeStream(stdError);

            closeStream(stdInputRuntime);
            closeStream(stdErrorRuntime);

        } catch (Exception e) {
            sb.append(e.getMessage());
            runningStatus = 1;
        }

        if (runningStatus == 0) {
            //成功
            resultJSON.put("code", "1");
            resultJSON.put("msg", "成功");
            return resultJSON.toJSONString();
        } else {
            resultJSON.put("code", "0");
            resultJSON.put("msg", "调用shell脚本失败..." + sb.toString());
            return resultJSON.toJSONString();
        }
    }

    public static void closeStream(BufferedReader reader) {
        try {
            if (reader != null) {
                reader.close();
            }
        } catch (Exception e) {
            reader = null;
        }
    }

//	public static void main(String[] args) {
//        String aa="a d df".trim();
//        if(!isEnglish(aa)) {
//            System.out.println(aa.replace(" ", ""));
//        }
//        System.out.println(aa);
//    }

    public static void main(String[] args) {
        String a = "121212121";
        if (a.matches("^[0-9]*[1-9][0-9]*$")) {

            System.out.println("11");
        } else {
            System.out.println("22");
        }
    }

    public static boolean isEnglish(String charaString) {
        String a = charaString.replace(" ", "");
        return a.matches("^[a-zA-Z]*");

    }

    /**
     * @date: 2019/3/12
     * @description: 两时间相减取天数判断是否超过指定天数
     */
    public static long pushDay(Timestamp time, long day) throws ParseException {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date d1 = df.parse(df.format(new Date(System.currentTimeMillis())));
        Date d2 = time;
        //计算
        long diff = d1.getTime() - d2.getTime();
        long oneDay = 1000 * 60 * 60 * 24;
        if (diff < oneDay) {
            return day;
        } else {
            long days = diff / (1000 * 60 * 60 * 24);
            return (day - days);
        }

    }
    /**
     * @date: 2019/3/29
     * @description: 判读是否是Base64字符串
     */
    public static boolean isBase64(String str) {
		String base64Pattern = "^([A-Za-z0-9+/]{4})*([A-Za-z0-9+/]{4}|[A-Za-z0-9+/]{3}=|[A-Za-z0-9+/]{2}==)$";
		return Pattern.matches(base64Pattern, str);
	}

}
