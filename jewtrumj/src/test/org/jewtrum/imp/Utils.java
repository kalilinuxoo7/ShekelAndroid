package org.jewtrum.imp;

import org.shekelj.core.ECKey;
import org.shekelj.params.TestNet3Params;
import org.junit.Test;
import org.spongycastle.math.ec.ECPoint;
import org.spongycastle.util.encoders.Hex;

import static org.shekelj.core.ECKey.CURVE;

/**
 * Created by kaali on 8/13/17.
 */

public class Utils {


    @Test
    public void testPubKey(){

        ECPoint ecPoint = CURVE.getCurve().decodePoint(Hex.decode("03af441a3308f82e6208a5d25f655ae45a5355f2f78a5b3a2d3514054ce4c974e1"));
        ECKey ecKey = ECKey.fromPublicOnly(ecPoint);
        System.out.println("address: "+ecKey.toAddress(TestNet3Params.get()));
    }

}
