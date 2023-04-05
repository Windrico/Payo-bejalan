package com.si61.payobejalan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ubahActivity extends AppCompatActivity {
    private EditText etNama,etAlamat,etJam;
    private Button btnUbah;
    private String yId,yNama,yAlamat,yjam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubah);

        etNama = findViewById(R.id.et_nama);
        etAlamat = findViewById(R.id.et_alamat);
        etJam = findViewById(R.id.et_jam);
        btnUbah = findViewById(R.id.btn_ubah);

        Intent terima = getIntent();
        yId = terima.getStringExtra("xId");
        yNama = terima.getStringExtra("xNama");
        yAlamat = terima.getStringExtra("xAlamat");
        yjam = terima.getStringExtra("xJam");

        etNama.setText(yNama);
        etAlamat.setText(yAlamat);
        etJam.setText(yjam);

        btnUbah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nama,alamat,jam;

                nama = etNama.getText().toString();
                alamat = etAlamat.getText().toString();
                jam = etJam.getText().toString();

                if (nama.trim().equals("")){
                    etNama.setError("Nama Tidak Boleh Kosong");
                }
                else if (alamat.trim().equals("")){
                    etAlamat.setError("Nama Tidak Boleh Kosong");
                }
                else if (jam.trim().equals("")) {
                    etJam.setError("Nama Tidak Boleh Kosong");
                }
                else {
                    MyDatabaseHelper myDB = new MyDatabaseHelper(ubahActivity.this);
                    long eks = myDB.ubahData(yId,nama,alamat,jam);

                    if (eks == -1){
                        Toast.makeText(ubahActivity.this, "Ubah Data Gagal", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(ubahActivity.this, "Ubah Data Sukses!", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }

            }
        });
    }
}