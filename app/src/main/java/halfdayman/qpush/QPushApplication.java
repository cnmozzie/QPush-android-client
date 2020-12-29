package halfdayman.qpush;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.os.Process;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.xiaomi.channel.commonutils.logger.LoggerInterface;
import com.xiaomi.mipush.sdk.Logger;
import com.xiaomi.mipush.sdk.MiPushClient;

import java.util.List;

/**
 * 1、为了打开客户端的日志，便于在开发过程中调试，需要自定义一个 Application。
 * 并将自定义的 application 注册在 AndroidManifest.xml 文件中。<br/>
 * 2、为了提高 push 的注册率，您可以在 Application 的 onCreate 中初始化 push。你也可以根据需要，在其他地方初始化 push。
 *
 * @author wangkuiwei
 */
public class QPushApplication extends Application {

    // user your appid the key.
    private static final String APP_ID = "2882303761518903632";
    // user your appid the key.
    private static final String APP_KEY = "5421890355632";

    // 此TAG在adb logcat中检索自己所需要的信息， 只需在命令行终端输入 adb logcat | grep
    // halfdayman.qpush
    public static final String TAG = "halfdayman.qpush";

    private static SystemLogsActivity sSystemLogsActivity = null;

    @Override
    public void onCreate() {
        super.onCreate();

        // 注册push服务，注册成功后会向QPushMessageReceiver发送广播
        // 可以从QPushMessageReceiver的onCommandResult方法中MiPushCommandMessage对象参数中获取注册信息
        if (shouldInit()) {
            MiPushClient.registerPush(this, APP_ID, APP_KEY);
            SystemLogsActivity.logList.add(0, "start register!");
            handleMessage();
        }
        //打开Log
        LoggerInterface newLogger = new LoggerInterface() {

            @Override
            public void setTag(String tag) {
                // ignore
            }

            @Override
            public void log(String content, Throwable t) {
                Log.d(TAG, content, t);
            }

            @Override
            public void log(String content) {
                Log.d(TAG, content);
            }
        };
        Logger.setLogger(this, newLogger);
    }

    private boolean shouldInit() {
        ActivityManager am = ((ActivityManager) getSystemService(Context.ACTIVITY_SERVICE));
        List<RunningAppProcessInfo> processInfos = am.getRunningAppProcesses();
        String mainProcessName = getPackageName();
        int myPid = Process.myPid();
        for (RunningAppProcessInfo info : processInfos) {
            if (info.pid == myPid && mainProcessName.equals(info.processName)) {
                return true;
            }
        }
        return false;
    }

    public static void setSystemLogsActivity(SystemLogsActivity activity) {
        sSystemLogsActivity = activity;
    }
    public static void handleMessage() {
        if (sSystemLogsActivity != null) {
            sSystemLogsActivity.refreshLogInfo();
        }
    }
}