package shekel.org.shekelwallet.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import shekel.org.shekelwallet.ui.wallet_activity.WalletActivity;

/**
 * Created by kaali on 10/19/17.
 */

public class NavigationUtils {

    public static void goBackToHome(Activity activity){
        Intent upIntent = new Intent(activity,WalletActivity.class);
        upIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        activity.startActivity(upIntent);
        activity.finish();
    }

}
