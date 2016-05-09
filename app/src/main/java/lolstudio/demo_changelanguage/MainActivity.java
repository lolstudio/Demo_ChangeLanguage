package lolstudio.demo_changelanguage;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private Button textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (Button) findViewById(R.id.textView);
        Locale locale = getResources().getConfiguration().locale;
        System.out.println("local language " + locale.getDefault().getLanguage());
    }

    public void changeTW(View v) {
        switchLanguage(Locale.TRADITIONAL_CHINESE);
    }


    public void changeZH(View v) {
        switchLanguage(Locale.SIMPLIFIED_CHINESE);
    }


    private void switchLanguage(Locale locale) {
        Resources res = getResources();
        Configuration config = res.getConfiguration();
        DisplayMetrics dm = res.getDisplayMetrics();
        Locale currentLocal = config.locale;
        config.locale = locale;
        res.updateConfiguration(config, dm);

        // 如果切换了语言
        if (!currentLocal.equals(config.locale)) {
            // 这里需要重新刷新当前页面中使用到的资源
            restartApplication(this);

        }
    }

    public static void restartApplication(Activity context) {
        final Intent intent = context.getPackageManager().getLaunchIntentForPackage(context.getPackageName());
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.getApplication().startActivity(intent);
        context.finish();
    }
}
