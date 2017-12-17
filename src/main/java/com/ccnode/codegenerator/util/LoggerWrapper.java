package com.ccnode.codegenerator.util;

import com.ccnode.codegenerator.genCode.UserConfigService;
import com.ccnode.codegenerator.pojoHelper.GenCodeResponseHelper;
import com.google.common.base.Objects;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.vfs.VirtualFileManager;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.Marker;

import java.io.File;
import java.util.Date;
import java.util.List;

/**
 * What always stop you is what you always believe.
 * Created by zhengjun.du on 2016/07/26 16:38
 */
public class LoggerWrapper implements Logger {

    public static List<String> logList = Lists.newArrayList(
            "", "----------------------    start    -------------------------");

    public static List<String> errorList = Lists.newArrayList(
            "", "----------------------    start    -------------------------"
    );

    public LoggerWrapper() {

    }

    public static void saveAllLogs(String projectPath)  {
        try{
            ApplicationManager.getApplication().saveAll();
            VirtualFileManager.getInstance().syncRefresh();
            String firstMatch = MapHelper.getFirstMatch(UserConfigService.userConfigMap, "printLog", "printlog", "debug");
            if(!StringUtils.endsWithIgnoreCase(firstMatch,"true") || StringUtils.isBlank(projectPath)){
                return;
            }
            logList.add("----------------------     end     -------------------------");
            if(!projectPath.endsWith(GenCodeResponseHelper.getPathSplitter())){
                projectPath = projectPath + GenCodeResponseHelper.getPathSplitter();
            }
            String path = projectPath + "codehelper.generator.log";
            File logFile = new File(path);
            List<String> allLines = Lists.newArrayList();
            if(logFile.exists()){
                List<String> oldLines = IOUtils.readLines(path);
                if(oldLines !=null && !oldLines.isEmpty()){
                    allLines.addAll(oldLines);
                }
            }
            allLines.addAll(logList);
            IOUtils.writeLines(new File(path),allLines);
            ApplicationManager.getApplication().saveAll();
            VirtualFileManager.getInstance().syncRefresh();
        }catch(Throwable ignored){

        }

    }

    public static Logger getLogger(Class clazz){
        return new LoggerWrapper();
    }

    @Override
    public String getName() {
        return "LOGGER";
    }

    @Override
    public void trace(String msg) {
    }

    private String format(String toFormat, Object... objects) {
        StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[3];
        List<String> splits = Splitter.on(".").splitToList(stackTraceElement.getClassName());
        String className = splits.get(splits.size() - 1);
        String methodName = stackTraceElement.getMethodName();
        int lineNumber = stackTraceElement.getLineNumber();
        String logLevel = Thread.currentThread().getStackTrace()[2].getMethodName().toUpperCase();
        for (Object object : objects) {
            toFormat += "," + LogHelper.toString(object);
        }
        String logContent = DateUtil.formatLong(new Date()) + " [" + className + "." + methodName + "] " + "["+ lineNumber + "]" + "[" + logLevel + "] " + toFormat;
        logList.add(logContent);
        if("error".equalsIgnoreCase(logLevel)){
            errorList.add(logContent);
        }
        return toFormat;
    }

    @Override
    public void trace(String msg, Throwable t) {

    }

    @Override
    public void info(String msg) {

        format(msg);

    }

    @Override
    public void info(String format, Object arg) {

        format(format, arg);

    }

    @Override
    public void info(String format, Object arg1, Object arg2) {

        format(format, arg1, arg2);

    }

    @Override
    public void info(String format, Object... arguments) {

        format(format, arguments);

    }

    @Override
    public void info(String msg, Throwable t) {

        format(msg, t);

    }

    @Override
    public void info(Marker marker, String msg) {

    }

    @Override
    public void info(Marker marker, String format, Object arg) {

    }

    @Override
    public void info(Marker marker, String format, Object arg1, Object arg2) {

    }

    @Override
    public void info(Marker marker, String format, Object... arguments) {

    }

    @Override
    public void info(Marker marker, String msg, Throwable t) {

    }

    @Override
    public void error(String msg) {

        format(msg);

    }

    @Override
    public void error(String format, Object arg) {

        format(format, arg);

    }

    @Override
    public void error(String format, Object arg1, Object arg2) {
        format(format, arg1, arg2);
    }

    @Override
    public void error(String format, Object... arguments) {
        format(format, arguments);
    }

    @Override
    public void error(String msg, Throwable t) {
        format(msg, t);
    }

    @Override
    public void error(Marker marker, String msg) {

    }

    @Override
    public void error(Marker marker, String format, Object arg) {

    }

    @Override
    public void error(Marker marker, String format, Object arg1, Object arg2) {

    }

    @Override
    public void error(Marker marker, String format, Object... arguments) {

    }

    @Override
    public void error(Marker marker, String msg, Throwable t) {

    }

    @Override
    public void trace(String format, Object arg) {

    }

    @Override
    public void trace(String format, Object arg1, Object arg2) {

    }

    @Override
    public void trace(String format, Object... arguments) {

    }

    @Override
    public boolean isTraceEnabled(Marker marker) {
        return false;
    }

    @Override
    public void trace(Marker marker, String msg) {

    }

    @Override
    public void trace(Marker marker, String format, Object arg) {

    }

    @Override
    public void trace(Marker marker, String format, Object arg1, Object arg2) {

    }

    @Override
    public void trace(Marker marker, String format, Object... argArray) {

    }

    @Override
    public void trace(Marker marker, String msg, Throwable t) {

    }

    @Override
    public boolean isDebugEnabled() {
        return false;
    }

    @Override
    public void debug(String msg) {

    }

    @Override
    public void debug(String format, Object arg) {

    }

    @Override
    public void debug(String format, Object arg1, Object arg2) {

    }

    @Override
    public void debug(String format, Object... arguments) {

    }

    @Override
    public void debug(String msg, Throwable t) {

    }

    @Override
    public boolean isDebugEnabled(Marker marker) {
        return false;
    }

    @Override
    public void debug(Marker marker, String msg) {

    }

    @Override
    public void debug(Marker marker, String format, Object arg) {

    }

    @Override
    public void debug(Marker marker, String format, Object arg1, Object arg2) {

    }

    @Override
    public void debug(Marker marker, String format, Object... arguments) {

    }

    @Override
    public void debug(Marker marker, String msg, Throwable t) {

    }

    @Override
    public boolean isInfoEnabled() {
        return false;
    }

    @Override
    public boolean isWarnEnabled() {
        return false;
    }

    @Override
    public void warn(String msg) {

    }

    @Override
    public void warn(String format, Object arg) {

    }

    @Override
    public void warn(String format, Object... arguments) {

    }

    @Override
    public void warn(String format, Object arg1, Object arg2) {

    }

    @Override
    public void warn(String msg, Throwable t) {

    }

    @Override
    public boolean isWarnEnabled(Marker marker) {
        return false;
    }

    @Override
    public void warn(Marker marker, String msg) {

    }

    @Override
    public void warn(Marker marker, String format, Object arg) {

    }

    @Override
    public void warn(Marker marker, String format, Object arg1, Object arg2) {

    }

    @Override
    public void warn(Marker marker, String format, Object... arguments) {

    }

    @Override
    public void warn(Marker marker, String msg, Throwable t) {

    }

    @Override
    public boolean isInfoEnabled(Marker marker) {
        return false;
    }

    @Override
    public boolean isErrorEnabled(Marker marker) {
        return false;
    }

    @Override
    public boolean isTraceEnabled() {
        return false;
    }

    @Override
    public boolean isErrorEnabled() {
        return false;
    }
}