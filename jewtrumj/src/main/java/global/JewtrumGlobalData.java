package global;

import java.util.ArrayList;
import java.util.List;

import jewtrum.JewtrumPeerData;

/**
 * Created by kaali on 7/2/17.
 */

public class JewtrumGlobalData {

    public static final String KAALI_TESTNET_SERVER = "node1.bulwarkcrypto.com";

    public static final String[] TRUSTED_NODES = new String[]{"node1.bulwarkcrypto.com"};

    public static final List<JewtrumPeerData> listTrustedHosts(){
        List<JewtrumPeerData> list = new ArrayList<>();
        list.add(new JewtrumPeerData(KAALI_TESTNET_SERVER,5500,55552));
        for (String trustedNode : TRUSTED_NODES) {
            list.add(new JewtrumPeerData(trustedNode,5500,55552));
        }
        return list;
    }

}
