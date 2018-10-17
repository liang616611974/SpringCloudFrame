package com.liangfeng.study.core.helper;


import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @author Liangfeng
 * @version 1.0
 * @Title: ShellHelper
 * @Description: 执行Linux Shell脚本命令
 * @date  2018/10/16 17:38
 */
@Slf4j
public class ShellHelper {

   /* public static void execute(String shellPath, String... args) {
        String[] cmd = new String[]{shellPath};
        //为了解决参数中包含空格
        cmd= ArrayUtils.addAll(cmd,args);
        execute(cmd);
    }*/

    /**
     * 执行Linux Shell脚本
     * @param args
     */
    public static void execute(String... args){
        log.info("执行Shell脚本：{}",ArrayUtils.toString(args));
        try {
            // 执行脚本
            Process ps = Runtime.getRuntime().exec(args);
            ps.waitFor();

            // 获取结果
            BufferedReader br = new BufferedReader(new InputStreamReader(ps.getInputStream()));
            StringBuffer sb = new StringBuffer();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line).append("\n");
            }
            String result = sb.toString();
            log.info("执行结果:{}",result);
        } catch (Exception e) {
            log.error("执行Shell脚本发生异常", e);
            throw new RuntimeException("执行Shell脚本发生异常",e);
        }
    }

}
