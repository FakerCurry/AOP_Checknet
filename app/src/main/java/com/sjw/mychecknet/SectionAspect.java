package com.sjw.mychecknet;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;

/**
 * Created by pc on 2018/6/30.
 */
@Aspect
public class SectionAspect {

    /**
     * 找到处理的切点
     * **(..)可以处理所有的方法
     */
    @Pointcut("execution(@com.sjw.mychecknet.CheckNet * *(..))")
    public void checkNetBehavior() {


    }

    /**
     * 处理切面
     */
    @Around("checkNetBehavior()")
    public Object checkNet(ProceedingJoinPoint joinPoint) throws Throwable {

        //做埋点  日志上传 权限检测  网络监测
        //网络监测
//        1.获取checknet注解
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();

        CheckNet checkNet = signature.getMethod().getAnnotation(CheckNet.class);
        Log.e("TAG","checkNet");
        if (checkNet != null) {
//        2.判断有没有网络  怎么获取context
            Object object = joinPoint.getThis();//view fragment activity： getThis 代表当前所在类
            Context context = getContext(object);
            if (context != null) {
                if (!isNetworkAvailable(context)) {
                    //        3.没有网络不要往下执行
                    Toast.makeText(context, "没有网络", Toast.LENGTH_SHORT).show();
                    return null;
                }


            }


        }

        return joinPoint.proceed();
    }

    //通过对象获取上下文
    private Context getContext(Object object) {

        if (object instanceof Activity) {

            return (Activity) object;
        } else if (object instanceof Fragment) {

            Fragment fragment = (Fragment) object;
            return fragment.getActivity();
        } else if (object instanceof View) {
            View view = (View) object;
            return view.getContext();
        }

        return null;
    }

    /**
     * 检测当的网络（WLAN、3G/2G）状态
     *
     * @param context Context
     * @return true 表示网络可用
     */
    private static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo info = connectivity.getActiveNetworkInfo();
            if (info != null && info.isConnected()) {
                // 当前网络是连接的
                if (info.getState() == NetworkInfo.State.CONNECTED) {
                    // 当前所连接的网络可用
                    return true;
                }
            }
        }
        return false;
    }
}
