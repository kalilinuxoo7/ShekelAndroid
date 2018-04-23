package shekel.org.shekelwallet.ui.base;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;

import shekel.org.shekelwallet.ShekelApplication;
import shekel.org.shekelwallet.R;
import shekel.org.shekelwallet.module.ShekelModule;
import shekel.org.shekelwallet.service.IntentsConstants;
import shekel.org.shekelwallet.ui.base.dialogs.SimpleTextDialog;
import shekel.org.shekelwallet.utils.DialogBuilder;
import shekel.org.shekelwallet.utils.DialogsUtil;

import static shekel.org.shekelwallet.service.IntentsConstants.ACTION_STORED_BLOCKCHAIN_ERROR;
import static shekel.org.shekelwallet.service.IntentsConstants.ACTION_TRUSTED_PEER_CONNECTION_FAIL;

/**
 * Created by kaali on 6/8/17.
 */

public class ShekelActivity extends AppCompatActivity {

    protected ShekelApplication shekelApplication;
    protected ShekelModule shekelModule;

    protected LocalBroadcastManager localBroadcastManager;
    private static final IntentFilter intentFilter = new IntentFilter(ACTION_TRUSTED_PEER_CONNECTION_FAIL);
    private static final IntentFilter errorIntentFilter = new IntentFilter(ACTION_STORED_BLOCKCHAIN_ERROR);

    private BroadcastReceiver trustedPeerConnectionDownReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals(ACTION_TRUSTED_PEER_CONNECTION_FAIL)) {
                SimpleTextDialog simpleTextDialog = DialogsUtil.buildSimpleErrorTextDialog(context,R.string.title_no_trusted_peer_connection,R.string.message_no_trusted_peer_connection);
                simpleTextDialog.show(getFragmentManager(),"fail_node_connection_dialog");
            }else if (action.equals(ACTION_STORED_BLOCKCHAIN_ERROR)){
                SimpleTextDialog simpleTextDialog = DialogsUtil.buildSimpleErrorTextDialog(context,R.string.title_blockstore_error,R.string.message_blockstore_error);
                simpleTextDialog.show(getFragmentManager(),"blockstore_error_dialog");
            }
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        shekelApplication = ShekelApplication.getInstance();
        shekelModule = shekelApplication.getModule();
        localBroadcastManager = LocalBroadcastManager.getInstance(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        localBroadcastManager.registerReceiver(trustedPeerConnectionDownReceiver,intentFilter);
        localBroadcastManager.registerReceiver(trustedPeerConnectionDownReceiver,errorIntentFilter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        localBroadcastManager.unregisterReceiver(trustedPeerConnectionDownReceiver);
    }
}
