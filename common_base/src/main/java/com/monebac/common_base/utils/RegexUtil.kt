package com.monebac.common_base.utils

import android.content.Context
import java.util.regex.Pattern

object RegexUtil {
    //TODO 2020-12-09
    /**
     * 完全匹配
     * | 或
     * [] 里面包含的都是  或   ag：abc==a|b|c
     * [a-c] a到c的任意字符
     *

     *
     * 限制符号 * + ? {}
     * ag:foooof
     * "*" 表示匹配前面的子表达式零次或多次。
     * "+" 表示匹配前面的子表达式一次或多次。
     * "?" 表示匹配前面的子表达式零次或一次。
     * "{n}" 表示匹配前面子表达式 n 次。
     * "{n，}" 表示匹配前面子表达式至少n 次。
     * "{2,15}" 表示匹配前面子表达式最少2次，最多15次
     *  so： "*" 等价于 "{0,}"，"+" 等价于 "{1,}"，"?" 等价于 "{0,1}"。
     *
     * 转义字符
     *
     *  "\d" 任意数字
     * "\D" 表示匹配非数字字符，等价于 "[^0-9]"
     * "\n" 表示匹配换行符
     * "\s" 表示匹配任何空白字符，等价于 "[\f\n\r\t\v]"
     * "\S" 表示匹配任何非空白字符，等价于 "[^\f\n\r\t\v]"
     *
     *  符号 .
     * 符号 "." 会匹配 "\n" 以外的所有字符，也是比较常用的。
     *
     *
     */


    fun Fregex() {

    }

