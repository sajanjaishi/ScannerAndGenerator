package com.example.scannerandgenerator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;
import com.budiyev.android.codescanner.DecodeCallback;
import com.google.zxing.Result;

public class MainActivity2 extends AppCompatActivity {
    CodeScanner codescanner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        CodeScannerView scannerview = findViewById(R.id.scanner);
        codescanner =new CodeScanner(this,scannerview);
        codescanner.setDecodeCallback(new DecodeCallback() {
            @Override
            public void onDecoded(@NonNull Result result) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MainActivity2.this,result.getText(),Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });
        scannerview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                codescanner.startPreview();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        codescanner.startPreview();
    }

    @Override
    protected void onPause() {
        codescanner.releaseResources();
        super.onPause();

    }
}