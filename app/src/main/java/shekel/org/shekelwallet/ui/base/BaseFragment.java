package shekel.org.shekelwallet.ui.base;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;

import shekel.org.shekelwallet.ShekelApplication;
import shekel.org.shekelwallet.module.ShekelModule;

/**
 * Created by kaali on 6/29/17.
 */

public class BaseFragment extends Fragment {

    protected ShekelApplication shekelApplication;
    protected ShekelModule shekelModule;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        shekelApplication = ShekelApplication.getInstance();
        shekelModule = shekelApplication.getModule();
    }

    protected boolean checkPermission(String permission) {
        int result = ContextCompat.checkSelfPermission(getActivity(),permission);
        return result == PackageManager.PERMISSION_GRANTED;
    }
}
