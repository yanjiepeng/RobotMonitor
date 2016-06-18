package com.zk.robotmonitor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class QRCodeActivity extends AppCompatActivity {

    Button btn_exit;
    ImageView iv_qr_Code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setTitle("刻码详情");
        setContentView(R.layout.activity_qrcode);

        btn_exit = (Button) findViewById(R.id.btn_qr_exit);
        iv_qr_Code = (ImageView) findViewById(R.id.iv_qr);
        btn_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                QRCodeActivity.this.finish();
            }
        });
    }
}