    /**
     * 验证密码 8-16 由数字和字母组合
     */
    fun isPassWordLegal(context: Context?, pwd: String?): Boolean {
        val regex = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,16}$"
        return Pattern.matches(regex, pwd)
    }

    /**
     * 手机号码，中间4位星号替换
     *
     * @param phone 手机号
     * @return 星号替换的手机号
     *
     */
    fun phoneNoHide(phone: String): String {
        // 括号表示组，被替换的部分$n表示第n组的内容
        // 正则表达式中，替换字符串，括号的意思是分组，在replace()方法中，
        // 参数二中可以使用$n(n为数字)来依次引用模式串中用括号定义的字串。
        // "(\d{3})\d{4}(\d{4})", "$1****$2"的这个意思就是用括号，
        // 分为(前3个数字)中间4个数字(最后4个数字)替换为(第一组数值，保持不变$1)(中间为*)(第二组数值，保持不变$2)
        return phone.replace("(\\d{3})\\d{4}(\\d{4})".toRegex(), "$1****$2")
    }

    /**
     * 银行卡号，保留最后4位，其他星号替换
     *
     * @param cardId 卡号
     * @return 星号替换的银行卡号
     */
    fun cardIdHide(cardId: String): String {
        return cardId.replace("\\d{15}(\\d{3})".toRegex(), "**** **** **** **** $1")
    }

    /**
     * 身份证号，中间10位星号替换
     *
     * @param id 身份证号
     * @return 星号替换的身份证号
     */
    fun idHide(id: String): String {
        return id.replace("(\\d{4})\\d{10}(\\d{4})".toRegex(), "$1** **** ****$2")
    }

    /**
     * 是否为车牌号（沪A88888）
     *
     * @param vehicleNo 车牌号
     * @return 是否为车牌号
     */
    fun checkVehicleNo(vehicleNo: String?): Boolean {
        val pattern = Pattern.compile("^[\u4e00-\u9fa5]{1}[a-zA-Z]{1}[a-zA-Z_0-9]{5}$")
        return pattern.matcher(vehicleNo).find()
    }

    /**
     * 验证身份证号码
     *
     * @param idCard 居民身份证号码15位或18位，最后一位可能是数字或字母
     * @return 验证成功返回true，验证失败返回false
     *
     */
    fun checkIdCard(idCard: String?): Boolean {
        val regex = "[1-9]\\d{13,16}[a-zA-Z0-9]{1}"
        return Pattern.matches(regex, idCard)
    }

    /**
     * 验证手机号码（支持国际格式，+86135xxxx...（中国内地），+00852137xxxx...（中国香港））
     *
     * @param mobile 移动、联通、电信运营商的号码段
     *
     * 移动的号段：134(0-8)、135、136、137、138、139、147（预计用于TD上网卡）
     * 、150、151、152、157（TD专用）、158、159、187（未启用）、188（TD专用）
     *
     * 联通的号段：130、131、132、155、156（世界风专用）、185（未启用）、186（3g）
     *
     * 电信的号段：133、153、180（未启用）、189
     * @return 验证成功返回true，验证失败返回false
     */
    fun checkMobile(mobile: String?): Boolean {
        val regex = "(\\+\\d+)?1[3458]\\d{9}$"
        return Pattern.matches(regex, mobile)
    }

    /**
     * 验证固定电话号码
     *
     * @param phone 电话号码，格式：国家（地区）电话代码 + 区号（城市代码） + 电话号码，如：+8602085588447
     *
     * **国家（地区） 代码 ：**标识电话号码的国家（地区）的标准国家（地区）代码。它包含从 0 到 9 的一位或多位数字，
     * 数字之后是空格分隔的国家（地区）代码。
     *
     * **区号（城市代码）：**这可能包含一个或多个从 0 到 9 的数字，地区或城市代码放在圆括号——
     * 对不使用地区或城市代码的国家（地区），则省略该组件。
     *
     * **电话号码：**这包含从 0 到 9 的一个或多个数字
     * @return 验证成功返回true，验证失败返回false
     */
    fun checkPhone(phone: String?): Boolean {
        val regex = "(\\+\\d+)?(\\d{3,4}\\-?)?\\d{7,8}$"
        return Pattern.matches(regex, phone)
    }

    /**
     * 验证Email
     *
     * @param email email地址，格式：zhangsan@sina.com，zhangsan@xxx.com.cn，xxx代表邮件服务商
     * @return 验证成功返回true，验证失败返回false
     */
    fun checkEmail(email: String?): Boolean {
        val regex = "\\w+@\\w+\\.[a-z]+(\\.[a-z]+)?"
        return Pattern.matches(regex, email)
    }

    /**
     * 验证整数（正整数和负整数）
     *
     * @param digit 一位或多位0-9之间的整数
     * @return 验证成功返回true，验证失败返回false
     */
    fun checkDigit(digit: String?): Boolean {
        val regex = "\\-?[1-9]\\d+"
        return Pattern.matches(regex, digit)
    }

    /**
     * 验证整数和浮点数（正负整数和正负浮点数）
     *
     * @param decimals 一位或多位0-9之间的浮点数，如：1.23，233.30
     * @return 验证成功返回true，验证失败返回false
     */
    fun checkDecimals(decimals: String?): Boolean {
        val regex = "\\-?[1-9]\\d+(\\.\\d+)?"
        return Pattern.matches(regex, decimals)
    }

    /**
     * 验证空白字符
     *
     * @param blankSpace 空白字符，包括：空格、\t、\n、\r、\f、\x0B
     * @return 验证成功返回true，验证失败返回false
     */
    fun checkBlankSpace(blankSpace: String?): Boolean {
        val regex = "\\s+"
        return Pattern.matches(regex, blankSpace)
    }

    /**
     * 验证中文
     *
     * @param chinese 中文字符
     * @return 验证成功返回true，验证失败返回false
     */
    fun checkChinese(chinese: String?): Boolean {
        val regex = "^[\u4E00-\u9FA5]+$"
        return Pattern.matches(regex, chinese)
    }

    /**
     * 验证日期（年月日）
     *
     * @param birthday 日期，格式：1992-09-03，或1992.09.03
     * @return 验证成功返回true，验证失败返回false
     */
    fun checkBirthday(birthday: String?): Boolean {
        val regex = "[1-9]{4}([-./])\\d{1,2}\\1\\d{1,2}"
        return Pattern.matches(regex, birthday)
    }

    /**
     * 验证URL地址
     *
     * @param url 格式：http://blog.csdn.net/gdutxiaoxu/article/details/71732642或 http://www.csdn.net:80
     * @return 验证成功返回true，验证失败返回false
     */
    fun checkURL(url: String?): Boolean {
        val regex = "(https?://(w{3}\\.)?)?\\w+\\.\\w+(\\.[a-zA-Z]+)*(:\\d{1,5})?(/\\w*)*(\\??(.+=.*)?(&.+=.*)?)?"
        return Pattern.matches(regex, url)
    }

    /**
     * 匹配中国邮政编码
     *
     * @param postcode 邮政编码
     * @return 验证成功返回true，验证失败返回false
     */
    fun checkPostcode(postcode: String?): Boolean {
        val regex = "[1-9]\\d{5}"
        return Pattern.matches(regex, postcode)
    }

    /**
     * 匹配IP地址(简单匹配，格式，如：192.168.1.1，127.0.0.1，没有匹配IP段的大小)
     *
     * @param ipAddress IPv4标准地址
     * @return 验证成功返回true，验证失败返回false
     */
    fun checkIpAddress(ipAddress: String?): Boolean {
        val regex = "[1-9](\\d{1,2})?\\.(0|([1-9](\\d{1,2})?))\\.(0|([1-9](\\d{1,2})?))\\.(0|([1-9](\\d{1,2})?))"
        return Pattern.matches(regex, ipAddress)
    }
}