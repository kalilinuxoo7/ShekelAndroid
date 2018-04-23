package shekel.org.shekelwallet.ui.start_node_activity;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import global.JewtrumGlobalData;
import jewtrum.JewtrumPeer;
import jewtrum.JewtrumPeerData;
import shekel.org.shekelwallet.R;
import shekel.org.shekelwallet.ui.base.BaseActivity;
import shekel.org.shekelwallet.ui.pincode_activity.PincodeActivity;
import shekel.org.shekelwallet.ui.wallet_activity.WalletActivity;
import shekel.org.shekelwallet.utils.DialogBuilder;
import shekel.org.shekelwallet.utils.DialogsUtil;

import static global.JewtrumGlobalData.KAALI_TESTNET_SERVER;

/**
 * Created by Neoperol on 6/27/17.
 */

public class StartNodeActivity extends BaseActivity {

    private Button openDialog;
    private Button btnSelectNode;
    private Spinner dropdown;
    private ArrayAdapter<String> adapter;
    private List<String> hosts = new ArrayList<>();

    private static final List<JewtrumPeerData> trustedNodes = JewtrumGlobalData.listTrustedHosts();

    @Override
    protected void onCreateView(Bundle savedInstanceState, ViewGroup container) {

        getLayoutInflater().inflate(R.layout.fragment_start_node, container);
        setTitle(R.string.select_node);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Open Dialog
        openDialog = (Button) findViewById(R.id.openDialog);
        openDialog.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                DialogBuilder dialogBuilder = DialogsUtil.buildtrustedNodeDialog(StartNodeActivity.this, new DialogsUtil.TrustedNodeDialogListener() {
                    @Override
                    public void onNodeSelected(JewtrumPeerData jewtrumPeerData) {
                        if(!trustedNodes.contains(jewtrumPeerData)) {
                            dropdown.setAdapter(null);
                            adapter.clear();
                            hosts = new ArrayList<String>();
                            trustedNodes.add(jewtrumPeerData);
                            for (JewtrumPeerData trustedNode : trustedNodes) {
                                if (trustedNode.getHost().equals(KAALI_TESTNET_SERVER)) {
                                    hosts.add("node1.shekelcrypto.com");
                                } else
                                    hosts.add(trustedNode.getHost());
                            }
                            adapter.addAll(hosts);
                            dropdown.setAdapter(adapter);
                            dropdown.setSelection(hosts.size() - 1);
                        }
                    }
                });
                dialogBuilder.show();
            }

        });

        // Node selected
        btnSelectNode = (Button) findViewById(R.id.btnSelectNode);
        btnSelectNode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selected = dropdown.getSelectedItemPosition();
                JewtrumPeerData selectedNode = trustedNodes.get(selected);
                boolean isStarted = shekelApplication.getAppConf().getTrustedNode()!=null;
                shekelApplication.setTrustedServer(selectedNode);

                if (isStarted){
                    shekelApplication.stopBlockchain();
                    // now that everything is good, start the service
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            shekelApplication.startShekelService();
                        }
                    }, TimeUnit.SECONDS.toMillis(5));
                }
                goNext();
                finish();
            }
        });

        dropdown = (Spinner)findViewById(R.id.spinner);

        // add connected node if it's not on the list
        JewtrumPeerData jewtrumPeer = shekelApplication.getAppConf().getTrustedNode();
        if (jewtrumPeer!=null && !jewtrumPeer.getHost().equals(KAALI_TESTNET_SERVER)){
            trustedNodes.add(jewtrumPeer);
        }

        int selectionPos = 0;

        for (int i=0;i<trustedNodes.size();i++){
            JewtrumPeerData trustedNode = trustedNodes.get(i);
            if (jewtrumPeer!=null && jewtrumPeer.getHost().equals(trustedNode)){
                selectionPos = i;
            }
            if (trustedNode.getHost().equals(KAALI_TESTNET_SERVER)){
                hosts.add("node1.shekelcrypto.com");
            }else
                hosts.add(trustedNode.getHost());
        }
        adapter = new ArrayAdapter<String>(this, R.layout.simple_spinner_dropdown_item,hosts){
            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                CheckedTextView view = (CheckedTextView) super.getDropDownView(position, convertView, parent);
                view.setTextColor(Color.BLACK);
                view.setPadding(16, 16, 16, 16);
                return view;
            }

            @NonNull
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                CheckedTextView view = (CheckedTextView) super.getView(position, convertView, parent);
                view.setTextColor(Color.BLACK);
                view.setPadding(8, 8, 8, 8);
                return view;
            }
        };
        dropdown.setSelection(selectionPos);
        dropdown.setAdapter(adapter);
    }

    private void goNext() {
        Class clazz = null;
        if (shekelApplication.getAppConf().getPincode()==null){
            clazz = PincodeActivity.class;
        }else {
            clazz = WalletActivity.class;
        }
        Intent intent = new Intent(this, clazz);
        startActivity(intent);
    }

    public static int convertDpToPx(Resources resources, int dp){
        return Math.round(dp*(resources.getDisplayMetrics().xdpi/ DisplayMetrics.DENSITY_DEFAULT));
    }

}
