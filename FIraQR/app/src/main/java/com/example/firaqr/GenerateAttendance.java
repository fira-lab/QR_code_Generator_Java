package com.example.firaqr;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

public class GenerateAttendance extends AppCompatActivity {

    private EditText mName, mId, mPhoneNumber;
    private Button mGenerateQrCode;
    private ImageView mQrCodeImageView;
    private Bitmap mQrCodeBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate_attendance);

        mName = findViewById(R.id.nameEditText);
        mId = findViewById(R.id.idEditText);
        mPhoneNumber = findViewById(R.id.phoneNumberEditText);
        mGenerateQrCode = findViewById(R.id.generateQrCodeButton);
        mQrCodeImageView = findViewById(R.id.qrCodeImageView);

        if(mGenerateQrCode != null) {
            mGenerateQrCode.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String name = mName.getText().toString();
                    String id = mId.getText().toString();
                    String phoneNumber = mPhoneNumber.getText().toString();
                    String data = "Name " + name + "\nID " + id + "\nPhone Number " + phoneNumber;
                    QRCodeWriter qrCodeWriter = new QRCodeWriter();
                    try {
                        BitMatrix bitMatrix = qrCodeWriter.encode(data, BarcodeFormat.QR_CODE, 512, 512);
                        int width = bitMatrix.getWidth();
                        int height = bitMatrix.getHeight();
                        mQrCodeBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
                        for (int x = 0; x < width; x++) {
                            for (int y = 0; y < height; y++) {
                                mQrCodeBitmap.setPixel(x, y, bitMatrix.get(x, y) ? getResources().getColor(R.color.black): getResources().getColor(R.color.white));
                            }
                        }
                        mQrCodeImageView.setImageBitmap(mQrCodeBitmap);
                    } catch (WriterException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }
}