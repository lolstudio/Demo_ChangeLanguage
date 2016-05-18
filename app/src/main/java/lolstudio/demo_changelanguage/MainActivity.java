package lolstudio.demo_changelanguage;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.tabDemoText);
//        Locale locale = getResources().getConfiguration().locale;
//        Toast.makeText(this,"system language :" + getResources().getConfiguration().locale.getLanguage(),Toast.LENGTH_LONG).show();//app语言
        System.out.println(Locale.getDefault().getLanguage());//系统语言
        System.out.println(Locale.ENGLISH.getLanguage());//系统语言
        System.out.println(Locale.CHINA.getCountry());//系统语言
        System.out.println(getSystemLanguage(this));//系统语言
        /*if (locale.getCountry().equals("TW")) {
//            textView.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.learning_btn_calender_active, 0, 0);
            switchLanguage2(Locale.TRADITIONAL_CHINESE);
        } else {
            switchLanguage2(Locale.SIMPLIFIED_CHINESE);
     }*/

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            //http://my.oschina.net/u/1256344/blog/176025
            switchLanguage(Locale.JAPAN);
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
        }
    }

    /**
     * 获取系统语言，格式为TW、JP、CN
     **/
    static public String getSystemLanguage(Context context) {
        Resources res = context.getResources();
        Configuration config = res.getConfiguration();
        String language = config.locale.getCountry();
        //getResources().getConfiguration().locale.getCountry().equals("TW")
        return language;
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Toast.makeText(this,"onRestart",Toast.LENGTH_LONG).show();
    }

    public void changeJA(View v) {
        switchLanguage(Locale.JAPAN);
//        Uri uri = Uri.parse("http://www.vipabc.com/About/AboutVIPABC");
//        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
//        startActivity(intent);
    }


    public void changeZH(View v) {
        switchLanguage(Locale.CHINA);
    }


    private void switchLanguage(Locale locale) {
        Resources res = getResources();
        Configuration config = res.getConfiguration();
        DisplayMetrics dm = res.getDisplayMetrics();
        Locale currentLocal = config.locale;
        config.locale = locale;
        res.updateConfiguration(config, dm);
        if (!currentLocal.equals(config.locale)) {
            restartApplication(this);

        }
    }
    private void switchLanguage(String locale) {
        Resources res = getResources();
        Configuration config = res.getConfiguration();
        DisplayMetrics dm = res.getDisplayMetrics();
        /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            config.setLocale(new Locale(locale));
        }*/
        res.updateConfiguration(config, dm);
            restartApplication(this);
    }

    public static void restartApplication(Activity context) {
        final Intent intent = context.getPackageManager().getLaunchIntentForPackage(context.getPackageName());
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.getApplication().startActivity(intent);
        context.finish();
    }

    private void switchLanguage2(Locale locale) {
        Resources res = getResources();
        Configuration config = res.getConfiguration();
        DisplayMetrics dm = res.getDisplayMetrics();
        Locale currentLocal = config.locale;
        config.locale = locale;
        res.updateConfiguration(config, dm);
    }
}
