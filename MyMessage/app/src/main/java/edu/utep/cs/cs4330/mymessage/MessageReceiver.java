package edu.utep.cs.cs4330.mymessage;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.telephony.SmsMessage;
import android.widget.Toast;

public class MessageReceiver extends BroadcastReceiver {

    public String messageSender;
    public String messageBody;

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        if (bundle == null) {
            return;
        }

        parseMessage(bundle);

        //--
        //-- TODO: Write your code here
        //--
        //--Added code
        Toast.makeText(context, "Intent Detected.", Toast.LENGTH_LONG).show();


    }

    /** Parse the message contained in the given bundle. */
    private void parseMessage(@NonNull Bundle bundle) {
        messageSender = messageBody = null;
        for (Object pdu: (Object[]) bundle.get("pdus")) {
            SmsMessage msg = SmsMessage.createFromPdu((byte[]) pdu);
            messageSender = msg.getOriginatingAddress();
            messageBody = msg.getMessageBody().toString();
        }
    }
}
