package shekel.org.shekelwallet.ui.initial;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import shekel.org.shekelwallet.ShekelApplication;
import shekel.org.shekelwallet.ui.splash_activity.SplashActivity;
import shekel.org.shekelwallet.ui.wallet_activity.WalletActivity;
import shekel.org.shekelwallet.utils.AppConf;

/**
 * Created by kaali on 8/19/17.
 */

public class InitialActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ShekelApplication shekelApplication = ShekelApplication.getInstance();
        AppConf appConf = shekelApplication.getAppConf();
        // show report dialog if something happen with the previous process
        Intent intent;
        if (!appConf.isAppInit() || appConf.isSplashSoundEnabled()){
            intent = new Intent(this, SplashActivity.class);
        }else {
            intent = new Intent(this, WalletActivity.class);
        }
        startActivity(intent);
        finish();
    }
}
