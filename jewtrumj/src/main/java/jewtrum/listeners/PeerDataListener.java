package jewtrum.listeners;

import java.util.List;

import jewtrum.JewtrumPeer;
import jewtrum.messages.responses.StatusHistory;
import jewtrum.messages.responses.Unspent;
import jewtrum.utility.TxHashHeightWrapper;

/**
 * Created by kaali on 6/17/17.
 */

public interface PeerDataListener {

    void onSubscribedAddressChange(JewtrumPeer jewtrumPeer, String address, String status);

    void onListUnpent(JewtrumPeer jewtrumPeer,String address, List<Unspent> unspent);

    void onBalanceReceive(JewtrumPeer jewtrumPeer, String address, long confirmed, long unconfirmed);

    void onGetHistory(JewtrumPeer jewtrumPeer, StatusHistory statusHistory);
}
