package jewtrum.listeners;

import jewtrum.JewtrumPeer;

/**
 * Created by kaali on 6/17/17.
 */

public interface PeerListener {

    void onConnected(JewtrumPeer jewtrumPeer);

    void onDisconnected(JewtrumPeer jewtrumPeer);

    void onExceptionCaught(JewtrumPeer jewtrumPeer, Exception e);
}
