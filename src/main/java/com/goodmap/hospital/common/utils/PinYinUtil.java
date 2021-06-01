package com.goodmap.hospital.common.utils;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
 * @author 刘智强
 * @date 2021/5/12
 * @Description
 */
public class PinYinUtil {

    public static String getPinYin(String chinese) {
        char[] spell = chinese.toCharArray();
        String pinYin = "";
        HanyuPinyinOutputFormat outputFormat = new HanyuPinyinOutputFormat();
        outputFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        outputFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        for (int i = 0; i < 1; i++) {
            if(spell[i] > 128) {
                try{
                    pinYin += PinyinHelper.toHanyuPinyinStringArray(spell[i],outputFormat)[0].charAt(0);
                } catch (BadHanyuPinyinOutputFormatCombination e) {
                    e.printStackTrace();
                }
            }else {
                pinYin += spell[i];
            }
        }
        return pinYin;
    }
}
